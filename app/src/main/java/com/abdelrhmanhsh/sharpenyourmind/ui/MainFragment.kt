package com.abdelrhmanhsh.sharpenyourmind.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.abdelrhmanhsh.sharpenyourmind.R

class MainFragment : BaseMainFragment(), View.OnClickListener {


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<LinearLayout>(R.id.reaction_time).setOnClickListener(this)
        view.findViewById<LinearLayout>(R.id.aim_trainer).setOnClickListener(this)
        view.findViewById<LinearLayout>(R.id.number_memory).setOnClickListener(this)
        view.findViewById<LinearLayout>(R.id.verbal_memory).setOnClickListener(this)
        view.findViewById<LinearLayout>(R.id.chimp_test).setOnClickListener(this)
        view.findViewById<LinearLayout>(R.id.visual_memory).setOnClickListener(this)
        view.findViewById<LinearLayout>(R.id.show_scores).setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v!!.id) {
            R.id.reaction_time -> navController.navigate(R.id.action_mainFragment_to_reactionTimeFragment)
            R.id.aim_trainer -> navController.navigate(R.id.action_mainFragment_to_aimTrainerFragment)
            R.id.number_memory -> navController.navigate(R.id.action_mainFragment_to_numberMemoryFragment)
            R.id.verbal_memory -> navController.navigate(R.id.action_mainFragment_to_verbalMemoryFragment)
            R.id.chimp_test -> navController.navigate(R.id.action_mainFragment_to_chimpTestFragment)
            R.id.visual_memory -> navController.navigate(R.id.action_mainFragment_to_visualMemoryFragment)
            R.id.show_scores -> navController.navigate(R.id.action_mainFragment_to_showScoresFragment)
        }
    }

}