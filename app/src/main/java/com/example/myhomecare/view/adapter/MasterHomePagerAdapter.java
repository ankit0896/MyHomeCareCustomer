package com.example.myhomecare.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.viewpager.widget.ViewPager;

import com.asksira.loopingviewpager.LoopingPagerAdapter;
import com.example.myhomecare.R;

import java.util.ArrayList;

public class MasterHomePagerAdapter extends LoopingPagerAdapter<Integer> {


    public MasterHomePagerAdapter(Context context, ArrayList<Integer> itemList, boolean isInfinite) {
        super(context,itemList,isInfinite);
    }

    @Override
    protected View getItemView(View convertView, int listPosition, ViewPager container) {
        ImageView view;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.custom_item_pager, null);
            container.addView(convertView);
        }
        view = convertView.findViewById(R.id.iv_view_pager);
        view.setImageResource(itemList.get(listPosition));
        return convertView;
    }


}
