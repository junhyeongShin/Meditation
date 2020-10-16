//package com.example.meditation;
//
//import android.content.Context;
//import android.graphics.drawable.Drawable;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//
//public class ListViewAdapter extends BaseAdapter{
//    //아이템 리스트를 선언한다. 클래스 내부에서 계속 사용되야 함으로 여기서 선언
//    private ArrayList<ListViewItem> listViewItemList = new ArrayList<>();
//    private ArrayList<CalData> calDataArrayList = new ArrayList<>();
//    private ArrayList<CalData> eventArrayList = new ArrayList<>();
//
//    //생성자
//    public ListViewAdapter() {
//
//    }
//
//    // listviewitem 항목개수
//    @Override
//    public int getCount() {
//        return listViewItemList.size();
//    }
//
//    // position 위치의 item 값을 리턴
//    @Override
//    public Object getItem(int position) {
//        return listViewItemList.get(position);
//    }
//
//    // position 위치의 item 의 row id값 리턴
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    // position 위치의 item항목을 View 형식으로 얻어온다.
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        final int pos = position;
//        final Context context = parent.getContext();
//
//        // listview_item의 layout을 inflate하여 xml을 view로 만들고 convertView 참조 획득
//        if (convertView == null) {
//            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            convertView = inflater.inflate(R.layout.listview_item, parent, false);
//        }
//
//        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
//        // 화면에 표시되는 view와 코드를 연결해준다.
//        ImageView iconImageView = (ImageView) convertView.findViewById(R.id.imageView1);
//        TextView titleTextView = (TextView) convertView.findViewById(R.id.textView1);
//        TextView descTextView = (TextView) convertView.findViewById(R.id.textView2);
////        ImageButton more = convertView.findViewById(R.id.more);
//
//        // Data Set (listViewItemList) 에서 position에 위치한 데이터참조 획득
//        ListViewItem listViewItem = listViewItemList.get(position);
//
//        // 아이템 내 각 위젯에 데이터 연결
//        iconImageView.setImageDrawable(listViewItem.getIcon());
//        titleTextView.setText(listViewItem.getTitle());
//        descTextView.setText(listViewItem.getDesc());
//
//        return convertView;
//    }
//
//    // List View에 표시되는 item 데이터 추가
//    public void addItem(Drawable icon, String title, String desc) {
//        ListViewItem item = new ListViewItem();
//        item.setIcon(icon);
//        item.setTitle(title);
//        item.setDesc(desc);
//        listViewItemList.add(item);
//    }
//
//    // List View에 표시되는 item 삭제
//    public void delItem(int position) {
//        listViewItemList.remove(position);
//    }
//
//
//}
//
