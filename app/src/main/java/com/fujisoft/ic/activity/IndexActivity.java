package com.fujisoft.ic.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.fujisoft.ic.R;
import com.fujisoft.ic.fragment.MyFragment;
import com.fujisoft.ic.fragment.NeighbourFragment;
import com.fujisoft.ic.fragment.PropertyFragment;
import com.fujisoft.ic.fragment.NewsFragment;
import com.fujisoft.ic.util.GlideImageLoader;
import com.jpeng.jptabbar.JPTabBar;
import com.jpeng.jptabbar.OnTabSelectListener;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

public class IndexActivity extends AppCompatActivity {


    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.tabbar)
    JPTabBar tabbar;

    private NeighbourFragment neighbourFragment;
    private PropertyFragment propertyFragment;
    private NewsFragment newsFragment;
    private MyFragment myFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        ButterKnife.bind(this);
        initBanner();
        initJPTabBar();
        setMainFragment();
    }

    private void initJPTabBar() {
        tabbar.setTitles("物业", "企业", "商家", "我的")
                .setNormalIcons(R.drawable.property, R.drawable.neighbour, R.drawable.star, R.drawable.myhead)
                .setSelectedIcons(R.drawable.property_selected, R.drawable.neighbour_selected, R.drawable.star_selected, R.drawable.myhead_selected)
                .generate();

        tabbar.setTabListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int i) {
                FragmentManager fm = getFragmentManager();
                //开启事务
                FragmentTransaction transaction = fm.beginTransaction();
                switch (i) {
                    case 0:
                        if (propertyFragment == null) {
                            propertyFragment = PropertyFragment.newInstance();
                        }
                        transaction.replace(R.id.fragment_content, propertyFragment);
                        break;
                    case 1:
                        if (neighbourFragment == null) {
                            neighbourFragment = NeighbourFragment.newInstance("邻里");
                        }
                        transaction.replace(R.id.fragment_content, neighbourFragment);
                        break;
                    case 2:
                        if (newsFragment == null) {
                            newsFragment = NewsFragment.newInstance("商家");
                        }
                        transaction.replace(R.id.fragment_content, newsFragment);
                        break;
                    case 3:
                        if (myFragment == null) {
                            myFragment = MyFragment.newInstance("我的");
                        }
                        transaction.replace(R.id.fragment_content, myFragment);
                        break;
                    default:
                        break;
                }
                // 事务提交
                transaction.commit();
            }

            @Override
            public boolean onInterruptSelect(int i) {
                return false;
            }
        });
    }

    private void initBanner() {
        List<Integer> list = new ArrayList<>();
        list.add(R.drawable.login);
        list.add(R.drawable.shangjia);
        list.add(R.drawable.sign);
        list.add(R.drawable.timg1);
        list.add(R.drawable.timg2);
        //设置图片加载器
        banner.setImages(list).setImageLoader(new GlideImageLoader()).start();
    }

    /**
     * 设置默认的
     */
    private void setMainFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        propertyFragment = PropertyFragment.newInstance();
        transaction.replace(R.id.fragment_content, propertyFragment);
        transaction.commit();
    }
}
