package com.myjisho.jishopreview.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.myjisho.jishopreview.R
import com.myjisho.jishopreview.logic.model.Word
import kotlinx.android.synthetic.main.activity_word_detail.*
import kotlinx.android.synthetic.main.detail.*
import kotlinx.android.synthetic.main.senses.*
import kotlinx.android.synthetic.main.senses_item.*

class WordDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_detail)

        val word = intent.getSerializableExtra("word") as Word
        showWordDetail(word)
    }

    private fun showWordDetail(word: Word) {
        slug.text = word.slug
        val readingText = "${word.japanese[0].word} (${word.japanese[0].reading})"
        reading.text = word.japanese[0].reading
        val jlptText = word.jlpt.joinToString(" | ")
        jlpt.text = jlptText

        sensesLayout.removeAllViews()
        for (item in word.senses) {
            val view = LayoutInflater.from(this).inflate(R.layout.senses_item, sensesLayout, false)
            val partsOfSpeech = view.findViewById<TextView>(R.id.partsOfSpeech)
            val enDefinition = view.findViewById<TextView>(R.id.enDefinition)
            partsOfSpeech.text = item.partsOfSpeech[0]
            enDefinition.text = item.englishDefinitions[0]
            sensesLayout.addView(view)
        }
        wordDetailLayout.visibility = View.VISIBLE
    }
}