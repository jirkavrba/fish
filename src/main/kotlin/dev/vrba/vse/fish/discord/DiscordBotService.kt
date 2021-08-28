package dev.vrba.vse.fish.discord

import dev.vrba.vse.fish.configuration.DiscordConfiguration
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Service

@Service
class DiscordBotService(configuration: DiscordConfiguration) {

    private val client: JDA = JDABuilder.createDefault(configuration.token).build()

    @Bean
    fun jda(): JDA = client
}