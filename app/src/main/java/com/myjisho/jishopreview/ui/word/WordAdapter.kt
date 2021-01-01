package com.myjisho.jishopreview.ui.word

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.myjisho.jishopreview.R
import com.myjisho.jishopreview.logic.model.Word

class WordAdapter(private val fragment: Fragment, private val wordList: List<Word>) :
    RecyclerView.Adapter<WordAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val word: TextView = view.findViewById(R.id.word)
        val reading : TextView = view.findViewById(R.id.reading)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.word_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val word = wordList[position]
        holder.word.text = word.japanese[0].word
        holder.reading.text = word.japanese[0].reading
    }

    override fun getItemCount() = wordList.size
}