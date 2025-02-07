package simulation

interface Scheduler {
    fun schedule(
        event: Event,
        dt: Double,
    )
}
