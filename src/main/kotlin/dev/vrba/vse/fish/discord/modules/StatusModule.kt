package dev.vrba.vse.fish.discord.modules

import dev.vrba.vse.fish.discord.DiscordModule
import dev.vrba.vse.fish.discord.SlashCommandsProvider
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent
import net.dv8tion.jda.api.interactions.commands.build.CommandData
import org.springframework.stereotype.Component
import java.time.Instant

@Component
class StatusModule : SlashCommandsProvider {

    override val commands: List<CommandData> = listOf(
        CommandData("ping", "Just a test command")
    )

    override fun handle(event: SlashCommandEvent) {
        when (event.name) {
            "ping" -> handlePing(event)
        }
    }

    private fun handlePing(event: SlashCommandEvent) = event.replyEmbeds(
        EmbedBuilder()
            .setTitle("Pong")
            .setTimestamp(Instant.now())
            .build()
    ).queue()
}