package io.amntoppo.userphotos.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.amntoppo.userphotos.R
import io.amntoppo.userphotos.databinding.FragmentDetailsBinding
import io.amntoppo.userphotos.presentation.ui.viewmodels.MainViewModel

@AndroidEntryPoint
class DetailsFragment: Fragment(R.layout.fragment_details) {
    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            viewModel.selectedItem.observe(viewLifecycleOwner) {
                Log.e("selected", it.name);
            }
    }
}