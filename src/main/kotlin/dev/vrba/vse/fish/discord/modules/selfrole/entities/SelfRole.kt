package dev.vrba.vse.fish.discord.modules.selfrole.entities

import java.util.*
import javax.persistence.*

@Entity
class SelfRole(
    @Id
    @GeneratedValue
    var id: UUID = UUID.randomUUID(),

    @Column(nullable = false)
    val emoji: String,

    @Column(nullable = false)
    val roleId: Long,

    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    var category: SelfRoleCategory
)
