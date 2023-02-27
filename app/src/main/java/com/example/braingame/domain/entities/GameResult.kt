package com.example.braingame.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameResult  (
    val winner : Boolean,
    val amountOfRA : Int,
    val countOfQuestion :Int,
    val gameSettings: GameSettings
        ) : Parcelable