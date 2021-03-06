package com.paxees.wastatussaver.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.boomplay.Dashboard
import com.example.boomplay.R
import com.paxees.wastatussaver.Adapter.RecyclerViewAdapter
import com.paxees.wastatussaver.Adapter.RecyclerViewAdapter2
import com.paxees.wastatussaver.Adapter.RecyclerViewAdapter3
import com.paxees.wastatussaver.Adapter.RecyclerViewAdapter4
import kotlinx.android.synthetic.main.fragment_library.*
import kotlinx.android.synthetic.main.fragment_music.*
import kotlinx.android.synthetic.main.fragment_videos.*
import kotlinx.android.synthetic.main.fragment_videos.recyclerView
import kotlinx.android.synthetic.main.fragment_videos.recyclerView2


class MusicFragment : Fragment() {
    val listString = ArrayList<String>()
    val listSquareString = ArrayList<Int>()
    val listImg = ArrayList<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_music, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        addList()
    }

    fun addList() {
        listString.clear()
        listString?.add("Local Music")
        listString?.add("Private FM")
        listString?.add("Favourite")
        listString?.add("Videos")
        listString?.add("Playlists")
        listString?.add("Downloads")
        listString?.add("Downloads")
        listString?.add("Downloads")
        listString?.add("Downloads")
        listString?.add("Downloads")
        listImg.clear()
        listImg?.add(R.mipmap.ic_history)
        listImg?.add(R.mipmap.ic_history)
        listImg?.add(R.mipmap.ic_history)
        listImg?.add(R.mipmap.ic_history)
        listImg?.add(R.mipmap.ic_history)
        listImg?.add(R.mipmap.ic_history)
        listImg?.add(R.mipmap.ic_history)
        listImg?.add(R.mipmap.ic_history)
        listImg?.add(R.mipmap.ic_history)
        listImg?.add(R.mipmap.ic_history)
        listSquareString?.add(R.drawable.poster1)
        listSquareString?.add(R.drawable.poster2)
        listSquareString?.add(R.drawable.poster3)
        listSquareString?.add(R.drawable.poster1)
        listSquareString?.add(R.drawable.poster2)
        listSquareString?.add(R.drawable.poster3)
        listSquareString?.add(R.drawable.poster1)
        listSquareString?.add(R.drawable.poster2)
        listSquareString?.add(R.drawable.poster3)
        listSquareString?.add(R.drawable.poster1)
    }

    @SuppressLint("WrongConstant")
    fun init() {
        val layoutManager = LinearLayoutManager(activity, LinearLayout.HORIZONTAL, false)
        recyclerView.setLayoutManager(layoutManager)
        var adapter = RecyclerViewAdapter2(listSquareString, listString, (activity as Dashboard))
        recyclerView.setAdapter(adapter)
        adapter.notifyDataSetChanged()
        val layoutManager2 = LinearLayoutManager(activity, LinearLayout.HORIZONTAL, false)
        recyclerView2.setLayoutManager(layoutManager2)
        var adapter2 = RecyclerViewAdapter4(listSquareString, listString, (activity as Dashboard))
        recyclerView2.setAdapter(adapter2)
        adapter2.notifyDataSetChanged()
        val layoutManager3 = LinearLayoutManager(activity, LinearLayout.HORIZONTAL, false)
        recyclerView3.setLayoutManager(layoutManager3)
        var adapter3 = RecyclerViewAdapter3(listSquareString, listString, (activity as Dashboard))
        recyclerView3.setAdapter(adapter3)
        adapter3.notifyDataSetChanged()

    }
}
