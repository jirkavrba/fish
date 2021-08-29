package dev.vrba.vse.fish.discord.modules.selfroles.entities

import org.hibernate.Hibernate
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
) {
    // Those are performance-optimized overrides specific to Hibernate

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        if (other !is SelfRole) return false

        return id == other.id
    }

    override fun hashCode(): Int = 1134847442

    override fun toString(): String = this::class.simpleName + "(id = $id )"
}