package school.sptech.zup.presenter.list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import school.sptech.zup.R
import school.sptech.zup.databinding.FragmentListFeedBinding
import school.sptech.zup.presenter.list.adapter.FeedAdapter

class ListFeedFragment : Fragment() {

    private val viewModel: ListFeedViewModel by viewModels()

    private var _binding: FragmentListFeedBinding? = null
    private val binding get() = _binding!!

    private lateinit var feedAdapter: FeedAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun initObservers(){
        viewModel.currentScrambledWord.observe((viewLifecycleOwner)){
            feed ->

            initRecycler()
        }
    }

    private fun initRecycler() {
        feedAdapter = FeedAdapter()



        with(binding.recyclerFeed) {
            adapter = feedAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}