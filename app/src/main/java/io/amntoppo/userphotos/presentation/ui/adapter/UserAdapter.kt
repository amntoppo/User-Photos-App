package io.amntoppo.userphotos.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.amntoppo.userphotos.domain.model.User
import io.amntoppo.userphotos.databinding.UserItemBinding

class UserAdapter: ListAdapter<User, UserAdapter.UserViewHolder>(UsersComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentItem = getItem(position)
        if(currentItem != null) {
            holder.bind(currentItem)
        }
//        currentItem ?:
    }

    class UserViewHolder(private val binding: UserItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(currentItem: User) {
            binding.apply {
                userName.text = currentItem.username
                //userAddress.text = currentItem.address.city
                userFullName.text = currentItem.name
                userPhoneNumber.text = currentItem.phone
            }
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