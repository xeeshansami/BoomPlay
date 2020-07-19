package com.example.boomplay

import android.os.Bundle
import android.view.*
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.paxees.wastatussaver.Adapter.ViewPagerAdapter
import com.paxees.wastatussaver.fragments.*
import kotlinx.android.synthetic.main.activity_sign_screens.*
import kotlinx.android.synthetic.main.app_bar_main.view_pager


class PlayActivity : AppCompatActivity() {
    var prevMenuItem: MenuItem? = null
    var downloadFragment: DownloadFragment? = null
    var playingNowFragment: PlayingNowFragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playing_now)
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
                        view_pager.setCurrentItem(0)
                    }
                    R.id.downloadMenu -> view_pager.setCurrentItem(1)
                    R.id.personMenu -> finish()
                }
                false
            })
//        adjustGravity(bottomNavTabs)
//        adjustWidth(bottomNavTabs)
    }

    private fun adjustGravity(v: View) {
        if (v.id == R.id.smallLabel) {
            val parent = v.parent as ViewGroup
            parent.setPadding(0, 0, 0, 0)

            val params = parent.layoutParams as FrameLayout.LayoutParams
            params.gravity = Gravity.CENTER
            parent.layoutParams = params
        }

        if (v is ViewGroup) {
            val vg = v as ViewGroup

            for (i in 0 until vg.childCount) {
                adjustGravity(vg.getChildAt(i))
            }
        }
    }

    private fun adjustWidth(nav: BottomNavigationView) {
        try {
            val menuViewField = nav.javaClass.getDeclaredField("mMenuView")
            menuViewField.isAccessible = true
            val menuView = menuViewField.get(nav)

            val itemWidth = menuView.javaClass.getDeclaredField("mActiveItemMaxWidth")
            itemWidth.isAccessible = true
            itemWidth.setInt(menuView, Integer.MAX_VALUE)
        } catch (e: NoSuchFieldException) {
            // TODO
        } catch (e: IllegalAccessException) {
            // TODO
        }
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        playingNowFragment = PlayingNowFragment()
        downloadFragment = DownloadFragment()
        adapter.addFrag(playingNowFragment!!)
        adapter.addFrag(downloadFragment!!)
        viewPager.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }
}