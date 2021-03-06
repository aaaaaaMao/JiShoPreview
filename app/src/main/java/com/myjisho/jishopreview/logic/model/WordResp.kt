package com.myjisho.jishopreview.logic.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class WordResp(
    val meta: Meta,
    @SerializedName("data") val words: List<Word>
)

data class Meta(
    val status : String
)

data class Word (
    val slug: String,
    @SerializedName("is_common") val isCommon: Boolean,
    val tags: List<String>,
    val jlpt: List<String>,
    val japanese: List<Japanese>,
    val senses : List<Sense>,
    val attribution : Attribution
) : Serializable

data class Japanese(val word: String, val reading: String) : Serializable

data class Sense(
    @SerializedName("english_definitions") val englishDefinitions: List<String>,
    @SerializedName("parts_of_speech") val partsOfSpeech: List<String>,
    val links : List<Link>,
    val tags: List<String>,
    val restrictions: List<String>,
    @SerializedName("see_also") val seeAlso: List<String>,
    val antonyms: List<String>,
    val source: List<Source>,
    val info: List<String>
) : Serializable

data class Link(val text: String, val url: String) : Serializable

data class Source(val language: String, val word: String) : Serializable

data class Attribution(
    val jmdict : Boolean,
    val jmnedict : Boolean,
    val dbpedia : Boolean
) : Serializable