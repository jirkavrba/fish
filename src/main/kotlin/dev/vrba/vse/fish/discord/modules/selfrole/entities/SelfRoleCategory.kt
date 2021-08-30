package dev.vrba.vse.fish.discord.modules.selfrole.entities

import javax.persistence.*

@Entity
class SelfRoleCategory(
    @Id
    @GeneratedValue
    var id: Long = 0,

    @Column(nullable = false, unique = true)
    val name: String,

    val channelId: Long,
    val messageId: Long
) {
    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    lateinit var roles: MutableList<SelfRole>
}
