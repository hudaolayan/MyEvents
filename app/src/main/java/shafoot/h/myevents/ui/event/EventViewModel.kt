/*
 * Copyright (c) 2021
 * project: MyEvents
 * by: hudashafoot
 */

package shafoot.h.myevents.ui.event

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import shafoot.h.myevents.R
import shafoot.h.myevents.data.network.Result
import shafoot.h.myevents.data.repositories.event.EventRepository
import shafoot.h.myevents.models.Event
import shafoot.h.myevents.ui.base.BaseViewModel
import shafoot.h.myevents.utils.OneTimeEvent
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(private val eventRepository: EventRepository) :
    BaseViewModel() {

    var isNewEvent: Boolean = true

    var event: Event? = null

    private val _message = MutableLiveData<OneTimeEvent<Int>>()
    val message: LiveData<OneTimeEvent<Int>> = _message

    fun initEvent(event: Event?) {
        this.event = event
        if (event?.name == null && event?.description == null) {
            isNewEvent = true
            return
        }
        isNewEvent = false
    }

    fun saveEvent() {
        if (event?.name.isNullOrEmpty()) {
            _message.value = OneTimeEvent(R.string.error_empty_event_name)
            return
        }
        if (event?.description.isNullOrEmpty()) {
            _message.value = OneTimeEvent(R.string.error_empty_event_description)
            return
        }

        if (isNewEvent || event?.id == null) {
            addEvent()
        } else {
            updateEvent()
        }
    }

    private val _addEditEvent: MutableLiveData<OneTimeEvent<Result<Unit>>> = MutableLiveData()
    val addEditEvent: LiveData<OneTimeEvent<Result<Unit>>> = _addEditEvent

    fun addEvent(): MutableLiveData<OneTimeEvent<Result<Unit>>> {
        viewModelScope.launch(Dispatchers.IO) {
            _addEditEvent.postValue(OneTimeEvent(Result.Loading(true)))
            val result = tryResult { eventRepository.insert(event) }
            _addEditEvent.postValue(OneTimeEvent(result))
            event = null
            getEvents()
        }
        return _addEditEvent
    }

    private fun updateEvent() {
        viewModelScope.launch {
            _addEditEvent.postValue(OneTimeEvent(Result.Loading(true)))
            val result = tryResult { eventRepository.updateEvent(event) }
            _addEditEvent.postValue(OneTimeEvent(result))
            event = null
            getEvents()
        }
    }

    private val _eventsList: MutableLiveData<Result<MutableList<Event?>>> = MutableLiveData()

    fun getEvents(): LiveData<Result<MutableList<Event?>>> {
        viewModelScope.launch(Dispatchers.IO) {
            _eventsList.postValue(Result.Loading(true))
            val result =
                tryResult { eventRepository.getEvents() }
            _eventsList.postValue(result)
        }
        return _eventsList
    }

    private val _deleteEvent: MutableLiveData<Result<Unit>> = MutableLiveData()

    fun deleteEvent(event: Event?): MutableLiveData<Result<Unit>> {
        viewModelScope.launch(Dispatchers.IO) {
            _deleteEvent.postValue(Result.Loading(true))
            val result = tryResult { eventRepository.deleteEvent(event) }
            _deleteEvent.postValue(result)
            getEvents()
        }
        return _deleteEvent
    }

    private val _deleteEventList: MutableLiveData<Result<Unit>> = MutableLiveData()

    fun deleteEvents(events: MutableList<Event?>): MutableLiveData<Result<Unit>> {
        viewModelScope.launch(Dispatchers.IO) {
            _deleteEventList.postValue(Result.Loading(true))
            val result = tryResult { eventRepository.delete(events) }
            _deleteEventList.postValue(result)
            getEvents()
        }
        return _deleteEventList
    }
}
