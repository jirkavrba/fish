package dev.vrba.vse.fish.discord.modules.selfrole.repositories

import dev.vrba.vse.fish.discord.modules.selfrole.entities.SelfRoleCategory
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SelfRoleCategoriesRepository : CrudRepository<SelfRoleCategory, Long> {

    fun findByName(name: String): SelfRoleCategory?

}