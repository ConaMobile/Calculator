package com.conamobile.calculator

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.TypedValue
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View

import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import android.view.ViewGroup.MarginLayoutParams
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlin.math.abs


class MainActivity : AppCompatActivity(), GestureDetector.OnGestureListener {

    lateinit var gestureDetector: GestureDetector
    lateinit var mAdView: AdView
     var object1 = 0
     var object2 = 0
     var object3 = 0
     var object4 = 0
     var object5 = 0
     var object6 = 0
     var object_math = ""

    var x2: Float = 0.0f
    var x1: Float = 0.0f
    var y2: Float = 0.0f
    var y1: Float = 0.0f

    companion object {

    }

    @SuppressLint("SetTextI18n", "ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        someTask()

        gestureDetector = GestureDetector(this, this)

        val bottomsheetFragment = bottom_sheet()

        val sharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE)

        val name = sharedPref.getString("name", null)

        if (name != "0") {
            txt.text = name
        } else
            txt.text = "0"

        MobileAds.initialize(this) {}
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        info_button.setOnClickListener {
            bottomsheetFragment.show(supportFragmentManager, "BottomSheet Dialog")
            if (bottomsheetFragment.showsDialog) {
                info_button.isClickable = false
            }
//            if (bottomsheetFragment.isCancelable){
//                info_button.isClickable = true
//            }
        }

        btn_c.setOnClickListener {
            info_button.isClickable = true
            Toast.makeText(this, "C", Toast.LENGTH_SHORT).show()
            sharedPref.edit().clear()
            sharedPref.edit().apply()
            txt.text = "0"
            txt1.text = ""
        }

        btn_foiz.setOnClickListener {
            Toast.makeText(this, "%", Toast.LENGTH_SHORT).show()
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                when {
                    txt.text == "0" -> {
                        txt.text = "0%"
                    }
                    txt.text.last().toString() == "÷" -> {
                        val length = txt.length()
                        txt.text = txt.text.subSequence(0, length - 1)
                        txt.text = "${txt.text}%"
                    }
                    txt.text.last().toString() == "×" -> {
                        val length = txt.length()
                        txt.text = txt.text.subSequence(0, length - 1)
                        txt.text = "${txt.text}%"
                    }
                    txt.text.last().toString() == "-" -> {
                        val length = txt.length()
                        txt.text = txt.text.subSequence(0, length - 1)
                        txt.text = "${txt.text}%"
                    }
                    txt.text.last().toString() == "+" -> {
                        val length = txt.length()
                        txt.text = txt.text.subSequence(0, length - 1)
                        txt.text = "${txt.text}%"
                    }
                    txt.text.last().toString() != "%" -> {
                        txt.text = "${txt.text}%"
                    }

                }
            } else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            }
        }

        btn_bolu.setOnClickListener {
            Toast.makeText(this, "/", Toast.LENGTH_SHORT).show()
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                when {
                    txt.text == "0" -> {
                        txt.text = "0÷"
                    }
                    txt.text.last().toString() == "%" -> {
                        val length = txt.length()
                        txt.text = txt.text.subSequence(0, length - 1)
                        txt.text = "${txt.text}÷"
                    }
                    txt.text.last().toString() == "×" -> {
                        val length = txt.length()
                        txt.text = txt.text.subSequence(0, length - 1)
                        txt.text = "${txt.text}÷"
                    }
                    txt.text.last().toString() == "-" -> {
                        val length = txt.length()
                        txt.text = txt.text.subSequence(0, length - 1)
                        txt.text = "${txt.text}÷"
                    }
                    txt.text.last().toString() == "+" -> {
                        val length = txt.length()
                        txt.text = txt.text.subSequence(0, length - 1)
                        txt.text = "${txt.text}÷"
                    }
                    txt.text.last().toString() != "÷" -> {
                        txt.text = "${txt.text}÷"
                    }

                }
            } else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            }
        }

