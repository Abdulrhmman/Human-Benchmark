package com.abdelrhmanhsh.sharpenyourmind.ui

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.widget.AppCompatButton
import com.abdelrhmanhsh.sharpenyourmind.R
import com.abdelrhmanhsh.sharpenyourmind.data.Score
import com.abdelrhmanhsh.sharpenyourmind.util.Constants
import com.abdelrhmanhsh.sharpenyourmind.util.TimestampUtils

class VerbalMemoryFragment : BaseMainFragment(), GameplayHandler {

    var lives = 3
    var score = 0
    var seen_words = mutableListOf<String>()

    private lateinit var linearStartGame : LinearLayout
    private lateinit var linearGameplay : LinearLayout
    private lateinit var linearEndResult : LinearLayout

    private lateinit var btnStart : AppCompatButton
    private lateinit var btnSaveScore : AppCompatButton
    private lateinit var btnSeen : AppCompatButton
    private lateinit var btnNew : AppCompatButton
    private lateinit var textLives : TextView
    private lateinit var textScore : TextView
    private lateinit var textWord : TextView
    private lateinit var textResult : TextView
    private lateinit var btnTryAgain : AppCompatButton
    private lateinit var backImg : ImageView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_verbal_memory, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

        linearStartGame = view.findViewById(R.id.linear_start_game)
        linearGameplay = view.findViewById(R.id.linear_gameplay)
        linearEndResult = view.findViewById(R.id.linear_result)

        textLives = view.findViewById(R.id.text_lives)
        textScore = view.findViewById(R.id.text_score)
        textWord = view.findViewById(R.id.text_word)
        textResult = view.findViewById(R.id.text_result)

        btnStart = view.findViewById(R.id.btn_start)
        btnSeen = view.findViewById(R.id.btn_seen)
        btnNew = view.findViewById(R.id.btn_new)
        btnSaveScore = view.findViewById(R.id.btn_save_score)
        btnTryAgain = view.findViewById(R.id.btn_try_again)
        backImg = view.findViewById(R.id.back_btn)

        btnStart.setOnClickListener {
            handleGameplay()
        }

        btnTryAgain.setOnClickListener {
            handleTryAgain()
            btnSaveScore.isEnabled = true
        }

        btnSaveScore.setOnClickListener {
            handleSaveScore()
            btnSaveScore.isEnabled = false
        }

        backImg.setOnClickListener {
            navController.navigate(R.id.action_verbalMemoryFragment_to_mainFragment)
        }
    }

    override fun handleGameplay(){

        linearStartGame.visibility = View.GONE
        linearGameplay.visibility = View.VISIBLE

        textLives.setText("Lives | $lives")
        textScore.setText("Score | $score")

        var word = ""

        if (score <= 10){
            word = generateRandomWord10()

        } else if (score > 10 && score <= 30){
            word = generateRandomWord30()

        } else if (score > 30 && score <= 75){
            word = generateRandomWord75()

        } else {
            word = generateRandomWord()
        }

        textWord.setText(word)

        btnSeen.setOnClickListener {
            handleButtonSeen(word)
        }

        btnNew.setOnClickListener {
            handleButtonNew(word)
        }

        if (lives == 0){
            linearGameplay.visibility = View.GONE
            linearEndResult.visibility = View.VISIBLE

            textResult.setText("$score Words")
        }
    }

    private fun handleButtonSeen(word: String){
        if (seen_words.contains(word)){
            score++
        } else {
            lives--
        }

        seen_words.add(word)
        handleGameplay()
    }

    private fun handleButtonNew(word: String){
        if (!seen_words.contains(word)){
            score++
        } else {
            lives--
        }

        seen_words.add(word)
        handleGameplay()
    }

    private fun handleTryAgain(){
        lives = 3
        score = 0
        seen_words.clear()

        linearEndResult.visibility = View.GONE
        linearGameplay.visibility = View.VISIBLE

        handleGameplay()
    }

    override fun handleSaveScore(){

        val timestamp: String? = TimestampUtils.getCurrentTimestamp()

        val score = Score(
            getString(R.string.verbal_memory_fragment),
            score,
            timestamp!!
        )

        viewModel.insert(score)

        Toast.makeText(context, getString(R.string.score_saved), LENGTH_SHORT).show()

    }

    private fun generateRandomWord10(): String {
        val randomWord = Constants.VOCAB10.random()
        return randomWord
    }

    private fun generateRandomWord30(): String {
        val randomWord = Constants.VOCAB30.random()
        return randomWord
    }

    private fun generateRandomWord75(): String {
        val randomWord = Constants.VOCAB75.random()
        return randomWord
    }

    private fun generateRandomWord(): String {
        val randomWord = Constants.VOCABULARY.random()
        return randomWord
    }

}