package com.example.lasai.retrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lasai.R;

import java.util.List;

public class TipsViewAdapter extends BaseAdapter {

    private Context context;
    private List<Tips> tipsList;

    public TipsViewAdapter(Context context, List<Tips> tipsList) {
        this.context = context;
        this.tipsList = tipsList;
    }

    @Override
    public int getCount() {
        return tipsList.size();
    }

    @Override
    public Object getItem(int position) {
        return tipsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return tipsList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.tips_layout_item, parent, false);

        Tips tips = tipsList.get(position);

        TextView itemTitle = convertView.findViewById(R.id.title_item);
        TextView itemText = convertView.findViewById(R.id.text_item);

        itemTitle.setText(tips.getTitle());
        itemText.setText(tips.getText());

        return convertView;
    }
}
