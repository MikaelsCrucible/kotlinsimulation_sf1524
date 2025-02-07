package simulation
import java.io.PrintStream

class TickEvent(
    private val printStream: PrintStream,
    private val simulator: Simulator,
) : Event {
    override fun invoke() {
        printStream.print("Tick at ${simulator.currentTime()}\n")
        simulator.schedule(TickEvent(printStream, simulator), 1.0)
    }
}

class TickSimulator(
    private val stoppingTime: Double,
) : Simulator() {
    override fun shouldTerminate(): Boolean = currentTime() + 1.0 >= stoppingTime
}

fun main() {
    val simulator = TickSimulator(10.0)
    simulator.schedule(TickEvent(System.out, simulator), 0.5)
    simulator.execute()
}
