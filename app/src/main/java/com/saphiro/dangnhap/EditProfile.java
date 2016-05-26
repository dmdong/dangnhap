package com.saphiro.dangnhap;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Saphiro on 5/19/2016.
 */
public class EditProfile extends AppCompatActivity{

    EditText changeuser;
    EditText changemail;
    Button submit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);

        changeuser = (EditText) findViewById(R.id.new_username);
        changemail = (EditText) findViewById(R.id.new_email);
        submit = (Button) findViewById(R.id.btnChange);
        //String content = readFile();
        final ReadWrite readWrite = new ReadWrite();
        String content = readWrite.readfile(this, getFilesDir());

        if(!content.equals("")){
            changeuser.setText(content.substring(0, content.indexOf(",")));
            changemail.setText(content.substring(content.indexOf(",")+1));
        }


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                writeFile("");
                String toWrite = changeuser.getText().toString()
                        +","+changemail.getText().toString();
                readWrite.writeFile(getParent(), toWrite);
            }
        });


    }

     String readFile() {

        String content="";
        try {
            File dir = getFilesDir();
            Log.d("file", dir.getAbsolutePath());
            FileReader fileReader =
                    new FileReader(dir.getAbsolutePath() + "/userdata");

            char[] buffer = new char[512];
            int length = 0;
                        while ((length = fileReader.read(buffer)) != -1){
                content += new String(buffer,0,length);
            }

            fileReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;

    }

     void writeFile(String toWrite) {
        try {
            FileOutputStream fileOutputStream = openFileOutput("userdata", MODE_PRIVATE);
            fileOutputStream.write((changeuser.getText().toString()
            +","+changemail.getText().toString()).getBytes());

            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
