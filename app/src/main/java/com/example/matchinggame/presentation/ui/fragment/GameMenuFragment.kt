package com.example.matchinggame.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.matchinggame.utils.Utils
import com.example.matchinggame.databinding.FragmentGameMenuBinding


class GameMenuFragment : Fragment() {

   private lateinit var binding: FragmentGameMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameMenuBinding.inflate(inflater, container, false)
        binding.btnLevel1.setOnClickListener {
            if (!Utils.haveNetworkConnection(requireActivity())) {
                Toast.makeText(requireActivity(), "No internet!", Toast.LENGTH_SHORT).show()
            } else {
                Utils.loadFragment(requireActivity(), FirstLevelFragment.newInstance())
            }
        }

        binding.btnLevel2.setOnClickListener {
         Utils.loadFragment(requireActivity(), SecondLevelFragment.newInstance())
        }

        binding.btnLevel3.setOnClickListener {
            Utils.loadFragment(requireActivity(), ThirdLevelFragment.newInstance())
        }
        return binding.root
    }

    companion object {
        fun newInstance() = GameMenuFragment()
    }
}