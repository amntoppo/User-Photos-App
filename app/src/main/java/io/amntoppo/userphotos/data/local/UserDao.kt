package io.amntoppo.userphotos.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import io.amntoppo.userphotos.domain.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM Users")
    fun getAllUsers(): Flow<List<User>>

    @Insert(onConflict = REPLACE)
    suspend fun insertUsers(users: List<User>)

    @Query("DELETE FROM Users")
    suspend fun deleteAllUsers()

}