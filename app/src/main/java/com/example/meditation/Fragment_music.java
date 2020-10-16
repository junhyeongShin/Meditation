package com.example.meditation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

public class Fragment_music extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    ArrayList<CalData> arrayList;
    ArrayList<CalData> eventList;
    ArrayList<Integer> DayList;
    Button btn_add;
    GridView gridView;
    DateAdapter dateAdapter;
    RecyclerView rv;
    ListAdapter adapter;
    ItemTouchHelper helper;

    Calendar calender;
    Calendar mCalToday;
    TextView prev;
    TextView next;
    TextView month;
    int thisMonth;
    int thisYear;
    int event_year;
    int event_month;
    int event_day;
    int position_day;
    int get_year;
    int get_month;
    int get_day;
    int get_img;

    @Override
    public void onStart() {
        super.onStart();

        System.out.println("onstart");
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("input", 0); // 0 :MODE_Privte
        get_year = sharedPreferences.getInt("year", 9999); //값 , 비어있을때
        get_month = sharedPreferences.getInt("month", 9999); //값 , 비어있을때
        get_day = sharedPreferences.getInt("day", 9999); //값 , 비어있을때
        get_img = sharedPreferences.getInt("img", 9999);


        if (get_year != 9999 && get_month != 9999 && get_day != 9999 && get_img != 9999) {
            System.out.println("추가했다");

            AddEvent(get_year, get_month, get_day, get_img); // 리스트 뷰 저장.

            System.out.println(get_year + "/" + get_month + "/" + get_day);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("year");
            editor.remove("month");
            editor.remove("day");
            editor.remove("img");
            editor.apply(); // commit 저장 완료시다음진행, apply 저장하면서 다음진행

        }

    }

    @Override
    public void onStop() {
        super.onStop();
        System.out.println("onstop");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.activity_calendar, container, false);


        btn_add = viewGroup.findViewById(R.id.btn_add);
        gridView = viewGroup.findViewById(R.id.gridView);
        month = viewGroup.findViewById(R.id.month);
        prev = viewGroup.findViewById(R.id.prev);
        next = viewGroup.findViewById(R.id.next);

        rv = viewGroup.findViewById(R.id.rv);

        //RecyclerView의 레이아웃 방식을 지정
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(manager);

        //RecyclerView의 Adapter 세팅
        adapter = new ListAdapter(getContext());
        rv.setAdapter(adapter);

        //ItemTouchHelper 생성
        helper = new ItemTouchHelper(new ItemTouchHelperCallback(adapter));

        //RecyclerView에 ItemTouchHelper 붙이기
        helper.attachToRecyclerView(rv);


        //레이아웃에 표시되는 달력
        calender = Calendar.getInstance();

        // 날짜 및 요일 구하기용 달력
        mCalToday = Calendar.getInstance();

        thisMonth = calender.get(Calendar.MONTH) + 1;
        thisYear = calender.get(Calendar.YEAR);

        setCalendarDate(thisYear, thisMonth);
        Log.i("" + thisYear, "" + thisMonth);
        month.setText(thisYear + " / " + thisMonth);


        prev.setOnClickListener(this);
        next.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        gridView.setOnItemClickListener(this);


        CalData calData = new CalData(thisYear,thisMonth,position_day,R.drawable.ic_baseline_sentiment_very_dissatisfied_48,
                "test","test");
        adapter.addItem(calData);
        adapter.addItem(calData);
        adapter.addItem(calData);
        adapter.addItem(calData);
        adapter.addItem(calData);
        adapter.addItem(calData);
        adapter.addItem(calData);
        adapter.addItem(calData);
        adapter.addItem(calData);
        adapter.addItem(calData);
        adapter.addItem(calData);
        adapter.addItem(calData);
        adapter.addItem(calData);

        return viewGroup;
    }

    public void setCalendarDate(int year, int month) {

        arrayList = new ArrayList<>();
        eventList = new ArrayList<>();

        calender.set(Calendar.MONTH, month - 1);

        calender.set(year, month - 1, 1);
        int startday = calender.get(Calendar.DAY_OF_WEEK);

        if (startday != 1) {
            for (int i = 0; i < startday - 1; i++) {
                arrayList.add(null);
                eventList.add(null);
            }
        }

        for (int i = 0; i < calender.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            eventList.add(null);
            mCalToday.set(calender.get(Calendar.YEAR), month - 1, (i + 1));
            arrayList.add(new CalData((i + 1), mCalToday.get(Calendar.DAY_OF_WEEK), mCalToday.get(Calendar.YEAR), mCalToday.get(Calendar.MONTH)));
        }

        dateAdapter = new DateAdapter(getContext(), arrayList, eventList);
        gridView.setAdapter(dateAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.prev:
                Log.i("" + thisYear, "" + thisMonth);
                if (thisMonth > 1) {
                    thisMonth--;
                } else {
                    thisYear--;
                    thisMonth = 12;
                }
                Log.i("" + thisYear, "" + thisMonth);
                setCalendarDate(thisYear, thisMonth);
                month.setText(thisYear + " / " + thisMonth);
                dateAdapter.notifyDataSetChanged();
                break;

            case R.id.next:
                Log.i("" + thisYear, "" + thisMonth);
                if (thisMonth < 12) {
                    thisMonth++;

                } else {
                    thisYear++;
                    thisMonth = 1;
                }
                Log.i("" + thisYear, "" + thisMonth);
                setCalendarDate(thisYear, thisMonth);
                month.setText(thisYear + " / " + thisMonth);
                dateAdapter.notifyDataSetChanged();
                break;
            case R.id.btn_add:
                Log.i("btn_add", "click");


                Intent mIntent = new Intent(getActivity().getApplicationContext(), InputEventActivity.class);
                getActivity().startActivity(mIntent);

                System.out.println("인텐트 갔다왔다");

//                SharedPreferences spf = getActivity().getSharedPreferences("",0);
//                SharedPreferences.Editor editor = spf.edit();
//                String str = thisYear+"-"+thisMonth+"-event"+eventList.size();
//                System.out.println(str);
//                editor.putString(str,"");
//                editor.apply();


                break;
        }
    }

    public void AddEvent(int year, int month, int day, int img) {
        System.out.println("AddEvent 실행");
        SharedPreferences spf = getActivity().getSharedPreferences(thisYear + "-" + thisMonth, 0); // 0 :MODE_Privte
        SharedPreferences.Editor editor = spf.edit();

        if (spf.getString("event", "defValue") == null || Objects.equals(spf.getString("event", "defValue"), "defValue")) {
            editor.putInt("year", year);
            editor.putInt("month", month);
            editor.putInt("day", day);
            editor.putInt("img", img);
            editor.commit();
            System.out.println("이벤트 저장 " + year + "/" + month + "/" + day + "/" + img);
        }
    }

    private void setStringArrayPref(String key, ArrayList<String> values) {
        SharedPreferences prefs = Objects.requireNonNull(getActivity()).getSharedPreferences(thisYear + "-" + thisMonth, 0);
        SharedPreferences.Editor editor = prefs.edit();
        JSONArray a = new JSONArray();
        for (int i = 0; i < values.size(); i++) {
            a.put(values.get(i));
        }
        if (!values.isEmpty()) {
            editor.putString(key, a.toString());
        } else {
            editor.putString(key, null);
        }
        editor.apply();
    }

    private ArrayList<String> getStringArrayPref(String key) {
        SharedPreferences prefs = Objects.requireNonNull(getActivity()).getSharedPreferences(thisYear + "-" + thisMonth, 0);
        String json = prefs.getString(key, null);
        ArrayList<String> urls = new ArrayList<String>();
        if (json != null) {
            try {
                JSONArray a = new JSONArray(json);
                for (int i = 0; i < a.length(); i++) {
                    String url = a.optString(i);
                    urls.add(url);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return urls;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId()) {
            case R.id.gridView:
                position_day = position - (arrayList.size() - calender.getActualMaximum(Calendar.DAY_OF_MONTH)) + 1;
                break;
        }

    }

    private void setUpRecyclerView() {
        rv.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                helper.onDraw(c, parent, state);
            }
        });
    }

}
