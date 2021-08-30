package dev.vrba.vse.fish.discord.modules.selfrole.service

import dev.vrba.vse.fish.discord.modules.selfrole.entities.SelfRoleCategory
import dev.vrba.vse.fish.discord.modules.selfrole.repositories.SelfRoleCategoriesRepository
import dev.vrba.vse.fish.discord.modules.selfrole.repositories.SelfRolesRepository
import dev.vrba.vse.fish.discord.utilities.DiscordColors
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.entities.ChannelType
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.TextChannel
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class SelfRolesService(
    private val rolesRepository: SelfRolesRepository,
    private val categoriesRepository: SelfRoleCategoriesRepository
) {
    fun createSelfRoleCategory(name: String, message: Message): SelfRoleCategory {
        return categoriesRepository.save(
            SelfRoleCategory(
                name = name,
                channelId = message.channel.idLong,
                messageId = message.idLong,
            )
        )
    }

    fun deleteSelfRoleCategory(name: String): SelfRoleCategory {
        return categoriesRepository.findByName(name)?.also { categoriesRepository.delete(it) }
            ?: throw IllegalArgumentException("Cannot find category with the provided name")
    }

    fun updateSelfRoleMenu(client: JDA, category: SelfRoleCategory) {
        val message = findSelfRoleMenuMessage(client, category)
        val roles = category.roles
            .joinToString("\n") { "${it.emoji}: <@&${it.roleId}>" }
            .ifEmpty { "_There are no self assignable roles yet_" }

        message.editMessageEmbeds(
            EmbedBuilder()
                .setTitle(category.name)
                .setDescription(roles)
                .setColor(DiscordColors.yellow)
                .setTimestamp(Instant.now())
                .build()
        ).queue()

        category.roles.forEach { message.addReaction(it.emoji).queue() }
    }

    fun deleteSelfRoleMenu(client: JDA, category: SelfRoleCategory) {
        findSelfRoleMenuMessage(client, category)
            .delete()
            .queue()
    }

    private fun findSelfRoleMenuMessage(client: JDA, category: SelfRoleCategory): Message {
        val channel = client.getGuildChannelById(category.channelId) as? TextChannel
        val message = channel?.retrieveMessageById(category.messageId)?.complete()
            ?: throw IllegalStateException("Cannot find the role menu message")

        return message
    }

}