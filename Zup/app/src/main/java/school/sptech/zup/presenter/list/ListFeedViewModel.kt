package school.sptech.zup.presenter.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import school.sptech.zup.domain.model.Feed
import javax.inject.Inject

class ListFeedViewModel @Inject constructor() : ViewModel() {

    private val _feedList = MutableLiveData<MutableList<Feed>>()
    val currentScrambledWord: LiveData<MutableList<Feed>> = _feedList

    fun insertAddress(address: Feed) {
        val currentList = _feedList.value ?: mutableListOf()
        currentList.add(address)
        _feedList.value = currentList
    }

}