package com.bignerdranch.android.geoquiz

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.SavedStateViewModelFactory
import com.bignerdranch.android.geoquiz.databinding.ActivityCheatBinding

const val EXTRA_ANSWER_SHOWN = "com.bignerdranch.android.geoquiz.answer_shown"
private const val EXTRA_ANSWER_IS_TRUE = "com.bignerdranch.android.geoquiz.answer_is_true"

class CheatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheatBinding

    private val cheatViewModel: CheatViewModel by viewModels {
        SavedStateViewModelFactory(application, this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)
        cheatViewModel.setAnswerIsTrue(answerIsTrue)

        binding.showAnswerButton.setOnClickListener {
            showAnswer()
        }

        if (cheatViewModel.isAnswerShown()) {
            showAnswer()
        }
    }
    companion object {
        fun newIntent(packageContext: Context, answerIsTrue: Boolean):
                Intent {
            return Intent(packageContext, CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            }
        }
    }


        private fun showAnswer() {
        val answerText = when {
            cheatViewModel.getAnswerIsTrue() -> R.string.true_button
            else -> R.string.false_button
        }
        binding.answerTextView.setText(answerText)
        cheatViewModel.setAnswerShown(true)
        setAnswerShownResult(true)
    }

    private fun setAnswerShownResult(isAnswerShown: Boolean) {
        val data = intent.apply {
            putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
        }
        setResult(RESULT_OK, data)
    }
}
