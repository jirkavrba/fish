package dev.vrba.vse.fish.discord.modules.selfroles

import dev.vrba.vse.fish.discord.modules.selfroles.repositories.SelfRoleCategoriesRepository
import dev.vrba.vse.fish.discord.modules.selfroles.repositories.SelfRolesRepository
import net.dv8tion.jda.api.entities.Role
import net.dv8tion.jda.api.events.message.guild.react.GenericGuildMessageReactionEvent
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionRemoveEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.springframework.stereotype.Component

@Component
class SelfRolesReactionsListener(
    private val categoriesRepository: SelfRoleCategoriesRepository,
    private val rolesRepository: SelfRolesRepository
) : ListenerAdapter() {

    override fun onGuildMessageReactionAdd(event: GuildMessageReactionAddEvent) {
        findMappedRole(event)?.let {
            event.guild.addRoleToMember(event.userId, it).queue()
        }
    }

    override fun onGuildMessageReactionRemove(event: GuildMessageReactionRemoveEvent) {
        findMappedRole(event)?.let {
            event.guild.removeRoleFromMember(event.userId, it).queue()
        }
    }

    private fun findMappedRole(event: GenericGuildMessageReactionEvent): Role? {
        // If the message, that the reaction was given to is not from the self-bot user,
        // There is no need for making database calls in order to find the self-role category
        if (event.userId != event.jda.selfUser.id) return null

        val category = categoriesRepository.findByGuildAndChannelAndMessage(
            event.guild.idLong,
            event.channel.idLong,
            event.messageIdLong
        ) ?: return null

        val role = rolesRepository.findByCategoryAndEmoji(category, event.reactionEmote.emoji)

        // If there is no role bound to the emoji, remove the reaction (as it was added to a category message)
        if (role == null) {
            event.reaction.removeReaction().queue()
            return null
        }

        return event.guild.getRoleById(role.role)
    }
}