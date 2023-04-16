package io.amntoppo.userphotos.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import io.amntoppo.userphotos.R
import io.amntoppo.userphotos.presentation.ui.viewmodels.MainViewModel
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import io.amntoppo.userphotos.databinding.FragmentMainBinding
import io.amntoppo.userphotos.presentation.ui.adapter.UserAdapter
import io.amntoppo.userphotos.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment: Fragment(R.layout.fragment_main) {
    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var userAdapter: UserAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
//        GlobalScope.launch(Dispatchers.IO) {
//            val ab = async { }
//            val bc = async {}
//
//            val bcaa = ab.await() + bc.await()
//        }
        userAdapter = UserAdapter()
        binding.apply {
            usersRecyclerView.apply {
                adapter = userAdapter
                layoutManager = LinearLayoutManager(context)
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel.loadUser()
//            .observe(viewLifecycleOwner) {
//                Log.e("MainFragment", it.toString())
//                userAdapter.submitList(it)
//            }

        viewModel.user.observe(viewLifecycleOwner) { result ->
            userAdapter.submitList(result.data)
            binding.apply {
                progressBar.isVisible = result is Resource.Loading && result.data.isNullOrEmpty()
                textViewError.isVisible = result is Resource.Error && result.data.isNullOrEmpty()
                textViewError.text = result.error?.localizedMessage
            }
        }
    }
}