/*
 * Copyright (c) 2021
 * project: MyEvents
 * by: hudashafoot
 */

package shafoot.h.myevents.ui.event

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsNot.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import shafoot.h.myevents.data.repositories.event.FakeEventRepository
import shafoot.h.myevents.getOrAwaitValue
import shafoot.h.myevents.models.Event

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class EventViewModelTest : TestCase() {

    private lateinit var eventViewModel: EventViewModel
    private lateinit var eventsRepository: FakeEventRepository

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {
        eventsRepository = FakeEventRepository()
        eventViewModel = EventViewModel(eventsRepository)
    }

    @Test
    fun testInitEvent_newEvent() {
        eventViewModel.initEvent(Event())
        val isNewEvent = eventViewModel.isNewEvent
        assertThat(isNewEvent, `is`(true))
    }

    @Test
    fun testInitEvent_oldEvent() {
        val event = Event(
            id = 22,
            name = "Test",
            description = "Test this event",
            gregorianDate = "06-12-2020",
            hijriDate = "26-11-1442",
            serverDatetime = 1607205600000
        )
        eventViewModel.initEvent(event)
        val isNewEvent = eventViewModel.isNewEvent
        assertThat(isNewEvent, `is`(false))
    }

    @Test
    fun testSaveEvent_validationFail() {
        val event = Event(
            id = 76,
            name = "Test",
            gregorianDate = "07-12-2021",
            hijriDate = "27-11-1442",
            serverDatetime = 1638828000000
        )
        eventViewModel.event = event
        eventViewModel.saveEvent()
        val result = eventViewModel.message.getOrAwaitValue()
        assertThat(result.getContentIfNotHandled(), not(nullValue()))
    }

    @Test
    fun testSaveEvent_validationSuccessNewEventValue() {
        val event = Event(
            id = 1,
            name = "Test",
            description = "Test Add event",
            gregorianDate = "07-12-2021",
            hijriDate = "27-11-1442",
            serverDatetime = 1638828000000
        )
        eventViewModel.event = event
        eventViewModel.saveEvent()
        val result = eventViewModel.addEditEvent.getOrAwaitValue()
        assertThat(result.getContentIfNotHandled(), not(nullValue()))
    }

}
