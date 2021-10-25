package com.example.csc415_recyclerviews

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.csc415_recyclerviews.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: WordListAdapter
    private var mWordList: LinkedList<String> = LinkedList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener { view ->
            val wordListSize: Int = mWordList.size
            mWordList.addLast("+ Word $wordListSize")
            mRecyclerView.adapter?.notifyItemInserted(wordListSize)
            mRecyclerView.smoothScrollToPosition(wordListSize)
        }

        populateLinkedList(mWordList)
        connectRecyclerAdapter()
    }

    private fun connectRecyclerAdapter() {
        mRecyclerView = findViewById(R.id.recyclerview)
        mAdapter = WordListAdapter(this, mWordList)
        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun populateLinkedList(list: LinkedList<String>) {
        for (i in 0..19) {
            list.add("Word $i")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_reset -> {
                mWordList.clear()
                populateLinkedList(mWordList)

                mRecyclerView.adapter?.notifyDataSetChanged()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}