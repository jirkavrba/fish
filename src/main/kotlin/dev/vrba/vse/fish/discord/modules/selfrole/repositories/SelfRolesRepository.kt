package dev.vrba.vse.fish.discord.modules.selfrole.repositories

import dev.vrba.vse.fish.discord.modules.selfrole.entities.SelfRole
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SelfRolesRepository : CrudRepository<SelfRole, Long>