package io.amntoppo.userphotos.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.amntoppo.userphotos.R
import io.amntoppo.userphotos.domain.model.User
import io.amntoppo.userphotos.databinding.UserItemBinding

class UserAdapter constructor(private val onItemClicked: (User) -> Unit): ListAdapter<User, UserAdapter.UserViewHolder>(UsersComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentItem = getItem(position)
        if(currentItem != null) {
            holder.bind(currentItem, onItemClicked)
        }
//        currentItem ?:
    }

    class UserViewHolder(private val binding: UserItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(currentItem: User, onItemClicked: (User) -> Unit) {
            binding.apply {
                userName.text = currentItem.username
                //userAddress.text = currentItem.address.city
                userFullName.text = currentItem.name
                userPhoneNumber.text = currentItem.phone
            }
//            binding.root.setOnClickListener { onItemClicked(currentItem) }
            itemView.setOnClickListener {
//                val navController = Navigation.findNavController(itemView)
//                navController.navigate(R.id.detailsFragment)
                onItemClicked(currentItem)
            }
//            binding.root.setOnClickListener {
//                val position = adapterPosition
//                val detailsFragment =  DetailsFragment()
//
//                findNavController(detailsFragment).navigate(R.id.detailsFragment)
//
////                findNavController(R.id.navHostFragment).navigate(R.id.mainFragment)
//            }
        }
    }
    class UsersComparator: DiffUtil.ItemCallback<User> () {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }
}