//package com.abdelrahmman.humanbenchmark.util
//
//import android.content.Context
//import androidx.datastore.preferences.createDataStore
//import androidx.datastore.preferences.edit
//import androidx.datastore.preferences.preferencesKey
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.map
//
//class UserManager(context: Context) {
//
//    private val dataStore = context.createDataStore(name = "user_prefs")
//
//    companion object {
//
//        val REACTION_TIME_KEY = preferencesKey<Int>(Constants.REACTION_TIME_KEY)
//        val AIM_TRAINER_KEY = preferencesKey<Int>(Constants.AIM_TRAINER_KEY)
//        val NUMBER_MEMORY_KEY = preferencesKey<Int>(Constants.NUMBER_MEMORY_KEY)
//        val VERBAL_MEMORY_KEY = preferencesKey<Int>(Constants.VERBAL_MEMORY_KEY)
//        val CHIMP_TEST_KEY = preferencesKey<Int>(Constants.CHIMP_TEST_KEY)
//        val VISUAL_MEMORY_KEY = preferencesKey<Int>(Constants.VISUAL_MEMORY_KEY)
//
//    }
//
//    suspend fun storeReactionTime(average: Int){
//        dataStore.edit {
//            it[REACTION_TIME_KEY] = average
//        }
//    }
//
//    suspend fun storeAimTrainer(average: Int){
//        dataStore.edit {
//            it[AIM_TRAINER_KEY] = average
//        }
//    }
//
//    suspend fun storeNumberMemory(level: Int){
//        dataStore.edit {
//            it[NUMBER_MEMORY_KEY] = level
//        }
//    }
//
//    suspend fun storeVerbalMemory(words: Int){
//        dataStore.edit {
//            it[VERBAL_MEMORY_KEY] = words
//        }
//    }
//
//    suspend fun storeChimpTest(numbers: Int){
//        dataStore.edit {
//            it[CHIMP_TEST_KEY] = numbers
//        }
//    }
//
//    suspend fun storeVisualMemory(level: Int){
//        dataStore.edit {
//            it[VISUAL_MEMORY_KEY] = level
//        }
//    }
//
////    val aimTrainerFlow: Flow<Any> = dataStore.data.map {
////        val logos = it[AIM_TRAINER_KEY] ?: 0
////        logos
////    }
//
//}