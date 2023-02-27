package com.example.braingame.domain.usecases

import com.example.braingame.domain.entities.Question
import com.example.braingame.domain.repository.GameRepository

class GetGQUseCase(private var gameRepository: GameRepository){

    operator fun invoke(maxSumValue:Int) : Question{
        return gameRepository.generateQuestion(maxSumValue, COUNT_OF_OPTIONS)
    }

    private companion object{
        const val COUNT_OF_OPTIONS = 6
    }
}