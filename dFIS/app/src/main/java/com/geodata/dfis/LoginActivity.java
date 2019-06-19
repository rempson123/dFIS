package com.geodata.dfis;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.geodata.dfis.Model.RegisterInfo;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    public static MyRoomDatabase myRoomDatabase;

    EditText editPassword, editUsername;
    TextView textViewRegister;

    private static final String TAG = "LoginActivity";

    List<RegisterInfo> checkAllDataInfo = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myRoomDatabase = Room.databaseBuilder(getApplicationContext(), MyRoomDatabase.class, "dFIS").allowMainThreadQueries().build();
        //asdaaa
        editUsername = (EditText) findViewById(R.id.edtxtUsername);
        editPassword = (EditText) findViewById(R.id.edtxtPassword);
        textViewRegister = findViewById(R.id.tv_register);

        checkIfUserHasAccount();
        goToRegisterActivity();

    }

    public void btn_login101(View view) {

        RegisterInfo register = myRoomDatabase.daoDFIS().getUser(editUsername.getText().toString(), editPassword.getText().toString());

        if (register != null) {
            Toast.makeText(getApplicationContext(), "Log-in succesfully", Toast.LENGTH_SHORT).show();
            //Snackbar.make(view, "Log-in succesfully", Snackbar.LENGTH_LONG).show();
            Intent i = new Intent(LoginActivity.this, NavigationDrawerActivity.class);
            startActivity(i);
        } else {
            Toast.makeText(this, "Unregistered account or Incorrect credentials", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(LoginActivity.this, "Login First!", Toast.LENGTH_SHORT).show();
    }

    public void goToRegisterActivity() {

        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
                finish();

            }
        });

    }

    public void checkIfUserHasAccount() {
        checkAllDataInfo = myRoomDatabase.daoDFIS().getAllRegisterInfo();
        if (checkAllDataInfo.isEmpty()) {
            textViewRegister.setVisibility(View.VISIBLE);
        } else {
            textViewRegister.setVisibility(View.INVISIBLE);

            RegisterActivity.retrieveUserData();
            editUsername.setText(RegisterActivity.email);
            editPassword.setText(RegisterActivity.password);

            editUsername.setFocusable(false);
            editPassword.setFocusable(false);

        }
    }

}
