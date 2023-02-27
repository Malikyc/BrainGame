package com.example.braingame.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize

data class GameSettings  (
    val maxSumValue : Int,
    val timeLimitInSec : Int,
    val sufficientNumberOfRA : Int,
    val minPercentOfRA : Int
        ) : Parcelable