package com.abdelrahmman.humanbenchmark.ui

import android.os.*
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.abdelrahmman.humanbenchmark.R


class ReactionTimeFragment : Fragment(), View.OnClickListener {

    // TODO: Save Score

    var millisStart: Long = 0
    var millisFinish: Long = 0
    var reactionMillis: Long = 0
    var reactionMillisAll: Long = 0
    var reactionMillisAverage: Long = 0
    var tries: Int = 0

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

        view.findViewById<LinearLayout>(R.id.start_game).setOnClickListener(this)
        view.findViewById<LinearLayout>(R.id.gameplay_red).setOnClickListener(this)
        view.findViewById<LinearLayout>(R.id.gameplay_green).setOnClickListener(this)
        view.findViewById<LinearLayout>(R.id.gameplay_too_soon).setOnClickListener(this)
        view.findViewById<LinearLayout>(R.id.gameplay_keep_going).setOnClickListener(this)
        view.findViewById<LinearLayout>(R.id.gameplay_result_reaction).setOnClickListener(this)

        btnSaveScore.setOnClickListener {
            // TODO
        }

        btnTryAgain.setOnClickListener {
            handleTryAgain()
        }

    }

    private fun startGame(){

        val linearStartGame = view?.findViewById<LinearLayout>(R.id.start_game)
        linearStartGame?.visibility = View.GONE

        val linearRed = view?.findViewById<LinearLayout>(R.id.gameplay_red)
        linearRed?.visibility = View.VISIBLE

        handleGameplay()

    }

    private fun handleGameplay(){

        if (tries < 5){

            val randomTimer : Long = (3000 until 6000).random().toLong()

            Handler(Looper.getMainLooper()).postDelayed({
                val linearRed = view?.findViewById<LinearLayout>(R.id.gameplay_red)
                linearRed?.visibility = View.GONE

                val linearGreen = view?.findViewById<LinearLayout>(R.id.gameplay_green)
                linearGreen?.visibility = View.VISIBLE
                tries++

                // start milli counter
                millisStart = System.currentTimeMillis()

            }, randomTimer)

        } else {

            val linearRed = view?.findViewById<LinearLayout>(R.id.gameplay_red)
            linearRed?.visibility = View.GONE

            val linearEndResult = view?.findViewById<LinearLayout>(R.id.gameplay_result_reaction)
            linearEndResult?.visibility = View.VISIBLE

            textMillisTotal.setText("Total: $reactionMillisAll ms")

            reactionMillisAverage = reactionMillisAll / 5

            textMillisAverage.setText("Average: $reactionMillisAverage ms")

        }
    }

    private fun tooSoon(){
        val linearRed = view?.findViewById<LinearLayout>(R.id.gameplay_red)
        linearRed?.visibility = View.GONE

        val linearGreen = view?.findViewById<LinearLayout>(R.id.gameplay_green)
        linearGreen?.visibility = View.GONE

        val linearTooSoon = view?.findViewById<LinearLayout>(R.id.gameplay_too_soon)
        linearTooSoon?.visibility = View.VISIBLE


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
        val linearKeepGoing = view?.findViewById<LinearLayout>(R.id.gameplay_keep_going)
        linearKeepGoing?.visibility = View.GONE

        val linearRed = view?.findViewById<LinearLayout>(R.id.gameplay_red)
        linearRed?.visibility = View.VISIBLE

        handleGameplay()
    }

    private fun handleTooSoon(){
        val linearTooSoon = view?.findViewById<LinearLayout>(R.id.gameplay_too_soon)
        linearTooSoon?.visibility = View.GONE

        val linearGreen = view?.findViewById<LinearLayout>(R.id.gameplay_green)
        linearGreen?.visibility = View.GONE

        val linearRed = view?.findViewById<LinearLayout>(R.id.gameplay_red)
        linearRed?.visibility = View.VISIBLE

        handleGameplay()
    }

    private fun handleUserInteraction(){
        val linearGreen = view?.findViewById<LinearLayout>(R.id.gameplay_green)
        linearGreen?.visibility = View.GONE

        val linearKeepGoing = view?.findViewById<LinearLayout>(R.id.gameplay_keep_going)
        linearKeepGoing?.visibility = View.VISIBLE

        textTriesKeepGoing.setText("Tries: " + tries.toString() + " of 5")

        millisFinish = System.currentTimeMillis()
        reactionMillis = millisFinish - millisStart
        reactionMillisAll += reactionMillis

        textMillis.setText("$reactionMillis ms")

    }

    private fun handleTryAgain(){
        tries = 0
        reactionMillisAll = 0

        val linearEndResult = view?.findViewById<LinearLayout>(R.id.gameplay_result_reaction)
        linearEndResult?.visibility = View.GONE

        Handler(Looper.getMainLooper()).postDelayed({
            startGame()
        }, 200)

    }

    override fun onClick(v: View?) {
        when(v!!.id) {
            R.id.start_game -> startGame()
            R.id.gameplay_red -> tooSoon()
            R.id.gameplay_keep_going -> handleKeepGoing()
            R.id.gameplay_green -> handleUserInteraction()
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