package com.example.meditation;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private ArrayList<Content> mData = null ;

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.grid_item, parent, false) ;
        RecyclerAdapter.ViewHolder vh = new RecyclerAdapter.ViewHolder(view) ;

        return vh ;
    }

    // 생성자에서 데이터 리스트 객체를 전달받음.
    RecyclerAdapter(ArrayList<Content> list) {
        Log.i("RecyclerAdapter","list");
        mData = list ;
    }

    //position 에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {

        holder.img.setImageResource(mData.get(position).img);
        holder.title.setText(mData.get(position).title);
        holder.content.setText(mData.get(position).content);
        holder.music = mData.get(position).music;

        String str = String.valueOf(position);
        Log.i("onBindViewHoler",str);
    }

    //리사이클러뷰 아이템 갯수 설정하는 메소드.
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        View layout;
        TextView title ;
        TextView content ;
        ImageView img;
        int music;

        ViewHolder(View itemView) {
            super(itemView) ;
            // 뷰 객체에 연결
            layout = itemView.findViewById(R.id.grid_item_layout);
            title = itemView.findViewById(R.id.grid_item_title);
            content = itemView.findViewById(R.id.grid_item_content);
            img = itemView.findViewById(R.id.grid_item_img);

            itemView.setClipToOutline(true);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.i("adapter",""+v.getId());
        }
    }

}
