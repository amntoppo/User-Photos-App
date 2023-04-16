package io.amntoppo.userphotos.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import io.amntoppo.userphotos.R
import io.amntoppo.userphotos.presentation.viewmodels.MainViewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import io.amntoppo.userphotos.databinding.FragmentMainBinding
import io.amntoppo.userphotos.domain.model.User
import io.amntoppo.userphotos.presentation.adapter.UserAdapter
import io.amntoppo.userphotos.utils.Resource

@AndroidEntryPoint
class MainFragment: Fragment(R.layout.fragment_main) {
    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var userAdapter: UserAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        userAdapter = UserAdapter(::onItemClicked)
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

        viewModel.loadUser()
            .observe(viewLifecycleOwner) { result ->
                when(result) {
                    is Resource.Loading -> {
                        binding.progressBar.isVisible = true
                    }
                    is Resource.Error -> {
                        binding.textViewError.isVisible = true
                        binding.textViewError.text = result.error?.localizedMessage
                    }
                    is Resource.Success -> {
                        binding.progressBar.isVisible = false
                        binding.textViewError.isVisible = false
                        userAdapter.submitList(result.data)
                    }
                }
            }
    }

    fun onItemClicked(user: User) {
        findNavController().navigate(R.id.detailsFragment)
        viewModel.selectedUser(user)
    }
}