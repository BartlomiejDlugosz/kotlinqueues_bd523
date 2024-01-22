package queues

interface Acceptor<T> {
    fun accept(item: T)
}

interface Forwarder<T> {
    fun forward()
}

class QueueNode<T>(
    private val queue: Queue<T>,
    private val successor: Acceptor<T>
) : Acceptor<T>, Forwarder<T> {

    override fun accept(item: T) {
        queue.enqueue(item)
    }

    override fun forward() {
        queue.dequeue()?.let { successor.accept(it) }
    }
}

class Sink<T> : Acceptor<T> {
    private val acceptedItems = mutableListOf<T>()

    override fun accept(item: T) {
        acceptedItems.add(item)
    }

    fun getAccepted(): List<T> = acceptedItems
}