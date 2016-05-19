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

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Saphiro on 5/16/2016.
 */
public class Fragment2 extends android.support.v4.app.Fragment{

    EditText edtUsername;
    EditText edtPass;
    EditText edtEmail;

    Button register;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.register, container, false);
        edtUsername = (EditText) root.findViewById(R.id.username);
        edtPass = (EditText) root.findViewById(R.id.password);
        edtEmail = (EditText) root.findViewById(R.id.email);

        register = (Button) root.findViewById(R.id.btnRegister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        return root;
    }

    private void signup() {
        boolean valid = validate();
        if (valid){
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("info", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("UserID", edtUsername.getText().toString());
            editor.putString("Password", edtPass.getText().toString());
            editor.putString("Email", edtEmail.getText().toString());
//            Set<String> users = new HashSet<>();
//            users.add(edtUsername.getText().toString());
//
//            editor.putStringSet("info2", users);
            editor.commit();

            Intent intent = new Intent(getActivity(), Navigation.class);
            startActivity(intent);
        }
    }

    private boolean validate() {
        boolean valid = false;
        String username = edtUsername.getText().toString();
        String password = edtPass.getText().toString();
        String email = edtEmail.getText().toString();

        if(username.isEmpty() || username.length()<8){
            edtUsername.setError("Username cannot be empty and must have at least 8 characters!");
            valid = false;
        }
        else if (password.isEmpty() || password.length()<4 || password.length() > 10){
            edtPass.setError("Password cannot be empty and must have at 4-10 characters!");
        }
        else if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            edtEmail.setError("Email type is invalid");
        }
        else {
            edtEmail.setError(null);
            valid = true;
        }

        return valid;
    }


}
