package com.softdream.exposicily.data.di

import android.content.Context
import androidx.room.Room
import com.softdream.exposicily.BuildConfig
import com.softdream.exposicily.data.local.LocationDao
import com.softdream.exposicily.data.local.LocationsDb
import com.softdream.exposicily.data.remote.LocationApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocationsModule {

    @Provides
    fun provideRoomDao(database: LocationsDb): LocationDao {
        return database.dao
    }

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext appContext: Context): LocationsDb {
        return Room.databaseBuilder(
            appContext,
            LocationsDb::class.java,
            "locations_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().addConverterFactory(
            GsonConverterFactory.create()
        ).baseUrl(BuildConfig.LOCATIONS_BASE_URL).build()
    }

    @Provides
    fun provideRetrofitApi(retrofit: Retrofit):
            LocationApiService {
        return retrofit.create(LocationApiService::class.java)
    }
}