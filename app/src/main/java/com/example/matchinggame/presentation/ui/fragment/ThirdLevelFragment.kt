package com.example.matchinggame.presentation.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.matchinggame.R
import com.example.matchinggame.databinding.FragmentThirdLevelBinding
import com.example.matchinggame.presentation.ui.adapter.Level6x6Adapter
import com.example.matchinggame.utils.Constants
import com.example.matchinggame.utils.Utils
import com.google.android.material.imageview.ShapeableImageView

class ThirdLevelFragment : Fragment() {

    lateinit var curView: ShapeableImageView
    private var countPair = 0
    var pos: MutableList<Int> = ArrayList()
    private var currentPos = -1
    private lateinit var countDownTimer: CountDownTimer
    private lateinit var binding: FragmentThirdLevelBinding
    private var timeRemaining: Long = 0
    private var isPaused = false
    var isRunning = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentThirdLevelBinding.inflate(inflater, container, false)

        for (i in 0..17) {
            repeat(2) {
                pos.add(i)
            }
        }
        pos.shuffle()

        val level6x6Adapter = Level6x6Adapter(requireActivity())
        binding.gridView.adapter = level6x6Adapter

        countTime()

        binding.gridView.onItemClickListener =
            AdapterView.OnItemClickListener { _, view, position, _ ->


                if (currentPos < 0) {
                    currentPos = position
                    curView = view as ShapeableImageView
                    curView.setImageResource(Constants.level3Images[pos[position]])

                } else {
                    if (currentPos == position) {
                        curView.setImageResource(R.drawable.cardback)
                    } else if (pos[currentPos] != pos[position]) {
                        curView.setImageResource(R.drawable.cardback)
                    } else {
                        curView.setImageResource(Constants.level3Images[pos[position]])
                        curView.visibility = View.INVISIBLE
                        view.visibility = View.INVISIBLE
                        countPair++
                        if (countPair == 18) {
                            Toast.makeText(requireActivity(), "You won!", Toast.LENGTH_SHORT).show()
                            stopCountdown()
                            gameWon()
                        }
                    }
                    currentPos = -1
                }
            }
        return binding.root
    }

    private fun countTime() {
        with(binding) {
            countDownTimer = object : CountDownTimer(Constants.millisInFuture, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    if (isPaused) {
                        cancel()
                    } else {
                        tvCountdown.text =
                            "Seconds remaining:" + millisUntilFinished / 1000
                        timeRemaining = millisUntilFinished
                    }
                }

                @SuppressLint("SetTextI18n")
                override fun onFinish() {
                    GameOverDialog.newInstance()
                        .show(requireActivity().supportFragmentManager, "GameOverDialog")
                    tvCountdown.text = "TIME'S UP!"
                }
            }.start()
            btnPause.setOnClickListener { pauseGame() }
            btnResume.setOnClickListener {
                isPaused = false
                val millisInFuture: Long = timeRemaining
                val countDownInterval: Long = 1000
                btnPause.visibility = View.VISIBLE
                btnResume.visibility = View.INVISIBLE
                gridView.isEnabled = true

                object : CountDownTimer(millisInFuture, countDownInterval) {
                    @SuppressLint("SetTextI18n")
                    override fun onTick(millisUntilFinished: Long) {
                        if (isPaused) {
                            cancel()
                        } else {
                            tvCountdown.text =
                                "Seconds remaining:" + millisUntilFinished / 1000
                            timeRemaining = millisUntilFinished
                        }
                        isRunning = true
                    }

                    @SuppressLint("SetTextI18n")
                    override fun onFinish() {
                        GameOverDialog.newInstance()
                            .show(requireActivity().supportFragmentManager, "GameOverDialog")
                        tvCountdown.text = "TIME'S UP!"
                        isRunning = false
                    }
                }.start()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        pauseGame()
    }

    override fun onResume() {
        super.onResume()
        requireView().isFocusableInTouchMode = true
        requireView().requestFocus()
        requireView().setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                Utils.loadFragment(requireActivity(), GameMenuFragment.newInstance())
                if (isRunning) {
                    stopCountdown()
                }
                true
            } else false
        }
    }

    private fun gameWon() {
        VictoryDialog.newInstance().show(requireActivity().supportFragmentManager, "VictoryDialog")
    }

    private fun pauseGame() {
        isPaused = true
        with(binding) {
            btnPause.visibility = View.INVISIBLE
            btnResume.visibility = View.VISIBLE
            gridView.isEnabled = false
        }
    }

    private fun stopCountdown() {
        countDownTimer.cancel()
    }

    companion object {
        fun newInstance() = ThirdLevelFragment()
    }

}