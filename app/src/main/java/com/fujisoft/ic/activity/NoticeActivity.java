package com.fujisoft.ic.activity;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.fujisoft.ic.R;
import com.fujisoft.ic.util.BaseAdapter;
import com.fujisoft.ic.util.BaseHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NoticeActivity extends AppCompatActivity {

    @BindView(R.id.back_img)
    ImageView backImg;
    @BindView(R.id.notice_rv)
    RecyclerView noticeRv;
    private List<Map<String, String>> list;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        ButterKnife.bind(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        noticeRv.setLayoutManager(layoutManager);
        initData();
        myAdapter = new MyAdapter(list);
        myAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onItemClick(View view, int postion) {
                TextView title = view.findViewById(R.id.rv_item_title);
                TextView date = view.findViewById(R.id.rv_item_date);
                Intent intent = new Intent(NoticeActivity.this, NoticeDetailActivity.class);
                intent.putExtra("title",title.getText());
                intent.putExtra("date",date.getText());
//                ActivityOptionsCompat options =
//                        ActivityOptionsCompat.makeSceneTransitionAnimation(NoticeActivity.this, title, "title");
//                ActivityOptionsCompat optionsCompat =
//                        ActivityOptionsCompat.makeSceneTransitionAnimation(NoticeActivity.this, Pair.create(title, "title"),
//                                Pair.create(date, "date"));
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(NoticeActivity.this,
                        new Pair(title, "title"), new Pair(date, "date"));
                startActivity(intent, options.toBundle());
            }
        });
        noticeRv.setAdapter(myAdapter);

    }

    private void initData() {
        list = new ArrayList<>();
        Map map = new HashMap();
        map.put("title", "Android RecyclerView比较常用的使用方法总结 - CSDN博客");
        map.put("date", "2018年03月30号");
        list.add(map);
        Map map1 = new HashMap();
        map1.put("title", "Android RecyclerView比较常用的使用方法");
        map1.put("date", "2018年03月30号");
        list.add(map1);
        Map map2 = new HashMap();
        map2.put("title", "Android RecyclerView比较常用的使用方法总结 - CSDN博客Android RecyclerView比较常用的使用方法总结 - CSDN博客");
        map2.put("date", "2018年03月30号");
        list.add(map2);
    }

    @OnClick(R.id.back_img)
    public void onViewClicked() {
        finish();
    }

    public class MyAdapter extends BaseAdapter<Map<String, String>> {
        public MyAdapter(List<Map<String, String>> list) {
            super(R.layout.notice_rv_item, list);
        }

        @Override
        protected void convert(BaseHolder holder, Map<String, String> item) {
            holder.setText(R.id.rv_item_title, item.get("title"));
            holder.setText(R.id.rv_item_date, item.get("date"));
        }
    }
}
