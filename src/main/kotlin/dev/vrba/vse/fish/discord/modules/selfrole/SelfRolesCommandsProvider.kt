package dev.vrba.vse.fish.discord.modules.selfrole

import dev.vrba.vse.fish.discord.SlashCommandsProvider
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.interactions.commands.build.CommandData
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData
import org.springframework.stereotype.Component

@Component
class SelfRolesCommandsProvider : SlashCommandsProvider {

    override val commands: List<CommandData> = listOf(
        CommandData("selfrole", "Manages self-assignable user roles + categories")
            .addSubcommands(
                SubcommandData("create-category", "Creates a new self role category + create the menu message")
                    .addOption(OptionType.CHANNEL, "channel", "Channel, in which the menu message should be created", true)
                    .addOption(OptionType.STRING, "name", "Unique name of the self role category", true),

                SubcommandData("delete-category", "Deletes the specified self role category")
                    .addOption(OptionType.STRING, "name", "Name of the category, that should be deleted", true),

                SubcommandData("bind", "Bind a new role to a self role category + update the menu message")
                    .addOption(OptionType.STRING, "category", "Name of the category, to which the role should be bound", true)
                    .addOption(OptionType.STRING, "emoji", "Emoji to which the role should be bound", true)
                    .addOption(OptionType.ROLE, "role", "Role that should be bound to the emoji", true),

                SubcommandData("unbind", "Removes a self role binding + update the menu message")
                    .addOption(OptionType.ROLE, "role", "Role that should be unbound", true)
            )
    )

    override fun handle(event: SlashCommandEvent) {
        when (event.subcommandName) {
            // TODO: Implement those subcommands
            "create-category" -> Unit
            "delete-category" -> Unit
            "bind" -> Unit
            "unbind" -> Unit
            else -> throw IllegalArgumentException("Subcommand not found")
        }
    }


}