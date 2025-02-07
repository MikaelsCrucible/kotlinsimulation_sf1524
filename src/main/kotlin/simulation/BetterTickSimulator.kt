package simulation
import java.io.PrintStream

class BetterTickSimulator(
    private val printStream: PrintStream,
    private val stoppingTime: Double,
) : Simulator() {
    inner class TickEvent : Event {
        override fun invoke() {
            printStream.print("Tick at $currentTime\n")
            if (currentTime() + 1.0 < stoppingTime) {
                schedule(TickEvent(), 1.0)
            }
        }
    }

    override fun shouldTerminate(): Boolean = currentTime() >= stoppingTime
}

fun main() {
    val simulator = BetterTickSimulator(System.out, 10.0)
    simulator.schedule(simulator.TickEvent(), 0.5)
    simulator.execute()
}
