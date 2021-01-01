package com.myjisho.jishopreview.logic

import androidx.lifecycle.liveData
import com.myjisho.jishopreview.logic.model.Word
import com.myjisho.jishopreview.logic.network.JiShoNetwork
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import java.lang.RuntimeException

object Repository {

    fun searchWords(query: String) = liveData(Dispatchers.IO) {
        val result = try {
            val wordResp = JiShoNetwork.searchWords(query)
            if (wordResp.meta.status == "200") {
                val words = wordResp.words
                Result.success(words)
            } else {
                Result.failure(RuntimeException(
                    "response status is ${wordResp.meta.status}"
                ))
            }
        } catch (e: Exception) {
            Result.failure<List<Word>>(e)
        }
        emit(result)
    }
}