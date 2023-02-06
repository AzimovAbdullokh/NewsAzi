package com.example.com.com.presentation.utils.extension

import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.example.newsazi.R

fun makeToast(message: String, context: Context) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun View.showView() {
    this.visibility = View.VISIBLE
}

fun View.hideView() {
    this.visibility = View.GONE
}

fun View.startSlideInLeftAnim() {
    this.startAnimation(AnimationUtils.loadAnimation(this.context, R.anim.slide_in_left_anim))
}