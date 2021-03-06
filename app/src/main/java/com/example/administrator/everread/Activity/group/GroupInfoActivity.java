package com.example.administrator.everread.Activity.group;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.everread.Activity.group.contentOfInfo.ActicityGroupActivity;
import com.example.administrator.everread.Activity.group.contentOfInfo.MemberGroupActivity;
import com.example.administrator.everread.Activity.group.contentOfInfo.NewsGroupActivity;
import com.example.administrator.everread.ImageAdapter;
import com.example.administrator.everread.R;

/**
 * Created by Administrator on 2017/5/6.
 */


public class GroupInfoActivity extends ActivityGroup {

    BottomNavigationView bottomNavigationView;
    private ImageAdapter topImgAdapter;
    private ImageView back;
    public LinearLayout container;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groupinfo);


        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);

        back= (ImageView) findViewById(R.id.back2);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GroupInfoActivity.this,GroupTalkActivity.class);
                startActivity(intent);
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                SwitchActivity(0);
                                return true;
                            case R.id.navigation_dashboard:
                                SwitchActivity(1);;
                                return true;
                            case R.id.navigation_notifications:
                                SwitchActivity(2);
                                return true;
                        }
                        return false;
                    }
                });
        container = (LinearLayout) findViewById(R.id.Container);
        SwitchActivity(0);//默认打开第0页
    }

    void SwitchActivity(int id)
    {
        container.removeAllViews();//必须先清除容器中所有的View
        Intent intent =null;
        if (id == 0 ) {
            intent = new Intent(GroupInfoActivity.this, MemberGroupActivity.class);
        } else if (id == 1) {
            intent = new Intent(GroupInfoActivity.this, NewsGroupActivity.class);
        }else if (id == 2) {
            intent = new Intent(GroupInfoActivity.this, ActicityGroupActivity.class);
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //Activity 转为 View
        Window subActivity = getLocalActivityManager().startActivity(
                "subActivity", intent);
        //容器添加View
        container.addView(subActivity.getDecorView(),
                ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
    }

}
