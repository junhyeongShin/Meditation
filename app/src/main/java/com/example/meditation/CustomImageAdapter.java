package com.example.meditation;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;


public class CustomImageAdapter extends BaseAdapter {
        Context context = null;
    MeditationContents meditationContents;
        //-----------------------------------------------------------
        // imageIDs는 이미지 파일들의 리소스 ID들을 담는 배열입니다.
        // 이 배열의 원소들은 자식 뷰들인 ImageView 뷰들이 무엇을 보여주는지를
        // 결정하는데 활용될 것입니다.

        int[] imageIDs = null;

    public CustomImageAdapter(Context context,MeditationContents meditationContents) {
        this.context = context;
        this.meditationContents = meditationContents;

        int[] imageIDs = new int[meditationContents.arrayList.size()];
        this.imageIDs = imageIDs;

        for(int i=0; i<meditationContents.arrayList.size(); i++) {
            imageIDs[i] = meditationContents.arrayList.get(i).img;
        }
    }

        public int getCount() {
            return (null != imageIDs) ? imageIDs.length : 0;
        }

        public Object getItem(int position) {
            return (null != imageIDs) ? imageIDs[position] : 0;
        }

        public long getItemId(int position) {
            return position;
        }


        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView = null;

            if (null != convertView)
                imageView = (ImageView)convertView;
            else {
                Bitmap bmp
                        = BitmapFactory.decodeResource(context.getResources(), imageIDs[position]);
                bmp = Bitmap.createScaledBitmap(bmp, 320, 240, false);
                //---------------------------------------------------------------
                // GridView 뷰를 구성할 ImageView 뷰들을 정의합니다.
                // 뷰에 지정할 이미지는 앞에서 정의한 비트맵 객체입니다.

                imageView = new ImageView(context);
                imageView.setAdjustViewBounds(true);
                imageView.setImageBitmap(bmp);


                // 사진 항목들의 클릭을 처리하는 ImageClickListener 객체를 정의합니다.
                // 그리고 그것을 ImageView의 클릭 리스너로 설정합니다.

                ImageClickListener imageViewClickListener
                        = new ImageClickListener(context, imageIDs[position]);
                imageView.setOnClickListener(imageViewClickListener);
            }
            return imageView;
        }
    public class ImageClickListener implements View.OnClickListener {

        Context context;

        //-----------------------------------------------------------
        // imageID는 확대해서 보여줄 이미지의 리소스 ID입니다.

        int imageID;

        public ImageClickListener(Context context, int imageID) {
            this.context = context;
            this.imageID = imageID;
        }



        public void onClick(View v) {
            //---------------------------------------------------------
            // 확대된 이미지를 보여주는 액티비티를 실행하기 위해 인텐트 객체를 정의합니다.
            // 그리고 이 액티비티에 전달할 imageID의 값을 이 객체에 저장합니다.
            // 인텐트 객체를 정의 후 이 액티비티를 실행합니다.

            Intent intent = new Intent(context, MedtationPlayer.class);
            int x = 210000000;
            for(int i=0; i<meditationContents.arrayList.size(); i++) {
                if(imageID==meditationContents.arrayList.get(i).img){
                   x=i;
                    break;
                }
            }

            Log.i("grid view onclick","클릭");
            intent.putExtra("contents ID", x);
            context.startActivity(intent);
        }
    }



}
