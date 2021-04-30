package com.abdelrahmman.humanbenchmark.ui

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.abdelrahmman.humanbenchmark.R
import com.abdelrahmman.humanbenchmark.data.Scores
import com.abdelrahmman.humanbenchmark.util.ProgressBarAnimation
import com.abdelrahmman.humanbenchmark.util.TimestampUtils

class NumberMemoryFragment : BaseMainFragment() {

    var number: String = ""
    var digits: Int = 1
    var thinkingTime: Long = 3000

    private lateinit var linearStartGame: LinearLayout
    private lateinit var linearGamplay: LinearLayout
    private lateinit var linearAnswer: LinearLayout
    private lateinit var linearResult: LinearLayout

    lateinit var textNumber: TextView
    lateinit var btnStart: AppCompatButton
    lateinit var btnSubmit: AppCompatButton
    lateinit var progressbar: ProgressBar
    lateinit var answer: EditText
    lateinit var textRightNumber: TextView
    lateinit var textUserAnswer: TextView
    lateinit var textLevel: TextView
    lateinit var btnContinue: AppCompatButton
    lateinit var btnSaveScore: AppCompatButton

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_number_memory, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

        linearStartGame = view.findViewById(R.id.linear_start_game)
        linearGamplay = view.findViewById(R.id.linear_gameplay)
        linearAnswer = view.findViewById(R.id.linear_answer)
        linearResult = view.findViewById(R.id.linear_result)

        textNumber = view.findViewById(R.id.text_number)
        btnStart = view.findViewById(R.id.btn_start)
        btnSubmit = view.findViewById(R.id.btn_submit)
        answer = view.findViewById(R.id.user_answer)
        textRightNumber = view.findViewById(R.id.text_right_number)
        textUserAnswer = view.findViewById(R.id.text_user_answer)
        textLevel = view.findViewById(R.id.text_level)
        btnContinue = view.findViewById(R.id.btn_continue)
        btnSaveScore = view.findViewById(R.id.btn_save_score)
        progressbar = view.findViewById(R.id.progressbar)

        btnStart.setOnClickListener {
            handleGameplay()
        }

        btnSubmit.setOnClickListener {
            handleUserAnswer()
        }

        btnContinue.setOnClickListener {
            linearResult.visibility = View.GONE
            linearGamplay.visibility = View.VISIBLE

            handleGameplay()

            btnSaveScore.isEnabled = true
        }

        btnSaveScore.setOnClickListener {
            handleSaveScore()

            btnSaveScore.isEnabled = false
        }

    }

    private fun handleGameplay(){
        linearStartGame.visibility = View.GONE
        linearGamplay.visibility = View.VISIBLE

        number = ""

        for (i in 1..digits) {
            val randomNumber : Int = (0 until 9).random()
            number += randomNumber
        }

        textNumber.setText(number)

        digits++
        thinkingTime += 500

        handleProgressBar()

        Handler(Looper.getMainLooper()).postDelayed({
            linearGamplay.visibility = View.GONE
            linearAnswer.visibility = View.VISIBLE

            answer.requestFocus()
            view?.showKeyboard()

        }, thinkingTime)

    }

    private fun handleUserAnswer(){
        linearAnswer.visibility = View.GONE
        linearResult.visibility = View.VISIBLE

        view?.hideKeyboard()

        val userAnswer: String = answer.text.toString()

        textRightNumber.setText(number)
        textUserAnswer.setText(userAnswer)
        textLevel.setText("Level " + number.length)

        if (userAnswer.equals(number)){

            btnContinue.setText(R.string.cont)
            textUserAnswer.setTextColor(ContextCompat.getColor(context!!, R.color.white))
            btnSaveScore.visibility = View.GONE

        } else {
            btnContinue.setText(R.string.try_again)
            textUserAnswer.setTextColor(ContextCompat.getColor(context!!, R.color.light_red))
            digits = 1
            thinkingTime = 3000
            btnSaveScore.visibility = View.VISIBLE
        }

        answer.setText("")

    }

    private fun handleSaveScore(){

        val timestamp: String? = TimestampUtils.getCurrentTimestamp()

        val score = Scores(
            getString(R.string.number_memory_fragment),
            number.length,
            timestamp!!
        )

        viewModel.insert(score)

        Toast.makeText(context, getString(R.string.score_saved), LENGTH_SHORT).show()

    }

    private fun handleProgressBar(){

        val anim = ProgressBarAnimation(progressbar, 100, 0)
        anim.duration = thinkingTime
        progressbar.startAnimation(anim)

    }

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    private fun View.showKeyboard() {
        val imm  = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

}