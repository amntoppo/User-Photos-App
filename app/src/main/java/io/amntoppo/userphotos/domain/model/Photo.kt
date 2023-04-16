package io.amntoppo.userphotos.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photos")
data class Photo(
    val albumId: Int,
    @PrimaryKey val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
) : java.io.Serializable