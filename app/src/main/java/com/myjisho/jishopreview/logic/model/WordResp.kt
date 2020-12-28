package com.myjisho.jishopreview.logic.model

import com.google.gson.annotations.SerializedName

data class WordResp(
    val slug: String,
    @SerializedName("is_common") val isCommon: Boolean,
    val tags: List<String>,
    val jlpt: List<String>,
    val japanese: List<Japanese>,
    val senses : List<Sense>
)

data class Japanese(val word: String, val reading: String)

data class Sense(
    @SerializedName("english_definitions") val englishDefinitions: List<String>,
    @SerializedName("parts_of_speech") val partsOfSpeech: List<String>,
    val links : List<Link>,
    val tags: List<String>,
    val restrictions: List<String>,
    @SerializedName("see_also") val seeAlso: List<String>,
    val antonyms: List<String>,
    val source: List<String>,
    val info: List<String>
)

data class Link(val text: String, val url: String)

data class Attribution(
    val jmdict : Boolean,
    val jmnedict : Boolean,
    val dbpedia : Boolean
)