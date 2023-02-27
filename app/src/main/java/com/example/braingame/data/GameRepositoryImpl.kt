package com.example.braingame.data

import com.example.braingame.domain.entities.GameSettings
import com.example.braingame.domain.entities.Level
import com.example.braingame.domain.entities.Question
import com.example.braingame.domain.repository.GameRepository
import java.lang.Integer.max
import java.lang.Math.min
import kotlin.random.Random

object GameRepositoryImpl : GameRepository {

    private const val MIN_SUM_NUMBER = 2
    private const val MIN_ANSWER_VALUE = 1

    override fun generateQuestion(maxSumValue: Int, countOfOptions: Int) : Question {
        val sum = Random.nextInt(MIN_SUM_NUMBER ,maxSumValue+1)
        val visibleNumber = Random.nextInt(MIN_ANSWER_VALUE , sum)
        val options = HashSet<Int>()
        val rightAnswer = sum - visibleNumber
        options.add(rightAnswer)
        val from = max(rightAnswer-countOfOptions, MIN_ANSWER_VALUE)
        val  to = min(maxSumValue,rightAnswer+countOfOptions)
        while (options.size < countOfOptions){
            val answer = Random.nextInt(from,to)
            options.add(answer)
        }
        return Question(sum,visibleNumber,options.toList())
    }

    override fun getGameSettings(level: Level): GameSettings {
        return when (level) {
            Level.TEST -> {
                GameSettings(
                    10,
                    3,
                    1,
                    8
                )
            }
            Level.EASY -> {
                GameSettings(
                    10,
                    10,
                    70,
                    60
                )
            }
            Level.NORMAL -> {
                GameSettings(
                    20,
                    20,
                    80,
                    40
                )
            }
            Level.HARD -> {
                GameSettings(
                    30,
                    30,
                    90,
                    40
                )
            }
    }
}
}