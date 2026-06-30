package com.ayurvedahome.multiplayer

import com.ayurvedahome.domain.model.AyurvedaHomeGame
import com.ayurvedahome.domain.model.Difficulty
import com.ayurvedahome.domain.model.MultiplayerMode
import com.ayurvedahome.domain.model.MultiplayerSession
import com.ayurvedahome.engine.AyurvedaHomeEngine
import com.ayurvedahome.engine.AyurvedaHomeGenerator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PuzzleBotSession @Inject constructor() {
    private val _session = MutableStateFlow<MultiplayerSession?>(null)
    val session: StateFlow<MultiplayerSession?> = _session.asStateFlow()

    private var playerGame: AyurvedaHomeGame? = null
    private var botGame: AyurvedaHomeGame? = null
    private var playerName = "You"
    private val botName = "AI Bot"

    fun start(player: String, difficulty: Difficulty, seed: Long = System.currentTimeMillis()) {
        playerName = player
        val level = AyurvedaHomeGenerator.generate(seed, 1, difficulty)
        val game = AyurvedaHomeEngine.createInitialGame(level)
        playerGame = game
        botGame = game
        _session.value = MultiplayerSession(
            mode = MultiplayerMode.SAME_DEVICE,
            localPlayerName = playerName,
            remotePlayerName = botName,
            activePlayerName = playerName,
            isActive = true,
            seed = seed,
            difficulty = difficulty
        )
    }

    fun getPlayerGame(): AyurvedaHomeGame? = playerGame

    fun applyPlayerTubeClick(tubeId: Int): AyurvedaHomeGame? {
        val game = playerGame ?: return null
        val updated = AyurvedaHomeEngine.onTubeSelected(game, tubeId)
        playerGame = updated
        botGame = updated
        return updated
    }

    fun applyBotMove(): AyurvedaHomeGame? {
        val game = botGame ?: return null
        val hint = AyurvedaHomeEngine.getHintMove(game) ?: return game
        var updated = AyurvedaHomeEngine.onTubeSelected(game, hint.first)
        if (updated.selectedTubeId != null) {
            updated = AyurvedaHomeEngine.onTubeSelected(updated, hint.second)
        }
        playerGame = updated
        botGame = updated
        val session = _session.value
        if (session != null) {
            _session.value = session.copy(
                remoteScore = session.remoteScore + if (updated.isCompleted) 1 else 0,
                activePlayerName = playerName
            )
        }
        return updated
    }

    fun onPlayerWon() {
        val session = _session.value ?: return
        _session.value = session.copy(
            localScore = session.localScore + 1,
            activePlayerName = playerName
        )
        startNewRound(session)
    }

    fun onBotWon() {
        val session = _session.value ?: return
        _session.value = session.copy(
            remoteScore = session.remoteScore + 1,
            activePlayerName = playerName
        )
        startNewRound(session)
    }

    private fun startNewRound(session: MultiplayerSession) {
        val newSeed = session.seed + session.localScore + session.remoteScore
        val level = AyurvedaHomeGenerator.generate(newSeed, session.localScore + session.remoteScore + 1, session.difficulty)
        val game = AyurvedaHomeEngine.createInitialGame(level)
        playerGame = game
        botGame = game
    }

    fun isBotThinking(): Boolean = false

    fun end() {
        _session.value = null
        playerGame = null
        botGame = null
    }
}
