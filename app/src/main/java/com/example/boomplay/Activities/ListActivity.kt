package com.example.boomplay.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.boomplay.R
import com.paxees.wastatussaver.fragments.MyListFragment


class ListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        openList()
    }

    fun openList() {
        val nextFrag = MyListFragment()
        getSupportFragmentManager().beginTransaction()
            .replace(R.id.layoutContainer, nextFrag, "findThisFragment")
            .addToBackStack(null)
            .commit()
    }
}
