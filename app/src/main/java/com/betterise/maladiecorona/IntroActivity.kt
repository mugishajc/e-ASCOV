package com.betterise.maladiecorona

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_intro.*
import java.util.*

class IntroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)


        btn_start.setOnClickListener{ startActivity(Intent(this, QuestionActivity::class.java))}
        btn_export.setOnClickListener{ startActivity(Intent(this, ExportActivity::class.java))}
        btn_agent.setOnClickListener{
            startActivity(Intent(this, AgentActivity::class.java))
            finish()
        }

        when (resources.configuration.locale.language){

            "fr" -> {
                lang_fr.setBackgroundResource(R.drawable.shape_button)
                lang_en.setBackgroundResource(R.drawable.shape_button_white)
                lang_rw.setBackgroundResource(R.drawable.shape_button_white)

                lang_fr.setTextColor(Color.WHITE)
                lang_en.setTextColor(Color.BLACK)
                lang_rw.setTextColor(Color.BLACK)
            }

            "en" -> {
                lang_fr.setBackgroundResource(R.drawable.shape_button_white)
                lang_en.setBackgroundResource(R.drawable.shape_button)
                lang_rw.setBackgroundResource(R.drawable.shape_button_white)

                lang_fr.setTextColor(Color.BLACK)
                lang_en.setTextColor(Color.WHITE)
                lang_rw.setTextColor(Color.BLACK)
            }

            "rw" -> {
                lang_fr.setBackgroundResource(R.drawable.shape_button_white)
                lang_en.setBackgroundResource(R.drawable.shape_button_white)
                lang_rw.setBackgroundResource(R.drawable.shape_button)

                lang_fr.setTextColor(Color.BLACK)
                lang_en.setTextColor(Color.BLACK)
                lang_rw.setTextColor(Color.WHITE)
            }
        }


        lang_fr.setOnClickListener {changeLocale("fr")}

        lang_en.setOnClickListener {changeLocale("en")}

        lang_rw.setOnClickListener {changeLocale("rw")}
    }

    private fun changeLocale(lang : String){
        val locale = Locale(lang)
        val conf = resources.configuration
        conf.setLocale(locale)
        resources.updateConfiguration(conf, resources.displayMetrics)
        finish()
        startActivity(Intent(this, IntroActivity::class.java ))
    }


}