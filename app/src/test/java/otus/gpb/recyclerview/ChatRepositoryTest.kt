package otus.gpb.recyclerview

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test
import java.time.LocalDateTime

class ChatRepositoryTest {

    private val baseTime = LocalDateTime.of(2026, 10, 20, 12, 0)

    @Test
    fun `same base time produces same chats`() {
        val first = ChatRepository(baseTime)
        val second = ChatRepository(baseTime)

        repeat(5) { assertEquals(first.loadPage(), second.loadPage()) }
    }

    @Test
    fun `restore recreates previously loaded chats`() {
        val original = ChatRepository(baseTime)
        val loaded = List(3) { original.loadPage() }.flatten()

        val restoredRepository = ChatRepository(LocalDateTime.parse(baseTime.toString()))
        val restored = restoredRepository.restore(original.loadedCount)

        assertEquals(loaded, restored)
        assertEquals(original.loadedCount, restoredRepository.loadedCount)
    }

    @Test
    fun `next page after restore continues the sequence`() {
        val original = ChatRepository(baseTime)
        original.loadPage()
        val restoredRepository = ChatRepository(baseTime).apply { restore(original.loadedCount) }

        assertEquals(original.loadPage(), restoredRepository.loadPage())
    }

    @Test
    fun `different pages contain different chats`() {
        val repository = ChatRepository(baseTime)

        assertNotEquals(repository.loadPage(), repository.loadPage())
    }
}
