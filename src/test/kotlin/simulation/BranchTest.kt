package simulation

// import org.junit.Test
// import queues.FifoQueue
// import kotlin.test.assertEquals

/*
class Branch(val stopTime: Double) : Simulator() {

    override fun shouldTerminate() = currentTime() >= stopTime

    fun runSim(): Double {
        val q1 = MeasurableQueue<Job>(FifoQueue(), this)
        val q2 = MeasurableQueue<Job>(FifoQueue(), this)
        val q3 = MeasurableQueue<Job>(FifoQueue(), this)

        val source = Source(ExponentialTimeDelay(0.5), this)
        val qn1 = QNode(q1, ExponentialTimeDelay(1.0), this)
        val qn2 = QNode(q2, ExponentialTimeDelay(0.5), this)
        val qn3 = QNode(q3, ExponentialTimeDelay(1.0 / 3.0), this)
        val sink = Sink(this)

        source.linkTo(qn1)
        qn1.linkTo(arrayOf(qn2, qn3), arrayOf(0.5, 0.5))
        qn2.linkTo(sink)
        qn3.linkTo(sink)

        execute()

        return (sink.meanResponseTime())
    }
}
*/

class BranchTest {
//    // If you have extended this simulator to handle Queueing Networks, you may find this test helpful. Please uncomment this test, and runSim in the Branch class above, to implement this.
//    @Test
//    fun `matches mm1 queueing theory`() {
//        assertEquals(Branch(10000000.0).runSim(), 10.0, 0.1)
//    }
}
