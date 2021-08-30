package dev.vrba.vse.fish.discord.modules.selfrole.entities

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class SelfRoleCategory(
    @Id
    @GeneratedValue
    var id: Long = 0,

    @Column(nullable = false, unique = true)
    val name: String,

    val channelId: Long,
    val messageId: Long
)
