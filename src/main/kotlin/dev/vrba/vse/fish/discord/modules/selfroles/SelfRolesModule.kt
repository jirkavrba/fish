package dev.vrba.vse.fish.discord.modules.selfroles

import dev.vrba.vse.fish.discord.DiscordModule
import net.dv8tion.jda.api.JDA
import org.springframework.stereotype.Component

@Component
class SelfRolesModule(private val listener: SelfRolesReactionsListener) : DiscordModule {

    override fun register(client: JDA) = client.addEventListener(listener)

}