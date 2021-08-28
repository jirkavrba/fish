package dev.vrba.vse.fish.discord

import dev.vrba.vse.fish.configuration.DiscordConfiguration
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Service

@Service
class DiscordBotService(
    configuration: DiscordConfiguration,
    private val modules: List<DiscordModule>,
    private val providers: List<SlashCommandsProvider>
) : ListenerAdapter() {

    private val client: JDA = JDABuilder.createDefault(configuration.token).build()

    private val handlers: Map<String, SlashCommandsProvider> = providers.flatMap {
            provider -> provider.commands
                .map { client.upsertCommand(it).complete(true) }
                .map { it.id to provider}
    }.toMap()

    @Bean
    fun jda(): JDA = client

    fun start() {
        client.addEventListener(this)
        modules.map { it.register(client) }
    }

    // TODO: Better handle this nullability check
    override fun onSlashCommand(event: SlashCommandEvent) = handlers[event.commandId]?.handle(event) ?: Unit
}