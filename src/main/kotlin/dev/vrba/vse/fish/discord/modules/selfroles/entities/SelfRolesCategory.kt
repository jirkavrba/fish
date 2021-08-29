package dev.vrba.vse.fish.discord.modules.selfroles.entities

import org.hibernate.Hibernate
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
) {
    // Those are performance-optimized overrides specific to Hibernate

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        if (other !is SelfRolesCategory) return false

        return id == other.id
    }

    override fun hashCode(): Int = 1513279959

    override fun toString(): String = this::class.simpleName + "(id = $id )"
}