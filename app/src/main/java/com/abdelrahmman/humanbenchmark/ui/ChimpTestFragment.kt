package com.abdelrahmman.humanbenchmark.ui

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.*
import android.widget.*
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.abdelrahmman.humanbenchmark.R
import java.lang.NullPointerException

class ChimpTestFragment : Fragment() {

    // TODO: Save Score

    var level: Int = 5
    var selected = mutableListOf<Int>()
    var pressCounter: Int = 0
    var userAnswer: String = ""
    var rightAnswer: String = ""
    var map = mutableMapOf<Int, Int>()
    var strikes: Int = 0

    lateinit var gridLayout: GridLayout
    lateinit var btnStart : AppCompatButton
    lateinit var textCurrentLevel : TextView
    lateinit var textStrikes : TextView
    lateinit var textScore : TextView
    lateinit var btnContinue : AppCompatButton
    lateinit var btnTryAgain : AppCompatButton

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chimp_test, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

        gridLayout = view.findViewById(R.id.chimp_grid)
        btnStart = view.findViewById(R.id.btn_start)
        textCurrentLevel = view.findViewById(R.id.text_current_level)
        textStrikes = view.findViewById(R.id.text_strikes)
        textScore = view.findViewById(R.id.text_score)
        btnContinue = view.findViewById(R.id.btn_continue)
        btnTryAgain = view.findViewById(R.id.btn_try_again)

        btnStart.setOnClickListener {

            val linearStart = view.findViewById<LinearLayout>(R.id.chimp_start_game)
            linearStart?.visibility = View.GONE

            val linearGameplay = view.findViewById<LinearLayout>(R.id.gameplay_chimp)
            linearGameplay?.visibility = View.VISIBLE

            handleGameplay()
        }

        btnContinue.setOnClickListener {
            val linearScore = view.findViewById<LinearLayout>(R.id.chimp_linear_result)
            linearScore?.visibility = View.GONE

            val linearGameplay = view.findViewById<LinearLayout>(R.id.gameplay_chimp)
            linearGameplay?.visibility = View.VISIBLE

            handleGameplay()
        }

        btnTryAgain.setOnClickListener {
            val linearEndResult = view.findViewById<LinearLayout>(R.id.chimp_linear_game_end)
            linearEndResult?.visibility = View.GONE

            val linearGameplay = view.findViewById<LinearLayout>(R.id.gameplay_chimp)
            linearGameplay?.visibility = View.VISIBLE
            handleGameplay()
        }

    }

    private fun handleGameplay(){

        gridLayout.removeAllViews()

        for (i in 0 until 20){

            val randomNumber: Int = generateRandomNumber() + 1

            addGridView(
                    gridLayout,
                    randomNumber.toString(),
                    i
            )

            map.set(randomNumber, i)
        }

        val sortedMap = map.toSortedMap()

        try {

            for (x in 1 until level){
                showSquares(
                        gridLayout,
                        sortedMap[x]
                )

            }

        } catch (e: NullPointerException){
            println(e.message)
        }

        selected.clear()
    }

    private fun addGridView(
        gridLayout: GridLayout,
        number: String,
        position: Int
    ){

        val linearLayoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        )

        linearLayoutParams.rightMargin = 10
        linearLayoutParams.leftMargin = 10
        linearLayoutParams.topMargin = 10
        linearLayoutParams.bottomMargin = 10

        val numbers = TextView(context)
        numbers.layoutParams = linearLayoutParams
        numbers.background = this.resources.getDrawable(R.drawable.rounded_transparent)
        numbers.width = 200
        numbers.height = 200
        numbers.gravity = Gravity.CENTER
        numbers.text = number
        numbers.textSize = 32f
        numbers.isClickable = false
        numbers.isFocusable = false
        gridLayout.addView(numbers)

        gridLayout.get(position).animate().alpha(0f).duration = 0

        squareClick(
                numbers,
                number
        )

    }

    private fun squareClick(
            numbers: TextView,
            numberValue: String
    ){
        numbers.setOnClickListener {

            if (numbers.text.toString().toInt() < level){
                for (i in 0 until 20){
                    gridLayout.get(i).background = this.resources.getDrawable(R.drawable.rounded_white_block)
                }

                if (pressCounter < level - 1) {

                    pressCounter++

                    userAnswer += numberValue

                    numbers.animate().alpha(0f).duration = 0
                    numbers.isClickable = false
                    numbers.isFocusable = false

                    if (pressCounter == level-1) {

                        validateAnswer(
                                level,
                                userAnswer
                        )

                        pressCounter = 0
                        userAnswer = ""
                    }

                }
            }

        }

    }

    private fun showSquares(
            gridLayout: GridLayout,
            position: Int?
    ){
//        for (y in 1 until level){
            gridLayout.get(position!!).animate().alpha(1f).duration = 0
//        }
    }

    private fun validateAnswer(
            currLevel: Int,
            userAnswer: String
    ){
        val linearGameplay = view?.findViewById<LinearLayout>(R.id.gameplay_chimp)
        linearGameplay?.visibility = View.GONE

        val linearGameResult = view?.findViewById<LinearLayout>(R.id.chimp_linear_result)
        linearGameResult?.visibility = View.VISIBLE

        for (i in 1 until currLevel){
            rightAnswer += i.toString()
        }

        if (userAnswer.equals(rightAnswer)){
            textStrikes.setTextColor(ContextCompat.getColor(context!!, R.color.white))
            level++
        } else {
            textStrikes.setTextColor(ContextCompat.getColor(context!!, R.color.light_red))
            strikes++
        }

        // game ended (user lost)
        if (strikes == 3){
            linearGameResult?.visibility = View.GONE

            val linearEndResult = view?.findViewById<LinearLayout>(R.id.chimp_linear_game_end)
            linearEndResult?.visibility = View.VISIBLE

            textScore.setText((currLevel-1).toString())

            level = 5
            strikes = 0
        }

        // game ended
        if (level == 22){
            linearGameResult?.visibility = View.GONE

            val linearEndResult = view?.findViewById<LinearLayout>(R.id.chimp_linear_game_end)
            linearEndResult?.visibility = View.VISIBLE

            textScore.setText((currLevel-1).toString())

            level = 5
            strikes = 0
        }

        textCurrentLevel.setText((currLevel-1).toString())
        textStrikes.setText("$strikes of 3")

        rightAnswer = ""

    }

    private fun generateRandomNumber() :Int {
        var randomNumber : Int
        do {
            randomNumber = (0 until 20).random()
        } while (selected.contains(randomNumber))

        selected.add(randomNumber)
        return randomNumber

    }

}