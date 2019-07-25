package com.geodata.dfis.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geodata.dfis.Adapter.DamageReportAdapter;
import com.geodata.dfis.Model.DamageReport;
import com.geodata.dfis.R;
import com.geodata.dfis.SendDamageReportViewerActivity;
import com.geodata.dfis.Tools.VerticalSpacingItemDecorator;

import java.util.ArrayList;
import java.util.List;

import static com.geodata.dfis.LoginActivity.myRoomDatabase;

/**
 * Created by rdulitin on 25/07/2019.
 */

public class SentFragment extends android.support.v4.app.Fragment implements DamageReportAdapter.OnDamageListener {

    private RecyclerView recyclerViewDamageReportSent;

    private List<DamageReport> mDamageReportsSent = new ArrayList<>();
    private DamageReportAdapter damageReportAdapter;

    public SentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Reports");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_sent, container, false);

        recyclerViewDamageReportSent = view.findViewById(R.id.rv_report_list_sent);

        mDamageReportsSent = myRoomDatabase.daoDFIS().getDamageSend();

        initRecyclerView();


        return view;
    }

    @Override
    public void onDamageClick(int position) {
        DamageReport damageReportNo = mDamageReportsSent.get(position);
        String reportNo =  String.valueOf(damageReportNo.getId());

        Intent intent = new Intent(getActivity(), SendDamageReportViewerActivity.class);
        intent.putExtra("dmgReportNoSend", reportNo);
        startActivity(intent);
    }

    private void initRecyclerView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewDamageReportSent.setLayoutManager(linearLayoutManager);
        VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(10);
        recyclerViewDamageReportSent.addItemDecoration(itemDecorator);
        damageReportAdapter = new DamageReportAdapter(mDamageReportsSent, this);
        recyclerViewDamageReportSent.setAdapter(damageReportAdapter);

    }
}

