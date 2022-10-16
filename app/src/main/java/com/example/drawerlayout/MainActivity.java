package com.example.drawerlayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToogle;

    Button mybtn,so0,so1,so2,so3,so4,so5,so6,so7,so8,so9,xoa;
    TextView txt,mytxt;
    Spinner spin;
    int re;
    final int moneyArrVND[] = {500000,200000,100000,50000,20000,10000,5000,2000,1000};
    int checkVND[] = {0,0,0,0,0,0,0,0,0};
    final int moneyUSD[] = {100,50,20,10,5,2,1};
    int checkUSD[] = {0,0,0,0,0,0,0};

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToogle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mytxt = (TextView) findViewById(R.id.txtTitle);
        mybtn = (Button) findViewById(R.id.buttonShow);
        txt = (TextView) findViewById(R.id.textView);
        so0 = (Button) findViewById(R.id.so0);
        so0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mytxt.setText(mytxt.getText().toString() + "0");
            }
        });
        so1 = (Button) findViewById(R.id.so1);
        so1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mytxt.setText(mytxt.getText().toString() + "1");
            }
        });
        so2 = (Button) findViewById(R.id.so2);
        so2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mytxt.setText(mytxt.getText().toString() + "2");
            }
        });
        so3 = (Button) findViewById(R.id.so3);
        so3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mytxt.setText(mytxt.getText().toString() + "3");
            }
        });
        so4 = (Button) findViewById(R.id.so4);
        so4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mytxt.setText(mytxt.getText().toString() + "4");

            }
        });
        so5 = (Button) findViewById(R.id.so5);
        so5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mytxt.setText(mytxt.getText().toString() + "5");
            }
        });
        so6 = (Button) findViewById(R.id.so6);
        so6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mytxt.setText(mytxt.getText().toString() + "6");
            }
        });
        so7 = (Button) findViewById(R.id.so7);
        so7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mytxt.setText(mytxt.getText().toString() + "7");
            }
        });
        so8 = (Button) findViewById(R.id.so8);
        so8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mytxt.setText(mytxt.getText().toString() + "8");
            }
        });
        so9 = (Button) findViewById(R.id.so9);
        so9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mytxt.setText(mytxt.getText().toString() + "9");
            }
        });
        xoa = (Button) findViewById(R.id.C);
        xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mytxt.setText("");
                txt.setText("");
            }
        });

        String[] currency = {"VND", "USD"};
        spin = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter adapter = new ArrayAdapter<String>(MainActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, currency);
        spin.setAdapter(adapter);
        mybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dulieu = mytxt.getText().toString();
                re = Integer.parseInt(dulieu);
                String chuoi = "";
                if (spin.getSelectedItem().toString() == "VND") {
                    for (int i = 0; i < 9; i++) {
                        while (re >= moneyArrVND[i]) {
                            checkVND[i] += 1;
                            re -= moneyArrVND[i];
                        }
                    }

                    boolean checked = false;
                    for (int i = 0; i < 9; i++) {
                        if (checked == false && checkVND[i] == 0) {
                            continue;
                        } else {
                            checked = true;
                            chuoi += moneyArrVND[i] + " VND : " + checkVND[i] + "\n";
                        }
                    }
                    txt.setText(chuoi);
                } else if (spin.getSelectedItem().toString() == "USD") {
                    for (int i = 0; i < 7; i++) {
                        while (re >= moneyUSD[i]) {
                            checkUSD[i] += 1;
                            re -= moneyUSD[i];
                        }
                    }

                    boolean checked = false;
                    for (int i = 0; i < 7; i++) {
                        if (checked == false && checkUSD[i] == 0) {
                            continue;
                        } else {
                            checked = true;
                            chuoi += "$" + moneyUSD[i] + " : " + checkUSD[i] + "\n";
                        }
                    }
                    txt.setText(chuoi);
                }
            }
        });



        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        drawerToogle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(drawerToogle);
        drawerToogle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home: {
                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.setting: {
                        Intent intent = new Intent(MainActivity.this, setting.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.exit: {
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle(R.string.exit_caption)
                                .setMessage(R.string.exit_content)
                                .setNegativeButton(R.string.yes, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        System.exit(0);
                                    }
                                })
                                .setPositiveButton(R.string.no, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        return;
                                    }
                                }).show();
                        break;
                    }

                }
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }
}