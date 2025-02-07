package simulation

import java.io.PrintStream
import java.util.Random

class RandomTickSimulator(
    private val printStream: PrintStream = System.out,
    private val stoppingTime: Double,
    private val interval: Pair<Double, Double>,
    private val random: Random = Random(),
) : Simulator() {
    override fun shouldTerminate(): Boolean = currentTime() >= stoppingTime

    inner class TickEvent : Event {
        override fun invoke() {
            printStream.println("Tick at ${currentTime()}")
            val nextInterval = interval.first + (interval.second - interval.first) * random.nextDouble()
            if (currentTime() + nextInterval < stoppingTime) {
                schedule(TickEvent(), nextInterval)
            }
        }
    }

    fun start(initialTime: Double) {
        schedule(TickEvent(), initialTime)
        execute()
    }
}

fun main() {
    val simulator = RandomTickSimulator(stoppingTime = 10.0, interval = 1.0 to 2.0)
    simulator.start(initialTime = 0.5)
}
