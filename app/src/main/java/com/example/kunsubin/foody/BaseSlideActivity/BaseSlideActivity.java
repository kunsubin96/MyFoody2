package com.example.kunsubin.foody.BaseSlideActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.kunsubin.foody.R;

/**
 * Created by kunsubin on 5/17/2017.
 */
//viết lại lớp kế thừa từ AppCompatActivity tạo hiệu ứng chuyển động trượt qua trái phải khi đóng mở activity
public class BaseSlideActivity extends AppCompatActivity {
    public BaseSlideActivity() {
        super();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //khi mở activity thì cho trượt từ phải sang trái
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    @Override
    public void finish() {
        super.finish();
        //đòng thì trượt từ trái sang phải
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }
}
