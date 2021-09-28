package com.betterise.maladiecorona;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.rdtoolkit.support.interop.RdtIntentBuilder;
import org.rdtoolkit.support.interop.RdtUtils;
import org.rdtoolkit.support.model.session.ProvisionMode;
import org.rdtoolkit.support.model.session.TestSession;

public class TestRDTActivity extends AppCompatActivity {
    private Button gotordt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_rdtactivity);

        gotordt=findViewById(R.id.btngotordt);
        gotordt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = RdtIntentBuilder.forProvisioning()
                        .setSessionId("123456ko") // Explicitly declare an ID for the session
                        .requestProfileCriteria("sars_cov2", ProvisionMode.CRITERIA_SET_AND) // Let the user choose any available RDT which provides a PF and a PV result
                        .setFlavorOne("Mugisha Jean Claude") // Text to differentiate running tests
                        .setFlavorTwo("kim123") // Text to differentiate running tests
                        .setReturnApplication(TestRDTActivity.this)
                        .build();
                TestRDTActivity.this.startActivityForResult(i, 7);

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {

                        Intent iz = RdtIntentBuilder.forCapture()
                                .setSessionId("123456ko") //Populated during provisioning callout, or result
                                .build();

                        TestRDTActivity.this.startActivityForResult(iz, 2);

                    }
                }, 6000);

            }
        });
    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 7 && resultCode == RESULT_OK) {
            TestSession session = RdtUtils.getRdtSession(data);
            System.out.println(String.format("Test will be available to read at %s", session.getTimeResolved().toString()));
            Toast.makeText(this, "Activity-Result: " + session.getTimeResolved().toString(), Toast.LENGTH_LONG).show();


        }else if (requestCode == 2 && resultCode == RESULT_OK){

            TestSession sess = RdtUtils.getRdtSession(data);
            TestSession.TestResult result = sess.getResult();

            String red=String.format("result is  %s", sess.getResult().toString());
            String u[]=red.split(",",100);


            String ibisubizoo = u[4].substring(u[4].indexOf("{")+1,u[4].indexOf("}"));

            String str;
            if (ibisubizoo.equals("sars_cov2=sars_cov2_pos")){
                 str="POSITIVE";
            }else if (ibisubizoo.equals("sars_cov2=sars_cov2_neg")){
                str="NEGATIVE";
            }else if (ibisubizoo.equals("sars_cov2=universal_control_failure")){
                str="Invalid Test";
            }else {
                str="Error occured";
            }

            AlertDialog.Builder builder=new AlertDialog.Builder(TestRDTActivity.this);
            builder.setMessage("IGISUBIZO NI : "+str);
            builder.setCancelable(false);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.show();

        }
    }

}