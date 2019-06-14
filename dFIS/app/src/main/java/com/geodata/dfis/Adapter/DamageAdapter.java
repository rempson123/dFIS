package com.geodata.dfis.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.geodata.dfis.R;

import java.util.ArrayList;

/**
 * Created by jrvicedo on 5/29/2019.
 */

public class DamageAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Bitmap> Image = new ArrayList<Bitmap>();
    private ArrayList<String> dateandtime = new ArrayList<String>();
    private ArrayList<String> Damagetype = new ArrayList<String>();
    private ArrayList<String> address = new ArrayList<String>();
    private ArrayList<String> description = new ArrayList<String>();
    private ArrayList<String> damageno = new ArrayList<String>();


    public DamageAdapter(Context mContext, ArrayList<Bitmap> image, ArrayList<String> dateandtime, ArrayList<String> damagetype, ArrayList<String>
            address, ArrayList<String> description, ArrayList<String> damageno) {
        this.mContext = mContext;
        Image = image;
        this.dateandtime = dateandtime;
        Damagetype = damagetype;
        this.address = address;
        this.description = description;
        this.damageno = damageno;
    }

    @Override
    public int getCount() {return description.size();}

    @Override
    public Object getItem(int position) {return null;}

    @Override
    public long getItemId(int position) {return 0;}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final    viewHolder holder;
        LayoutInflater layoutInflater;

        if (convertView == null) {
            layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.report_list_row, null);
            holder = new viewHolder();
            holder.image = convertView.findViewById(R.id.img_reported_picture);
            holder.dateandtime = convertView.findViewById(R.id.tv_reported_date);
            holder.Damagetype = convertView.findViewById(R.id.tv_damage_type);
            holder.address = convertView.findViewById(R.id.tv_address);
            holder.description = convertView.findViewById(R.id.tv_description);
            holder.damagenumber = convertView.findViewById(R.id.tv_damageNo);
            convertView.setTag(holder);
        } else {
            holder = (viewHolder) convertView.getTag();
        }
        //holder.image.setBackground(Image.get(position));
        //holder.image.setBackgroundResource(R.drawable.ic_launcher_background);
     //   holder.image.setImageBitmap(Image.get(position));
     //   holder.image.setScaleType(ImageView.ScaleType.FIT_XY);

        holder.image.setImageBitmap(Image.get(position));
        holder.image.setScaleType(ImageView.ScaleType.FIT_XY);
        holder.dateandtime.setText("Date: " + dateandtime.get(position));
        holder.Damagetype.setText("Type of Damage: " +Damagetype.get(position) );
        holder.address.setText("Address: "+address.get(position));
        holder.description.setText("Description: "+description.get(position));
        holder.damagenumber.setText(damageno.get(position));
        return convertView;
    }

    public class viewHolder {
        ImageView image;
        TextView dateandtime;
        TextView Damagetype;
        TextView address;
        TextView description;
        TextView damagenumber;
    }
}
