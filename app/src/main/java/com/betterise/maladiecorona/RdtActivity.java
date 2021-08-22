package com.betterise.maladiecorona;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.betterise.maladiecorona.databinding.ActivityRdtBinding;

import org.rdtoolkit.support.interop.RdtIntentBuilder;
import org.rdtoolkit.support.interop.RdtUtils;
import org.rdtoolkit.support.model.session.ProvisionMode;
import org.rdtoolkit.support.model.session.TestSession;

import java.util.UUID;

public class RdtActivity extends AppCompatActivity {


    private static final int ACTIVITY_PROVISION = 1;


    private AppBarConfiguration mAppBarConfiguration;
    private ActivityRdtBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        binding = ActivityRdtBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarRdt.toolbar);
        binding.appBarRdt.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Check your Result", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_rdt);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.rdt, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_rdt);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void simulateTestRequest(View view) {
        Intent i = RdtIntentBuilder
                .forProvisioning().setSessionId(UUID.randomUUID().toString())
                //.requestTestProfile("debug_mal_pf_pv")
                //.requestTestProfile("sd_bioline_mal_pf_pv")
                .requestProfileCriteria("mal_pf", ProvisionMode.CRITERIA_SET_AND)
                //.requestProfileCriteria("sd_bioline_mal_pf_pv carestart_mal_pf_pv", ProvisionMode.CRITERIA_SET_OR)
                //.requestProfileCriteria("fake", ProvisionMode.CRITERIA_SET_OR)
                .setFlavorOne("MUTABAZI JOSUE")
                .setFlavorTwo("#4SFS")
                //.setClassifierBehavior(ClassifierMode.CHECK_YOUR_WORK)
                .setInTestQaMode()
                //.setSecondaryCaptureRequirements("capture_windowed")
                //.setSubmitAllImagesToCloudworks(true)
                .setIndeterminateResultsAllowed(true)
                .build();

        this.startActivityForResult(i, ACTIVITY_PROVISION);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ACTIVITY_PROVISION && resultCode == RESULT_OK) {
            TestSession session = RdtUtils.getRdtSession(data);
            System.out.println(String.format("Test will be available to read at %s", session.getTimeResolved().toString()));
        }
    }



}