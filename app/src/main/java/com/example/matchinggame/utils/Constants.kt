package com.example.matchinggame.utils

import com.example.matchinggame.R


class Constants {
    companion object {
        const val BASE_URL = "https://picsum.photos/"
        const val IMG_SIZE = "500/600"
        const val millisInFuture: Long = 100100

        val level2Images: IntArray = intArrayOf(
            R.drawable.arrow,
            R.drawable.circle,
            R.drawable.cone,
            R.drawable.crescent,
            R.drawable.cross,
            R.drawable.cube,
            R.drawable.cylinder,
            R.drawable.diamond,
            R.drawable.half_circle,
            R.drawable.heart,
            R.drawable.hexagon,
            R.drawable.pentagon,
            R.drawable.pyramid,
            R.drawable.rhombus,
            R.drawable.sphere,
            R.drawable.square,
            R.drawable.star2,
            R.drawable.triangle
        )

        val level3Images: IntArray = intArrayOf(
            R.drawable.queen_of_clubs2, R.drawable.queen_of_diamonds2,
            R.drawable.queen_of_hearts2, R.drawable.queen_of_spades2,
            R.drawable.king_of_spades2, R.drawable.king_of_hearts2,
            R.drawable.king_of_clubs2, R.drawable.king_of_diamonds2,
            R.drawable.jack_of_hearts2, R.drawable.jack_of_spades2,
            R.drawable.jack_of_clubs2, R.drawable.jack_of_diamonds2,
            R.drawable.ten_of_clubs, R.drawable.ten_of_hearts,
            R.drawable.ace_of_clubs, R.drawable.ace_of_diamonds,
            R.drawable.ace_of_hearts, R.drawable.ace_of_spades
        )
    }
}