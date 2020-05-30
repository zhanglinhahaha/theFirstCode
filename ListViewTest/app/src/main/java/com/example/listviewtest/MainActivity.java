package com.example.listviewtest;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /*
    //直接写入数据
    private String[] data = {"RNG", "FPX", "IG", "SKT", "GRF", "DWG",
    "TL", "C9", "CG", "G2", "FNC", "SPY", "HKA", "JT", "GAM", "AHQ"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                MainActivity.this,android.R.layout.simple_list_item_1,data);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }
    */
    private List<Team> teamList = new ArrayList<>();

//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        initTeams();
//        TeamAdapter adapter = new TeamAdapter(MainActivity.this,
//                R.layout.team_item,teamList);
//        ListView listView = (ListView) findViewById(R.id.list_view);
//        listView.setAdapter(adapter);
//    }
    //添加点击事件
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTeams();
        TeamAdapter adapter = new TeamAdapter(MainActivity.this,
                R.layout.team_item,teamList);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Team team = teamList.get(i);
                Toast.makeText(MainActivity.this,team.getName(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initTeams() {
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
