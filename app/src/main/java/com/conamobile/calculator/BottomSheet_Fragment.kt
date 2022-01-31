package com.conamobile.calculator

import android.content.ClipData
import android.content.ClipboardManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getSystemService
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottomsheet_dialog.*
import android.graphics.Paint
import android.widget.Toast
import android.content.Intent
import android.net.Uri

class bottom_sheet : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottomsheet_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        email_username.paintFlags = email_username.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        telegram_username.paintFlags = telegram_username.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        play_market_txt2.paintFlags = telegram_username.paintFlags or Paint.UNDERLINE_TEXT_FLAG

        email_username.setOnClickListener {

            val emailIntent = Intent(
                Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", "conamobiledev@gmail.com", null
                )
            )
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Calculator app client")
            emailIntent.putExtra(Intent.EXTRA_TEXT, "")
            startActivity(Intent.createChooser(emailIntent, "Send email..."))

        }

        telegram_username.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/Cona_Mobile"))
            startActivity(browserIntent)
        }

        copy_btn.setOnClickListener {

            val myClipboard =
                getSystemService(requireContext(), ClipboardManager::class.java) as ClipboardManager
            val clip: ClipData = ClipData.newPlainText("Cona Mobile", "conamobiledev@gmail.com")
            Toast.makeText(context, "Copied", Toast.LENGTH_SHORT).show()

            myClipboard.setPrimaryClip(clip)
        }

        copy_btn2.setOnClickListener {

            val myClipboard =
                getSystemService(requireContext(), ClipboardManager::class.java) as ClipboardManager
            val clip: ClipData = ClipData.newPlainText("Cona Mobile", "@Cona_Mobile")
            Toast.makeText(context, "Copied", Toast.LENGTH_SHORT).show()

            myClipboard.setPrimaryClip(clip)

        }

        copy_btn3.setOnClickListener {

            val myClipboard =
                getSystemService(requireContext(), ClipboardManager::class.java) as ClipboardManager
            val clip: ClipData = ClipData.newPlainText("Cona Mobile", "https://play.google.com/store/apps/developer?id=Cona+Mobile")
            Toast.makeText(context, "Copied", Toast.LENGTH_SHORT).show()

            myClipboard.setPrimaryClip(clip)

        }

        play_market_txt2.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=Cona+Mobile"))
            startActivity(browserIntent)
        }

        hide_bottom.setOnClickListener {
            dismiss()
        }

    }

}