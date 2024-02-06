package com.example.fruitapp.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("app:doubleToString")
    fun doubleToString(textView: TextView, value: Double?) {
        value?.let {
            textView.text = it.toString()
        } ?: run {
            textView.text = "0.0"
        }
    }

    @JvmStatic
    @BindingAdapter("app:intToString")
    fun intToString(textView: TextView, value: Int?) {
        value?.let {
            textView.text = it.toString()
        } ?: run {
            textView.text = "0"
        }
    }
}