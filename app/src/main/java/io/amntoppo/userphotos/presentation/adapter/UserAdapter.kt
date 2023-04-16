package io.amntoppo.userphotos.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.amntoppo.userphotos.domain.model.User
import io.amntoppo.userphotos.databinding.UserItemBinding

class UserAdapter constructor(private val onItemClicked: (User) -> Unit): ListAdapter<User, UserAdapter.UserViewHolder>(
    UsersComparator()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentItem = getItem(position)
        if(currentItem != null) {
            holder.bind(currentItem, onItemClicked)
        }
    }

    class UserViewHolder(private val binding: UserItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(currentItem: User, onItemClicked: (User) -> Unit) {
            binding.apply {

                userAddress.text = currentItem.address.city
                userDisplayName.text = currentItem.name
                userName.text = currentItem.username
                userEmail.text = currentItem.email
                userCompany.text = currentItem.company.name
            }
            itemView.setOnClickListener {
                onItemClicked(currentItem)
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