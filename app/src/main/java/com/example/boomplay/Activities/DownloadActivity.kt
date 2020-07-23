package com.example.boomplay
import com.example.boomplay.R
import android.os.Bundle
import android.view.*
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.youtube.player.YouTubeBaseActivity
import com.paxees.wastatussaver.Adapter.ViewPagerAdapter
import com.paxees.wastatussaver.fragments.*
import kotlinx.android.synthetic.main.activity_sign_screens.*
import kotlinx.android.synthetic.main.app_bar_main.view_pager


class DownloadActivity : AppCompatActivity() {
    var prevMenuItem: MenuItem? = null
    var downloadFragment: DownloadFragment? = null
    var settingFragment: SettingFragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download)
        init()
        viewPager()
    }

    fun viewPager() {
        view_pager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                if (prevMenuItem != null) prevMenuItem!!.setChecked(false) else bottomNavTabs.getMenu().getItem(
                    0
                ).setChecked(false)
                bottomNavTabs.getMenu().getItem(position).setChecked(true)
                prevMenuItem = bottomNavTabs.getMenu().getItem(position)
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
        setupViewPager(view_pager)
    }

    fun init() {
        bottomNavTabs.setOnNavigationItemSelectedListener(
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.homeMenu -> {
                        finish()
                    }
                    R.id.downloadMenu -> view_pager.setCurrentItem(0)
                    R.id.personMenu -> view_pager.setCurrentItem(1)
                }
                false
            })
//        adjustGravity(bottomNavTabs)
//        adjustWidth(bottomNavTabs)
    }


    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        downloadFragment = DownloadFragment()
        settingFragment = SettingFragment()
        adapter.addFrag(downloadFragment!!)
        adapter.addFrag(settingFragment!!)
        viewPager.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }
}