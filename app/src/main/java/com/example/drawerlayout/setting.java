package com.example.drawerlayout;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import dataBase.MoneyDatabase;

public class setting extends AppCompatActivity {

    private static final int REQUESTCODE = 10;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToogle;


    private MoneyAdapter mMoneyAdapter;
    private List<Money> mMoneyList;


    EditText editmoney,editprice;
    Button btn_add;
    private RecyclerView rcvMoney;


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToogle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_layout);


        editmoney = findViewById(R.id.editcurrency);
        editprice = findViewById(R.id.editprice);
        rcvMoney = findViewById(R.id.rcv_money);
        btn_add = findViewById(R.id.btn_add);

        mMoneyAdapter = new MoneyAdapter(new MoneyAdapter.ClickItemMoney() {
            @Override
            public void updateMoney(Money money) {
                clickUpdateMoney(money);
            }

            @Override
            public void deleteMoney(Money money) {
                clickDeleteMoney(money);
            }
        });
        mMoneyList = new ArrayList<>();

        mMoneyAdapter.setData(mMoneyList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvMoney.setLayoutManager(linearLayoutManager);
        rcvMoney.setAdapter(mMoneyAdapter);


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMoney();
            }
        });
        loadData();

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
                        Intent intent = new Intent(setting.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.setting: {
                        Intent intent = new Intent(setting.this, setting.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.exit: {
                        new AlertDialog.Builder(setting.this)
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

    private void clickDeleteMoney(Money money) {
        new AlertDialog.Builder(this).setTitle("Comfirm delete Money")
                .setMessage("Are you sure?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MoneyDatabase.getInstance(setting.this).mMoneyDAO().deleteMoney(money);
                        Toast.makeText(setting.this, "Deleted", Toast.LENGTH_SHORT).show();
                        loadData();
                    }
                })
                .setNegativeButton("No",null)
                .show();
    }

    private void clickUpdateMoney(Money money) {
            Intent intent = new Intent(setting.this,UpdateActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("object_money",money);
            intent.putExtras(bundle);
            startActivityForResult(intent,REQUESTCODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUESTCODE && resultCode == Activity.RESULT_OK){
            loadData();
        }
    }

    private void addMoney() {
        String strMoney = editmoney.getText().toString().trim();
        String strPrice = editprice.getText().toString().trim();
        if (TextUtils.isEmpty(strMoney)||TextUtils.isEmpty(strPrice)){
            return;
        }
        Money money = new Money(strMoney,strPrice);
        if (isMoneyExist(money)){
            Toast.makeText(this, "Exist", Toast.LENGTH_SHORT).show();
            return;
        }
        MoneyDatabase.getInstance(this).mMoneyDAO().insertMoney(money);
        Toast.makeText(this, "Successed", Toast.LENGTH_SHORT).show();
        editmoney.setText("");
        editprice.setText("");
        loadData();
    }

    public void loadData(){
        mMoneyList = MoneyDatabase.getInstance(this).mMoneyDAO().getListMoney();
        mMoneyAdapter.setData(mMoneyList);
    }

    public boolean isMoneyExist(Money money){
        List<Money> list = MoneyDatabase.getInstance(this).mMoneyDAO().checkData(money.getMoney());
        return list != null  && !list.isEmpty();
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


