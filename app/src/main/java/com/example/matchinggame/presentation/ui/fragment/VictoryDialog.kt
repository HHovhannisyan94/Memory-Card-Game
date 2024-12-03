package com.example.matchinggame.presentation.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

import com.example.matchinggame.R
import com.example.matchinggame.utils.Utils
import com.example.matchinggame.databinding.VictoryDialogBinding

class VictoryDialog : DialogFragment() {
    private lateinit var binding: VictoryDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = VictoryDialogBinding.inflate(inflater, container, false)
        view?.setBackgroundResource(R.drawable.rounded_background)
        isCancelable = false

        binding.btnOk.setOnClickListener {
            dismiss()
            Utils.loadFragment(requireActivity(), GameMenuFragment.newInstance())
        }

        return binding.root
    }


    override fun show(manager: FragmentManager, tag: String?) {
        try {
            val ft: FragmentTransaction = manager.beginTransaction()
            ft.add(this, tag)
            ft.commitAllowingStateLoss()
        } catch (e: IllegalStateException) {
            Log.d("ABSDIALOGFRAG", "Exception", e)
        }
    }



    override fun getTheme(): Int {
        return R.style.NoBackgroundDialogTheme
    }

    companion object {
        fun newInstance() = VictoryDialog()
    }
}