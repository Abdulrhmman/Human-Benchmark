package com.abdelrahmman.humanbenchmark.ui

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.abdelrahmman.humanbenchmark.R
import java.lang.NullPointerException

class VisualMemoryFragment : Fragment() {

    // TODO: Save Score

    private var squares: Int = 3
    private var blocks: Int = 0
    private var dimen: Int = 0
    private var rightAttempts: Int = 0
    private var wrongAttempts: Int = 0
    private var level: Int = 1
    private var lives: Int = 3
    var selected = mutableListOf<Int>()

    private lateinit var gridLayout: GridLayout
    private lateinit var btnStart : AppCompatButton
    private lateinit var textCurrentLevel : TextView
    private lateinit var textLives: TextView
    private lateinit var textScore: TextView
    private lateinit var btnSaveScore : AppCompatButton
    private lateinit var btnTryAgain : AppCompatButton
    private lateinit var linearStartGame : LinearLayout
    private lateinit var linearGameplay : LinearLayout
    private lateinit var linearTexts : LinearLayout
    private lateinit var linearEndResult : LinearLayout

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_visual_memory, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

        gridLayout = view.findViewById(R.id.visual_memory_grid)
        btnStart = view.findViewById(R.id.btn_start)
        textCurrentLevel = view.findViewById(R.id.text_level)
        textLives = view.findViewById(R.id.text_lives)
        textScore = view.findViewById(R.id.text_score)
        btnSaveScore = view.findViewById(R.id.btn_save_score)
        btnTryAgain = view.findViewById(R.id.btn_try_again)
        linearStartGame = view.findViewById(R.id.visual_start_game)
        linearGameplay = view.findViewById(R.id.gameplay_visual_memory)
        linearTexts = view.findViewById(R.id.visual_memory_texts)
        linearEndResult = view.findViewById(R.id.visual_memory_game_end)


        btnStart.setOnClickListener {

            handleGameplay()

        }

    }

    private fun handleGameplay(){

        gridLayout.removeAllViews()
        gridLayout.isClickable = true
        gridLayout.isFocusable = true

        rightAttempts = 0
        wrongAttempts = 0

        linearStartGame.visibility = View.GONE
        linearTexts.visibility = View.VISIBLE
        linearGameplay.visibility = View.VISIBLE

        if (level in 1 .. 2){

            blocks = 9
            gridLayout.columnCount = 3
            dimen = 200

            for (i in 0 until blocks){

                addGridView(
                        gridLayout,
                        i
                )
            }

        } else if (level in 3 .. 5){

            blocks = 16
            gridLayout.columnCount = 4
            dimen = 200

            for (i in 0 until blocks){

                addGridView(
                        gridLayout,
                        i
                )
            }

        } else if (level in 6 .. 8){

            blocks = 25
            gridLayout.columnCount = 5
            dimen = 175

            for (i in 0 until blocks){

                addGridView(
                        gridLayout,
                        i
                )
            }

        } else if (level in 9 .. 13){

            blocks = 36
            gridLayout.columnCount = 6
            dimen = 150

            for (i in 0 until blocks){

                addGridView(
                        gridLayout,
                        i
                )
            }

        } else if (level in 14 .. 18){

            blocks = 49
            gridLayout.columnCount = 7
            dimen = 125

            for (i in 0 until blocks){

                addGridView(
                        gridLayout,
                        i
                )
            }

        } else if (level in 19 .. 22){

            blocks = 64
            gridLayout.columnCount = 8
            dimen = 100

            for (i in 0 until blocks){

                addGridView(
                        gridLayout,
                        i
                )
            }

        }

        if (level < 23){

            try {

                for (x in 0 until squares){
                    val randomNumber: Int = generateRandomNumber()

                    showSquares(
                            gridLayout,
                            randomNumber
                    )
                }

            } catch (e: NullPointerException){
                println(e.message)
            }

            textCurrentLevel.setText("Level: $level")
            textLives.setText("Lives: $lives")

        }

        if (level == 23){
            handleGameEnd()
        }

    }

    private fun addGridView(
            gridLayout: GridLayout,
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

        val block = TextView(context)
        block.layoutParams = linearLayoutParams
        block.background = this.resources.getDrawable(R.drawable.rounded_dark_block)
        block.width = dimen
        block.height = dimen
        gridLayout.addView(block)

        squareClick(
                block,
                position
        )

    }


    private fun showSquares(
            gridLayout: GridLayout,
            position: Int?
    ){

        Handler(Looper.getMainLooper()).postDelayed({
            gridLayout.get(position!!).background = this.resources.getDrawable(R.drawable.rounded_white_block)
        }, 500)

        Handler(Looper.getMainLooper()).postDelayed({
            gridLayout.get(position!!).background = this.resources.getDrawable(R.drawable.rounded_dark_block)
        }, 1500)

    }

    private fun squareClick(
            block: TextView,
            position: Int
    ) {
        block.setOnClickListener {

            if (selected.contains(position)){
                gridLayout.get(position).background = this.resources.getDrawable(R.drawable.rounded_white_block)
                rightAttempts++
            } else {
                gridLayout.get(position).background = this.resources.getDrawable(R.drawable.rounded_black_block)
                wrongAttempts++
            }

            block.isClickable = false
            block.isFocusable = false

            if (lives > 0){

                if (rightAttempts == squares){
                    level++
                    squares++
                    selected.clear()

                    gridLayout.isEnabled = false

                    Handler(Looper.getMainLooper()).postDelayed({
                        handleGameplay()
                    }, 500)
                }

                if (wrongAttempts == 3){
                    lives--
                    selected.clear()

                    if (lives > 0){
                        Handler(Looper.getMainLooper()).postDelayed({
                            handleGameplay()
                        }, 500)

                    }

                }

            }

            if (lives == 0){
                handleGameEnd()
            }

        }
    }

    private fun handleGameEnd(){

        linearTexts.visibility = View.GONE
        linearGameplay.visibility = View.GONE
        linearEndResult.visibility = View.VISIBLE

        selected.clear()

        squares = 3
        lives = 3

        textScore.setText("Level: $level")

        btnTryAgain.setOnClickListener {
            linearEndResult.visibility = View.GONE
            level = 1
            handleGameplay()
        }

    }

    private fun generateRandomNumber() :Int {
        var randomNumber : Int
        do {
            randomNumber = (0 until blocks).random()
        } while (selected.contains(randomNumber))

        selected.add(randomNumber)
        return randomNumber

    }

}