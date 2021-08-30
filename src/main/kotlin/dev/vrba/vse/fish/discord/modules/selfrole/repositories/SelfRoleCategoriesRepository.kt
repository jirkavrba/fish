package dev.vrba.vse.fish.discord.modules.selfrole.repositories

import dev.vrba.vse.fish.discord.modules.selfrole.entities.SelfRoleCategory
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface SelfRoleCategoriesRepository : CrudRepository<SelfRoleCategory, UUID> {

    fun findByName(name: String): SelfRoleCategory?

}