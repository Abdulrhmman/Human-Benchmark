package com.abdelrahmman.humanbenchmark.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "scores")
data class Scores(

    @ColumnInfo(name = "game")
    var game: String,

    @ColumnInfo(name = "score")
    var score: Int,

    @ColumnInfo(name = "timestamp")
    var timestamp: String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0

) {

    override fun toString(): String {
        return "Scores(game='$game', score=$score, timestamp='$timestamp', id=$id)"
    }
}