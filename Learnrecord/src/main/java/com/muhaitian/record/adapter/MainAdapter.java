package com.muhaitian.record.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.muhaitian.record.R;
import com.muhaitian.record.entity.Item;

import java.util.List;

/**
 * Created by wangkang001 on 2017/7/8.
 */

public class MainAdapter extends BaseAdapter {

    private List<Item> MainContent;
    private Context mContext;
    private ListView.OnItemClickListener mOnItemClickListener;

    public MainAdapter(Context context, List<Item> list) {
        mContext = context;
        MainContent = list;
    }

    @Override
    public int getCount() {
        return MainContent.size();
    }

    @Override
    public Object getItem(int position) {
        if (MainContent != null && MainContent.size() > 0) {
            return MainContent.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Item mItem = MainContent.get(position);
        ViewHolder mViewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.main_list_item, null);
            mViewHolder = new ViewHolder();
            mViewHolder.Content = (Button) convertView.findViewById(R.id.list_item);
            final View finalConvertView = convertView;
            mViewHolder.Content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(null, finalConvertView,position,0);
                }
            });
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        mViewHolder.Content.setText(mItem.getName());

        return convertView;
    }

    public void setmOnItemClickListener(ListView.OnItemClickListener OnItemClickListener) {
        this.mOnItemClickListener = OnItemClickListener;
    }

    class ViewHolder {
        Button Content;
    }
}
