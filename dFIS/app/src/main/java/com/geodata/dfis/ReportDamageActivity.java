package com.geodata.dfis;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.geodata.dfis.Model.DamageReport;
import com.geodata.dfis.Model.RegisterInfo;
import com.geodata.dfis.Retrofit.APIIClient;
import com.geodata.dfis.Retrofit.APIInterface;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportDamageActivity extends AppCompatActivity {

    ImageView Imageview_damage;
    String lati, longi;
    TextView damage_type, tv_date, damage_id, txtview_name, contact_num;
    EditText edit_desc, address;
    String Date;

    APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_damage);

        apiInterface = APIIClient.getClient().create(APIInterface.class);

        setTitle("Report");

        Imageview_damage = findViewById(R.id.Imageview_damage);
        address = findViewById(R.id.address);
        damage_type = findViewById(R.id.damage_type);
        tv_date = findViewById(R.id.textview_Date);
        damage_id = findViewById(R.id.damage_id);
        txtview_name = findViewById(R.id.txtview_name);
        contact_num = findViewById(R.id.contact_num);

        edit_desc = findViewById(R.id.edit_desc);

        String s = getIntent().getStringExtra("report");
        if (s.equals("1")) {

            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, 1);

        } else if (s.equals("2")) {

        }

        lati = getIntent().getStringExtra("longi");
        longi = getIntent().getStringExtra("lati");
        //address.setText(getIntent().getStringExtra("address"));
        damage_type.setText(getIntent().getStringExtra("damagetype"));
        //Date = "" + DateFormat.format("yyyy MMM dd", System.currentTimeMillis());
        Date = "" + DateFormat.format("E MMMM dd yyyy hh:mm a", System.currentTimeMillis());

        tv_date.setText(Date);
        damage_id.setText(UUID.randomUUID().toString());

        RegisterActivity.retrieveUserData();
        txtview_name.setText(RegisterActivity.fullname);
        contact_num.setText(RegisterActivity.contactnumber);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");

            Drawable pic = new BitmapDrawable(getResources(), photo);
            Imageview_damage.setBackground(pic);
        }
    }

    public void sendDamage() {

        Bitmap yourBitmap = ((BitmapDrawable) Imageview_damage.getBackground()).getBitmap();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        yourBitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
        byte[] bArray = bos.toByteArray();
        String imageString = Base64.encodeToString(bArray, Base64.DEFAULT);

        //Toast.makeText(this, imageString, Toast.LENGTH_SHORT).show();

        String fullname1 = txtview_name.getText().toString();
        String damageid1 = damage_id.getText().toString();
        String damagetype1 = damage_type.getText().toString();
        String contactno1 = contact_num.getText().toString();
        String description1 = edit_desc.getText().toString();
        String address1 = address.getText().toString();
        String xcoor1 = lati;
        String ycoor1 = longi;
        String dateandtime1 = tv_date.getText().toString();

        sendReport(fullname1, damageid1, damagetype1, contactno1, description1, address1, xcoor1, ycoor1, imageString, dateandtime1);

        DamageReport damageReport = new DamageReport();

        damageReport.setFullName(txtview_name.getText().toString());
        damageReport.setDamageId(damage_id.getText().toString());
        damageReport.setDamageType(damage_type.getText().toString());
        damageReport.setContactNo(contact_num.getText().toString());
        damageReport.setDescription(edit_desc.getText().toString());
        damageReport.setStatus("SENT");
        damageReport.setAddress(address.getText().toString());
        damageReport.setXCoordinates(lati);
        damageReport.setYCoordinates(longi);
        damageReport.setImagePic(bArray);
        damageReport.setImageString(imageString);
        damageReport.setDateAndTime(tv_date.getText().toString());

        LoginActivity.myRoomDatabase.daoDFIS().addDamageRecord(damageReport);
        Toast.makeText(this, "Report successfully sent", Toast.LENGTH_SHORT).show();

    }

    public void sendReport(String fullname1, String damageid1, String damagetype1, String contactno1,
                           String description1, String address1, String xcoor1, String ycoor1, String imageString, String dateandtime1) {
        apiInterface.createReports(fullname1, damageid1, damagetype1, contactno1,
                description1, address1, xcoor1, ycoor1, imageString, dateandtime1).enqueue(new Callback<RegisterInfo>() {
            @Override
            public void onResponse(Call<RegisterInfo> call, Response<RegisterInfo> response) {
                //Toast.makeText(ReportDamageActivity.this, "Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<RegisterInfo> call, Throwable t) {
                //Toast.makeText(ReportDamageActivity.this, "Failed", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void saveReport() {

        Bitmap yourBitmap = ((BitmapDrawable) Imageview_damage.getBackground()).getBitmap();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        yourBitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
        byte[] bArray = bos.toByteArray();
        String imageString = Base64.encodeToString(bArray, Base64.DEFAULT);

        DamageReport damageReport = new DamageReport();

        damageReport.setFullName(txtview_name.getText().toString());
        damageReport.setDamageId(damage_id.getText().toString());
        damageReport.setDamageType(damage_type.getText().toString());
        damageReport.setContactNo(contact_num.getText().toString());
        damageReport.setDescription(edit_desc.getText().toString());
        damageReport.setStatus("SAVE");
        damageReport.setAddress(address.getText().toString());
        damageReport.setXCoordinates(lati);
        damageReport.setYCoordinates(longi);
        damageReport.setImagePic(bArray);
        damageReport.setImageString(imageString);
        damageReport.setDateAndTime(tv_date.getText().toString());

        LoginActivity.myRoomDatabase.daoDFIS().addDamageRecord(damageReport);
        Toast.makeText(this, "Report saved", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_report_damage, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_send:

                sendDamage();
                Intent intentSend = new Intent(ReportDamageActivity.this, NavigationDrawerActivity.class);
                startActivity(intentSend);
                finish();

                return true;

            case R.id.action_save:

                if (address.getText().toString().trim().equals("")) {
                    address.setError("Enter an address");
                    return false;
                } else if (edit_desc.getText().toString().trim().equals("")) {
                    edit_desc.setError("Enter a description");
                    return false;
                } else {
                    saveReport();
                    Intent intentSave = new Intent(ReportDamageActivity.this, NavigationDrawerActivity.class);
                    startActivity(intentSave);
                    finish();
                }


                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
