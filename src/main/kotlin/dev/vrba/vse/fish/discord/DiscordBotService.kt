package dev.vrba.vse.fish.discord

import dev.vrba.vse.fish.configuration.DiscordConfiguration
import dev.vrba.vse.fish.discord.utilities.DiscordEmbeds
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.interactions.commands.Command
import net.dv8tion.jda.api.interactions.commands.build.CommandData
import org.springframework.context.annotation.Bean
import org.springframework.core.env.Environment
import org.springframework.core.env.Profiles
import org.springframework.stereotype.Service

@Service
class DiscordBotService(
    private val environment: Environment,
    private val configuration: DiscordConfiguration,
    private val modules: List<DiscordModule>,
    providers: List<SlashCommandsProvider>
) : ListenerAdapter() {

    private val client: JDA = JDABuilder.createDefault(configuration.token).build().awaitReady()

    private val commands: Map<String, SlashCommandsProvider> =
        providers.flatMap { provider ->
            provider.commands
                .map { registerCommand(it) }
                .map { it.id to provider }
        }.toMap()

    private fun registerCommand(command: CommandData): Command {
        // If the application is running in the development mode,
        // register all commands to guild (as there is no delay)
        if (environment.acceptsProfiles(Profiles.of("development"))) {
            val guild = client.getGuildById(configuration.testGuild)
                ?: throw IllegalStateException("Cannot find the test guild")

            return guild.upsertCommand(command).complete(true)
        }

        // Otherwise, register the command globally
        return client.upsertCommand(command).complete(true)
    }

    @Bean
    fun jda(): JDA = client

    fun start() {
        client.addEventListener(this)
        modules.map { it.register(client) }
    }

    override fun onSlashCommand(event: SlashCommandEvent) {
        val provider = commands[event.commandId] ?: return

        try {
            provider.handle(event)
        }
        catch (exception: Throwable) {
            val embed = DiscordEmbeds.error(
                "There was an error during the command execution",
                "**```${exception.message}```**\n```${exception.stackTraceToString().substring(0..3000)}```"
            )

            if (event.isAcknowledged) event.hook.editOriginalEmbeds(embed).queue()
            else event.replyEmbeds(embed).queue()
        }
    }
}