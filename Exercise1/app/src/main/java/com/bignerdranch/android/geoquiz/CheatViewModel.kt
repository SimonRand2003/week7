package com.bignerdranch.android.geoquiz

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

private const val ANSWER_IS_TRUE_KEY = "ANSWER_IS_TRUE_KEY"
private const val ANSWER_SHOWN_KEY = "ANSWER_SHOWN_KEY"

class CheatViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    fun setAnswerIsTrue(answerIsTrue: Boolean) {
        savedStateHandle.set(ANSWER_IS_TRUE_KEY, answerIsTrue)
    }

    fun getAnswerIsTrue(): Boolean {
        return savedStateHandle.get<Boolean>(ANSWER_IS_TRUE_KEY) ?: false
    }

    fun setAnswerShown(isAnswerShown: Boolean) {
        savedStateHandle.set(ANSWER_SHOWN_KEY, isAnswerShown)
    }

    fun isAnswerShown(): Boolean {
        return savedStateHandle.get<Boolean>(ANSWER_SHOWN_KEY) ?: false
    }
}
