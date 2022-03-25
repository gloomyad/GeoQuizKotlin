package com.example.geoquizkotlin

import androidx.annotation.StringRes
import kotlin.properties.Delegates

data class Question(@StringRes val textResId:Int, val answerTrue:Boolean)