package school.sptech.zup.presenter.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class ListViewModel: ViewModel() {

    private val _feedList = MutableLiveData<MutableList<Feed>>()
    val currentFeedList: LiveData<MutableList<Feed>> = _feedList

    fun insertFeed(feed: Feed){
        val currentList = _feedList.value ?: mutableListOf()
        currentList.add(feed)
        _feedList.value = currentList
    }
}