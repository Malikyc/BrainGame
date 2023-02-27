package com.example.braingame.domain.usecases

import com.example.braingame.domain.entities.GameSettings
import com.example.braingame.domain.entities.Level
import com.example.braingame.domain.repository.GameRepository

class GetGameSettingsUseCase(private val gameRepository: GameRepository) {

    operator fun invoke(level: Level) : GameSettings{
        return gameRepository.getGameSettings(level)
    }
}