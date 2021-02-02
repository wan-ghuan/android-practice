package com.alex.wang.lean.practice;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alex.wang.lean.practice.dialog.AlertDialog;
import com.alex.wang.lean.practice.dialog.ListDialog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private AlertDialog mAlertDialog;
    private ListDialog mListDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button alert = findViewById(R.id.dialog_alert);
        alert.setOnClickListener(this);
        Button list = findViewById(R.id.dialog_list);
        list.setOnClickListener(this);

        mAlertDialog = new AlertDialog();
        mListDialog = new ListDialog();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.dialog_alert) {
            mAlertDialog.setAlertIcon(getDrawable(R.drawable.icon_info));
            mAlertDialog.setAlertText("I'm Alex!");
            mAlertDialog.show(getSupportFragmentManager(), "dialog_alert");
        } else if (view.getId() == R.id.dialog_list) {
            List<String> itemList = new ArrayList<>();
            itemList.add("Log in with password");
            itemList.add("QR Code Login");
            itemList.add("Log in via Whatapps");
            mListDialog.setItemList(itemList);
            mListDialog.setOnItemClickListener((parent, view1, position, id) -> {

            });
            mListDialog.show(getSupportFragmentManager(), "dialog_list");
        }
    }
}
