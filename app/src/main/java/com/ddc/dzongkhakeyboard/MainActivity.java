package com.ddc.dzongkhakeyboard;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener, CompoundButton.OnCheckedChangeListener{
    Button enableSetting;
    Button addkeyboards;
    Button setting;
    private Switch aSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        enableSetting = findViewById(R.id.keyboard_button);
        addkeyboards = findViewById(R.id.edit_button);
        setting = findViewById(R.id.setting);

        enableSetting.setOnClickListener(this);
        addkeyboards.setOnClickListener(this);
        setting.setOnClickListener(this);

        aSwitch =findViewById(R.id.switch1);
        aSwitch.setOnCheckedChangeListener(this);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.homeid);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener(){
            @Override
            public  boolean onNavigationItemSelected (MenuItem item){
                switch (item.getItemId()){
                    case R.id.homeid:
                        break;
                    case R.id.aboutid:
                        startActivity(new Intent(getApplicationContext(),About.class));
                        finish();
                        overridePendingTransition(0,0);
                        break;
                }
                return  true;
            }

        });
    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Are you sure you want to Exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
//                            System.exit(0);

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    @Override
    public void onCheckedChanged (CompoundButton buttonView,boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.switch1:
                if (isChecked)
                    startActivity(new Intent(getApplicationContext(), MainActivity_dzo.class));
                overridePendingTransition(0,0);
                finish();

        }

    }
    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.keyboard_button:
                startActivityForResult(
                        new Intent(android.provider.Settings.ACTION_INPUT_METHOD_SETTINGS), 0);
                break;
            case R.id.edit_button:

                ((InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE))
                        .showInputMethodPicker();

                break;
            case R.id.setting:
                startActivity(new Intent(this, Setting.class));
                overridePendingTransition(0,0);
                break;

            default:
                break;

        }

    }
}
