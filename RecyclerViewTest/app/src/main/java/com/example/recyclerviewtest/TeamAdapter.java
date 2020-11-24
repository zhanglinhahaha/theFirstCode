package com.example.recyclerviewtest;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Created by zl on 19-10-15.
 */
public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder>  {
    private static final String TAG = "TeamAdapter";
    private List<Team> mTeamList;

    public class ViewHolder extends RecyclerView.ViewHolder {

        //实现点击事件
        View teamView;

        ImageView teamImage;
        TextView teamName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            teamView = itemView;
            teamImage = (ImageView) itemView.findViewById(R.id.team_image);
            teamName = (TextView) itemView.findViewById(R.id.team_name);
        }
    }

    public TeamAdapter(List<Team> teamList) {
        mTeamList = teamList;
    }

    @NonNull
    @Override
    public TeamAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.team_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);

        holder.teamView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = holder.getAdapterPosition();
                Team team = mTeamList.get(pos);
                Log.d(TAG, "onClick: "+team.getName());
            }
        });
        holder.teamImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = holder.getAdapterPosition();
                Team team = mTeamList.get(pos);
                Log.d(TAG, "onClick: "+team.getName());
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TeamAdapter.ViewHolder holder, int position) {
        Team team = mTeamList.get(position);
        holder.teamImage.setImageResource(team.getImageId());
        holder.teamName.setText(team.getName());
    }

    @Override
    public int getItemCount() {
        return mTeamList.size();
    }

}
