package com.example.boomplay.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.boomplay.R
import kotlinx.android.synthetic.main.activity_member_ship.*

class SettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        toolbar.setTitle("")
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        toolbar.setOnClickListener(View.OnClickListener {
            finish()
        })
    }
}