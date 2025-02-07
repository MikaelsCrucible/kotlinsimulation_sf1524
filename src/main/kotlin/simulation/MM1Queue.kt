package simulation

import java.util.Random

class MM1Queue(
    private val lambda: Double,
    private val mu: Double,
    private val randomSeed: Long,
    private val stoppingTime: Double,
) : Simulator() {
    private val random = Random(randomSeed)
    private val meanArrivalTime = 1.0 / lambda
    private val meanServiceTime = 1.0 / mu
    private var queueLength: Int = 0
    private var lastEventTime: Double = 0.0
    private var accumulatedQueueTime: Double = 0.0

    inner class Arrival : Event {
        override fun invoke() {
            updateQueueStats()
            queueLength++
            schedule(Arrival(), sampleExponential(meanArrivalTime))
            if (queueLength == 1) {
                schedule(Completion(), sampleExponential(meanServiceTime))
            }
        }
    }

    inner class Completion : Event {
        override fun invoke() {
            updateQueueStats()
            queueLength--
            if (queueLength > 0) {
                schedule(Completion(), sampleExponential(meanServiceTime))
            }
        }
    }

    override fun shouldTerminate(): Boolean = currentTime() >= stoppingTime

    private fun sampleExponential(mean: Double): Double = -Math.log(1.0 - random.nextDouble()) * mean

    private fun updateQueueStats() {
        val timeDelta = currentTime() - lastEventTime
        accumulatedQueueTime += queueLength * timeDelta
        lastEventTime = currentTime()
    }

    fun runSim(): Double {
        schedule(Arrival(), 0.0)
        execute()
        return accumulatedQueueTime / stoppingTime
    }
}

fun main() {
    print(MM1Queue(4.0, 5.0, 12345L, 2000000.0).runSim())
}
