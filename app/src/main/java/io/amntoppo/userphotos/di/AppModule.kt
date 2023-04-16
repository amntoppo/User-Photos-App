package io.amntoppo.userphotos.di

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.amntoppo.userphotos.BuildConfig
import io.amntoppo.userphotos.data.local.Converters
import io.amntoppo.userphotos.data.local.UserDatabase
import io.amntoppo.userphotos.data.remote.PhotoApi
import io.amntoppo.userphotos.data.remote.UserApi
import io.amntoppo.userphotos.data.repository.UserRepository
import io.amntoppo.userphotos.domain.repository.IUserRepository
import io.amntoppo.userphotos.utils.GsonParser
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideUserApi(retrofit: Retrofit): UserApi =
        retrofit.create(UserApi::class.java)

    @Provides
    @Singleton
    fun providePhotoApi(retrofit: Retrofit): PhotoApi =
        retrofit.create(PhotoApi::class.java)

    @Provides
    @Singleton
    fun provideRepository(
        db: UserDatabase,
        userApi: UserApi,
        photoApi: PhotoApi
    ): IUserRepository {
        return UserRepository(db, userApi, photoApi)
    }

    @Provides
    @Singleton
    fun provideDatabase(app: Application): UserDatabase =
        Room.databaseBuilder(app, UserDatabase::class.java, "user_database")
            .addTypeConverter(Converters(GsonParser(Gson())))
            .build()

}