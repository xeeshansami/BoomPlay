package com.paxees.wastatussaver.fragments

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.VideoView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.boomplay.PlayActivity
import com.example.boomplay.R
import com.paxees.wastatussaver.Adapter.RecyclerViewAdapter2
import kotlinx.android.synthetic.main.fragment_library.*
import kotlinx.android.synthetic.main.fragment_library.recyclerView
import kotlinx.android.synthetic.main.fragment_playing_now.*


class PlayingNowFragment : Fragment() {
    val listString= ArrayList<String>()
    val listSquareString= ArrayList<Int>()
    val listImg= ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_playing_now, container, false)
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
        listSquareString.clear()
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
        val layoutManager = LinearLayoutManager(activity,LinearLayout.HORIZONTAL,false)
        recyclerView.setLayoutManager(layoutManager)
        var adapter = RecyclerViewAdapter2(listSquareString, listString, (activity as PlayActivity))
        recyclerView.setAdapter(adapter)
        adapter.notifyDataSetChanged()
    }
    fun playVideo(){
/*        val path = "android.resource://" + activity?.getPackageName() + "/" + android.R.raw.video
        videoView.setVideoURI(Uri.parse(path))
        videoView.start()*/
    }
}
