package com.geodata.dfis;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.geodata.dfis.Model.RegisterInfo;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {

    EditText edit_first_name, edit_middle_name, edit_last_name, edit_birthdate, edit_mobile, edit_email, edit_password,
            edit_confirmpassword, edit_street, edit_barangay, edit_towncity, edit_zipcode, edit_province;
    RadioButton gender_female, gender_male;
    RadioGroup gendergroup;
    Calendar calendar;
    String gender = "Male";
    private int year, month, day;

    static String fullname, contactnumber, email, password, fname, minitial, lname;

    private static final String TAG = "RegisterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        edit_first_name = (EditText) findViewById(R.id.edit_first_name);
        edit_middle_name = (EditText) findViewById(R.id.edit_middle_name);
        edit_last_name = (EditText) findViewById(R.id.edit_last_name);
        edit_birthdate = (EditText) findViewById(R.id.edit_birthdate);
        edit_mobile = (EditText) findViewById(R.id.edit_mobile);
        edit_email = (EditText) findViewById(R.id.edit_email);
        edit_password = (EditText) findViewById(R.id.edit_password);
        edit_confirmpassword = (EditText) findViewById(R.id.edit_confirmpassword);
        edit_street = (EditText) findViewById(R.id.edit_street);
        edit_barangay = (EditText) findViewById(R.id.edit_barangay);
        edit_towncity = (EditText) findViewById(R.id.edit_towncity);
        edit_zipcode = (EditText) findViewById(R.id.edit_zipcode);
        edit_province = (EditText) findViewById(R.id.edit_province);
        gender_female = (RadioButton) findViewById(R.id.rb_female);
        gender_male = (RadioButton) findViewById(R.id.rb_male);
        gendergroup = (RadioGroup) findViewById(R.id.radio_group_gender);
        //CALENDAR

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month + 1, day);

        edit_birthdate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (v.isFocused()) {
                    showDialog(999);
                }
            }
        });

        edit_birthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(999);
            }
        });

        edit_mobile.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

        setupFloatingLabelError();

        gendergroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int id = gendergroup.getCheckedRadioButtonId();
                if (id == -1) {

                } else {
                    if (id == R.id.rb_male) {
                        gender = "Male";
                    }
                    if (id == R.id.rb_female) {
                        gender = "Female";
                    }
                }
            }
        });
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2 + 1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        edit_birthdate.setText(new StringBuilder().append(month).append("/")
                .append(day).append("/").append(year));
    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "ca", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_submit:

                final TextInputLayout txtIL_first_name = findViewById(R.id.txtIL_first_name);
                final TextInputLayout txtIL_last_name = findViewById(R.id.txtIL_last_name);
                final TextInputLayout txtIL_bdate = findViewById(R.id.txtIL_bdate);
                final TextInputLayout txtIL_Mobile = findViewById(R.id.txtIL_Mobile);
                final TextInputLayout txtinput_password = findViewById(R.id.edit_pass_textinputlayout);
                final TextInputLayout txtinput_cpassword = findViewById(R.id.txtinput_cpassword);
                final TextInputLayout txtinput_city = findViewById(R.id.txtinput_city);
                final TextInputLayout til_brgy = findViewById(R.id.til_brgy);
                if (edit_first_name.getText().toString().equals("") ||
                        edit_last_name.getText().toString().equals("") ||
                        edit_birthdate.getText().toString().equals("") ||
                        edit_mobile.getText().toString().equals("") ||
                        edit_password.getText().toString().equals("") ||
                        edit_confirmpassword.getText().toString().equals("") ||
                        edit_towncity.getText().toString().equals("") ||
                        edit_barangay.getText().toString().equals("")
                        ) {

                    if (edit_first_name.getText().toString().equals("")) {
                        txtIL_first_name.setErrorEnabled(true);
                        txtIL_first_name.setError("Required Field");
                    } else {
                        txtIL_first_name.setErrorEnabled(false);
                    }

                    if (edit_last_name.getText().toString().equals("")) {
                        txtIL_last_name.setErrorEnabled(true);
                        txtIL_last_name.setError("Required Field");
                    } else {
                        txtIL_last_name.setErrorEnabled(false);
                    }

                    if (edit_birthdate.getText().toString().equals("")) {
                        txtIL_bdate.setErrorEnabled(true);
                        txtIL_bdate.setError("Required Field");
                    } else {
                        txtIL_bdate.setErrorEnabled(false);
                    }

                    if (edit_mobile.getText().toString().equals("")) {
                        txtIL_Mobile.setErrorEnabled(true);
                        txtIL_Mobile.setError("Required Field");
                    } else {
                        txtIL_Mobile.setErrorEnabled(false);
                    }

                    if (edit_password.getText().toString().equals("")) {
                        txtinput_password.setErrorEnabled(true);
                        txtinput_password.setError("Required Field");
                    } else {
                        txtinput_password.setErrorEnabled(false);
                    }

                    if (edit_confirmpassword.getText().toString().equals("")) {
                        txtinput_cpassword.setErrorEnabled(true);
                        txtinput_cpassword.setError("Required Field");
                    } else {
                        txtinput_cpassword.setErrorEnabled(false);
                    }

                    if (edit_towncity.getText().toString().equals("")) {
                        txtinput_city.setErrorEnabled(true);
                        txtinput_city.setError("Required Field");
                    } else {
                        txtinput_city.setErrorEnabled(false);
                    }

                    if (edit_barangay.getText().toString().equals("")) {
                        til_brgy.setErrorEnabled(true);
                        til_brgy.setError("Required Field");
                    } else {
                        til_brgy.setErrorEnabled(false);
                    }

                } else {
                    //CHANGE THIS ERROR PA

                    if (edit_password.getText().toString().equals(edit_confirmpassword.getText().toString())) {
                        //model yan iiihhh
                        RegisterInfo register = new RegisterInfo();
                        register.setFirstName(edit_first_name.getText().toString());
                        register.setMiddleName(edit_middle_name.getText().toString());
                        register.setLastName(edit_last_name.getText().toString());
                        register.setBirthdate(edit_birthdate.getText().toString());
                        register.setMobile(edit_mobile.getText().toString());
                        register.setGender(gender);
                        register.setEmail(edit_email.getText().toString());
                        register.setPassword(edit_password.getText().toString());
                        register.setStreet(edit_street.getText().toString());
                        register.setBarangay(edit_barangay.getText().toString());
                        register.setTownorCity(edit_towncity.getText().toString());
                        register.setZipcode(edit_zipcode.getText().toString());
                        register.setProvince(edit_province.getText().toString());

                        LoginActivity.myRoomDatabase.daoDFIS().addUser(register);

                        // custom dialog
                        final Context context = this;
                        final Dialog dialog = new Dialog(context);
                        dialog.setContentView(R.layout.custom_dialog_reg_ok);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

                        Button button_ok = (Button) dialog.findViewById(R.id.button_ok);
                        // if button is clicked, close the custom dialog
                        button_ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent intent = new Intent(RegisterActivity.this, NavigationDrawerActivity.class);
                                Toast.makeText(context, "Successfully registered", Toast.LENGTH_SHORT).show();
                                startActivity(intent);

                                retrieveUserData();

                            }
                        });
                        dialog.show();
                    } else {
                        final TextInputLayout floatingUsernameLabel = findViewById(R.id.txtinput_cpassword);
                        floatingUsernameLabel.setErrorEnabled(true);
                        floatingUsernameLabel.setError("Your Password did not match!");
                    }
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void setupFloatingLabelError() {
        final TextInputLayout floatingUsernameLabel = (TextInputLayout) findViewById(R.id.txtinput_cpassword);
        floatingUsernameLabel.getEditText().addTextChangedListener(new TextWatcher() {
            // ...
            @Override
            public void onTextChanged(CharSequence text, int start, int count, int after) {
                if (text.length() > 0 && text.length() <= 50) {
                    if (edit_password.getText().toString().equals(edit_confirmpassword.getText().toString())) {

                        floatingUsernameLabel.setErrorEnabled(false);


                    } else {

                        floatingUsernameLabel.setErrorEnabled(true);
                        floatingUsernameLabel.setError("Password did not match!");
                    }
                } else {
                    floatingUsernameLabel.setErrorEnabled(true);
                    floatingUsernameLabel.setError("Retype Password");
                }

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public static void retrieveUserData() {

        Cursor getCredentials = LoginActivity.myRoomDatabase.daoDFIS().getRegisterInfo();
        if (getCredentials == null) {
            Log.d(TAG, "No credential retrieved");
        } else {
            if (getCredentials.moveToFirst()) {
                do {
                    email = (getCredentials.getString(getCredentials.getColumnIndex("EMAIL")));
                    password = (getCredentials.getString(getCredentials.getColumnIndex("PASSWORD")));
                    fname = (getCredentials.getString(getCredentials.getColumnIndex("FIRSTNAME")));
                    minitial = (getCredentials.getString(getCredentials.getColumnIndex("MIDDLENAME")));
                    lname = (getCredentials.getString(getCredentials.getColumnIndex("LASTNAME")));
                    contactnumber = (getCredentials.getString(getCredentials.getColumnIndex("MOBILE")));

                    fullname = (fname + " " + minitial + " " + lname);


                } while (getCredentials.moveToNext());
            }
        }
    }
}

