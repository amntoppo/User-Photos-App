package io.amntoppo.userphotos.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.amntoppo.userphotos.domain.model.Photo
import io.amntoppo.userphotos.domain.model.User

@Database(entities = [User::class, Photo::class], version = 1)
@TypeConverters(Converters::class)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    abstract fun photoDao(): PhotoDao
}