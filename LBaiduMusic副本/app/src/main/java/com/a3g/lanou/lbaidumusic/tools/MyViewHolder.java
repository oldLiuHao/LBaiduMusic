package com.a3g.lanou.lbaidumusic.tools;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.a3g.lanou.lbaidumusic.R;
import com.bumptech.glide.Glide;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by liuHao on 17/2/14.
 */
public class MyViewHolder extends RecyclerView.ViewHolder {
    private View view;
    private Context context;
    private SparseArray<View> viewSparseArray;

    public View getView() {
        return view;
    }

    public MyViewHolder(View itemView, Context context) {
        super(itemView);
        view = itemView;
        this.context = context;
        viewSparseArray = new SparseArray<>();
    }

    public static MyViewHolder creatMyViewHolder(Context context, ViewGroup viewGroup,int layoutId){
        View itemView = LayoutInflater.from(context).inflate(layoutId,viewGroup,false);
        MyViewHolder myViewHolder = new MyViewHolder(itemView,context);

        return myViewHolder;
    }
    public static MyViewHolder creatListViewHolder(View view,ViewGroup viewGroup,int layoutId){
        MyViewHolder myViewHolder =null;
        if (view == null ){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(layoutId,viewGroup,false);
            myViewHolder = new MyViewHolder(view,viewGroup.getContext());
            view.setTag(myViewHolder);
        }else {
            myViewHolder = (MyViewHolder) view.getTag();


        }
        return  myViewHolder;
    }

    public <T extends View> T getView(int resId){
            View view = viewSparseArray.get(resId);
            if (view==null){
                view = itemView.findViewById(resId);
                viewSparseArray.put(resId,view);
            }
            return (T) view;

    }


    public MyViewHolder setText(int resId,String s){
        TextView textView = getView(resId);
        if (s!=null){

            textView.setText(s);
        }
        return this;

    }
    public MyViewHolder setImage(int resId,String url){
        ImageView imageView   = getView(resId);
        if (url!=null){
            Glide.with(context).load(url).into(imageView);

        }
        return this;


    }
    public MyViewHolder setCircleImage(int resId,String url){
        ImageView imageView   = getView(resId);
        if (url!=null){
            Glide.with(context).load(url).bitmapTransform(new CropCircleTransformation(context)).into(imageView);

        }
        return this;


    }
    public MyViewHolder setTextColor(int resId,int color){
        TextView textView = getView(resId);
        if (color!=0){

            textView.setTextColor(color);
        }
        return this;

    }


}
