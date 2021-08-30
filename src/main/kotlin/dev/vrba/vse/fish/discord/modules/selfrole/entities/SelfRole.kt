package dev.vrba.vse.fish.discord.modules.selfrole.entities

import javax.persistence.*

@Entity
class SelfRole(
    @Id
    @GeneratedValue
    var id: Long = 0,

    @Column(nullable = false)
    var emoji: String,

    @Column(nullable = false)
    var roleId: Long,
) {

    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    lateinit var category: SelfRoleCategory
}
