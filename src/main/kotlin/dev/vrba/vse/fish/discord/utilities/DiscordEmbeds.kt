package dev.vrba.vse.fish.discord.utilities

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.MessageEmbed
import java.time.Instant

object DiscordEmbeds {
    fun errorBuilder(title: String, description: String? = null): EmbedBuilder = EmbedBuilder()
        .setTitle(title)
        .setDescription(description ?: "")
        .setColor(DiscordColors.red)
        .setTimestamp(Instant.now())

    fun error(title: String, description: String? = null): MessageEmbed = errorBuilder(title, description).build()

    fun success(title: String, description: String? = null): MessageEmbed = EmbedBuilder()
        .setTitle(title)
        .setDescription(description ?: "")
        .setColor(DiscordColors.green)
        .setTimestamp(Instant.now())
        .build()
}