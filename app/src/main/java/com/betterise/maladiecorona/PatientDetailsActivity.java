package com.betterise.maladiecorona;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PatientDetailsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private Spinner sp_gender,sp_nationality,sp_residence;
    private String nationality,gender, yearr, month, date,residence;
    private ImageButton btndatepicker;
    private Button btn_next;
    private ImageView btn_back;
    private TextView tvdob;
    private int mYear, mMonth, mDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details);

        sp_gender=findViewById(R.id.sp_gender);
        sp_nationality=findViewById(R.id.sp_nationality);
        sp_residence=findViewById(R.id.spresidence);

        tvdob=findViewById(R.id.tvdob);
        btndatepicker=findViewById(R.id.btndatepicker);
        btndatepicker.setOnClickListener(this);

        sp_nationality.setOnItemSelectedListener(this);

        List<String> listcountry = new ArrayList<String>();

        listcountry.add("Rwanda");
        listcountry.add("Tanzania");
        listcountry.add("Burundi");
        listcountry.add("Kenya");
        listcountry.add("Uganda");
        listcountry.add("DRC");
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listcountry);
        // Drop down layout style - list view with radio button
        ArrayAdapter<String> dataAdapte = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listcountry);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapte.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        sp_nationality.setAdapter(dataAdapter);
        sp_residence.setAdapter(dataAdapte);

        List<String> listgender = new ArrayList<String>();

        listgender.add("Female");
        listgender.add("Male");
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdaptergender = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listgender);
        // Drop down layout style - list view with radio button
        dataAdaptergender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_gender.setAdapter(dataAdaptergender);

        sp_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(PatientDetailsActivity.this, "sex is "+parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();

                gender=parent.getItemAtPosition(position)+"";
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp_residence.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                residence = parent.getItemAtPosition(position).toString();
                Toast.makeText(PatientDetailsActivity.this, "country of residence is "+residence, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btn_next=findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);
        btn_back=findViewById(R.id.btn_backe);
        btn_back.setOnClickListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        nationality = parent.getItemAtPosition(position).toString();
        Toast.makeText(this, "taped "+nationality, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btndatepicker:

// Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(PatientDetailsActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                tvdob.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                                month = (monthOfYear + 1) + "";
                                yearr = year + "";
                                date = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();



                break;

            case R.id.btn_next:
                startActivity(new Intent(PatientDetailsActivity.this,QuestionActivity.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
                break;

            case R.id.btn_back:
         overridePendingTransition(R.anim.fadeout,R.anim.fadein);
         finish();
            break;

        }
    }
}