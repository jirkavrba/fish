package dev.vrba.vse.fish.discord.modules.selfroles.repositories

import dev.vrba.vse.fish.discord.modules.selfroles.entities.SelfRolesCategory
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SelfRoleCategoriesRepository : CrudRepository<SelfRolesCategory, Long> {

    fun findByGuildAndChannelAndMessage(guild: Long, channel: Long, message: Long): SelfRolesCategory?

}