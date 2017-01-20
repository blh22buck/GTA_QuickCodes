package com.example.m.designdemo;

import android.media.Image;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.example.m.designdemo.R.drawable.ic_attachment;
import static com.example.m.designdemo.R.drawable.ic_done;
import static com.example.m.designdemo.R.drawable.ic_emoticon;
import static com.example.m.designdemo.R.drawable.ic_image;

public class DesignDemoRecyclerAdapter extends RecyclerView.Adapter<DesignDemoRecyclerAdapter.ViewHolder> {

    private List<String> mItems;
    private ArrayList<Object> iconArray;

    DesignDemoRecyclerAdapter(List<String> items) {
        mItems = items;

    }
    DesignDemoRecyclerAdapter(List<String> _items, ArrayList<Object> _iconArray){
        mItems = _items;
        iconArray = _iconArray;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row, viewGroup, false);

        return new ViewHolder(v);
    }


    //for each viewholder, create content
    //set up each item on each card in the list
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        String item = mItems.get(i);
        viewHolder.mTextView.setText(item);
        //image is set here
        viewHolder.mImageView.setImageResource(R.drawable.frost);

        //cast object to Image
        //add icons/imgs to the mImageView's on card
       /* int img =(int) (iconArray.get(i));
        viewHolder.mImageView.setImageResource(img);
       // viewHolder.mImageView.setImageResource(ic_attachment);

       */

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //create the TextView for the Card
        //including icon and text
        private final TextView mTextView;
        private final ImageView mImageView;

        ViewHolder(View v) {
            super(v);

            mTextView = (TextView)v.findViewById(R.id.card_text);
            mImageView = (ImageView)v.findViewById(R.id.cardIcon);
        }
    }

}