package com.saphiro.dangnhap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Saphiro on 5/16/2016.
 */
public class Fragment1 extends android.support.v4.app.Fragment{

    EditText edtUsername;
    EditText edtPass;
    CheckBox checked;

    Button login;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.login, container, false);
        edtUsername = (EditText) root.findViewById(R.id.username);
        edtPass = (EditText) root.findViewById(R.id.password);
        checked = (CheckBox) root.findViewById(R.id.checked);
        login = (Button) root.findViewById(R.id.btnLogin);

        final SharedPreferences sharedPreferences = getActivity().getSharedPreferences("info", Context.MODE_PRIVATE);
        edtUsername.setText(sharedPreferences.getString("UserID", ""));

        checked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signin(sharedPreferences);
            }
        });

        return root;
    }

    private void signin(SharedPreferences sharedPreferences) {
        boolean valid = validate(sharedPreferences);
        if (valid){

            Intent intent = new Intent(getActivity(), Navigation.class);
            startActivity(intent);
        }

    }

    private boolean validate(SharedPreferences sharedPreferences) {
        boolean valid = false;
        String username = edtUsername.getText().toString();
        String password = edtPass.getText().toString();

        if(username.equals(sharedPreferences.getString("UserID", ""))
                && password.equals(sharedPreferences.getString("Password", ""))){
            valid = true;}
        else Toast.makeText(getContext(), "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show();

        return valid;
    }




}
