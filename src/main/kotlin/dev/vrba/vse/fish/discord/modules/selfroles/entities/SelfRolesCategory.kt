package dev.vrba.vse.fish.discord.modules.selfroles.entities

import javax.persistence.*

@Entity
@Table(
    name = "self_role_categories",
    uniqueConstraints = [
        UniqueConstraint(
            name = "UC_message",
            columnNames = [
                "guild",
                "channel",
                "message"
            ]
        )
    ]
)
data class SelfRolesCategory(
    @Id
    @GeneratedValue
    val id: Long,

    val name: String,

    // IDs leading to a message with the roles menu
    val guild: Long,
    val channel: Long,
    val message: Long,

    @OneToMany(mappedBy = "category")
    val roles: List<SelfRole>
)