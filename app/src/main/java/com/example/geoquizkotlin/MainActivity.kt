package com.example.geoquizkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.Gravity
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

private const val TAG="MainActivity"
private const val KEY_INDEX="index"

class MainActivity : AppCompatActivity() {


    private lateinit var questionField: TextView
    private lateinit var trueBtn: Button
    private lateinit var falseBtn: Button
    private lateinit var nextBtn:ImageButton
    private lateinit var prevBtn:ImageButton

    private val quizViewModel:QuizViewModel by lazy {
        ViewModelProvider(this).get(QuizViewModel::class.java)

    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState?.let{
            quizViewModel.currentIndex=it.getInt(KEY_INDEX,0)
        }

        Log.d(TAG,"Got a QuizViewModel")

        //questionField = findViewById(R.id.question)
        trueBtn = findViewById(R.id.trueBtn)
        falseBtn = findViewById(R.id.falseBtn)
        nextBtn=findViewById(R.id.nextBtn)
        prevBtn=findViewById(R.id.prevBtn)


        trueBtn.setOnClickListener { checkAnswer(true) }
        falseBtn.setOnClickListener { checkAnswer(false) }
        nextBtn.setOnClickListener {nextQuestion()}
        prevBtn.setOnClickListener { prevQuestion() }


        setQuestion()
        setButtons()
        Log.v(TAG,"onCreate")
    }

    private fun setQuestion() {
        questionField.setText(quizViewModel.currentQuestionText)
    }

    private fun nextQuestion(){
        quizViewModel.movetoNext()
        setQuestion()
        setButtons()
    }

    private fun prevQuestion(){
        quizViewModel.movetoPrevious()
        setQuestion()
        setButtons()
    }

    private fun checkAnswer(b: Boolean) {
        val result=(b == quizViewModel.currentQuestionAnswer)
        val message= if(result)
            R.string.correct_toast
        else
            R.string.incorrect_toast

        quizViewModel.makeAnswer(result)

            Toast.makeText(this, message, Toast.LENGTH_SHORT).runCatching {
                    setGravity(Gravity.TOP and Gravity.START, 0, 0)
                    show()
                }
        setButtons()
        checkFinishGame()
    }

    private fun setButtons(){
        trueBtn.isEnabled=quizViewModel.isActiveButton
        falseBtn.isEnabled=quizViewModel.isActiveButton
    }

    private fun checkFinishGame(){
        if(quizViewModel.doesGameOver){
            Toast.makeText(this,"Finish game\nYou score is ${quizViewModel.rightAnswer}",Toast.LENGTH_SHORT).runCatching {
                setGravity(Gravity.TOP and Gravity.START, 0, 0)
                show()
            }
        }
    }

    private fun log(text:String)=Log.v(TAG,text)

    override fun onStart() {
        super.onStart()
        log("onStart")
    }

    override fun onResume() {
        super.onResume()
        log("onResume")
    }

    override fun onPause() {
        super.onPause()
        log("onPause")
    }

    override fun onStop() {
        super.onStop()
        log("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_INDEX,quizViewModel.currentIndex)
    }

    }