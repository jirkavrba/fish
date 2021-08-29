package dev.vrba.vse.fish.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "discord")
data class DiscordConfiguration (
    var token: String = "",
    var testGuild: Long = 0L
)