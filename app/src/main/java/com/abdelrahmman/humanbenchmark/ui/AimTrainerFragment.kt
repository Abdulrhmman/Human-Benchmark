package com.abdelrahmman.humanbenchmark.ui

import android.content.pm.ActivityInfo
import android.media.Image
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.abdelrahmman.humanbenchmark.R
import com.abdelrahmman.humanbenchmark.util.screenRectDp


class AimTrainerFragment : Fragment() {

    // TODO: Save Score
    var remaining: Int = 30
    var widthDp: Float = 0F
    var heightDp: Float = 0F
    var millisStart: Long = 0
    var millisFinish: Long = 0
    var millisAll: Long = 0
    var millisAverage: Long = 0

    private lateinit var imgStart : ImageView
    private lateinit var target: ImageView
    private lateinit var btnSaveScore : AppCompatButton
    private lateinit var linearStartGame : LinearLayout
    private lateinit var linearGameplay : LinearLayout
    private lateinit var linearAimRemaining : LinearLayout
    private lateinit var linearEndResult : LinearLayout
    private lateinit var textRemaining : TextView
    private lateinit var textTotalTime : TextView
    private lateinit var textAverageTime : TextView
    private lateinit var btnTryAgain : AppCompatButton

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_aim_trainer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

        linearStartGame = view.findViewById(R.id.aim_start_game)
        linearGameplay = view.findViewById(R.id.aim_gameplay)
        linearAimRemaining = view.findViewById(R.id.aim_remaining)
        linearEndResult = view.findViewById(R.id.aim_linear_game_end)

        imgStart = view.findViewById(R.id.img_target_start_game)
        target = view.findViewById(R.id.img_target)
        btnSaveScore = view.findViewById(R.id.btn_save_score_aim)
        textRemaining = view.findViewById(R.id.text_remaining)
        textTotalTime = view.findViewById(R.id.text_time_aim)
        textAverageTime = view.findViewById(R.id.text_average_aim)
        btnTryAgain = view.findViewById(R.id.btn_try_again)

        widthDp = screenRectDp.width()
        heightDp = screenRectDp.height()

        imgStart.setOnClickListener {
            handleStartGame()
        }

        target.setOnClickListener {

            remaining--

            if (remaining == 0){
                handleGameEnded()
            } else {
                generateNewPosition()
                textRemaining.setText(remaining.toString())
                println(remaining)
            }

        }

        btnTryAgain.setOnClickListener {
            handleTryAgain()
        }


    }

    private fun handleStartGame(){
        linearStartGame.visibility = View.GONE
        linearGameplay.visibility = View.VISIBLE
        linearAimRemaining.visibility = View.VISIBLE

        // start milli counter
        millisStart = System.currentTimeMillis()

        textRemaining.setText(remaining.toString())
        generateNewPosition()

    }

    private fun handleGameEnded(){

        millisFinish = System.currentTimeMillis()
        millisAll = millisFinish - millisStart
        millisAverage = millisAll / 30

        linearGameplay.visibility = View.GONE
        linearAimRemaining.visibility = View.GONE
        linearEndResult.visibility = View.VISIBLE

        val millisConvert = millisAll.toDouble()
        val totalTime: Double = String.format("%.2f", (millisConvert / 1000)).toDouble()

        textTotalTime.setText("$totalTime s")
        textAverageTime.setText("$millisAverage ms")

    }

    private fun handleTryAgain(){
        remaining = 30

        linearEndResult.visibility = View.GONE
        linearGameplay.visibility = View.VISIBLE

        handleStartGame()
    }

    private fun generateNewPosition(){

        val startMarginLimit: Int = widthDp.toInt() - 100
        val topMarginLimit: Int = heightDp.toInt() - 340

        val startMarginRandom = (0 until startMarginLimit).random()
        val topMarginRandom = (0 until topMarginLimit).random()

        val startMarginInDp = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                startMarginRandom.toFloat(),
                resources.displayMetrics
        ).toInt()

        val topMarginInDp = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                topMarginRandom.toFloat(),
                resources.displayMetrics
        ).toInt()


        val linearLayoutParams = target.getLayoutParams() as LinearLayout.LayoutParams
        linearLayoutParams.setMargins(startMarginInDp, topMarginInDp, 0, 0)
        target.setLayoutParams(linearLayoutParams)

    }

}