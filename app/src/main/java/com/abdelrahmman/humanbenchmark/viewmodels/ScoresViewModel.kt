package com.abdelrahmman.humanbenchmark.viewmodels

import androidx.lifecycle.*
import com.abdelrahmman.humanbenchmark.data.Scores
import com.abdelrahmman.humanbenchmark.data.ScoresRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class ScoresViewModel
@Inject constructor(
    private val repository: ScoresRepository
): ViewModel() {

    val allScores: LiveData<List<Scores>>

    fun insert(scores: Scores) = viewModelScope.launch {
        repository.insert(scores)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }

    init {
        allScores = repository.scores
    }

}
