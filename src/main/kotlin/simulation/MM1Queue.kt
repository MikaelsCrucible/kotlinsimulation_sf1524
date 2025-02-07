package simulation

import java.util.Random

class MM1Queue(
    private val lambda: Double,
    private val miu: Double,
    private val seed: Long,
    private val stopTime: Double,
) : Simulator() {
    private val random = Random(seed)
    private val meanArrTime = 1.0 / lambda
    private val meanSerTime = 1.0 / miu
    private var length: Int = 0
    private var lastEvent: Double = 0.0
    private var accTime: Double = 0.0

    inner class Arrival : Event {
        override fun invoke() {
            update()
            length++
            schedule(Arrival(), expDistribution(meanArrTime))
            if (length == 1) {
                schedule(Completion(), expDistribution(meanSerTime))
            }
        }
    }

    inner class Completion : Event {
        override fun invoke() {
            update()
            length--
            if (length > 0) {
                schedule(Completion(), expDistribution(meanSerTime))
            }
        }
    }

    override fun shouldTerminate(): Boolean = currentTime() >= stopTime

    private fun expDistribution(mean: Double): Double = -Math.log(random.nextDouble()) * mean

    private fun update() {
        val t = currentTime() - lastEvent
        accTime += length * t
        lastEvent = currentTime()
    }

    fun runSim(): Double {
        schedule(Arrival(), 0.0)
        execute()
        return accTime / stopTime
    }
}

fun main() {
    print(MM1Queue(4.0, 5.0, 12345L, 2000000.0).runSim())
}
