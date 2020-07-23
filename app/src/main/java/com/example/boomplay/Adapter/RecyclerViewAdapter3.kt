package com.paxees.wastatussaver.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.boomplay.Activities.MediaPlayer
import com.example.boomplay.R
import kotlinx.android.synthetic.main.library_listview_square.view.*

class RecyclerViewAdapter3(listImg:ArrayList<Int>?, items: ArrayList<String>?, val context: Context
) :
    RecyclerView.Adapter<RecyclerViewAdapter3.ViewHolder>() {
    var listString: ArrayList<String>? = null
    var listImg: ArrayList<Int>? = null

    override fun getItemCount(): Int {
        return listString?.size!!
    }

    init {
        this.listString = items
        this.listImg = listImg
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.library_listview_large_item,
                parent,
                false
            )
        )
    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var img = listImg?.get(position)
        var name = listString?.get(position)
        Glide.with(context).load(img).into(holder?.thumnails_img)
        holder.libImgTxt.text = name
        holder?.itemView.setOnClickListener(View.OnClickListener {
            var Intent = Intent(context, MediaPlayer::class.java)
            context.startActivity(Intent)
        })
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each animal to
        val libImgTxt = view.libImgTxt
        val thumnails_img = view.circleImg
    }
}