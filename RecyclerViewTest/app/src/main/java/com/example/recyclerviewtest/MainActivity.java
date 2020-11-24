package com.example.recyclerviewtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Team> teamList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTeam();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);


        //实现垂直滑动效果
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //实现水平滑动效果
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        //实现网络布局,第一个参数是布局的列数，第二个参数是排列方向
//        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(
//                3,StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        TeamAdapter adapter = new TeamAdapter(teamList);
        recyclerView.setAdapter(adapter);
    }

    private void initTeam() {
        Team rng = new Team("RNG",R.drawable.rng);
        teamList.add(rng);
        Team ig = new Team("IG",R.drawable.ig);
        teamList.add(ig);
        Team fpx = new Team("FPX",R.drawable.rng);
        teamList.add(fpx);
        Team skt = new Team("SKT",R.drawable.ig);
        teamList.add(skt);
        Team grf = new Team("GRF",R.drawable.rng);
        teamList.add(grf);
        Team dwg = new Team("DWG",R.drawable.ig);
        teamList.add(dwg);
        Team g2 = new Team("G2",R.drawable.rng);
        teamList.add(g2);
        Team fnc = new Team("FNC",R.drawable.ig);
        teamList.add(fnc);
        Team spy = new Team("SPY",R.drawable.rng);
        teamList.add(spy);
        Team tl = new Team("TL",R.drawable.ig);
        teamList.add(tl);
        Team c9 = new Team("C9",R.drawable.rng);
        teamList.add(c9);
        Team cg = new Team("CG",R.drawable.ig);
        teamList.add(cg);
        Team hka = new Team("HKA",R.drawable.rng);
        teamList.add(hka);
        Team ahq = new Team("AHQ",R.drawable.ig);
        teamList.add(ahq);
        Team gam = new Team("GAM",R.drawable.rng);
        teamList.add(gam);
        Team jt = new Team("JT",R.drawable.ig);
        teamList.add(jt);
    }
}
