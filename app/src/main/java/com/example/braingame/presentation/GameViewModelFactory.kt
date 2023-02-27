package com.example.braingame.presentation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.braingame.domain.entities.Level

class GameViewModelFactory
    (private val application: Application,
   private val level: Level): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(GameViewModel::class.java)){
        return GameViewModel(application, level) as T}
        throw  RuntimeException("Unknown viewModel class")
    }
}