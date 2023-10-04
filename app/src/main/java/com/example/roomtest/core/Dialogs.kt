package com.example.roomtest.core

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import com.example.roomtest.databinding.DialogInfoBinding
import com.example.roomtest.databinding.DialogQuestionBinding

class Dialogs(private val context: Context) {

    fun infoDialog(message: String, onAccept: () -> Unit = {}) {
        Dialog(context).apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(false)
            val binding = DialogInfoBinding.inflate(layoutInflater)
            val view = binding.root
            setContentView(view)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            binding.tvMessage.text = message

            binding.btnAccept.setOnClickListener {
                onAccept.invoke()
                dismiss()
            }
        }.show()
    }

    fun questionDialog(message: String, onAccept: () -> Unit = {}) {
        Dialog(context).apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(false)
            val binding = DialogQuestionBinding.inflate(layoutInflater)
            val view = binding.root
            setContentView(view)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            binding.tvMessage.text = message

            binding.btnAccept.setOnClickListener {
                onAccept.invoke()
                dismiss()
            }
            binding.btnCancel.setOnClickListener {
                dismiss()
            }
        }.show()
    }

}