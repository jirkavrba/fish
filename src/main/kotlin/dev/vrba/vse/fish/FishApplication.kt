package dev.vrba.vse.fish

import dev.vrba.vse.fish.discord.DiscordBotService
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FishApplication(private val service: DiscordBotService) : CommandLineRunner {

    override fun run(vararg args: String) = service.start()

}

fun main(args: Array<String>) {
    runApplication<FishApplication>(*args)
}
