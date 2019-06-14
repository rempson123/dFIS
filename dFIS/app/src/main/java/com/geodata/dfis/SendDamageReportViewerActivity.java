package com.geodata.dfis;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.geodata.dfis.Model.DamageReport;

import java.util.List;

/**
 * Created by rdulitin on 07/06/2019.
 */

public class SendDamageReportViewerActivity extends AppCompatActivity {

    TextView textViewDamageReportNoSend;
    ImageView imageViewDamageSend;
    TextView textViewFullNameSend, textViewAddressSend, textViewContactNoSend, textViewDamageIdSend, textViewDamageTypeSend,
            textViewDateAndTimeSend;
    TextView textViewDescriptionSend;
    List<DamageReport> mDamageReports;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_damage_report_viewer);

        textViewDamageReportNoSend = findViewById(R.id.tv_dmgReportNoSend);

        imageViewDamageSend = findViewById(R.id.imgV_damageSend);
        textViewFullNameSend = findViewById(R.id.tv_full_name_send);
        textViewAddressSend = findViewById(R.id.tv_address_send);
        textViewContactNoSend = findViewById(R.id.tv_contact_no_send);
        textViewDamageIdSend = findViewById(R.id.tv_damage_id_send);
        textViewDamageTypeSend = findViewById(R.id.tv_damage_type_send);
        textViewDateAndTimeSend = findViewById(R.id.tv_date_and_time_send);
        textViewDescriptionSend = findViewById(R.id.tv_desc_send);

        String damageReportNumber = getIntent().getStringExtra("dmgReportNoSend");

        mDamageReports = LoginActivity.myRoomDatabase.daoDFIS().getDamageReport(damageReportNumber);

        for (DamageReport damageReport : mDamageReports) {

            byte[] recordImage = damageReport.getImagePic();
            Bitmap imageDamage = BitmapFactory.decodeByteArray(recordImage, 0, recordImage.length);

            imageViewDamageSend.setImageBitmap(imageDamage);
            textViewFullNameSend.setText(damageReport.getFullName());
            textViewAddressSend.setText(damageReport.getAddress());
            textViewContactNoSend.setText(damageReport.getContactNo());
            textViewDamageIdSend.setText(damageReport.getDamageId());
            textViewDamageTypeSend.setText(damageReport.getDamageType());
            textViewDateAndTimeSend.setText(damageReport.getDateAndTime());
            textViewDescriptionSend.setText(damageReport.getDescription());
        }
    }
}

