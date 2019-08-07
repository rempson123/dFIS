package com.geodata.dfis.Adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.geodata.dfis.Model.DamageReport;
import com.geodata.dfis.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rdulitin on 06/05/2019.
 */

public class DamageReportAdapter extends RecyclerView.Adapter<DamageReportAdapter.ViewHolder> {

    private static final String TAG = "DamageReportAdapter";

    private List<DamageReport> damageReports = new ArrayList<>();
    private OnDamageListener mOnDamageListener;


    public DamageReportAdapter(List<DamageReport> damageReports, OnDamageListener mOnDamageListener) {
        this.damageReports = damageReports;
        this.mOnDamageListener = mOnDamageListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_damage_report_item, parent, false);
        return new ViewHolder(view, mOnDamageListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        byte[] damageImage = damageReports.get(position).getImagePic();
        Bitmap damageBitmap = BitmapFactory.decodeByteArray(damageImage, 0, damageImage.length);
        holder.imageViewReport.setImageBitmap(damageBitmap);
        holder.imageViewReport.setScaleType(ImageView.ScaleType.FIT_XY);
        holder.textViewDamageDate.setText(damageReports.get(position).getDateAndTime());
        holder.textViewDamageType.setText(damageReports.get(position).getDamageType());
        holder.textViewDamageAddress.setText(damageReports.get(position).getAddress());
        holder.textViewDamageDescription.setText(damageReports.get(position).getDescription());
        holder.textViewReportNo.setText(String.valueOf(damageReports.get(position).getId()));

        if (position % 2 == 1) {
            holder.textViewReportTriangleBg.setBackgroundResource(R.drawable.bg_triangle_primary);
        } else {
            holder.textViewReportTriangleBg.setBackgroundResource(R.drawable.bg_triangle_accent);
        }

    }

    @Override
    public int getItemCount() {
        return damageReports.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageViewReport;
        TextView textViewDamageDate, textViewDamageType, textViewDamageAddress, textViewDamageDescription,
                textViewReportNo, textViewReportTriangleBg;

        public ViewHolder(View itemView, OnDamageListener onDamageListener) {
            super(itemView);


            imageViewReport = itemView.findViewById(R.id.imgv_report);
            textViewDamageDate = itemView.findViewById(R.id.tv_damage_report_date);
            textViewDamageType = itemView.findViewById(R.id.tv_damage_report_type);
            textViewDamageAddress = itemView.findViewById(R.id.tv_damage_report_address);
            textViewDamageDescription = itemView.findViewById(R.id.tv_damage_report_description);
            textViewDamageDate = itemView.findViewById(R.id.tv_damage_report_date);
            textViewReportNo = itemView.findViewById(R.id.tv_report_no);
            textViewReportTriangleBg = itemView.findViewById(R.id.tv_triangle_corner);
            mOnDamageListener = onDamageListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mOnDamageListener.onDamageClick(getAdapterPosition());
        }
    }

    public interface OnDamageListener {
        void onDamageClick(int position);
    }
}
