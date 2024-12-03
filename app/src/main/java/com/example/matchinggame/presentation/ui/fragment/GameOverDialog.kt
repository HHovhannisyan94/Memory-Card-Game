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
import com.example.matchinggame.databinding.GameOverDialogBinding


class GameOverDialog : DialogFragment() {
    private var view: View? = null
    private lateinit var binding: GameOverDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = GameOverDialogBinding.inflate(inflater, container, false)
        isCancelable = false

        view?.setBackgroundResource(R.drawable.rounded_background)

        binding.btnOk.setOnClickListener { v: View? ->
          dismiss()
            Utils.loadFragment(requireActivity(), GameMenuFragment.newInstance())

            //requireActivity().finish()
        }

        return  binding.root
    }

    override fun getTheme(): Int {
        return R.style.NoBackgroundDialogTheme
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

    override fun onDestroyView() {
        super.onDestroyView()
        view = null
        dismiss()
    }

    companion object{
        fun newInstance()= GameOverDialog()
    }
}
