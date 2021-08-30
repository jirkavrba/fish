package dev.vrba.vse.fish.discord.modules.selfrole.entities

import javax.persistence.*

@Entity
class SelfRole(
    @Id
    @GeneratedValue
    var id: Long = 0,

    @Column(nullable = false)
    val emoji: String,

    @Column(nullable = false)
    val roleId: Long,
) {

    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    lateinit var category: SelfRoleCategory
}
