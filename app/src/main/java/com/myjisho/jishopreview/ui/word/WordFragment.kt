package com.myjisho.jishopreview.ui.word

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.myjisho.jishopreview.R
import kotlinx.android.synthetic.main.fragment_word.*

class WordFragment : Fragment() {

    val viewModel by lazy { ViewModelProvider(this).get(WordViewModel::class.java)}

    private lateinit var adapter: WordAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_word, container, false)
    }

    private fun initViewVisibility() {
        recyclerView.visibility = View.GONE
        bgImageView.visibility = View.VISIBLE
        searchProgressView.visibility = View.GONE
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
        adapter = WordAdapter(this, viewModel.wordList)
        recyclerView.adapter = adapter
        searchWordEdit.addTextChangedListener { text: Editable? ->
            val content = text.toString()
            if (content.isNotEmpty()) {
                searchProgressView.visibility = View.VISIBLE
                viewModel.searchWords(content)
            } else {
                recyclerView.visibility = View.GONE
                bgImageView.visibility = View.VISIBLE
                viewModel.wordList.clear()
                adapter.notifyDataSetChanged()
            }
        }
        viewModel.wordLiveData.observe(this, Observer { result ->
            val words = result.getOrNull()
            cleanEdit.visibility = View.VISIBLE
            if (words !== null && words.isNotEmpty()) {
                recyclerView.visibility = View.VISIBLE
                bgImageView.visibility = View.GONE
                searchProgressView.visibility = View.GONE
                viewModel.wordList.clear()
                viewModel.wordList.addAll(words)
                adapter.notifyDataSetChanged()
            } else {
                initViewVisibility()
                Toast.makeText(activity, "未能查到该单词", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })

        cleanEdit.setOnClickListener {
            searchWordEdit.text.clear()
            viewModel.wordList.clear()
            initViewVisibility()
            cleanEdit.visibility = View.GONE
        }

    }
}