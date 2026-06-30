package com.ayurvedahome.di

import com.ayurvedahome.data.repository.ChallengeRepositoryImpl
import com.ayurvedahome.data.repository.GameRepositoryImpl
import com.ayurvedahome.data.repository.PreferencesRepositoryImpl
import com.ayurvedahome.data.repository.ProgressionRepositoryImpl
import com.ayurvedahome.domain.repository.ChallengeRepository
import com.ayurvedahome.domain.repository.GameRepository
import com.ayurvedahome.domain.repository.PreferencesRepository
import com.ayurvedahome.domain.repository.ProgressionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds @Singleton abstract fun bindGameRepository(impl: GameRepositoryImpl): GameRepository
    @Binds @Singleton abstract fun bindChallengeRepository(impl: ChallengeRepositoryImpl): ChallengeRepository
    @Binds @Singleton abstract fun bindProgressionRepository(impl: ProgressionRepositoryImpl): ProgressionRepository
    @Binds @Singleton abstract fun bindPreferencesRepository(impl: PreferencesRepositoryImpl): PreferencesRepository
}
