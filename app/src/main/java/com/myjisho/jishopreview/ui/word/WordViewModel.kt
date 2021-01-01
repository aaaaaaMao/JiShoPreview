package com.myjisho.jishopreview.ui.word

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.myjisho.jishopreview.logic.Repository
import com.myjisho.jishopreview.logic.model.Word

class WordViewModel : ViewModel() {

    private val searchLiveData = MutableLiveData<String>()

    val wordList = ArrayList<Word>()

    val wordLiveData = Transformations.switchMap(searchLiveData) {
        query -> Repository.searchWords(query)
    }

    fun searchWords(query: String) {
        searchLiveData.value = query
    }
}