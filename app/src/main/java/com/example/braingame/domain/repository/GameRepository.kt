package com.example.braingame.domain.repository

import com.example.braingame.domain.entities.GameSettings
import com.example.braingame.domain.entities.Level
import com.example.braingame.domain.entities.Question

interface GameRepository {

    fun generateQuestion(maxSumValue : Int, countOfOptions : Int) : Question

    fun getGameSettings(level: Level) : GameSettings
}