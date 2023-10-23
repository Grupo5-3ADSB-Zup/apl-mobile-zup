import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import school.sptech.zup.domain.usecase.GetFeedUseCase
import school.sptech.zup.util.StateView
import java.lang.Exception
import javax.inject.Inject

class FeedViewModel @Inject constructor(
    private val getFeedUseCase: GetFeedUseCase
): ViewModel() {

    fun getFeed() = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())

            val feed = getFeedUseCase()

            emit(StateView.Success(feed))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}