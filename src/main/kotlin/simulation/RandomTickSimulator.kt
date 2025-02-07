package simulation

import java.io.PrintStream
import java.util.Random

class RandomTickSimulator(
    private val printStream: PrintStream,
    private val stoppingTime: Double,
    private val interval: Pair<Double, Double>,
    private val random: Random,
) : Simulator() {
    override fun shouldTerminate(): Boolean = currentTime() >= stoppingTime

    inner class TickEvent : Event {
        override fun invoke() {
            printStream.print("Tick at ${currentTime()}\n")
            val newInterval = interval.first + (interval.second - interval.first) * random.nextDouble()
            if (currentTime() + newInterval < stoppingTime) {
                schedule(TickEvent(), newInterval)
            }
        }
    }

    fun start(initTime: Double) {
        schedule(TickEvent(), initTime)
        execute()
    }
}

fun main() {
    val simulator = RandomTickSimulator(System.out, 10.0, 1.0 to 2.0, java.util.Random())
    simulator.start(0.5)
}
