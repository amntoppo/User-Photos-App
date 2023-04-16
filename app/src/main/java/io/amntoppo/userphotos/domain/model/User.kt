package io.amntoppo.userphotos.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")

data class User(
    val address: Address,
    val email: String,
    @PrimaryKey val id: Int,
    val name: String,
    val username: String,
    val phone: String,
    val company: Company,
    val website: String
) : java.io.Serializable