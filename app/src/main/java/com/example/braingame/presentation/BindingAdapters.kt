package com.example.braingame.presentation

import android.content.res.ColorStateList
import android.graphics.Color
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.braingame.R
import com.example.braingame.domain.entities.GameResult
import com.example.braingame.domain.entities.Question

interface OnClickListener{
    fun onClick(int: Int)
}

@BindingAdapter("setImage")
fun bindImage(imageView: ImageView,winner : Boolean){
    when(winner){
        true -> imageView.setImageResource(R.drawable.ic_smile)
        false -> imageView.setImageResource(R.drawable.ic_sad)
    }
}

@BindingAdapter("requiredAnswers")
fun bindRequiredAnswers(textView: TextView,count : Int){
    textView.text = String.format(
        (textView.context.resources.getString(R.string.required_score)),
        count)
}

@BindingAdapter("requiredPercentage")
fun bindRequiredPercent(textView: TextView,percent: Int){
    textView.text = String.format(
        (textView.context.resources.getString(R.string.required_percentage)),
       percent)
}

@BindingAdapter("givenAnswers")
fun bindGivenAnswers(textView: TextView,answers: Int){
    textView.text = String.format(
        (textView.context.resources.getString(R.string.score_answers)),
        answers)
}

@BindingAdapter("givenPecnetage")
fun givenPecnetage(textView: TextView,gameResult: GameResult){
    textView.text = String.format(
        (textView.context.resources.getString(R.string.score_percentage)),
        findPercent(gameResult)
    )
}

private fun findPercent(gameResult: GameResult) =
    if(gameResult.countOfQuestion == 0){
        0
    }
    else{
        ((gameResult.amountOfRA / gameResult.countOfQuestion.toDouble()) * 100).toInt()}

@BindingAdapter("setQSum")
fun bindQSum(textView: TextView,number : Int){
    textView.text = number.toString()
}
@BindingAdapter("setVisibileNumber")
fun bindVisibileNumber(textView: TextView,number : Int){
    textView.text = number.toString()
}
@BindingAdapter("setPercentOfRightAnswer")
fun bindAnswerProgress(progressBar: ProgressBar,percntage : Int){
    progressBar.progress = percntage
}
@BindingAdapter("setSecondartyProgress")
fun bindSecondartyProgress(progressBar: ProgressBar,secProg : Int){
    progressBar.secondaryProgress = secProg
}
@BindingAdapter("setTextColorForAnswers")
fun bindTextColorForAnswers (textView: TextView,boolean: Boolean){
    when (boolean) {
        true -> textView.setTextColor(Color.GREEN)
        false -> textView.setTextColor(Color.RED)
    }
}
@BindingAdapter("setBgForProgressBar")
fun bindBgForProgressBar(progressBar: ProgressBar,boolean: Boolean){
    when (boolean) {
        true -> progressBar.progressTintList= ColorStateList.valueOf(Color.GREEN)
        false -> progressBar.progressTintList= ColorStateList.valueOf(Color.RED)
    }
}
@BindingAdapter("setOnTVClickListener")
fun bindOnTVClickListener(textView: TextView, clickListener : OnClickListener ){
  textView.setOnClickListener{
      clickListener.onClick(textView.text.toString().toInt())
  }
}


