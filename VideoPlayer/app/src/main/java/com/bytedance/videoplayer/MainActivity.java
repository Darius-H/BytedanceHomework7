package com.bytedance.videoplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.TransitionOptions;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = findViewById(R.id.imageView);
        //String url = "https://s3.pstatp.com/toutiao/static/img/logo.271e845.png";
        String url="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1562328963756&di=9c0c6c839381c8314a3ce8e7db61deb2&imgtype=0&src=http%3A%2F%2Fpic13.nipic.com%2F20110316%2F5961966_124313527122_2.jpg";
        RequestOptions cropOptions = new RequestOptions();
        cropOptions = cropOptions.transform(new RoundedCorners(20));;//圆角
        /*
        "https://s3.pstatp.com/toutiao/static/img/logo.271e845.png"
        可以发现，这个url的图片会导致glide使用transition时会出现占位图不消失的bug，
        参见：https://github.com/bumptech/glide/issues/3028?source=post_page---------------------------
        和：http://bumptech.github.io/glide/doc/transitions.html#common-errors
        应该是由于图片尺寸或含有透明像素的问题,占位图大小还会对要展示的图片的大小和拉伸造成影响
        而且由于图片尺寸问题，把图片弄成圆形视觉效果也很差，应该自定义一个cropOption比较好
         */
        Glide.with(this).load(url)
                .apply(cropOptions)
                .placeholder(R.drawable.icon_progress_bar)
                .error(R.drawable.icon_failure)
                .transition(withCrossFade(1000))
                .into(imageView);
        Button videobt=findViewById(R.id.video_button);
        videobt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent t=new Intent(MainActivity.this,VideoActivity.class);
                startActivity(t);
            }
        });
    }
}
