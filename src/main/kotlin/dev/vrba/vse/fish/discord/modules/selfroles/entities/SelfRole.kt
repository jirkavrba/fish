package dev.vrba.vse.fish.discord.modules.selfroles.entities

import javax.persistence.*

@Entity
@Table(
    name = "self_roles",
    uniqueConstraints = [
        UniqueConstraint(
            name = "UC_role",
            columnNames = [
                "category",
                "role"
            ]
        ),
        UniqueConstraint(
            name = "UC_emoji",
            columnNames = [
                "category",
                "emoji"
            ]
        )
    ]
)
data class SelfRole(
    @Id
    @GeneratedValue
    val id: Long,

    // ID of the discord role
    var role: Long,
    var emoji: String,

    // Mapped category
    @ManyToOne
    @JoinColumn(name = "category", referencedColumnName = "id", unique = true, nullable = false)
    val category: SelfRolesCategory
)