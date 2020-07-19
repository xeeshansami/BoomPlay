package com.paxees.wastatussaver.fragments

import android.os.Bundle
import android.view.*
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.boomplay.PlayActivity
import com.example.boomplay.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.paxees.wastatussaver.Adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_download.*


class DownloadFragment : Fragment() {
    var downloadVideosFragment: DownloadVideoFragment? = null
    var downloadMusicFragment: DownloadMusicFragment? = null
    var prevMenuItem: MenuItem? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_download, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        viewPager()
    }

    fun viewPager() {
        viewPagerFragment.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
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
        setupViewPager(viewPagerFragment)
    }

    fun init() {
        bottomNavTabs.setOnNavigationItemSelectedListener(
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.videosMenu -> {
                        viewPagerFragment.setCurrentItem(0)
                    }
                    R.id.musicMenu -> {viewPagerFragment.setCurrentItem(1)}
                }
                false
            })
    }


    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter((activity as PlayActivity ).getSupportFragmentManager())
        downloadVideosFragment = DownloadVideoFragment()
        downloadMusicFragment = DownloadMusicFragment()
        adapter.addFrag(downloadVideosFragment!!)
        adapter.addFrag(downloadMusicFragment!!)
        viewPagerFragment.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }
}
