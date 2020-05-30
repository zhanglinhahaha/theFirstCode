package com.example.listviewtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zl on 19-10-15.
 */
public class TeamAdapter extends ArrayAdapter<Team> {

    private int resourceId;


    public TeamAdapter(Context context, int resource, List<Team> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        Team team = getItem(position);//获取当前实例
//        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
//        ImageView teamImage = (ImageView) view.findViewById(R.id.team_image);
//        TextView teamName = (TextView) view.findViewById(R.id.team_name);
//        teamImage.setImageResource(team.getImageId());
//        teamName.setText(team.getName());
//        return view;
//    }
    //利用convertView提高性能, 不会重复加载布局
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        Team team = getItem(position);//获取当前实例
//        View view;
//        if(convertView == null) {
//           view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
//        }
//        else {
//            view = convertView;
//        }
//        ImageView teamImage = (ImageView) view.findViewById(R.id.team_image);
//        TextView teamName = (TextView) view.findViewById(R.id.team_name);
//        teamImage.setImageResource(team.getImageId());
//        teamName.setText(team.getName());
//        return view;
//    }
    //添加一个Viewholder，对控件的实例进行缓存
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Team team = getItem(position);//获取当前实例
        View view;
        ViewHolder viewholder;
        if(convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewholder = new ViewHolder();
            viewholder.teamImage = (ImageView) view.findViewById(R.id.team_image);
            viewholder.teamName = (TextView) view.findViewById(R.id.team_name);
            view.setTag(viewholder);//将viewholder存到view中
        }
        else {
            view = convertView;
            viewholder = (ViewHolder) view.getTag();//重新获取viewholder
        }
        viewholder.teamImage.setImageResource(team.getImageId());
        viewholder.teamName.setText(team.getName());
        return view;
    }

    class ViewHolder{
        ImageView teamImage;
        TextView teamName;
    }
}
