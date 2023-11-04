package school.sptech.zup.presenter.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import school.sptech.zup.domain.model.FeedRequest
import javax.inject.Inject

class ListFeedViewModel @Inject constructor() : ViewModel() {

    private val _feedList = MutableLiveData<MutableList<FeedRequest>>()
    val currentScrambledWord: LiveData<MutableList<FeedRequest>> = _feedList

    fun insertAddress(address: FeedRequest) {
        val currentList = _feedList.value ?: mutableListOf()
        currentList.add(address)
        _feedList.value = currentList
    }

}