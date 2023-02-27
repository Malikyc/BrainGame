package com.example.braingame.presentation

import android.app.Application
import android.os.CountDownTimer
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.braingame.R
import com.example.braingame.data.GameRepositoryImpl
import com.example.braingame.domain.entities.GameResult
import com.example.braingame.domain.entities.GameSettings
import com.example.braingame.domain.entities.Level
import com.example.braingame.domain.entities.Question
import com.example.braingame.domain.usecases.GetGQUseCase
import com.example.braingame.domain.usecases.GetGameSettingsUseCase

class GameViewModel(private val application: Application,
                    private val level: Level) : ViewModel() {

    private val gameRepository = GameRepositoryImpl



    private val getGameQuestionUseCase = GetGQUseCase(gameRepository)
    private val getGameSettingsUseCase = GetGameSettingsUseCase(gameRepository)

    private lateinit var gameSettings: GameSettings

    private var timer : CountDownTimer? = null

    private var countOfRightAnswers = 0
    private var countOfOverallAnswers = 0

    private val _formattedTime = MutableLiveData<String>()
    val  formattedTime : LiveData<String>
    get() = _formattedTime

    private val _percentOfRightAnswers = MutableLiveData<Int>()
    val  percentOfRightAnswers : LiveData<Int>
        get() = _percentOfRightAnswers

    private val _progress = MutableLiveData<String>()
    val  progress : LiveData<String>
        get() = _progress

    private val _question = MutableLiveData<Question>()
    val question : LiveData<Question>
    get() = _question

    private val _enoughAmountOfRightAnswers = MutableLiveData<Boolean>()
    val  enoughAmountOfRightAnswers : LiveData<Boolean>
        get() = _enoughAmountOfRightAnswers

    private val _enoughPercentOfRightAnswers = MutableLiveData<Boolean>()
    val  enoughPercentOfRightAnswers : LiveData<Boolean>
        get() = _enoughPercentOfRightAnswers

    private val _minPercent = MutableLiveData<Int>()
    val  minPercent : LiveData<Int>
        get() = _minPercent

    private val _gameResults = MutableLiveData<GameResult>()
    val gameResult : LiveData<GameResult>
    get() = _gameResults

    init {
        startGame(level)
    }

    private fun startGame(level: Level){
         getGameSettings(level)
         startTimer()
         countProgress()
         generateQuestion()
     }

    private fun getGameSettings(level: Level){
        this.gameSettings = getGameSettingsUseCase.invoke(level)
        _minPercent.value = gameSettings.minPercentOfRA
    }

    private fun generateQuestion(){
        val maxSum = gameSettings.maxSumValue
        _question.value = getGameQuestionUseCase.invoke(maxSum)
    }

     fun chooseAnswer(answer : Int){
         validateAnswer(answer)
         calculatePercentOfRightAnswers()
         countProgress()
         generateQuestion()


    }
    private fun calculatePercentOfRightAnswers(){

        if(countOfOverallAnswers == 0){
            _percentOfRightAnswers.value = 0
        }
        else{
            _percentOfRightAnswers.value  = (countOfRightAnswers/countOfOverallAnswers.toDouble() * 100).toInt()
        }

        _enoughPercentOfRightAnswers.value = percentOfRightAnswers.value!! >= gameSettings.minPercentOfRA
    }

    private fun countProgress(){
        val progress : String = String.format((application.resources.getString(R.string.progress_answers)),
            countOfRightAnswers,
            gameSettings.sufficientNumberOfRA)
        _progress.value = progress
        _enoughAmountOfRightAnswers.value = countOfRightAnswers >= gameSettings.sufficientNumberOfRA

    }

    private fun validateAnswer(answer: Int) {
        val rightAnswer = question.value?.rightAnswer
        if (rightAnswer == answer) {
            countOfRightAnswers++
        }
        countOfOverallAnswers++
    }

    private fun startTimer(){
       timer = object : CountDownTimer(
           gameSettings.timeLimitInSec * MILLIS_IN_SECONDS,
           MILLIS_IN_SECONDS
       ){
           override fun onTick(millisUntilFinished: Long) {
               _formattedTime.value = formatTime(millisUntilFinished)
           }

           override fun onFinish() {
               gameFinished()
           }
       }
        timer?.start()
    }



    private fun formatTime(millisUntilFinished: Long) : String{
      val seconds = millisUntilFinished / MILLIS_IN_SECONDS
      val minutes = seconds/ SECONDS_IN_MINUTES
      val leftSeconds = seconds - (seconds * minutes)
      return String.format("%02d:%02d",minutes,seconds)
  }


  private fun gameFinished(){
    _gameResults.value = GameResult(winner =enoughAmountOfRightAnswers.value == true
            && enoughPercentOfRightAnswers.value == true,
        countOfRightAnswers, countOfOverallAnswers,gameSettings)
  }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }

  companion object{
      const val MILLIS_IN_SECONDS = 1000L
      const val SECONDS_IN_MINUTES = 60
  }
}