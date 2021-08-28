package dev.vrba.vse.fish.discord

import net.dv8tion.jda.api.JDA

/**
 * Common interface for all discord modules, so all instances can be loaded from the application context
 */
interface DiscordModule {

    fun register(client: JDA)

}