//        btn_backspace.setOnTouchListener(View.OnTouchListener
//        { _, motionEvent ->
//            when (motionEvent.action){
//                MotionEvent.ACTION_DOWN -> {
//                }
//                MotionEvent.ACTION_UP -> {
//                }
//            }
//            return@OnTouchListener true
//        })

        btn_backspace.setOnClickListener {
            Toast.makeText(this, "Bascpace", Toast.LENGTH_SHORT).show()
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                val length = txt.length()
                if (txt.length() == 1) {
                    txt.text = "0"
                }
                if (length > 1) {
                    txt.text = txt.text.subSequence(0, length - 1)
                }
            } else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                Toast.makeText(this, "Bascpace", Toast.LENGTH_SHORT).show()
                when {
                    txt.text == "0" -> {
                        txt.text = "0×"
                    }
                    txt.text.last().toString() == "%" -> {
                        val length = txt.length()
                        txt.text = txt.text.subSequence(0, length - 1)
                        txt.text = "${txt.text}×"
                    }
                    txt.text.last().toString() == "÷" -> {
                        val length = txt.length()
                        txt.text = txt.text.subSequence(0, length - 1)
                        txt.text = "${txt.text}×"
                    }
                    txt.text.last().toString() == "-" -> {
                        val length = txt.length()
                        txt.text = txt.text.subSequence(0, length - 1)
                        txt.text = "${txt.text}×"
                    }
                    txt.text.last().toString() == "+" -> {
                        val length = txt.length()
                        txt.text = txt.text.subSequence(0, length - 1)
                        txt.text = "${txt.text}×"
                    }
                    txt.text.last().toString() != "×" -> {
                    }

                }
            }
        }

        btn_x.setOnClickListener {
            Toast.makeText(this, "x", Toast.LENGTH_SHORT).show()
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                when {
                    txt.text == "0" -> {
                        txt.text = "0×"
                    }
                    txt.text.last().toString() == "%" -> {
                        val length = txt.length()
                        txt.text = txt.text.subSequence(0, length - 1)
                        txt.text = "${txt.text}×"
                    }
                    txt.text.last().toString() == "÷" -> {
                        val length = txt.length()
                        txt.text = txt.text.subSequence(0, length - 1)
                        txt.text = "${txt.text}×"
                    }
                    txt.text.last().toString() == "-" -> {
                        val length = txt.length()
                        txt.text = txt.text.subSequence(0, length - 1)
                        txt.text = "${txt.text}×"
                    }
                    txt.text.last().toString() == "+" -> {
                        val length = txt.length()
                        txt.text = txt.text.subSequence(0, length - 1)
                        txt.text = "${txt.text}×"
                    }
                    txt.text.last().toString() != "×" -> {
                        txt.text = "${txt.text}×"
                    }

                }
            } else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {

            }

        }

        btn_minus.setOnClickListener {
            Toast.makeText(this, "minus", Toast.LENGTH_SHORT).show()
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                when {
                    txt.text == "0" -> {
                        txt.text = "0-"
                    }
                    txt.text.last().toString() == "%" -> {
                        val length = txt.length()
                        txt.text = txt.text.subSequence(0, length - 1)
                        txt.text = "${txt.text}-"
                    }
                    txt.text.last().toString() == "÷" -> {
                        val length = txt.length()
                        txt.text = txt.text.subSequence(0, length - 1)
                        txt.text = "${txt.text}-"
                    }
                    txt.text.last().toString() == "×" -> {
                        val length = txt.length()
                        txt.text = txt.text.subSequence(0, length - 1)
                        txt.text = "${txt.text}-"
                    }
                    txt.text.last().toString() == "+" -> {
                        val length = txt.length()
                        txt.text = txt.text.subSequence(0, length - 1)
                        txt.text = "${txt.text}-"
                    }
                    txt.text.last().toString() != "-" -> {
                        txt.text = "${txt.text}-"
                    }
                }
            } else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {

            }

        }

        btn_plus.setOnClickListener {
            Toast.makeText(this, "+", Toast.LENGTH_SHORT).show()
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {

//                if (object1 == 0){
//                    object_math = "+"
//                    object1 = txt.text.toString().toInt()
//                    txt.text = "0"
//                }else if (object1 != 0 && object2 == 0){
//                    object2 = txt.text.toString().toInt()
//                }

                Toast.makeText(this, "$object1", Toast.LENGTH_SHORT).show()
                when {
                    txt.text == "0" -> {
                        txt.text = "0+"
                    }
                    txt.text.last().toString() == "%" -> {
                        val length = txt.length()
                        txt.text = txt.text.subSequence(0, length - 1)
                        txt.text = "${txt.text}+"
                    }
                    txt.text.last().toString() == "×" -> {
                        val length = txt.length()
                        txt.text = txt.text.subSequence(0, length - 1)
                        txt.text = "${txt.text}+"
                    }
                    txt.text.last().toString() == "-" -> {
                        val length = txt.length()
                        txt.text = txt.text.subSequence(0, length - 1)
                        txt.text = "${txt.text}+"
                    }
                    txt.text.last().toString() == "÷" -> {
                        val length = txt.length()
                        txt.text = txt.text.subSequence(0, length - 1)
                        txt.text = "${txt.text}+"
                    }
                    txt.text.last().toString() != "+" -> {
                        txt.text = "${txt.text}+"
                    }

                }
            } else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {

            }


        }

        btn_nuq.setOnClickListener {
            Toast.makeText(this, "nuqta", Toast.LENGTH_SHORT).show()
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                if (txt.text == "0") {
                    txt.text = "0."
                } else if (txt.text.last().toString() != "." &&
                    txt.text.last().toString() != "+" &&
                    txt.text.last().toString() != "-" &&
                    txt.text.last().toString() != "×" &&
                    txt.text.last().toString() != "÷" &&
                    txt.text.last().toString() != "%" &&
                    txt.text.last().toString() != "(" &&
                    txt.text.last().toString() != ")"
                ) {
                    txt.text = "${txt.text}."
                }
            } else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            }
        }


