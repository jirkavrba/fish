package dev.vrba.vse.fish.discord.utilities

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.MessageEmbed
import java.time.Instant

object DiscordEmbeds {
    fun error(title: String, description: String? = null): MessageEmbed = EmbedBuilder()
        .setTitle(title)
        .setDescription(description ?: "")
        .setColor(0xED4245)
        .setTimestamp(Instant.now())
        .build()

    fun success(title: String, description: String? = null): MessageEmbed = EmbedBuilder()
        .setTitle(title)
        .setDescription(description ?: "")
        .setColor(0x57F287)
        .setTimestamp(Instant.now())
        .build()
}