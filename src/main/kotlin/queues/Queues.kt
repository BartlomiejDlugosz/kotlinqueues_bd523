package queues

import java.util.PriorityQueue

open class Queue<T> {
    protected val queue: ArrayDeque<T> = ArrayDeque()

    fun isEmpty(): Boolean = queue.isEmpty()
    fun size(): Int = queue.size

    open fun enqueue(item: T) = queue.add(item)
    open fun peek(): T? = queue.firstOrNull()
    open fun dequeue(): T? = queue.removeFirstOrNull()
}

class FifoQueue<T>: Queue<T>()

class LifoQueue<T>: Queue<T>() {
    override fun dequeue(): T? = queue.removeLastOrNull()
}

class PrQueue<T>(comparator: Comparator<T>? = null) : PriorityQueue<T>(comparator) {
    fun enqueue(item: T) = super.add(item)
    fun dequeue(): T? = super.poll()
}