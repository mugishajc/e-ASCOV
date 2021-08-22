package com.betterise.maladiecorona

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.betterise.maladiecorona.model.ResultType
import kotlinx.android.synthetic.main.activity_results.*
import org.rdtoolkit.support.interop.RdtIntentBuilder
import org.rdtoolkit.support.model.session.ProvisionMode
import java.util.*

/**
 * Created by Alexandre on 24/06/20.
 */

class ResultActivity : AppCompatActivity() {

    private  val ACTIVITY_PROVISION = 1

//    private val binding: ActivityRdtBinding? = null


    companion object { const val EXTRA_RESULT = "EXTRA_RESULT"}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)



        val res: ResultType = intent.extras?.getSerializable(EXTRA_RESULT) as ResultType



        result_text.text =
            "Names  "+ "             null"  +"\n"+  "\n"+
                    "Gender  "+ "              null" +  "\n" +"\n"+
                    "Telephone  "+ "           null"+  "\n"+"\n"+
                    "Date of Birth  "+ "      null" +  "\n" +"\n"+
                    "National ID  "+ "            null"+  "\n"  +"\n"+
                    "Results with e-ASCOV                        "+


            getString(
            when (res) {
                ResultType.CASE1 -> R.string.result1
                ResultType.CASE2 -> R.string.result2
                ResultType.CASE3 -> R.string.result3
                ResultType.CASE3bis -> R.string.result3bis
                ResultType.CASE4 -> R.string.result4
                ResultType.CASE5 -> R.string.result5


            }

        )+"\n"+  "\n"+  "\n"+  "\n"+"\n"+"RDT Toolkit test type"  +  "        COVID-19 Ag Test"+
                    "\n"+  "\n"+ "\n"+ "Results with RDT Toolkit" + "     null"




        btn_start.setOnClickListener {
            startActivity(Intent(this, IntroActivity::class.java))
         overridePendingTransition(R.anim.fadein,R.anim.fadeout)
            finish()
        }
    }

    fun simulateTestRequest(view: View?) {
        val i = RdtIntentBuilder.forProvisioning()
            .setSessionId(UUID.randomUUID().toString()) //.requestTestProfile("debug_mal_pf_pv")
            //.requestTestProfile("sd_bioline_mal_pf_pv")
            .requestProfileCriteria(
                "mal_pf",
                ProvisionMode.CRITERIA_SET_AND
            ) //.requestProfileCriteria("sd_bioline_mal_pf_pv carestart_mal_pf_pv", ProvisionMode.CRITERIA_SET_OR)
            //.requestProfileCriteria("fake", ProvisionMode.CRITERIA_SET_OR)
            .setFlavorOne("MUTABAZI JOSUE")
            .setFlavorTwo("#4SFS") //.setClassifierBehavior(ClassifierMode.CHECK_YOUR_WORK)
            .setInTestQaMode() //.setSecondaryCaptureRequirements("capture_windowed")
            //.setSubmitAllImagesToCloudworks(true)
            .setIndeterminateResultsAllowed(true)
            .build()
        this.startActivityForResult(i, 1)
    }
}