package simulation

// import org.junit.Test
// import queues.FifoQueue
// import kotlin.test.assertEquals

/*
class CyclicQueue(val stopTime: Double) : Simulator() {

    override fun shouldTerminate() = currentTime() >= stopTime

    fun runSim(): Double {
        val q1 = MeasurableQueue<Job>(FifoQueue(), this)

        val source = Source(ExponentialTimeDelay(0.5), this)
        val qn1 = QNode(q1, ExponentialTimeDelay(1.0), this)
        val sink = Sink(this)

        source.linkTo(qn1)
        qn1.linkTo(arrayOf(qn1, sink), arrayOf(1.0 / 3.0, 2.0 / 3.0))

        execute()

        val meanQueueLength = q1.meanQueueLength()
        println("Mean queue length = $meanQueueLength")
        return (sink.meanResponseTime())
    }
}
*/

class CycleTest {
//    // If you have extended this simulator to handle Queueing Networks, you may find this test helpful. Please uncomment this test, and runSim in the CyclicQueue class above, to implement this.
//    @Test
//    fun `matches mm1 queueing theory`() {
//        assertEquals(CyclicQueue(10000000.0).runSim(), 6.0, 0.1)
//    }
}
