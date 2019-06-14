package com.geodata.dfis;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.geodata.dfis.Adapter.DamageAdapter;

import java.util.ArrayList;

public class MyReportsActivity extends AppCompatActivity {
    ListView Reported_Incident_List, Reported_Incident_List_SAVED;
    LinearLayout SavedLayout, SendLayout, Linearsend_listview, LinearSave_listview;
    ImageView Sent_arrow, Save_arrow;
    TextView load_Sent, load_Save ;
    TextView save_updown_tv, sent_updown_tv, showsave_tv, showsent_tv;

    private ArrayList<Bitmap> Image = new ArrayList<Bitmap>();
    private ArrayList<String> Date = new ArrayList<String>();
    private ArrayList<String> Type = new ArrayList<String>();
    private ArrayList<String> Address = new ArrayList<String>();
    private ArrayList<String> Description = new ArrayList<String>();
    private ArrayList<String> SaveInciNo = new ArrayList<String>();

    private ArrayList<Bitmap> Image1 = new ArrayList<Bitmap>();
    private ArrayList<String> Date1 = new ArrayList<String>();
    private ArrayList<String> Type1 = new ArrayList<String>();
    private ArrayList<String> Address1 = new ArrayList<String>();
    private ArrayList<String> Description1 = new ArrayList<String>();
    private ArrayList<String> SendInciNo = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reports);

        SavedLayout = findViewById(R.id.Linear_SAVED);
        SendLayout = findViewById(R.id.Linear_SENT);
        Linearsend_listview = findViewById(R.id.LinearSend_listview);
        LinearSave_listview = findViewById(R.id.LinearSave_listview);
        Reported_Incident_List_SAVED = findViewById(R.id.Reported_Incident_ListSAVED);
        Reported_Incident_List = findViewById(R.id.Reported_Incident_ListSEND);
        Sent_arrow = findViewById(R.id.Sent_arrow);
        Save_arrow = findViewById(R.id.Save_arrow);
        load_Sent = findViewById(R.id.load_Sent);
        load_Save = findViewById(R.id.load_Saved);

        save_updown_tv = findViewById(R.id.tv_saveupdown);
        sent_updown_tv = findViewById(R.id.tv_sentupdown);

        save();
        send();

        TextView textView_numItems = findViewById(R.id.textView_numItems_Saved);
        textView_numItems.setText( Reported_Incident_List_SAVED.getAdapter().getCount() + " Damage(s)");

        TextView textView_numItems_Sent = findViewById(R.id.textView_numItems_Sent);
        textView_numItems_Sent.setText( Reported_Incident_List.getAdapter().getCount() + " Damage(s)");

        Reported_Incident_List_SAVED.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MyReportsActivity.this, SaveDamageReportViewerActivity.class);
                intent.putExtra("dmgReportNoSave", SaveInciNo.get(position));
                startActivity(intent);
            }
        });

        Reported_Incident_List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MyReportsActivity.this, SendDamageReportViewerActivity.class);
                intent.putExtra("dmgReportNoSend", SendInciNo.get(position));
                startActivity(intent);
            }
        });

    }
    public void save() {
        Cursor damages = LoginActivity.myRoomDatabase.daoDFIS().getDamageSave();
        if (damages.moveToFirst()){
            do {
                Date.add(damages.getString(damages.getColumnIndex("DATEANDTIME")));
                Type.add(damages.getString(damages.getColumnIndex("DAMAGETYPE")));
                Address.add(damages.getString(damages.getColumnIndex("ADDRESS")));
                Description.add(damages.getString(damages.getColumnIndex("DESCRIPTION")));
                SaveInciNo.add(damages.getString(damages.getColumnIndex("id")));

                byte[] bytes = damages.getBlob(damages.getColumnIndex("IMAGEPICTURE"));
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0 ,bytes.length);
                Image.add(bitmap);

            }while (damages.moveToNext());
        }

        DamageAdapter ca = new DamageAdapter(MyReportsActivity.this,Image, Date, Type, Address,Description, SaveInciNo);
        Reported_Incident_List_SAVED.setAdapter(ca);
    }
    public void send(){
        Cursor damages1 = LoginActivity.myRoomDatabase.daoDFIS().getDamageSend();
        if (damages1.moveToFirst()){
            do {
                Date1.add(damages1.getString(damages1.getColumnIndex("DATEANDTIME")));
                Type1.add(damages1.getString(damages1.getColumnIndex("DAMAGETYPE")));
                Address1.add(damages1.getString(damages1.getColumnIndex("ADDRESS")));
                Description1.add(damages1.getString(damages1.getColumnIndex("DESCRIPTION")));
                SendInciNo.add(damages1.getString(damages1.getColumnIndex("id")));


                byte[] bytes = damages1.getBlob(damages1.getColumnIndex("IMAGEPICTURE"));
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0 ,bytes.length);
                Image1.add(bitmap);

            }while (damages1.moveToNext());
        }

        DamageAdapter ca = new DamageAdapter(MyReportsActivity.this,Image1, Date1, Type1, Address1,Description1, SendInciNo);
        Reported_Incident_List.setAdapter(ca);
    }

    public void btn_lsave(View view){

        if(save_updown_tv.getText().toString().equals("up")){

            save_updown_tv.setText("down");
            Save_arrow.setImageResource(R.mipmap.ic_down_arrow);
            LinearSave_listview.setVisibility(View.GONE);

        }else if(save_updown_tv.getText().toString().equals("down")){

            save_updown_tv.setText("up");
            Save_arrow.setImageResource(R.mipmap.ic_up_arrow);
            LinearSave_listview.setVisibility(View.VISIBLE);
        }

    }
    public void btn_lsent(View view){

        if(sent_updown_tv.getText().toString().equals("up")){

            sent_updown_tv.setText("down");
            Sent_arrow.setImageResource(R.mipmap.ic_down_arrow);
            Linearsend_listview.setVisibility(View.GONE);

        }else if(sent_updown_tv.getText().toString().equals("down")){

            sent_updown_tv.setText("up");
            Sent_arrow.setImageResource(R.mipmap.ic_up_arrow);
            Linearsend_listview.setVisibility(View.VISIBLE);
        }
    }

    public void btn_loadsave(View view){

        if(load_Save.getText().toString().equals("Show All>>")){
            load_Save.setText("Show Less>>");
            //Reported_Incident_List_SAVED
            ViewGroup.LayoutParams params = Reported_Incident_List_SAVED.getLayoutParams();
            params.height = -2;
            Reported_Incident_List_SAVED.setLayoutParams(params);
            Reported_Incident_List_SAVED.requestLayout();
            setListViewHeightBasedOnItems(Reported_Incident_List_SAVED);

        }else if(load_Save.getText().toString().equals("Show Less>>")){
            load_Save.setText("Show All>>");
            ViewGroup.LayoutParams params = Reported_Incident_List_SAVED.getLayoutParams();
            params.height = -2;
            Reported_Incident_List_SAVED.setLayoutParams(params);
            Reported_Incident_List_SAVED.requestLayout();
        }
    }
    public void btn_loadsent(View view){

        if(load_Sent.getText().toString().equals("Show All>>")){
            load_Sent.setText("Show Less>>");

            ViewGroup.LayoutParams params = Reported_Incident_List.getLayoutParams();
            params.height = -2;
            Reported_Incident_List.setLayoutParams(params);
            Reported_Incident_List.requestLayout();
            setListViewHeightBasedOnItems(Reported_Incident_List);

        }else if(load_Sent.getText().toString().equals("Show Less>>")){
            load_Sent.setText("Show All>>");

            ViewGroup.LayoutParams params = Reported_Incident_List.getLayoutParams();
            params.height = -2;
            Reported_Incident_List.setLayoutParams(params);
            Reported_Incident_List.requestLayout();

            // Reported_Incident_List.setLayoutParams(new RelativeLayout.LayoutParams(155, 115));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //Write your logic here
                Intent intent = new Intent(MyReportsActivity.this, NavigationDrawerActivity.class);
                startActivity(intent);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(MyReportsActivity.this, NavigationDrawerActivity.class);
        startActivity(intent);
    }
    public static boolean setListViewHeightBasedOnItems(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter != null) {

            int numberOfItems = listAdapter.getCount();

            // Get total height of all items.
            int totalItemsHeight = 0;
            for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
                View item = listAdapter.getView(itemPos, null, listView);
                item.measure(0, 0);
                totalItemsHeight += item.getMeasuredHeight();
            }

            // Get total height of all item dividers.
            int totalDividersHeight = listView.getDividerHeight() *
                    (numberOfItems - 1);

            // Set list height.
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalItemsHeight + totalDividersHeight;
            listView.setLayoutParams(params);
            listView.requestLayout();

            return true;

        } else {
            return false;
        }

    }
}
