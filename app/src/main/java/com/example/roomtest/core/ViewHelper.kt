package com.example.roomtest.core

import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class ViewHelper {

    companion object {
        fun TextInputEditText.setFieldError(message: String): Boolean {
            val til = parent.parent as TextInputLayout
            til.error = message
            this.addTextChangedListener {
                if (!text.isNullOrEmpty()) {
                    til.error = null
                    til.isErrorEnabled = false
                }
            }
            return false
        }
    }

}