package com.paxees.wastatussaver.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.boomplay.Activities.ListActivity
import com.example.boomplay.Dashboard
import com.example.boomplay.PlayActivity
import com.example.boomplay.R
import com.paxees.wastatussaver.Adapter.RecyclerViewAdapter
import com.paxees.wastatussaver.Adapter.RecyclerViewAdapter2
import com.paxees.wastatussaver.Adapter.RecyclerViewAdapter3
import kotlinx.android.synthetic.main.fragment_library.*
import kotlinx.android.synthetic.main.fragment_library.recyclerView
import kotlinx.android.synthetic.main.fragment_music.*
import kotlinx.android.synthetic.main.fragment_mylist.*
import kotlinx.android.synthetic.main.fragment_videos.*
import kotlinx.android.synthetic.main.fragment_videos.recyclerView2


class MyListFragment : Fragment() ,View.OnClickListener{
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
        return inflater.inflate(R.layout.fragment_mylist, container, false)
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
        listImg?.add(R.drawable.person1)
        listImg?.add(R.drawable.person2)
        listImg?.add(R.drawable.person3)
        listImg?.add(R.drawable.person4)
        listImg?.add(R.drawable.person5)
        listImg?.add(R.drawable.person6)
        listImg?.add(R.drawable.person1)
        listImg?.add(R.drawable.person2)
        listImg?.add(R.drawable.person3)
        listImg?.add(R.drawable.person4)
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
        backNavBtn.setOnClickListener(this)
        val layoutManager = GridLayoutManager(activity,3)
        recyclerView.setLayoutManager(layoutManager)
        var adapter = RecyclerViewAdapter3(listSquareString, listString, (activity as ListActivity))
        recyclerView.setAdapter(adapter)
        adapter.notifyDataSetChanged()

    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.backNavBtn->{
            activity?.finish()
            }
        }
    }
}
