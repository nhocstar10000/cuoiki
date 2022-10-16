package com.example.drawerlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import dataBase.MoneyDatabase;

public class UpdateActivity extends AppCompatActivity {
    EditText editmoney,editprice;
    Button btn_update_money;

    private Money mMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        editmoney = findViewById(R.id.editcurrency);
        editprice = findViewById(R.id.editprice);
        btn_update_money = findViewById(R.id.btn_update_money);

        mMoney = (Money) getIntent().getExtras().get("object_money");
        if (mMoney != null){
            editmoney.setText(mMoney.getMoney());
            editprice.setText(mMoney.getPrice());
        }
        btn_update_money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateMoney();
            }
        });

    }

    private void updateMoney() {
        String strMoney = editmoney.getText().toString().trim();
        String strPrice = editprice.getText().toString().trim();
        if (TextUtils.isEmpty(strMoney)||TextUtils.isEmpty(strPrice)){
            return;
        }
        mMoney.setMoney(strMoney);
        mMoney.setPrice(strPrice);
        MoneyDatabase.getInstance(this).mMoneyDAO().updateMoney(mMoney);
        Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
        Intent intentresult = new Intent();
        setResult(Activity.RESULT_OK,intentresult);
        finish();
    }
}