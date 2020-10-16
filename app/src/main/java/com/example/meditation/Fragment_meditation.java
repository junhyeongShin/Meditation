package com.example.meditation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Objects;

public class Fragment_meditation extends Fragment implements View.OnClickListener {

    RecyclerView recycler;
    Content content;
    Content content1;
    Content content2;
    Content content3;
    Content content4;
    Content content5;
    Content content6;
    Content content7;
    Content content8;
    Content content9;
    ArrayList<Content> list;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_meditation, container, false);

        content = new Content("title","content1",R.drawable.milkyway,R.raw.coldblue);
        content1 = new Content("title1","content1",R.drawable.happy,R.raw.whispering);
        content2 = new Content("title2","content2",R.drawable.background_sky_gif,R.raw.enviroment);
        content3 = new Content("title3","content3",R.drawable.candle,R.raw.etenalgarden);
        content4 = new Content("title4","content4",R.drawable.replay_10,R.raw.nebularfocus);
        content5 = new Content("title5","content5",R.drawable.red_heart,R.raw.whispering);
        content6 = new Content("title6","content6",R.drawable.white_heart,R.raw.coldblue);
        content7 = new Content("title7","content7",R.drawable.heart_fill,R.raw.enviroment);
        content8 = new Content("title8","content8",R.drawable.forward_10,R.raw.etenalgarden);
        content9 = new Content("title9","content9",R.drawable.bright_moon,R.raw.nebularfocus);

        list = new ArrayList<>();
        list.add(content);
        list.add(content1);
        list.add(content2);
        list.add(content3);
        list.add(content4);
        list.add(content5);
        list.add(content6);
        list.add(content7);
        list.add(content8);
        list.add(content9);


        // 리사이클러뷰에 LinearLayoutManager 객체 지정.
        recycler=viewGroup.findViewById(R.id.recycler);
        recycler.setLayoutManager(new GridLayoutManager(getContext(),2)) ;

        // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
        RecyclerAdapter adapter = new RecyclerAdapter(list) ;
        recycler.setAdapter(adapter) ;
        recycler.setOnClickListener(this);
//        recycler.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.i("recycler click","");
//            }
//        });

        return viewGroup;
    }

    @Override
    public void onClick(View v) {
        Log.i("recycler click",""+v.getId());
    }
}