package com.example.matchinggame.presentation.ui.fragment


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.CountDownTimer
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.matchinggame.R
import com.example.matchinggame.databinding.FragmentFirstLevelBinding
import com.example.matchinggame.presentation.ui.LoadingDialog
import com.example.matchinggame.presentation.ui.adapter.Level1Adapter
import com.example.matchinggame.presentation.ui.viewmodel.MainViewModel
import com.example.matchinggame.utils.NetworkResult
import com.example.matchinggame.utils.Utils
import com.google.android.material.imageview.ShapeableImageView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

@AndroidEntryPoint
class FirstLevelFragment : Fragment() {
    lateinit var curView: ShapeableImageView
    private var countPair = 0
    var mBitmapList = ArrayList<Bitmap>()
    var pos: MutableList<Int> = ArrayList()
    private var currentPos = -1
    private lateinit var countDownTimer: CountDownTimer
    lateinit var binding: FragmentFirstLevelBinding

    private val mainViewModel by viewModels<MainViewModel>()
    private lateinit var loading: LoadingDialog
    var isRunning = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstLevelBinding.inflate(inflater, container, false)
        for (i in 0..7) {
            repeat(2) {
                pos.add(i)
            }
        }
        pos.shuffle()

        loading = LoadingDialog(requireActivity())
        val imageViewAdapter = Level1Adapter(requireActivity())
        binding.gridView.adapter = imageViewAdapter
        if (!Utils.haveNetworkConnection(requireActivity())) {
            Toast.makeText(requireActivity(), "No internet!", Toast.LENGTH_SHORT).show()
            Utils.loadFragment(requireActivity(), GameMenuFragment.newInstance())
        } else {
            loading.startLoading()
        }

        fetchData()
        binding.gridView.onItemClickListener =
            AdapterView.OnItemClickListener { _, view, position, _ ->
                if (currentPos < 0) {
                    currentPos = position
                    curView = view as ShapeableImageView
                    curView.setImageBitmap((mBitmapList)[pos[position]])
                } else {
                    if (currentPos == position) {
                        curView.setImageResource(R.drawable.cardback)
                    } else if (pos[currentPos] != pos[position]) {
                        lifecycleScope.launch {
                            delay(2000L)
                              curView.setImageResource(R.drawable.cardback)
                        }
                      // curView.setImageResource(R.drawable.cardback)
                        Toast.makeText(requireActivity(), "Not match!", Toast.LENGTH_SHORT).show()
                    } else {
                        curView.setImageBitmap((mBitmapList)[pos[position]])
                        curView.visibility = View.INVISIBLE
                        view.visibility = View.INVISIBLE
                        countPair++
                        if (countPair == 8) {
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
        countDownTimer = object : CountDownTimer(40100, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                isRunning = true
                binding.tvCountDown.text = "Seconds remaining:" + millisUntilFinished / 1000
            }

            override fun onFinish() {
                isRunning = false
                GameOverDialog.newInstance()
                    .show(requireActivity().supportFragmentManager, "GameOverDialog")
                Toast.makeText(requireActivity(), "Game over!", Toast.LENGTH_SHORT).show()
            }
        }.start()
    }

    private fun stopCountdown() {
        countDownTimer.cancel()
    }

    private fun fetchData(): List<Bitmap> {
        mainViewModel.fetchImgResponse()
        viewLifecycleOwner.lifecycleScope.launch {
            mainViewModel.response.collect { response ->
                when (response) {
                    is NetworkResult.Success<*> -> {
                        response.data?.byteStream().let {
                            mBitmapList.add(
                                BitmapFactory.decodeStream(
                                    it
                                )
                            )
                        }
                        mBitmapList.shuffle(Random(System.nanoTime()))
                        if (mBitmapList.size == 8) {
                            loading.dismissDialog()
                            countTime()
                        }
                    }

                    is NetworkResult.Error<*> -> {
                        Toast.makeText(
                            requireActivity(),
                            response.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    is NetworkResult.Loading<*> -> {
                        loading.startLoading()
                    }
                }
            }
        }
        return mBitmapList
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

    companion object {
        fun newInstance() = FirstLevelFragment()
    }
}