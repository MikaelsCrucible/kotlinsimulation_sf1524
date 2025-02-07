package simulation
import java.util.PriorityQueue

abstract class Simulator :
    Clock,
    Scheduler {
    protected var currentTime: Double = 0.0
        private set
    protected val eventQueue = PriorityQueue<EventInfo>()

    override fun currentTime(): Double = currentTime

    override fun schedule(
        event: Event,
        dt: Double,
    ) {
        eventQueue.add(EventInfo(currentTime + dt, event))
    }

    fun execute() {
        while (eventQueue.isNotEmpty() && !shouldTerminate()) {
            val nxtEvent = eventQueue.poll()
            currentTime = nxtEvent.time
            nxtEvent.event.invoke()
        }
    }

    abstract fun shouldTerminate(): Boolean
}

data class EventInfo(
    val time: Double,
    val event: Event,
) : Comparable<EventInfo> {
    override fun compareTo(other: EventInfo): Int = time.compareTo(other.time)
}
