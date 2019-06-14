package com.geodata.dfis;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.geodata.dfis.Model.DamageReport;
import com.geodata.dfis.Model.RegisterInfo;
import com.geodata.dfis.Retrofit.APIIClient;
import com.geodata.dfis.Retrofit.APIInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by rdulitin on 03/06/2019.
 */

public class SaveDamageReportViewerActivity extends AppCompatActivity {

    TextView textViewDamageReportNo;
    ImageView imageViewDamage;
    TextView textViewFullNameSave, textViewAddressSave, textViewContactNoSave, textViewDamageIdSave, textViewDamageTypeSave,
            textViewDateAndTimeSave;
    EditText editTextDescriptionSave;
    List<DamageReport> mDamageReports;
    APIInterface apiInterface;
    Button buttonSaveSend;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_damage_report_viewer);

        apiInterface = APIIClient.getClient().create(APIInterface.class);
        buttonSaveSend = findViewById(R.id.btn_save_send);
        textViewDamageReportNo = findViewById(R.id.tv_dmgReportNo);

        imageViewDamage = findViewById(R.id.imgV_damage);
        textViewFullNameSave = findViewById(R.id.tv_full_name_save);
        textViewAddressSave = findViewById(R.id.tv_address_save);
        textViewContactNoSave = findViewById(R.id.tv_contact_no_save);
        textViewDamageIdSave = findViewById(R.id.tv_damage_id_save);
        textViewDamageTypeSave = findViewById(R.id.tv_damage_type_save);
        textViewDateAndTimeSave = findViewById(R.id.tv_date_and_time_save);
        editTextDescriptionSave = findViewById(R.id.et_desc_save);

        String damageReportNumber = getIntent().getStringExtra("dmgReportNoSave");

        mDamageReports = LoginActivity.myRoomDatabase.daoDFIS().getDamageReport(damageReportNumber);

        for (DamageReport damageReport : mDamageReports) {

            byte[] recordImage = damageReport.getImagePic();
            Bitmap imageDamage = BitmapFactory.decodeByteArray(recordImage, 0, recordImage.length);

            imageViewDamage.setImageBitmap(imageDamage);
            textViewFullNameSave.setText(damageReport.getFullName());
            textViewAddressSave.setText(damageReport.getAddress());
            textViewContactNoSave.setText(damageReport.getContactNo());
            textViewDamageIdSave.setText(damageReport.getDamageId());
            textViewDamageTypeSave.setText(damageReport.getDamageType());
            textViewDateAndTimeSave.setText(damageReport.getDateAndTime());
            editTextDescriptionSave.setText(damageReport.getDescription());
        }

        buttonSaveSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendDamageReport();
            }
        });


    }

    public void sendDamageReport() {

        String damageReportNumber = getIntent().getStringExtra("dmgReportNoSave");
        mDamageReports = LoginActivity.myRoomDatabase.daoDFIS().getDamageReport(damageReportNumber);

        for (DamageReport damageReport : mDamageReports) {

            byte[] recordImage = damageReport.getImagePic();
            Bitmap imageDamage = BitmapFactory.decodeByteArray(recordImage, 0, recordImage.length);

            imageViewDamage.setImageBitmap(imageDamage);
            textViewFullNameSave.setText(damageReport.getFullName());
            textViewAddressSave.setText(damageReport.getAddress());
            textViewContactNoSave.setText(damageReport.getContactNo());
            textViewDamageIdSave.setText(damageReport.getDamageId());
            textViewDamageTypeSave.setText(damageReport.getDamageType());
            textViewDateAndTimeSave.setText(damageReport.getDateAndTime());
            editTextDescriptionSave.setText(damageReport.getDescription());

            String fullname = damageReport.getFullName();
            String damageid = damageReport.getDamageId();
            String damagetype = damageReport.getDamageType();
            String contactno = damageReport.getContactNo();
            String description = damageReport.getDescription();
            String address = damageReport.getAddress();
            String xcoor = damageReport.getXCoordinates();
            String ycoor = damageReport.getYCoordinates();
            String imageString = damageReport.getImageString();
            String dateandtime = damageReport.getDateAndTime();

            DamageReport damageReport1 = new DamageReport();
            damageReport1.setId(Integer.valueOf(damageReportNumber));
            damageReport1.setFullName(fullname);
            damageReport1.setDamageId(damageid);
            damageReport1.setDamageType(damagetype);
            damageReport1.setContactNo(contactno);
            damageReport1.setDescription(description);
            damageReport1.setAddress(address);
            damageReport1.setStatus("SENT");
            damageReport1.setXCoordinates(xcoor);
            damageReport1.setYCoordinates(ycoor);
            damageReport1.setImageString(imageString);
            damageReport1.setDateAndTime(dateandtime);

            damageReport1.setImagePic(recordImage);
            LoginActivity.myRoomDatabase.daoDFIS().updateDamageReport(damageReport1);

            sendReport(fullname, damageid, damagetype, contactno, description, address, xcoor, ycoor, imageString, dateandtime);
            Toast.makeText(SaveDamageReportViewerActivity.this, "Report successfully sent", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(SaveDamageReportViewerActivity.this, NavigationDrawerActivity.class));
            finish();
        }

    }

    public void sendReport(String fullname1, String damageid1, String damagetype1, String contactno1,
                           String description1, String address1, String xcoor1, String ycoor1, String imageString, String dateandtime1) {
        apiInterface.createReports(fullname1, damageid1, damagetype1, contactno1,
                description1, address1, xcoor1, ycoor1, imageString, dateandtime1).enqueue(new Callback<RegisterInfo>() {
            @Override
            public void onResponse(Call<RegisterInfo> call, Response<RegisterInfo> response) {
                //Toast.makeText(SaveDamageReportViewerActivity.this, "Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<RegisterInfo> call, Throwable t) {
                //Toast.makeText(SaveDamageReportViewerActivity.this, "Failed", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
