package simulation

import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class RandomTickSimulatorTest {
    private fun simulateAndValidate(simulatorSetup: (PrintStream) -> RandomTickSimulator) {
        for (repeat in 0..20) {
            val outputStream = ByteArrayOutputStream()
            val printStream = PrintStream(outputStream)
            val randomTickSimulator = simulatorSetup(printStream)
            randomTickSimulator.schedule(randomTickSimulator.TickEvent(), 0.5)
            randomTickSimulator.execute()

            // We cannot know exactly what the simulation should produce,
            // but we can check that the first time is 0.5, that the times
            // then increase by at least 1.0 and at most 2.0, and that no
            // time exceeds 10.0.
            val components = outputStream.toString().split("\n")
            val regex = """Tick at (\d+\.\d+)""".toRegex()
            var foundLastLine = false
            var lastTime: Double? = null
            for (component in components) {
                assertFalse(foundLastLine)
                if (component == "") {
                    foundLastLine = true
                } else {
                    assertTrue(regex.containsMatchIn(component))
                    val matchResult = regex.find(component)
                    val (matchString) = matchResult!!.destructured
                    val time = matchString.toDouble()
                    assertTrue(time <= 10.0)
                    if (lastTime == null) {
                        assertEquals(0.5, time)
                    } else {
                        assertTrue(time >= lastTime + 1.0)
                        assertTrue(time <= lastTime + 2.0)
                    }
                    lastTime = time
                }
            }
        }
    }

    @Test
    fun `test random tick simulator`() {
        // Repeat the simulation several times
        simulateAndValidate { printStream -> RandomTickSimulator(printStream, 10.0, Pair(1.0, 2.0)) }
    }

    @Test
    fun `test random tick simulator with random source`() {
        // Repeat the simulation several times
        val randomSource = java.util.Random()
        simulateAndValidate { printStream ->
            RandomTickSimulator(printStream, 10.0, Pair(1.0, 2.0), randomSource)
        }
    }
}
