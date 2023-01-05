package com.example.liber.common

import android.view.View
import androidx.fragment.app.Fragment
import com.example.liber.R
import com.example.liber.presentation.activities.ContentActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

fun Fragment.hideBottomNavigationView() {
    val bottomNavigationView = (activity as ContentActivity).findViewById<BottomNavigationView>(R.id.bottomNavigation)
    bottomNavigationView.visibility = View.GONE
}

fun Fragment.showBottomNavigationView() {
    val bottomNavigationView = (activity as ContentActivity).findViewById<BottomNavigationView>(R.id.bottomNavigation)
    bottomNavigationView.visibility = View.VISIBLE
}