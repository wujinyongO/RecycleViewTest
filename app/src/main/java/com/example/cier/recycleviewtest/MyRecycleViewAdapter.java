package com.example.cier.recycleviewtest;

import android.content.Context;
import android.graphics.Rect;
import android.media.Image;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import static android.provider.VoicemailContract.Voicemails.ITEM_TYPE;

/**
 * Created by cier on 2017/5/15.
 */

public class MyRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> list;
    private Context context;
    private LayoutInflater inflater;
    private OnItemClickListener mOnItemClickListener;

    MyRecycleViewAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(this.context);
    }

    MyRecycleViewAdapter() {

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==0) {
            View view = inflater.inflate(R.layout.item, parent, false);
            MyViewHolder viewHolder = new MyViewHolder(view);
            return viewHolder;
        }else{
            View view=inflater.inflate(R.layout.item_second,parent,false);
            ImageViewHolder viewHolder=new ImageViewHolder(view);
            return viewHolder;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return  position%2;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        //设置随机高度
        ViewGroup.LayoutParams param = holder.itemView.getLayoutParams();//得到item的LayoutParams布局参数
        param.height = (int) (100 + Math.random() * 400);//把随机的高度赋予item布局
        holder.itemView.setLayoutParams(param);//把params设置给item布局
        System.out.println("height=" + param.height);

        if(holder instanceof MyViewHolder) {
            ((MyViewHolder) holder).tv.setText(list.get(position));
        }else if (holder instanceof ImageViewHolder) {
            ((ImageViewHolder) holder).tv.setText(list.get(position));
            ((ImageViewHolder) holder).image.setImageResource(R.drawable.asd);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos=holder.getLayoutPosition();
                mOnItemClickListener.onItemClick(holder.itemView,pos);
            }
        });
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //自定义ViewHolder
    static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.itemText);
        }
    }

    //imageViewHolder
    static class ImageViewHolder extends RecyclerView.ViewHolder{
        private TextView tv;
        private ImageView image;

        public ImageViewHolder(View itemView) {
            super(itemView);
            tv= (TextView) itemView.findViewById(R.id.item_text2);
            image= (ImageView) itemView.findViewById(R.id.item_image);
        }
    }

    //自定义分割线
    static class DividerItemDecoration extends RecyclerView.ItemDecoration {

        private int space = 1;

        DividerItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;
//            if(parent.getChildAdapterPosition(view)==0){
            outRect.top = space;
//            }
        }

    }

    //自定义监听器
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
