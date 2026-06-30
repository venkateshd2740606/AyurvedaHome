package com.ayurvedahome.di

import android.content.Context
import androidx.room.Room
import com.ayurvedahome.data.local.database.AyurvedaHomeDatabase
import com.ayurvedahome.data.local.database.dao.AchievementDao
import com.ayurvedahome.data.local.database.dao.ChallengeDao
import com.ayurvedahome.data.local.database.dao.EconomyDao
import com.ayurvedahome.data.local.database.dao.GameDao
import com.ayurvedahome.data.local.database.dao.ProfileDao
import com.ayurvedahome.data.local.database.dao.StatsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AyurvedaHomeDatabase =
        Room.databaseBuilder(context, AyurvedaHomeDatabase::class.java, "ayurvedahome.db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides fun provideGameDao(db: AyurvedaHomeDatabase): GameDao = db.gameDao()
    @Provides fun provideStatsDao(db: AyurvedaHomeDatabase): StatsDao = db.statsDao()
    @Provides fun provideAchievementDao(db: AyurvedaHomeDatabase): AchievementDao = db.achievementDao()
    @Provides fun provideChallengeDao(db: AyurvedaHomeDatabase): ChallengeDao = db.challengeDao()
    @Provides fun provideEconomyDao(db: AyurvedaHomeDatabase): EconomyDao = db.economyDao()
    @Provides fun provideProfileDao(db: AyurvedaHomeDatabase): ProfileDao = db.profileDao()
}
