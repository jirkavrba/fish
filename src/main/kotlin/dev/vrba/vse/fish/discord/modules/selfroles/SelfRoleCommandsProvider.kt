package dev.vrba.vse.fish.discord.modules.selfroles

import dev.vrba.vse.fish.discord.SlashCommandsProvider
import dev.vrba.vse.fish.discord.modules.selfroles.entities.SelfRolesCategory
import dev.vrba.vse.fish.discord.modules.selfroles.repositories.SelfRoleCategoriesRepository
import dev.vrba.vse.fish.discord.utilities.DiscordEmbeds
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.TextChannel
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.interactions.commands.build.CommandData
import org.springframework.stereotype.Component
import java.time.Instant

@Component
class SelfRoleCommandsProvider(
    private val categoriesRepository: SelfRoleCategoriesRepository
) : SlashCommandsProvider {

    override val commands: List<CommandData> = listOf(
        // TODO: Disable this command for everyone, and whitelist admins only
        CommandData("create-self-role-category", "Create a new self role category with a message menu")
            .addOption(OptionType.CHANNEL, "channel", "Channel, in which should be the message created", true)
            .addOption(OptionType.STRING, "name", "Name of the role category", true),

        CommandData("create-self-role", "Creates a new self role binding")
            .addOption(OptionType.STRING, "category", "Name of the category, to which this self role should be bound", true)
            .addOption(OptionType.ROLE, "role", "Role that should be mapped", true)
            .addOption(OptionType.STRING, "emoji", "Emoji that should be mapped", true),
    )

    override fun handle(event: SlashCommandEvent) {
        when (event.name) {
            "create-self-role-category" -> createSelfRoleCategory(event)
            "create-self-role" -> createRoleCategory(event)
        }
    }

    private fun createSelfRoleCategory(event: SlashCommandEvent) {
        val channel = event.getOption("channel")?.asGuildChannel
            ?: return event.replyEmbeds(DiscordEmbeds.error("Cannot find the specified channel!")).queue()

        if (channel !is TextChannel) {
            return event.replyEmbeds(DiscordEmbeds.error("The provided channel is not a text channel!")).queue()
        }

        val interaction = event.deferReply().complete()

        val message = channel.sendMessageEmbeds(EmbedBuilder().setTitle("Creating the self-role category menu...").build()).complete()
        val category = SelfRolesCategory(
            // TODO: Check those null assertions, looks kinda ugly imo
            name = event.getOption("name")!!.asString,
            guild = message.guild.idLong,
            channel = message.channel.idLong,
            message = message.idLong,
            roles = listOf()
        )

        updateCategoryMessage(message, categoriesRepository.save(category))
        interaction.editOriginalEmbeds(DiscordEmbeds.success("Role menu created")).queue()
    }

    private fun createRoleCategory(event: SlashCommandEvent) {

    }

    private fun updateCategoryMessage(message: Message, category: SelfRolesCategory) {
        val embed = EmbedBuilder()
            .setTitle(category.name)
            .setDescription(category.roles.joinToString("\n") { "${it.emoji}: <@&${it.role}>" })
            .setColor(0x5865F2)
            .setTimestamp(Instant.now())
            .build()

        message.editMessageEmbeds(embed).queue()

        category.roles.forEach {
            message.addReaction(it.emoji)
        }
    }
}