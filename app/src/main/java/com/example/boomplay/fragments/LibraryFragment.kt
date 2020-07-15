package com.paxees.wastatussaver.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.boomplay.Dashboard
import com.example.boomplay.R
import com.paxees.wastatussaver.Adapter.RecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_library.*


class LibraryFragment : Fragment() {
    val listString= ArrayList<String>()
    val listImg= ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_library, container, false)
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
        listImg.clear()
        listImg?.add(R.mipmap.ic_history)
        listImg?.add(R.mipmap.ic_history)
        listImg?.add(R.mipmap.ic_history)
        listImg?.add(R.mipmap.ic_history)
        listImg?.add(R.mipmap.ic_history)
        listImg?.add(R.mipmap.ic_history)
    }

    fun init() {
        val layoutManager = GridLayoutManager(activity, 3)
        recyclerView.setLayoutManager(layoutManager)
        var adapter = RecyclerViewAdapter(listImg, listString, (activity as Dashboard))
        recyclerView.setAdapter(adapter)
        adapter.notifyDataSetChanged()
    }
}
