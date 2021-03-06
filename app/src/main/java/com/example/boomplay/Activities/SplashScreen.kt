package com.example.boomplay.Activities

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.example.boomplay.Dashboard
import com.example.boomplay.R
import com.example.boomplay.SignActivity
import com.nabinbhandari.android.permissions.PermissionHandler
import com.nabinbhandari.android.permissions.Permissions
import java.util.*

class SplashScreen : Activity() {
    var permissions = arrayOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
    }

    private fun checkForPermissions() {
        Permissions.check(
            this /*context*/,
            permissions,
            null /*rationale*/,
            null /*options*/,
            object : PermissionHandler() {
                override fun onGranted() { // do your task.
                    sendToDashboard()
                }

                @SuppressLint("WrongConstant")
                override fun onDenied(
                    context: Context,
                    deniedPermissions: ArrayList<String>
                ) {
                    Toast.makeText(
                        this@SplashScreen,
                        "Permission denied, please enabled the permissions",
                        1000
                    ).show()

                    /*===*/
                }
            })
    }

    fun sendToDashboard() {
        Handler().postDelayed({
            startActivity(Intent(this, SignActivity::class.java))
            finish()
        }, 2000)

    }

    override fun onStart() {
        super.onStart()
        checkForPermissions()
    }
}
