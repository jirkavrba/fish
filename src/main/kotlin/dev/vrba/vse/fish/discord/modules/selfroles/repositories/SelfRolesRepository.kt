package dev.vrba.vse.fish.discord.modules.selfroles.repositories

import dev.vrba.vse.fish.discord.modules.selfroles.entities.SelfRole
import dev.vrba.vse.fish.discord.modules.selfroles.entities.SelfRolesCategory
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SelfRolesRepository : CrudRepository<SelfRole, Long> {

    fun findByCategoryAndEmoji(category: SelfRolesCategory, emoji: String): SelfRole?

}
