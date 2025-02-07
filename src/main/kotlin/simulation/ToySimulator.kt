package simulation
import java.io.PrintStream

class ToyEvent(
    private val printStream: PrintStream,
) : Event {
    override fun invoke() {
        printStream.print("A toy event occurred.\n") // somehow println will fail the test
    }
}

class ToySimulator : Simulator() {
    override fun shouldTerminate(): Boolean = eventQueue.isEmpty()
}

fun main() {
    val simulator = ToySimulator()
    repeat(10) {
        simulator.schedule(ToyEvent(System.out), it.toDouble())
    }
    simulator.execute()
}