//        btn_qavus.setOnTouchListener(View.OnTouchListener
//        { _, motionEvent ->
//            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
//                gestureDetector.onTouchEvent(motionEvent)
//                when (motionEvent.action) {
//
//                    MotionEvent.ACTION_HOVER_ENTER->{
//                        txt1.text = "ACTION_ENTER"
//                    }
//                    MotionEvent.ACTION_POINTER_DOWN->{
//                        txt1.text = "POINTER_DOWN"
//                    }
//                    MotionEvent.ACTION_SCROLL->{
//                        txt1.text = "SCROLL"
//                    }
//                    MotionEvent.EDGE_LEFT ->{
//                        txt1.text = "LEFT"
//                    }
//                    MotionEvent.ACTION_DOWN -> {
//                    }
//                    MotionEvent.ACTION_UP -> {
//                    }
//                }
//            } else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            }
//
//            return@OnTouchListener true
//        })
        btn_qavus.setOnClickListener {
            Toast.makeText(this, "( )", Toast.LENGTH_SHORT).show()
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                try {

                    val a = txt.text.toString().toInt()
                    val result = a * a
                    txt1.text = result.toString()

                } catch (e: Exception) {
                    txt.text = "ERROR"
                }
            } else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            }
        }
        btn_ten.setOnClickListener {
            Toast.makeText(this, "=", Toast.LENGTH_SHORT).show()
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                val a = "+"
            } else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            }

        }

        //succesfully
        btn_0.setOnClickListener {
            Toast.makeText(this, "0", Toast.LENGTH_SHORT).show()
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                when {
                    txt.text.last().toString() == "-" && txt.text.first()
                        .toString() == "0" && txt.text.length >= 2 -> {
                    }
                    txt.text.last().toString() == "+" && txt.text.first()
                        .toString() == "0" && txt.text.length >= 2 -> {
                    }
                    txt.text.last().toString() == "%" && txt.text.first()
                        .toString() == "0" && txt.text.length >= 2 -> {
                    }
                    txt.text.last().toString() == "×" && txt.text.first()
                        .toString() == "0" && txt.text.length >= 2 -> {
                    }
                    txt.text.last().toString() == "÷" && txt.text.first()
                        .toString() == "0" && txt.text.length >= 2 -> {
                    }
                    txt.text.first().toString() == "0" && txt.text.length < 2 -> {}
                    else -> {
                        txt.text = "${txt.text}0"
                    }
                }
            } else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {

                if (txt.text == "0") {
                    txt.text = "1"
                } else {
                    txt.text = "${txt.text}1"
                }
            }
        }

        //succesfully
        btn_1.setOnClickListener {
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                if (txt.text == "0") {
                    txt.text = "1"
                } else {
                    txt.text = "${txt.text}1"
                }
            } else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {

                if (txt.text == "0") {
                    txt.text = "4"
                } else {
                    txt.text = "${txt.text}4"
                }
            }
        }

        //succesfully
        btn_2.setOnClickListener {
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                if (txt.text == "0") {
                    txt.text = "2"
                } else {
                    txt.text = "${txt.text}2"
                }
            } else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {

                if (txt.text == "0") {
                    txt.text = "5"
                } else {
                    txt.text = "${txt.text}5"
                }
            }
        }

        btn_3.setOnClickListener {
            Toast.makeText(this, "3", Toast.LENGTH_SHORT).show()
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                if (txt.text == "0") {
                    txt.text = "3"
                } else {
                    txt.text = "${txt.text}3"
                }
            } else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            }
        }

        //succesfully
        btn_4.setOnClickListener {
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                if (txt.text == "0") {
                    txt.text = "4"
                } else {
                    txt.text = "${txt.text}4"
                }
            } else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {

                if (txt.text == "0") {
                    txt.text = "9"
                } else {
                    txt.text = "${txt.text}9"
                }
            }
        }

        btn_5.setOnClickListener {
            Toast.makeText(this, "5", Toast.LENGTH_SHORT).show()
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                if (txt.text == "0") {
                    txt.text = "5"
                } else {
                    txt.text = "${txt.text}5"
                }
            } else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            }
        }

        //succesfully
        btn_6.setOnClickListener {
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                if (txt.text == "0") {
                    txt.text = "6"
                } else {
                    txt.text = "${txt.text}6"
                }
            } else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {

                if (txt.text == "0") {
                    txt.text = "2"
                } else {
                    txt.text = "${txt.text}2"
                }
            }
        }

        btn_7.setOnClickListener {
            Toast.makeText(this, "7", Toast.LENGTH_SHORT).show()
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                if (txt.text == "0") {
                    txt.text = "7"
                } else {
                    txt.text = "${txt.text}7"
                }
            } else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {

                if (txt.text == "0") {
                    txt.text = "<"
                } else {
                    txt.text = "${txt.text}<"
                }
            }
        }

        //succesfully
        btn_8.setOnClickListener {
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                if (txt.text == "0") {
                    txt.text = "8"
                } else {
                    txt.text = "${txt.text}8"
                }
            } else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {

                if (txt.text == "0") {
                    txt.text = "6"
                } else {
                    txt.text = "${txt.text}6"
                }
            }
        }

        //succesfully
        btn_9.setOnClickListener {
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                if (txt.text == "0") {
                    txt.text = "9"
                } else {
                    txt.text = "${txt.text}9"
                }
            } else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {

                if (txt.text == "0") {
                    txt.text = "7"
                } else {
                    txt.text = "${txt.text}7"
                }
            }
        }


        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {

            grid_layout.columnCount = 4

            backpace_img.visibility = View.GONE
            txt_7.visibility = View.VISIBLE
            backpace.visibility = View.VISIBLE
            txt_x2.visibility = View.GONE
            txt_5.setText(R.string._5)
            txt_3.setText(R.string._3)
            txt_plus.setText(R.string.plus)
            txt_qavus.setText(R.string.x2)
            txt_0.setText(R.string._0)
            txt_6.setText(R.string._6)
            txt_minus.setText(R.string.minus)
            txt_1.setText(R.string._1)


            txt_nuq.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimensionPixelSize(R.dimen.nuq_default_size).toFloat()
            )

            txt_c.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimensionPixelSize(R.dimen.default_text_size).toFloat()
            )

            txt_foiz.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimensionPixelSize(R.dimen.default_text_size).toFloat()
            )

            txt_bolu.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimensionPixelSize(R.dimen.default_text_size).toFloat()
            )

            txt_x.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimensionPixelSize(R.dimen.default_text_size).toFloat()
            )

            txt_minus.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimensionPixelSize(R.dimen.default_text_size).toFloat()
            )

            txt_plus.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimensionPixelSize(R.dimen.default_text_size).toFloat()
            )

            txt_qavus.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimensionPixelSize(R.dimen.default_text_size).toFloat()
            )

            txt_0.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimensionPixelSize(R.dimen.default_text_size).toFloat()
            )

            txt_1.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimensionPixelSize(R.dimen.default_text_size).toFloat()
            )

            txt_2.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimensionPixelSize(R.dimen.default_text_size).toFloat()
            )

            txt_3.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimensionPixelSize(R.dimen.default_text_size).toFloat()
            )

            txt_4.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimensionPixelSize(R.dimen.default_text_size).toFloat()
            )

            txt_5.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimensionPixelSize(R.dimen.default_text_size).toFloat()
            )

            txt_6.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimensionPixelSize(R.dimen.default_text_size).toFloat()
            )

            txt_7.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimensionPixelSize(R.dimen.default_text_size).toFloat()
            )

            txt_8.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimensionPixelSize(R.dimen.default_text_size).toFloat()
            )

            txt_9.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimensionPixelSize(R.dimen.default_text_size).toFloat()
            )

        }
        else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {

            grid_layout.columnCount = 5

            backpace_img.visibility = View.VISIBLE
            txt_7.visibility = View.GONE
            backpace.visibility = View.GONE
            txt_x2.visibility = View.VISIBLE
            txt_5.setText(R.string.minus)
            txt_5.scaleX = 1.5F
            txt_3.setText(R.string.plus)
            txt_plus.setText(R.string.x2)
            adView.visibility = View.GONE

            val param = txt_minus.layoutParams as MarginLayoutParams
            param.setMargins(11, 7, 11, 7)
            txt_minus.layoutParams = param

            val layoutParams = (btn_backspace?.layoutParams as? MarginLayoutParams)
            layoutParams?.setMargins(22, 12, 22, 12)
            btn_backspace?.layoutParams = layoutParams

            val layoutParams2 = (btn_7?.layoutParams as? MarginLayoutParams)
            layoutParams2?.setMargins(5, 2, 5, 2)
            btn_7?.layoutParams = layoutParams2


            txt_qavus.setText(R.string._0)
            txt_0.setText(R.string._1)
            txt_6.setText(R.string._2)
            txt_minus.setText(R.string._3)
            txt_1.setText(R.string._4)
            txt_2.setText(R.string._5)
            txt_8.setText(R.string._6)
            txt_9.setText(R.string._7)
            txt_x.setText(R.string._8)
            txt_4.setText(R.string._9)


            txt_x2.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimensionPixelSize(R.dimen.portrait_text_size).toFloat()
            )

            txt_nuq.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimensionPixelSize(R.dimen.portrait_text_size).toFloat()
            )

            txt_c.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimensionPixelSize(R.dimen.portrait_text_size).toFloat()
            )

            txt_foiz.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimensionPixelSize(R.dimen.portrait_text_size).toFloat()
            )

            txt_bolu.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimensionPixelSize(R.dimen.portrait_text_size).toFloat()
            )

            txt_x.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimensionPixelSize(R.dimen.portrait_text_size).toFloat()
            )

            txt_minus.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimensionPixelSize(R.dimen.portrait_text_size).toFloat()
            )

            txt_plus.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimensionPixelSize(R.dimen.portrait_text_size).toFloat()
            )

            txt_qavus.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimensionPixelSize(R.dimen.portrait_text_size).toFloat()
            )

            txt_0.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimensionPixelSize(R.dimen.portrait_text_size).toFloat()
            )

            txt_1.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimensionPixelSize(R.dimen.portrait_text_size).toFloat()
            )

            txt_2.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimensionPixelSize(R.dimen.portrait_text_size).toFloat()
            )

            txt_3.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimensionPixelSize(R.dimen.portrait_text_size).toFloat()
            )

            txt_4.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimensionPixelSize(R.dimen.portrait_text_size).toFloat()
            )

            txt_5.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimensionPixelSize(R.dimen.portrait_text_size).toFloat()
            )

            txt_6.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimensionPixelSize(R.dimen.portrait_text_size).toFloat()
            )

            txt_7.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimensionPixelSize(R.dimen.portrait_text_size).toFloat()
            )

            txt_8.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimensionPixelSize(R.dimen.portrait_text_size).toFloat()
            )

            txt_9.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimensionPixelSize(R.dimen.portrait_text_size).toFloat()
            )

        }

