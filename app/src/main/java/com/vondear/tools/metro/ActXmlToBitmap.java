package com.vondear.tools.metro;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.vondear.tools.R;
import com.vondear.tools.tools.LogT;

public class ActXmlToBitmap extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml_to_bitmap);

        LayoutInflater factory = LayoutInflater.from(this);
        View view = factory.inflate(R.layout.activity_xml_to_bitmap, null);
        //获得布局文件中的TextView
        TextView num = (TextView) view.findViewById(R.id.tv);
        //设置city的文本信息
        num.setText("xml中的textview");
        //调用下面这个方法非常重要，如果没有调用这个方法，得到的bitmap为null
        view.measure(View.MeasureSpec.makeMeasureSpec(256, View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(256, View.MeasureSpec.EXACTLY));
        //这个方法也非常重要，设置布局的尺寸和位置
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        //生成bitmap，各类bitmap的格式可能不同Bitmap.Config.ARGB_8888也比较常用
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),
                Bitmap.Config.RGB_565);
        //利用bitmap生成画布
        Canvas canvas = new Canvas(bitmap);
        LogT.w("bitmap="+bitmap.toString());
        //把view中的内容绘制在画布上
        view.draw(canvas);
    }

}
