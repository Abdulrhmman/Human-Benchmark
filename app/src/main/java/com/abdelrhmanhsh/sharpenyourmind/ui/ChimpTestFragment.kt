package com.abdelrhmanhsh.sharpenyourmind.ui

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.*
import android.widget.*
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.core.view.get
import com.abdelrhmanhsh.sharpenyourmind.R
import com.abdelrhmanhsh.sharpenyourmind.data.Score
import com.abdelrhmanhsh.sharpenyourmind.util.TimestampUtils
import java.lang.NullPointerException

class ChimpTestFragment : BaseMainFragment(), GameplayHandler {

    var level: Int = 5
    var selected = mutableListOf<Int>()
    var pressCounter: Int = 0
    var userAnswer: String = ""
    var rightAnswer: String = ""
    var map = mutableMapOf<Int, Int>()
    var strikes: Int = 0

    private lateinit var linearStartGame : LinearLayout
    private lateinit var linearGameplay : LinearLayout
    private lateinit var linearResult : LinearLayout
    private lateinit var linearEndResult : LinearLayout

    private lateinit var gridLayout: GridLayout
    private lateinit var btnStart : AppCompatButton
    private lateinit var backImg : ImageView
    private lateinit var textCurrentLevel : TextView
    private lateinit var textStrikes : TextView
    private lateinit var textScore : TextView
    private lateinit var btnContinue : AppCompatButton
    private lateinit var btnTryAgain : AppCompatButton
    private lateinit var btnSaveScore : AppCompatButton

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chimp_test, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

        linearStartGame = view.findViewById(R.id.linear_start_game)
        linearGameplay = view.findViewById(R.id.linear_gameplay)
        linearResult = view.findViewById(R.id.linear_result)
        linearEndResult = view.findViewById(R.id.linear_game_end)

        gridLayout = view.findViewById(R.id.chimp_grid)
        btnStart = view.findViewById(R.id.btn_start)
        textCurrentLevel = view.findViewById(R.id.text_current_level)
        textStrikes = view.findViewById(R.id.text_strikes)
        textScore = view.findViewById(R.id.text_score)
        backImg = view.findViewById(R.id.back_btn)
        btnContinue = view.findViewById(R.id.btn_continue)
        btnTryAgain = view.findViewById(R.id.btn_try_again)
        btnSaveScore = view.findViewById(R.id.btn_save_score)

        btnStart.setOnClickListener {

            linearStartGame.visibility = View.GONE
            linearGameplay.visibility = View.VISIBLE

            handleGameplay()
        }

        btnContinue.setOnClickListener {
            linearResult.visibility = View.GONE
            linearGameplay.visibility = View.VISIBLE

            handleGameplay()
        }

        btnTryAgain.setOnClickListener {
            linearEndResult.visibility = View.GONE
            linearGameplay.visibility = View.VISIBLE
            handleGameplay()

            btnSaveScore.isEnabled = true
        }

        backImg.setOnClickListener {
            navController.navigate(R.id.action_chimpTestFragment_to_mainFragment)
        }

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
        gridLayout.get(position!!).animate().alpha(1f).duration = 0
    }

    private fun validateAnswer(
            currLevel: Int,
            userAnswer: String
    ){
        linearGameplay.visibility = View.GONE
        linearResult.visibility = View.VISIBLE

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

        // game ended (player lost)
        if (strikes == 3){
            linearResult.visibility = View.GONE
            linearEndResult.visibility = View.VISIBLE

            textScore.setText((currLevel-1).toString())

            level = 5
            strikes = 0
        }

        // game ended
        if (level == 22){
            linearResult.visibility = View.GONE
            linearEndResult.visibility = View.VISIBLE

            textScore.setText((currLevel-1).toString())

            level = 5
            strikes = 0
        }

        textCurrentLevel.setText((currLevel-1).toString())
        textStrikes.setText("$strikes of 3")

        rightAnswer = ""

        btnSaveScore.setOnClickListener {
            handleSaveScore()
            btnSaveScore.isEnabled = false
        }

    }

    private fun generateRandomNumber() :Int {
        var randomNumber : Int
        do {
            randomNumber = (0 until 20).random()
        } while (selected.contains(randomNumber))

        selected.add(randomNumber)
        return randomNumber

    }


    override fun handleGameplay(){

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

    override fun handleSaveScore() {

        val timestamp: String? = TimestampUtils.getCurrentTimestamp()

        val score = Score(
            getString(R.string.chimp_test_fragment),
            level-1,
            timestamp!!
        )

        viewModel.insert(score)

        Toast.makeText(context, getString(R.string.score_saved), LENGTH_SHORT).show()

    }

}