package com.example.geoquizkotlin;

import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG="QuizViewModel"

class QuizViewModel: ViewModel(){
    var currentIndex=0
    private var questionBank= listOf(
        Question(R.string.question_australia,true),
        Question(R.string.question_oceans,true),
        Question(R.string.question_mideast,false),
        Question(R.string.question_africa,false),
        Question(R.string.question_americas,true),
        Question(R.string.question_asia,true))

    private var isNotAnswered= mutableListOf(true,true,true,true,true,true)
    var amountAnswer=0;
    var rightAnswer=0;

    val currentQuestionAnswer: Boolean
    get()=questionBank[currentIndex].answerTrue

    val currentQuestionText:Int
    get()=questionBank[currentIndex].textResId

    val isActiveButton:Boolean
    get()=isNotAnswered[currentIndex]

    val doesGameOver:Boolean
    get()=amountAnswer==questionBank.size

    fun movetoNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }

    fun movetoPrevious(){
        currentIndex=(questionBank.size+currentIndex-1) % questionBank.size
    }

    fun makeAnswer(answer:Boolean){
        if(answer) rightAnswer++
        isNotAnswered[currentIndex]=false
        amountAnswer++
    }








}
