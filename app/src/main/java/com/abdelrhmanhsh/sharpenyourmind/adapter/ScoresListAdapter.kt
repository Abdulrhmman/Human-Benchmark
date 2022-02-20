package com.abdelrhmanhsh.sharpenyourmind.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.abdelrhmanhsh.sharpenyourmind.R
import com.abdelrhmanhsh.sharpenyourmind.data.Score
import com.abdelrhmanhsh.sharpenyourmind.util.TimestampUtils

class ScoresListAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Score>() {

        override fun areItemsTheSame(oldItem: Score, newItem: Score): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Score, newItem: Score): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return ScoresViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.scores_list_item,
                parent,
                false
            ),
            interaction
        )

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ScoresViewHolder -> {
                holder.bind(differ.currentList.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun getScoreAt(position: Int): Score {
        return differ.currentList.get(position)
    }

    fun submitList(list: List<Score>) {
        differ.submitList(list)
    }

    class ScoresViewHolder
    constructor(
        itemView: View,
        private val interaction: Interaction?,
    ) : RecyclerView.ViewHolder(itemView) {

        lateinit var img: ImageView
        lateinit var game: TextView
        lateinit var score: TextView
        lateinit var timestamp: TextView

        fun bind(item: Score) = with(itemView) {
            itemView.setOnClickListener {
                interaction?.onItemSelected(bindingAdapterPosition, item)
            }

            img = itemView.findViewById(R.id.img_game)
            game = itemView.findViewById(R.id.text_game)
            score = itemView.findViewById(R.id.text_score)
            timestamp = itemView.findViewById(R.id.text_timestamp)

            if(item.game.equals("Aim Trainer")) {
                img.setBackgroundResource(R.drawable.accuracy)
                score.text = item.score.toString() + " ms"

            } else if (item.game.equals("Chimp Test")){
                img.setBackgroundResource(R.drawable.chimp)
                score.text = item.score.toString() + " numbers"

            } else if (item.game.equals("Number Memory")) {
                img.setBackgroundResource(R.mipmap.numbers)
                score.text = item.score.toString() + " digits"

            } else if (item.game.equals("Reaction Time")) {
                img.setBackgroundResource(R.mipmap.clock)
                score.text = item.score.toString() + " ms"

            } else if (item.game.equals("Verbal Memory")) {
                img.setBackgroundResource(R.drawable.book)
                score.text = item.score.toString() + " words"

            } else if (item.game.equals("Visual Memory")) {
                img.setBackgroundResource(R.drawable.squares)
                score.text = item.score.toString() + " points"

            } else {
                return
            }

            game.text = item.game

            val day: String = item.timestamp.substring(0, 2)
            var month: String = item.timestamp.substring(3, 5)
            month = TimestampUtils.getMonthFromNumber(month)
            val year: String = item.timestamp.substring(6)
            val convertedTimestamp = "$day $month $year"
            timestamp.text = convertedTimestamp

        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: Score)
    }
}