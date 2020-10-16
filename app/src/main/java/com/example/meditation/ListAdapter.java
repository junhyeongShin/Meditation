package com.example.meditation;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ItemViewHolder> implements ItemTouchHelperListener, OnDialogListener {
    ArrayList<CalData> items = new ArrayList<CalData>();
    Context context;

    public ListAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //LayoutInflater를 이용해서 원하는 레이아웃을 띄워줌
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.listview_item, parent, false);
        return new ItemViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        //ItemViewHolder가 생성되고 넣어야할 코드들을 넣어준다.
        holder.onBind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(CalData calData) {
        items.add(calData);
        System.out.println("addItem");

    }


    @Override
    public void onItemSwipe(int position) {
        items.remove(position);
        notifyItemRemoved(position);
        System.out.println("onItemSwipe");
    }

    //왼쪽 버튼 누르면 수정할 다이얼로그 띄우기
    @Override
    public void onLeftClick(int position, RecyclerView.ViewHolder viewHolder) {
        //수정 버튼 클릭시 다이얼로그 생성
        CustomDialog dialog = new CustomDialog(context, position, items.get(position));
        //화면 사이즈 구하기
        DisplayMetrics dm = context.getApplicationContext().getResources().getDisplayMetrics();
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        //다이얼로그 사이즈 세팅
        WindowManager.LayoutParams wm = dialog.getWindow().getAttributes();
        wm.copyFrom(dialog.getWindow().getAttributes());
        wm.width = (int) (width * 0.7);
        wm.height = (int) (height*0.6); //다이얼로그 Listener 세팅
        dialog.setDialogListener(this); //다이얼로그 띄우기
        dialog.show();
        System.out.println("onLeftClick");
        notifyDataSetChanged();
    }

    //오른쪽 버튼 누르면 아이템 삭제
    @Override
    public void onRightClick(int position, RecyclerView.ViewHolder viewHolder) {
        items.remove(position);
        notifyItemRemoved(position);
        System.out.println("onRightClick");
    }

    @Override
    public void onFinish(int position, CalData calData) {
        items.set(position, calData);
        notifyItemChanged(position);
        System.out.println("onFinish");
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView1;
        TextView textView1,textView2;

        public ItemViewHolder(View itemView) {
            super(itemView);
            imageView1 = itemView.findViewById(R.id.imageView1);
            textView1 = itemView.findViewById(R.id.textView1);
            textView2 = itemView.findViewById(R.id.textView2);
        }

        public void onBind(CalData calData) {
            imageView1.setImageResource(calData.getImg());
            textView1.setText(calData.getTitle());
            textView2.setText(calData.getContent());
        }
    }
}

