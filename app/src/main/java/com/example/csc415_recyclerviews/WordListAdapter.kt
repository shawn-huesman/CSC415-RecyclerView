package com.example.csc415_recyclerviews

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class WordListAdapter(context: Context?, wordList: LinkedList<String>) : RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {
    private val mWordList: LinkedList<String> = wordList
    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordListAdapter.WordViewHolder {
        val mItemView: View = mInflater.inflate(R.layout.wordlist_item, parent, false)
        return WordViewHolder(mItemView, this)
    }

    override fun onBindViewHolder(holder: WordListAdapter.WordViewHolder, position: Int) {
        val mCurrent: String = mWordList.get(position)
        holder.wordItemView.text = mCurrent
    }

    override fun getItemCount(): Int {
        return mWordList.size;
    }

    inner class WordViewHolder(itemView: View, adapter: WordListAdapter) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val wordItemView: TextView = itemView.findViewById(R.id.word)
        private val mAdapter: WordListAdapter = adapter

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val mPosition: Int = layoutPosition
            val element: String = mWordList[mPosition]
            mWordList[mPosition] = "Clicked! $element"
            mAdapter.notifyDataSetChanged()
        }
    }
}