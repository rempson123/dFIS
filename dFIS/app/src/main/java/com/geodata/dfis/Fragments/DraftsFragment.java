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
import com.geodata.dfis.SaveDamageReportViewerActivity;
import com.geodata.dfis.SendDamageReportViewerActivity;
import com.geodata.dfis.Tools.VerticalSpacingItemDecorator;

import java.util.ArrayList;
import java.util.List;

import static com.geodata.dfis.LoginActivity.myRoomDatabase;


public class DraftsFragment extends android.support.v4.app.Fragment implements DamageReportAdapter.OnDamageListener {

    private RecyclerView recyclerViewDamageReportDraft;

    private List<DamageReport> mDamageReportsDraft = new ArrayList<>();
    private DamageReportAdapter damageReportAdapter;

    public DraftsFragment() {
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
        View view = inflater.inflate(R.layout.fragment_list_drafts, container, false);

        recyclerViewDamageReportDraft = view.findViewById(R.id.rv_report_list_drafts);

        mDamageReportsDraft = myRoomDatabase.daoDFIS().getDamageSave();

        initRecyclerView();


        return view;
    }

    @Override
    public void onDamageClick(int position) {
        DamageReport damageReportNo = mDamageReportsDraft.get(position);
        String reportNo =  String.valueOf(damageReportNo.getId());

        Intent intent = new Intent(getActivity(), SaveDamageReportViewerActivity.class);
        intent.putExtra("dmgReportNoSave", reportNo);
        startActivity(intent);
    }

    private void initRecyclerView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewDamageReportDraft.setLayoutManager(linearLayoutManager);
        VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(10);
        recyclerViewDamageReportDraft.addItemDecoration(itemDecorator);
        damageReportAdapter = new DamageReportAdapter(mDamageReportsDraft, this);
        recyclerViewDamageReportDraft.setAdapter(damageReportAdapter);

    }
}
