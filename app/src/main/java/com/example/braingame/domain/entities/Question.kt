package com.example.braingame.domain.entities

data class Question (
  val qSum : Int,
  val visibleNumb:Int,
  val options : List<Int>
        ){
    val rightAnswer : Int
    get() = qSum - visibleNumb
}
