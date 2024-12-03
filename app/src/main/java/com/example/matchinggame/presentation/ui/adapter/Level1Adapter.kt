package com.example.matchinggame.presentation.ui.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.BaseAdapter
import android.widget.ImageView
import com.example.matchinggame.R
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.ShapeAppearanceModel

class Level1Adapter(private val context: Context) : BaseAdapter() {
    override fun getCount(): Int {
        return 16
    }

    override fun getItem(i: Int): Any? {
        return null
    }

    override fun getItemId(i: Int): Long {
        return 0
    }

    override fun getView(i: Int, view: View?, viewGroup: ViewGroup): View {
        val displayMetrics = context.resources.displayMetrics
        val pxHeight = displayMetrics.heightPixels
        val shapeableImageView: ShapeableImageView = if (view == null) {
            ShapeableImageView(context)
        } else {
            view as ShapeableImageView
        }
        shapeableImageView.apply {
            layoutParams = AbsListView.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                pxHeight / 4 - 50
            )
            scaleType = ImageView.ScaleType.FIT_XY
            setPadding(5, 5, 5, 5)

            val shapeAppearanceModel =
                ShapeAppearanceModel()
                    .toBuilder()
                    .setAllCorners(CornerFamily.ROUNDED, 20F).build()

            this.shapeAppearanceModel = shapeAppearanceModel
            strokeColor= ColorStateList.valueOf(Color.YELLOW)
            strokeWidth=3F
            setImageResource(R.drawable.cardback)
        }
        return shapeableImageView
    }
}