package otus.gpb.recyclerview

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ChatFactoryTest {

    @Test
    fun `pages contain stable non-overlapping ids`() {
        val firstPageIds = ChatFactory.createPage(page = 0).map(Chat::id)
        val secondPageIds = ChatFactory.createPage(page = 1).map(Chat::id)

        assertEquals((0L..9L).toList(), firstPageIds)
        assertTrue(firstPageIds.intersect(secondPageIds.toSet()).isEmpty())
    }

    @Test
    fun `time correctly crosses hour boundary`() {
        val chatAtNoon = ChatFactory.createPage(page = 2)[2]

        assertEquals(22L, chatAtNoon.id)
        assertEquals("12:00", chatAtNoon.time)
    }

    @Test
    fun `factory exposes all special presentation states`() {
        val chats = ChatFactory.createPage(page = 0)

        assertTrue(chats.any(Chat::isVerified))
        assertTrue(chats.any(Chat::isMuted))
        assertTrue(chats.any(Chat::isPinned))
        assertTrue(chats.any(Chat::hasMention))
        assertTrue(chats.any { it.messageState == Chat.MessageState.TYPING })
        assertTrue(chats.any { it.deliveryState == Chat.DeliveryState.READ })
    }
}
