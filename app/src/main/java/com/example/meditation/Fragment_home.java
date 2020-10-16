package com.example.meditation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class Fragment_home extends Fragment {

    MeditationContents meditationContents;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
    ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);

        User user = new User("ID","pass");
        user.img_uri_profile = String.valueOf(R.drawable.home_fill);
        user.introduce = "user introduce";

        meditationContents = new MeditationContents(0,"title","test",user,0,"category");
        meditationContents.init();

        int[] idVar = new int[10];
        idVar[0] = R.drawable.book;
        idVar[1] = R.drawable.background_sky_gif;
        idVar[2] = R.drawable.milkyway;
        idVar[3] = R.drawable.heart_empty;
        idVar[4] = R.drawable.candle;
        idVar[5] = R.drawable.book;
        idVar[6] = R.drawable.book;
        idVar[7] = R.drawable.book;
        idVar[8] = R.drawable.book;
        idVar[9] = R.drawable.book;

        GridView grid_home = viewGroup.findViewById(R.id.grid_home);
        CustomImageAdapter imageGridAdapter = new CustomImageAdapter(getActivity(), meditationContents);
        grid_home.setAdapter(imageGridAdapter);



        return viewGroup;
    }
}