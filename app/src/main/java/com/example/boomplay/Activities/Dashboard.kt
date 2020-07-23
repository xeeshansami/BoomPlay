package com.example.boomplay

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.example.boomplay.Activities.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.paxees.wastatussaver.Adapter.ViewPagerAdapter
import com.paxees.wastatussaver.fragments.LibraryVideos
import com.paxees.wastatussaver.fragments.MusicFragment
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.app_bar_main.view_pager


class Dashboard : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    var prevMenuItem: MenuItem? = null
    var libraryFragment: LibraryVideos? = null
    var musicFragment: MusicFragment? = null
    var drawer: DrawerLayout? = null
    var navigationView: NavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
                if (prevMenuItem != null) prevMenuItem!!.setChecked(false) else tabs.getMenu().getItem(
                    0
                ).setChecked(false)
                tabs.getMenu().getItem(position).setChecked(true)
                prevMenuItem = tabs.getMenu().getItem(position)
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
        setupViewPager(view_pager)
    }

    fun init() {
        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        setSupportActionBar(toolbar)
        drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        val toggle =
            ActionBarDrawerToggle(
                this,
                drawer,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
            )
        drawer!!.addDrawerListener(toggle)
        toggle.syncState()
        toolbar.setNavigationIcon(R.mipmap.ic_toggle_menu);
        navigationView = findViewById<View>(R.id.nav_view) as NavigationView
        val hView = navigationView!!.getHeaderView(0)
        navigationView!!.setNavigationItemSelectedListener(this)
        tabs.setOnNavigationItemSelectedListener(
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.videosMenu -> view_pager.setCurrentItem(0)
                    R.id.musicMenu -> view_pager.setCurrentItem(1)
                }
                false
            })
        bottomNavTabsDashboard.setOnNavigationItemSelectedListener(
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.homeMenu -> {

                    }
                    R.id.downloadMenu -> {
                        var Intent = Intent(this, DownloadActivity::class.java)
                        startActivity(Intent)
                    }
                    R.id.personMenu -> {
                    }
                }
                false
            })
        adjustGravity(tabs)
        adjustWidth(tabs)
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
        libraryFragment = LibraryVideos()
        musicFragment = MusicFragment()
        adapter.addFrag(libraryFragment!!)
        adapter.addFrag(musicFragment!!)
        viewPager.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.memberShipMenu -> {
                var Intent = Intent(this, MemberShipActivity::class.java)
                startActivity(Intent)
            }
            R.id.videoSettingMenu -> {
                var Intent = Intent(this, VideoSetting::class.java)
                startActivity(Intent)
            }
            R.id.appSettingMenu -> {
                var Intent = Intent(this, SettingActivity::class.java)
                startActivity(Intent)
            }
            R.id.privacyPolicyMenuID -> {
                var Intent = Intent(this, PrivacyPolicyActivity::class.java)
                startActivity(Intent)
            }
            R.id.termsOfUserMenuID -> {
                var Intent = Intent(this, TermOfUseActivity::class.java)
                startActivity(Intent)
            }
        }
        return true
    }
}
