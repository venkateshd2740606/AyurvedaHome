package com.ayurvedahome.domain.repository

import com.ayurvedahome.domain.model.Achievement
import com.ayurvedahome.domain.model.ChallengeRecord
import com.ayurvedahome.domain.model.ChallengeType
import com.ayurvedahome.domain.model.AyurvedaHomeGame
import com.ayurvedahome.domain.model.AyurvedaHomeLevel
import com.ayurvedahome.domain.model.Difficulty
import com.ayurvedahome.domain.model.EconomyState
import com.ayurvedahome.domain.model.PuzzleProfile
import com.ayurvedahome.domain.model.UserStats
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    suspend fun createNewGame(difficulty: Difficulty, levelNumber: Int): AyurvedaHomeGame
    suspend fun createGameFromSeed(seed: Long, levelNumber: Int, difficulty: Difficulty): AyurvedaHomeGame
    suspend fun createTutorialGame(tutorialIndex: Int): AyurvedaHomeGame?
    suspend fun createEndlessGame(wave: Int): AyurvedaHomeGame
    suspend fun saveGame(game: AyurvedaHomeGame): Long
    suspend fun getGame(gameId: Long): AyurvedaHomeGame?
    suspend fun getInProgressGame(): AyurvedaHomeGame?
    fun observeInProgressGame(): Flow<AyurvedaHomeGame?>
    suspend fun completeGame(game: AyurvedaHomeGame): AyurvedaHomeGame
    suspend fun abandonGame(gameId: Long)
    suspend fun getLevel(seed: Long, levelNumber: Int, difficulty: Difficulty): AyurvedaHomeLevel
}

interface ChallengeRepository {
    suspend fun getChallenge(type: ChallengeType, key: String): ChallengeRecord?
    suspend fun createChallenge(type: ChallengeType, key: String, difficulty: Difficulty): ChallengeRecord
    suspend fun resolveActiveChallenge(type: ChallengeType): ChallengeRecord
    fun observeActiveChallenge(type: ChallengeType): Flow<ChallengeRecord?>
    suspend fun completeChallenge(record: ChallengeRecord, timeSeconds: Long, moves: Int): ChallengeRecord
    fun observeChallengeHistory(type: ChallengeType): Flow<List<ChallengeRecord>>
    suspend fun getCurrentStreak(type: ChallengeType): Int
    suspend fun getChallengeGame(record: ChallengeRecord): AyurvedaHomeGame
}

interface ProgressionRepository {
    fun observeStats(): Flow<UserStats>
    suspend fun getStats(): UserStats
    suspend fun updateStatsAfterGame(game: AyurvedaHomeGame)
    suspend fun grantChallengeRewards(rewardCoins: Int, rewardXp: Int)
    fun observePuzzleProfile(): Flow<PuzzleProfile>
    suspend fun getPuzzleProfile(): PuzzleProfile
    fun observeAchievements(): Flow<List<Achievement>>
    suspend fun checkAndUnlockAchievements(
        game: AyurvedaHomeGame,
        sameDevicePlayed: Boolean = false
    ): List<Achievement>
    fun observeEconomy(): Flow<EconomyState>
    suspend fun getEconomy(): EconomyState
    suspend fun spendCoins(amount: Int): Boolean
    suspend fun earnCoins(amount: Int)
    suspend fun unlockTheme(themeId: String): Boolean
}

interface PreferencesRepository {
    fun getUserPreferences(): Flow<com.ayurvedahome.domain.model.UserPreferences>
    suspend fun updatePreferences(transform: (com.ayurvedahome.domain.model.UserPreferences) -> com.ayurvedahome.domain.model.UserPreferences)
    suspend fun getCampaignLevel(difficulty: Difficulty): Int
    suspend fun advanceCampaignLevel(difficulty: Difficulty): Int
}
