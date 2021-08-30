package dev.vrba.vse.fish.discord.modules.selfrole.entities

import java.util.*
import javax.persistence.*

@Entity
class SelfRoleCategory(
    @Id
    @GeneratedValue
    var id: UUID = UUID.randomUUID(),

    @Column(nullable = false, unique = true)
    val name: String,

    val channelId: Long,

    val messageId: Long,

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    var roles: MutableList<SelfRole> = mutableListOf()
)
