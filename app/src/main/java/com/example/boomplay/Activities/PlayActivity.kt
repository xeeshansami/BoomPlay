package com.example.boomplay

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.paxees.wastatussaver.Adapter.RecyclerViewAdapter2
import com.paxees.wastatussaver.Adapter.RecyclerViewAdapter3
import com.paxees.wastatussaver.Adapter.ViewPagerAdapter
import com.paxees.wastatussaver.fragments.*
import kotlinx.android.synthetic.main.activity_member_ship.*
import kotlinx.android.synthetic.main.activity_playing_now.*
import kotlinx.android.synthetic.main.activity_playing_now.recyclerView
import kotlinx.android.synthetic.main.activity_playing_now.toolbar
import kotlinx.android.synthetic.main.activity_sign_screens.*
import kotlinx.android.synthetic.main.app_bar_main.view_pager
import kotlinx.android.synthetic.main.fragment_videos.*


class PlayActivity : YouTubeBaseActivity() {
    val listString= ArrayList<String>()
    val listSquareString= ArrayList<Int>()
    val listImg= ArrayList<Int>()
    var youtubeInitializer: YouTubePlayer.OnInitializedListener? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playing_now)
        toolbar.setTitle("YoutubePlayer")
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        toolbar.setOnClickListener(View.OnClickListener {
            finish()
        })
        videoPlayer()
        addList()
        videoView.initialize("AIzaSyCEbOB9gSLk7pwLMUiRxTTKz3Ze6k_QVcY", youtubeInitializer)
        videoList()
    }

    fun videoPlayer() {
        youtubeInitializer = object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(
                p0: YouTubePlayer.Provider?,
                youTubePlayer: YouTubePlayer?,
                p2: Boolean
            ) {
                youTubePlayer?.loadPlaylist("ybBncE6wROo")
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {

            }
        }
        videoPlayBtn.setOnClickListener(View.OnClickListener {
        })
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