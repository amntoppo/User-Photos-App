package io.amntoppo.userphotos.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import io.amntoppo.userphotos.domain.model.User

@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
}