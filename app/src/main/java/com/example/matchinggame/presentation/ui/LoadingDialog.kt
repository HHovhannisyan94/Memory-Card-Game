package com.example.matchinggame.presentation.ui

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import com.example.matchinggame.R

class LoadingDialog (private val mActivity: Activity) {
    private lateinit var dialog: AlertDialog
    fun startLoading(){
        val dialogView = mActivity.layoutInflater.inflate(R.layout.loading_item,mActivity.findViewById(android.R.id.content), false)
        val builder = AlertDialog.Builder(mActivity).apply {
            setView(dialogView)
            setCancelable(false)
        }
        dialog = builder.create()
        dialog.show()
    }
    fun dismissDialog(){
        dialog.dismiss()
    }
}