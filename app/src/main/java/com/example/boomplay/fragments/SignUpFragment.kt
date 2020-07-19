package com.paxees.wastatussaver.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.boomplay.Dashboard
import com.example.boomplay.R
import kotlinx.android.synthetic.main.fragment_signup.*

class SignUpFragment : Fragment() ,View.OnClickListener{

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signUpBtn.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.signUpBtn -> {
                var Intent = Intent(activity, Dashboard::class.java)
                startActivity(Intent)
            }
        }
    }
}
