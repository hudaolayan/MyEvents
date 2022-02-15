/*
 * Copyright (c) 2021
 * project: MyEvents
 * by: hudashafoot
 */

package shafoot.h.myevents.ui.converter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import shafoot.h.myevents.data.network.Result
import shafoot.h.myevents.data.repositories.date.DateRepository
import shafoot.h.myevents.models.BaseResponse
import shafoot.h.myevents.models.ConvertDateResponseModel
import shafoot.h.myevents.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class ConvertDateViewModel @Inject constructor(private val dateRepository: DateRepository) :
    BaseViewModel() {

    private val _date: MutableLiveData<Result<BaseResponse<ConvertDateResponseModel>>> =
        MutableLiveData()

    fun getHijriDate(gregorianDate: String?): LiveData<Result<BaseResponse<ConvertDateResponseModel>>> {
        viewModelScope.launch(Dispatchers.IO) {
            _date.postValue(Result.Loading(true))
            val result =
                tryResult { dateRepository.convertGregorianToHijri(gregorianDate) }
            _date.postValue(result)
        }
        return _date
    }

}
