package com.example.liber.presentation.fragments.login.dialog

import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.example.liber.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

fun Fragment.setupBottomSheetDialog(
    onSendClick: (String) -> Unit
) {
    val dialog = BottomSheetDialog(requireContext(), R.style.DialogStyle)
    val view = layoutInflater.inflate(R.layout.reset_password_dialog, null)
    dialog.setContentView(view)
    dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
    dialog.show()

    //The input of email(edit text)
    val edEmail = view.findViewById<EditText>(R.id.edResetPassword)
    //The button that sends to the email the reset of password
    val buttonSend = view.findViewById<AppCompatButton>(R.id.buttonSendResetPassword)
    //Quit the dialog when pressing Cancel button
    val buttonCancel = view.findViewById<AppCompatButton>(R.id.buttonCancelResetPassword)

    buttonSend.setOnClickListener {
        val email = edEmail.text.toString().trim()
        onSendClick(email)
        dialog.dismiss()
    }

    buttonCancel.setOnClickListener {
        dialog.dismiss()
    }
}