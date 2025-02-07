package simulation

import org.junit.Test
import kotlin.test.assertEquals

class SSQTest {
    @Test
    fun `matches mm1 queueing theory`() {
        val lambda = 4.0
        val mu = 5.0
        val randomSeed = 12345L
        val stoppingTime = 1250000.0 // 1M->4.0106 1.25M->can pass
        val expectedLongRunQueueLength = (lambda / mu) / (1 - lambda / mu) // = 4.0
        // assertEquals(expectedLongRunQueueLength, MM1Queue(lambda, mu, stoppingTime).runSim(), 0.01)
        // For the extension "Deterministic Random Numbers"
        assertEquals(expectedLongRunQueueLength, MM1Queue(lambda, mu, randomSeed, stoppingTime).runSim(), 0.01)
    }
}
