package com.toly1994.analyzer.widget;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.toly1994.analyzer.R;

import java.util.List;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2019/2/19/019:16:23<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：
 */
public class FlowerAdapter extends BaseAdapter {

    private List<Petal> mPetals;

    public FlowerAdapter(List<Petal> petals) {
        mPetals = petals;
    }

    @Override
    public int getCount() {
        return mPetals.size();
    }

    @Override
    public Object getItem(int position) {
        return mPetals.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.flower_item, parent, false);
        ImageView iv = view.findViewById(R.id.id_pic);
        iv.setImageResource(mPetals.get(position).resId);
        TextView tv = view.findViewById(R.id.id_info);
        tv.setText(mPetals.get(position).info);
        return view;
    }
}
