package io.amntoppo.userphotos.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import io.amntoppo.userphotos.R
import io.amntoppo.userphotos.databinding.FragmentDetailsBinding
import io.amntoppo.userphotos.presentation.viewmodels.MainViewModel
import io.amntoppo.userphotos.utils.Resource

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
            viewModel.currentUser.observe(viewLifecycleOwner) {user ->
                viewModel.loadPhoto(user.id).observe(viewLifecycleOwner) {photo ->
                    when(photo) {
                        is Resource.Success -> {
                            Glide.with(binding.root)
                                .load(photo.data?.url)
                                .placeholder(R.drawable.user_placeholder)
                                .into(binding.profileImage)
                        }
                        is Resource.Error -> Glide.with(binding.root)
                            .load(R.drawable.user_placeholder)
                            .into(binding.profileImage)
                        is Resource.Loading -> Glide.with(binding.root)
                            .load(R.drawable.user_placeholder)
                            .into(binding.profileImage)
                    }
                }
                binding.apply {
                    displayName.text = user.name
                    userName.text = user.username
                    companyValue.text = user.company.name
                    cityValue.text = user.address.city
                    streetValue.text = user.address.street
                    suiteValue.text = user.address.suite
                    websiteValue.text = user.website
                }
            }
    }
}