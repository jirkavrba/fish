package dev.vrba.vse.fish.discord.modules.selfroles.repositories

import dev.vrba.vse.fish.discord.modules.selfroles.entities.SelfRole
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SelfRolesRepositories : CrudRepository<SelfRole, Long>
