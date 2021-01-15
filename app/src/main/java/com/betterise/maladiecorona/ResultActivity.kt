package com.betterise.maladiecorona

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.betterise.maladiecorona.model.ResultType
import kotlinx.android.synthetic.main.activity_results.*

/**
 * Created by Alexandre on 24/06/20.
 */
class ResultActivity : AppCompatActivity() {

    companion object { const val EXTRA_RESULT = "EXTRA_RESULT"}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        val res : ResultType = intent.extras?.getSerializable(EXTRA_RESULT) as ResultType

        result_text.text = getString(
            when (res){
                ResultType.CASE1 -> R.string.result1
                ResultType.CASE2 -> R.string.result2
                ResultType.CASE3 -> R.string.result3
                ResultType.CASE3bis -> R.string.result3bis
                ResultType.CASE4 -> R.string.result4
                ResultType.CASE5 -> R.string.result5
            }
        )

        btn_start.setOnClickListener {
            startActivity(Intent(this, IntroActivity::class.java))
            finish()
        }
    }
}