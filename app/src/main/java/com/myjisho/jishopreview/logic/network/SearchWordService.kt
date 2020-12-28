package com.myjisho.jishopreview.logic.network

import com.myjisho.jishopreview.logic.model.WordResp
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchWordService {
    @GET("api/v1/search/words")
    fun searchKeyword(@Query("keyword") keyword: String): Call<WordResp>
}