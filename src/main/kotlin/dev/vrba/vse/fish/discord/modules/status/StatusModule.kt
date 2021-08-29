package dev.vrba.vse.fish.discord.modules.status

import dev.vrba.vse.fish.discord.DiscordModule
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.events.ReadyEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.springframework.stereotype.Component

@Component
class StatusModule : DiscordModule, ListenerAdapter() {

    override fun register(client: JDA) {
        client.addEventListener(this)
    }

    override fun onReady(event: ReadyEvent) {
        event.jda.presence.activity = Activity.watching("https://git.io/JEKq1")
    }

}