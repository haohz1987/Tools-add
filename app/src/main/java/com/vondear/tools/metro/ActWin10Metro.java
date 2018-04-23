package com.vondear.tools.metro;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.vondear.tools.R;
import com.vondear.tools.metro.Metro.Type;
import com.vondear.tools.tools.LogT;

public class ActWin10Metro extends AppCompatActivity{

    private MetroLayout layout;
    private  int metroCount = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win10_metro);
        //暂时5354才能排满窗口，色块为至少2大一中能填充满，大色块可以设置更多，会自动缩小，如果未满8个色块，填充敬请期待
        layout = new MetroLayout(this, 5, 3, 5, 4);

        putMetroData(Type.BIG, R.drawable.login, "大图标", Color.MAGENTA);
        putMetroData(Type.BIG, R.drawable.remberme, "可编辑", Color.BLUE);
        putMetroData(Type.BIG, R.drawable.e_mail, "邮件", Color.GRAY);
        putMetroDataBg(Type.BIG, R.drawable.weather, "天气", R.drawable.yellow_bg);
        putMetroData(Type.MIDDLE, R.drawable.e_mail, "设置", Color.LTGRAY);
        putMetroDataBg(Type.BIG, R.drawable.menu, "邮箱", R.drawable.black_bg);

        while (metroCount < 8) {
            LogT.w("已安装的应用数量：" + metroCount);
            putMetroDataBg(Type.SMALL, R.drawable.weather, "敬请期待", R.drawable.yellow_bg);
        }
        for (int i = 0; i < MetroLayout.metros.size(); i++) {
            MetroLayout.metros.get(MetroLayout.metros.keyAt(i))
                    .setOnClickListener(new Metro.OnClickListener() {

                        @Override
                        public void onClick(Metro metro) {
                            Toast.makeText(getApplicationContext(),
                                    "Click" + metro.getTitle(), Toast.LENGTH_SHORT)
                                    .show();
                        }
                    });
        }
        layout.useGraph();
        FrameLayout box = (FrameLayout) findViewById(R.id.box);
        box.addView(layout);
    }
    /**
     * 纯色背景
     */
    private void putMetroData(Type t, int ImgRes, String metroTxt, int bgColor) {
        Metro metro = new Metro(this, t);
        metro.setTag("metro" + (metroCount + 1));
        metro.setImg(ImgRes);
        metro.setTxt(metroTxt);
        if (bgColor != -1)
            metro.setBackgroundColor(bgColor);
        metroCount++;
        metro.addInMetro(layout);
    }

    /**
     * 自定义背景
     */
    private void putMetroDataBg(Type t, int ImgRes, String metroTxt, int bgDrawable) {
        Metro metro = new Metro(this, t);
        metro.setTag("metro" + (metroCount + 1));
        metro.setImg(ImgRes);
        metro.setTxt(metroTxt);
        metroCount++;
        if (bgDrawable != -1)
            metro.setBackground(bgDrawable);
        metro.addInMetro(layout);
    }
}
