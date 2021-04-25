package com.abdelrahmman.humanbenchmark.ui

import android.content.Context
import android.graphics.Color.parseColor
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.abdelrahmman.humanbenchmark.R
import com.abdelrahmman.humanbenchmark.util.ProgressBarAnimation

class NumberMemoryFragment : Fragment() {

    // TODO: Save Score

    var number: String = ""
    var digits: Int = 1
    var thinkingTime: Long = 3000

    lateinit var textNumber: TextView
    lateinit var btnStart: AppCompatButton
    lateinit var btnSubmit: AppCompatButton
    lateinit var progressbar: ProgressBar
    lateinit var answer: EditText
    lateinit var textRightNumber: TextView
    lateinit var textUserAnswer: TextView
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

        textNumber = view.findViewById(R.id.text_number)
        btnStart = view.findViewById(R.id.btn_start)
        btnSubmit = view.findViewById(R.id.btn_submit)
        answer = view.findViewById(R.id.user_answer)
        textRightNumber = view.findViewById(R.id.text_right_number)
        textUserAnswer = view.findViewById(R.id.text_user_answer)
        btnContinue = view.findViewById(R.id.btn_continue)
        btnSaveScore = view.findViewById(R.id.btn_save_score_numbers)
        progressbar = view.findViewById(R.id.progressbar)

        btnStart.setOnClickListener {
            handleGameplay()
        }

        btnSubmit.setOnClickListener {
            handleUserAnswer()
        }

        btnContinue.setOnClickListener {

            val linearGreen = view.findViewById<LinearLayout>(R.id.gameplay_result_numbers)
            linearGreen?.visibility = View.GONE

            val linearNumber = view.findViewById<LinearLayout>(R.id.gameplay_number)
            linearNumber?.visibility = View.VISIBLE

            handleGameplay()
        }

        btnSaveScore.setOnClickListener {
            // TODO
        }

    }

    private fun handleGameplay(){

        val linearRed = view?.findViewById<LinearLayout>(R.id.number_start_game)
        linearRed?.visibility = View.GONE

        val linearGreen = view?.findViewById<LinearLayout>(R.id.gameplay_number)
        linearGreen?.visibility = View.VISIBLE

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
            val linearRed = view?.findViewById<LinearLayout>(R.id.gameplay_number)
            linearRed?.visibility = View.GONE

            val linearGreen = view?.findViewById<LinearLayout>(R.id.gameplay_answer)
            linearGreen?.visibility = View.VISIBLE

            answer.requestFocus()
            view?.showKeyboard()

        }, thinkingTime)

    }

    private fun handleUserAnswer(){

        val linearRed = view?.findViewById<LinearLayout>(R.id.gameplay_answer)
        linearRed?.visibility = View.GONE

        val linearGreen = view?.findViewById<LinearLayout>(R.id.gameplay_result_numbers)
        linearGreen?.visibility = View.VISIBLE

        view?.hideKeyboard()

        val userAnswer: String = answer.text.toString()

        textRightNumber.setText(number)
        textUserAnswer.setText(userAnswer)

        if (userAnswer.equals(number)){

            btnContinue.setText("Continue")
            textUserAnswer.setTextColor(ContextCompat.getColor(context!!, R.color.white))
            btnSaveScore.visibility = View.GONE

        } else {
            btnContinue.setText("Try Again")
            textUserAnswer.setTextColor(ContextCompat.getColor(context!!, R.color.light_red))
            digits = 1
            thinkingTime = 3000
            btnSaveScore.visibility = View.VISIBLE
        }

        answer.setText("")

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