//        txt.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//            }
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//            }
//
//            override fun afterTextChanged(p0: Editable?) {
////                if (txt.text.isNotEmpty()){
////                    txt1.visibility = View.VISIBLE
////                }else
////                    txt1.visibility = View.GONE
//            }
//
//        })

    }

    fun find(
        input: CharSequence,
        startIndex: Int = 0
    ): MatchResult?{
        return null
    }

//    override fun onResume() {
//        super.onResume()
//        txt.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
//            }
//
//            override fun afterTextChanged(s: Editable) {
//
//            }
//
//            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
//                if (txt.text != "0")
//                    txt1.visibility = View.VISIBLE
//                if (txt.text == "0")
//                    txt1.visibility = View.GONE
//            }
//
//
//
//        })
//    }

    override fun onDestroy() {
        super.onDestroy()

        val sharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        val name = txt.text.toString()

        editor.apply {
            putString("name", name)
            apply()
        }

    }

    fun hisobla(){
        txt.text.toString().toInt()
    }

    class someTask() : AsyncTask<Void, Void, String>() {
        override fun doInBackground(vararg params: Void?): String? {

            return null
        }

        override fun onPreExecute() {
            super.onPreExecute()

        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

        }
    }

    @Suppress("UNREACHABLE_CODE")
    override fun onTouchEvent(event: MotionEvent?): Boolean {

//        when (event?.action) {
//            0 -> {
//                x1 = event.x
//                y1 = event.y
//            }
//            1 -> {
//                x2 = event.x
//                y2 = event.y
//
//                val valueX: Float = x2 - x1
//                val valueY: Float = y2 - y1
//
//                if (abs(valueX) > MIN_DISTANCE) {
//                    if (x2 > x1) {
//                        Toast.makeText(this, "RIGHT", Toast.LENGTH_SHORT).show()
//                    } else {
//                        Toast.makeText(this, "LEFT", Toast.LENGTH_SHORT).show()
//                    }
//                } else if (abs(valueY) > MIN_DISTANCE) {
//                    if (y2 > y1) {
//                        Toast.makeText(this, "BOTTOM", Toast.LENGTH_SHORT).show()
//                    } else {
//                        Toast.makeText(this, "TOP", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }
//        }
        return super.onTouchEvent(event)
    }

    override fun onDown(p0: MotionEvent?): Boolean {
        return false
    }

    override fun onShowPress(p0: MotionEvent?) {
//        txt.text = p0.toString()
    }

    override fun onSingleTapUp(p0: MotionEvent?): Boolean {
//        txt1.text = p0.toString()
        return false
    }

    override fun onScroll(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
        return false
    }

    override fun onLongPress(p0: MotionEvent?) {
    }

    override fun onFling(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
        return false
    }
}