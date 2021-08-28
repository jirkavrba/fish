package dev.vrba.vse.fish.discord

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent
import net.dv8tion.jda.api.interactions.commands.build.CommandData

interface SlashCommandsProvider {

    val commands: List<CommandData>

    fun handle(event: SlashCommandEvent)

}