package com.geodata.dfis.Adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geodata.dfis.Model.DamageReport;
import com.geodata.dfis.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rdulitin on 06/05/2019.
 */

public class DamageReportAdapter extends BaseAdapter {

    private Context mContext;
    private List<DamageReport> mReports;


    public DamageReportAdapter(Context mContext, List<DamageReport> mReports) {
        this.mContext = mContext;
        this.mReports = mReports;
    }

    @Override
    public int getCount() {
        return mReports.size();
    }
    @Override
    public Object getItem(int position) {
        return mReports;
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final viewHolder holder;

        LayoutInflater layoutInflater;
        if (convertView == null) {
            layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.report_list_row, null);
            holder = new viewHolder();
            holder.imageViewReportedImage = convertView.findViewById(R.id.img_reported_picture);
            holder.textViewDate = convertView.findViewById(R.id.tv_reported_date);
            holder.textViewDamageType = convertView.findViewById(R.id.tv_damage_type);
            holder.textViewAddress = convertView.findViewById(R.id.tv_address);
            holder.textViewDescription = convertView.findViewById(R.id.tv_description);
            //holder.inciNo = convertView.findViewById(R.id.inciNo);
            convertView.setTag(holder);
        } else {
            holder = (viewHolder) convertView.getTag();
        }

        DamageReport damageReport =  mReports.get(position);

        byte[] recordImage = damageReport.getImagePic();
        Bitmap bitmap = BitmapFactory.decodeByteArray(recordImage, 0, recordImage.length);
        holder.imageViewReportedImage.setImageBitmap(bitmap);
        holder.imageViewReportedImage.setScaleType(ImageView.ScaleType.FIT_XY);

        holder.textViewDate.setText("Date: "+ damageReport.getDateAndTime());
        holder.textViewDamageType.setText("Type of Damage: " + damageReport.getDamageType());
        holder.textViewAddress.setText("Address: "+ damageReport.getAddress());
        holder.textViewDescription.setText("Description: "+ damageReport.getDescription());

        return convertView;
    }
    public class viewHolder {
        ImageView imageViewReportedImage;
        TextView textViewDate, textViewDamageType, textViewAddress,textViewDescription;
        //TextView inciNo;
    }


}
