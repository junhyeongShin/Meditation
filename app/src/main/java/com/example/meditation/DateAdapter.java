package com.example.meditation;

import android.content.Context;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

class DateAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<CalData> arrData;
    private ArrayList<CalData> eventData;
    private ArrayList<Integer> DayEvent;
    private LayoutInflater inflater;
    private Calendar calendar;
    private Date date;
    private int isclick ;
    private int day_check;

    public DateAdapter(Context c, ArrayList arr,ArrayList eventData) {
        this.context = c;
        this.arrData = arr;
        this.eventData = eventData;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return arrData.size();
    }

    public Object getItem(int position) {
        return arrData.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public ArrayList<Integer> getDayEvent(int position){
        return getDayEvent(position);
    }

    public void SetDayEvent(int position, int day){
        DayEvent.remove(position);
        DayEvent.add(position,day);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        day_check=0;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.viewitem, parent, false);
        }
        date = new Date();
        calendar = Calendar.getInstance();
        calendar.setTime(date);

        TextView ViewText = convertView.findViewById(R.id.ViewText);
        ImageView icon_1 = convertView.findViewById(R.id.icon_1);
//        View grid_item = convertView.findViewById(R.id.grid_item);
        if (arrData.get(position) == null){
            ViewText.setText("");
            day_check++;
        }else {
            ViewText.setText(arrData.get(position).getDay() + "");
            if (arrData.get(position).getDayofweek() == 1) {
                ViewText.setTextColor(Color.RED);
            } else if (arrData.get(position).getDayofweek() == 7) {
                ViewText.setTextColor(Color.BLUE);
            } else {
                ViewText.setTextColor(Color.BLACK);
            }
        }

        if (arrData.get(position) != null
                && arrData.get(position).getDay() == calendar.get(Calendar.DATE)
                && calendar.get(Calendar.YEAR) == arrData.get(position).getYear()
                &&calendar.get(Calendar.MONTH) == arrData.get(position).getMonth()) {
            ViewText.setTextColor(Color.GREEN);
            Log.i("today", "" + arrData.get(position).getYear()
                    + arrData.get(position).getMonth() + calendar.get(Calendar.DATE));
        }

        if(eventData.get(position)!=null){
        Log.i("eventData"+eventData.get(position).getYear()+" / "+eventData.get(position).getMonth(),""+eventData.get(position).getDay());
        Log.i("arrData position",""+position);
        Log.i("day_check",""+day_check);
        if(eventData.get(position).getDay() == position){
//            DayEvent.add(position);
           icon_1.setVisibility(0);
        }else {
            icon_1.setVisibility(100);
        }
        }

        return convertView;
    }
}
