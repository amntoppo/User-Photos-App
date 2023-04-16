package io.amntoppo.userphotos.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import io.amntoppo.userphotos.domain.model.Photo
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotoDao {

    @Query("SELECT * FROM photos WHERE id LIKE '%' || :id || '%'")
    fun getPhoto(id: Int): Flow<Photo>

    @Insert(onConflict = REPLACE)
    suspend fun insertPhoto(photo: Photo)

    @Query("DELETE FROM photos WHERE id LIKE '%' || :id || '%'")
    suspend fun deletePhoto(id: Int)
}
