package com.abdelrahmman.humanbenchmark.ui

import android.content.pm.ActivityInfo
import android.os.*
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.abdelrahmman.humanbenchmark.R
import com.abdelrahmman.humanbenchmark.data.Scores
import com.abdelrahmman.humanbenchmark.util.TimestampUtils

class ReactionTimeFragment : BaseMainFragment(), View.OnClickListener {

    var millisStart: Long = 0
    var millisFinish: Long = 0
    var reactionMillis: Long = 0
    var reactionMillisAll: Long = 0
    var reactionMillisAverage: Long = 0
    var tries: Int = 0

    private lateinit var linearStartGame : LinearLayout
    private lateinit var linearGameplayRed : LinearLayout
    private lateinit var linearGameplayGreen : LinearLayout
    private lateinit var linearGameplayTooSoon : LinearLayout
    private lateinit var linearGameplayKeepGoing : LinearLayout
    private lateinit var linearResult : LinearLayout

    lateinit var textTriesTooSoon: TextView
    lateinit var timerText: TextView
    lateinit var textTriesKeepGoing: TextView
    lateinit var textMillis: TextView
    lateinit var textMillisTotal: TextView
    lateinit var textMillisAverage: TextView
    lateinit var btnSaveScore: AppCompatButton
    lateinit var btnTryAgain: AppCompatButton

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_reaction_time, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

        linearStartGame = view.findViewById(R.id.linear_start_game)
        linearGameplayRed = view.findViewById(R.id.linear_gameplay_red)
        linearGameplayGreen = view.findViewById(R.id.linear_gameplay_green)
        linearGameplayTooSoon = view.findViewById(R.id.linear_gameplay_too_soon)
        linearGameplayKeepGoing = view.findViewById(R.id.linear_gameplay_keep_going)
        linearResult = view.findViewById(R.id.linear_result)

        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            activity?.window?.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            activity?.window?.setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        textTriesTooSoon = view.findViewById(R.id.text_tries_too_soon)
        timerText = view.findViewById(R.id.timer_text)
        textTriesKeepGoing = view.findViewById(R.id.text_tries_keep_going)
        textMillis = view.findViewById(R.id.text_result)
        textMillisTotal = view.findViewById(R.id.finished_result_total)
        textMillisAverage = view.findViewById(R.id.finished_result_average)
        btnSaveScore = view.findViewById(R.id.btn_save_score)
        btnTryAgain = view.findViewById(R.id.btn_try_again)

        view.findViewById<LinearLayout>(R.id.linear_start_game).setOnClickListener(this)
        view.findViewById<LinearLayout>(R.id.linear_gameplay_red).setOnClickListener(this)
        view.findViewById<LinearLayout>(R.id.linear_gameplay_green).setOnClickListener(this)
        view.findViewById<LinearLayout>(R.id.linear_gameplay_too_soon).setOnClickListener(this)
        view.findViewById<LinearLayout>(R.id.linear_gameplay_keep_going).setOnClickListener(this)
        view.findViewById<LinearLayout>(R.id.linear_result).setOnClickListener(this)

        btnTryAgain.setOnClickListener {
            handleTryAgain()
            btnSaveScore.isEnabled = true
        }

        btnSaveScore.setOnClickListener {
            handleSaveScore()
            btnSaveScore.isEnabled = false
        }

    }

    private fun startGame(){
        linearStartGame.visibility = View.GONE
        linearGameplayRed.visibility = View.VISIBLE

        handleGameplay()

    }

    private fun handleGameplay(){

        if (tries < 5){

            val randomTimer : Long = (3000 until 6000).random().toLong()

            Handler(Looper.getMainLooper()).postDelayed({
                linearGameplayRed.visibility = View.GONE
                linearGameplayGreen.visibility = View.VISIBLE
                tries++

                // start milli counter
                millisStart = System.currentTimeMillis()

            }, randomTimer)

        } else {
            linearGameplayRed.visibility = View.GONE
            linearResult.visibility = View.VISIBLE

            textMillisTotal.setText("Total: $reactionMillisAll ms")

            reactionMillisAverage = reactionMillisAll / 5

            textMillisAverage.setText("Average: $reactionMillisAverage ms")

        }
    }

    private fun tooSoon(){
        linearGameplayRed.visibility = View.GONE
        linearGameplayGreen.visibility = View.GONE
        linearGameplayTooSoon.visibility = View.VISIBLE

        textTriesTooSoon.setText("Tries: $tries of 5")

        object : CountDownTimer(6000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                if ((millisUntilFinished / 1000).toInt() == 0){
                    timerText.setText("Hold on")
                } else {
                    timerText.setText("Hold on " + millisUntilFinished / 1000)
                }

            }

            override fun onFinish() {
                handleTooSoon()
                tries--
            }

        }.start()
    }

    private fun handleKeepGoing(){
        linearGameplayKeepGoing.visibility = View.GONE
        linearGameplayRed.visibility = View.VISIBLE

        handleGameplay()
    }

    private fun handleTooSoon(){
        linearGameplayTooSoon.visibility = View.GONE
        linearGameplayGreen.visibility = View.GONE
        linearGameplayRed.visibility = View.VISIBLE

        handleGameplay()
    }

    private fun handleUserInteraction(){
        linearGameplayGreen.visibility = View.GONE
        linearGameplayKeepGoing.visibility = View.VISIBLE

        textTriesKeepGoing.setText("Tries: " + tries.toString() + " of 5")

        millisFinish = System.currentTimeMillis()
        reactionMillis = millisFinish - millisStart
        reactionMillisAll += reactionMillis

        textMillis.setText("$reactionMillis ms")

    }

    private fun handleTryAgain(){
        tries = 0
        reactionMillisAll = 0

        linearResult.visibility = View.GONE

        Handler(Looper.getMainLooper()).postDelayed({
            startGame()
        }, 200)

    }

    private fun handleSaveScore(){

        val timestamp: String? = TimestampUtils.getCurrentTimestamp()

        val score = Scores(
            getString(R.string.reaction_time_fragment),
            reactionMillisAverage.toInt(),
            timestamp!!
        )

        viewModel.insert(score)

        Toast.makeText(context, getString(R.string.score_saved), LENGTH_SHORT).show()

    }

    override fun onClick(v: View?) {
        when(v!!.id) {
            R.id.linear_start_game -> startGame()
            R.id.linear_gameplay_red -> tooSoon()
            R.id.linear_gameplay_keep_going -> handleKeepGoing()
            R.id.linear_gameplay_green -> handleUserInteraction()
            R.id.btn_save_score -> handleGameplay()
        }
    }

    override fun onStop() {
        super.onStop()

        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            activity?.window?.insetsController?.show(WindowInsets.Type.statusBars())
        } else {
            activity?.window?.clearFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

    }
}