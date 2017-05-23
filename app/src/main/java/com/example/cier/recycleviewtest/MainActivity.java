package com.example.cier.recycleviewtest;

import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /**
     * RecyclerView使用步骤：
     * 1.自定义 ViewHolder
     * 3.设置 LayoutManager
     * 4.自定义分割线的类并继承 RecyclerView.ItemDecoration
     * 5.自定义监听器并提供设置监听器的接口
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String> list=getData();
        System.out.println("size="+list.size());
        //初始化Adapter
        MyRecycleViewAdapter adapter=new MyRecycleViewAdapter(MainActivity.this,list);
        //获取RecyclerView
        RecyclerView recycle= (RecyclerView) findViewById(R.id.recycleList);
        //设置监听器
        recycle.setAdapter(adapter);
        //选择不同的布局管理器，会有不同的布局效果
//        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
//        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(this,2);
        RecyclerView.LayoutManager layoutManager=new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL);
        //设置布局管理器
        recycle.setLayoutManager(layoutManager);
        //设置分割线
        recycle.addItemDecoration(new MyRecycleViewAdapter.DividerItemDecoration(10));
        //给自定义的adapter设置 textItem 监听器
        adapter.setOnItemClickListener(new MyRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, "click on text,position="+position, Toast.LENGTH_SHORT).show();
                ImageView image= (ImageView) view.findViewById(R.id.item_image);
                final int pos=position;
                if(image!=null) {
                    image.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(MainActivity.this, "image image image,position=" + pos, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

    }

    private List<String> getData(){
        List<String> list=new ArrayList<>();
        for(int i=0;i<40;i++)
            list.add("list"+i);
        return list;
    }
}
