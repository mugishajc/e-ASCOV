package com.betterise.maladiecorona

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.betterise.maladiecorona.managers.AgentManager
import kotlinx.android.synthetic.main.activity_agent.*

/**
 * Created by Alexandre on 01/07/20.
 */

class AgentActivity : AppCompatActivity(){

    companion object {
        const val PHONE_SIZE = 10
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agent)

        phone.setSelection(4)
        phone.doOnTextChanged { text, _, _, _ -> btn_next.isEnabled = !text.isNullOrEmpty() }
        btn_next.setOnClickListener {
            if (phone.text.toString().replace(" ","").length == PHONE_SIZE)
                validate(phone.text.toString())
            else {
                error.visibility = View.VISIBLE
                phone.setBackgroundResource(R.drawable.edit_bg_error)
                btn_next.isEnabled = false
            }
        }

        phone.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus)
                phone.setBackgroundResource(R.drawable.edit_bg_focused)

            btn_next.isEnabled = true
            error.visibility = View.INVISIBLE
        }
    }

    private fun validate(agentNumber : String){
        AgentManager().saveAgentNumber(this, agentNumber)
        startActivity(Intent(this, IntroActivity::class.java))
        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, IntroActivity::class.java))
    }
}