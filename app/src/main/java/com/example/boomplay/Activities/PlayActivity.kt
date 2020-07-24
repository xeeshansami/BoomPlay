package com.example.boomplay

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.boomplay.Adapter.VideoAdapter
import com.example.boomplay.Models.YouTubeVideos
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.paxees.wastatussaver.Adapter.RecyclerViewAdapter3
import kotlinx.android.synthetic.main.activity_playing_now.*
import java.util.*
import kotlin.collections.ArrayList


class PlayActivity : YouTubeBaseActivity() {
    val listString = ArrayList<String>()
    val listSquareString = ArrayList<Int>()
    val listImg = ArrayList<Int>()
    var youtubeInitializer: YouTubePlayer.OnInitializedListener? = null
    var youtubeVideos: Vector<YouTubeVideos> = Vector<YouTubeVideos>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playing_now)
        toolbar.setTitle("YoutubePlayer")
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        toolbar.setOnClickListener(View.OnClickListener {
            finish()
        })
        videoPlayer()
//        addList()
        videoView.initialize("AIzaSyCEbOB9gSLk7pwLMUiRxTTKz3Ze6k_QVcY", youtubeInitializer)
        addYoutubeVideos()
    }

    @SuppressLint("WrongConstant")
    fun addYoutubeVideos() {
        recyclerView.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        recyclerView.setLayoutManager(layoutManager)
        youtubeVideos.add(YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/eWEF1Zrmdow\" frameborder=\"0\" allowfullscreen></iframe>"))
        youtubeVideos.add(YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/KyJ71G2UxTQ\" frameborder=\"0\" allowfullscreen></iframe>"))
        youtubeVideos.add(YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/y8Rr39jKFKU\" frameborder=\"0\" allowfullscreen></iframe>"))
        youtubeVideos.add(YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/8Hg1tqIwIfI\" frameborder=\"0\" allowfullscreen></iframe>"))
        youtubeVideos.add(YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/uhQ7mh_o_cM\" frameborder=\"0\" allowfullscreen></iframe>"))
        val videoAdapter = VideoAdapter(youtubeVideos)
        recyclerView.adapter = videoAdapter
    }

    fun videoPlayer() {
        youtubeInitializer = object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(
                p0: YouTubePlayer.Provider?,
                youTubePlayer: YouTubePlayer?,
                p2: Boolean
            ) {
                youTubePlayer?.loadVideo("TmRgK-pXH9c")
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
//                Log.i("errorYoutube", p1?)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
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
    fun videoList() {
        val layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        recyclerView.setLayoutManager(layoutManager)
        var adapter = RecyclerViewAdapter3(listSquareString, listString, this)
        recyclerView.setAdapter(adapter)
        adapter.notifyDataSetChanged()
    }
}