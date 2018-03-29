package com.fujisoft.ic.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.fujisoft.ic.R;
import com.fujisoft.ic.fragment.MyFragment;
import com.fujisoft.ic.fragment.NeighbourFragment;
import com.fujisoft.ic.fragment.NewsFragment;
import com.fujisoft.ic.fragment.PropertyFragment;
import com.fujisoft.ic.util.AppBarUtil;
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
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.collapsing_tl)
    CollapsingToolbarLayout collapsingTl;
    @BindView(R.id.appbarl)
    AppBarLayout appbarl;
    @BindView(R.id.fragment_content)
    NestedScrollView fragmentContent;
    private NeighbourFragment neighbourFragment;
    private PropertyFragment propertyFragment;
    private NewsFragment newsFragment;
    private MyFragment myFragment;
    AppBarUtil appBarUtil;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        ButterKnife.bind(this);
        initView();
        initBanner();
        initJPTabBar();
        setMainFragment();
    }

    private void initView() {
        appBarUtil = new AppBarUtil(appbarl);
    }

    private void initJPTabBar() {
        tabbar.setTitles(R.string.property, R.string.company, R.string.market, R.string.mine)
                .setNormalIcons(R.drawable.property, R.drawable.neighbour, R.drawable.star, R.drawable.myhead)
                .setSelectedIcons(R.drawable.property_selected, R.drawable.neighbour_selected, R.drawable.star_selected, R.drawable.myhead_selected)
                .generate();

        tabbar.setTabListener(new OnTabSelectListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
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
                        setAppBarScroll(true);
                        titleTv.setText(R.string.app_name);
                        transaction.replace(R.id.fragment_content, propertyFragment);
                        break;
                    case 1:
                        if (neighbourFragment == null) {
                            neighbourFragment = NeighbourFragment.newInstance("邻里");
                        }
                        setAppBarScroll(true);
                        titleTv.setText(R.string.app_name);
                        transaction.replace(R.id.fragment_content, neighbourFragment);
                        break;
                    case 2:
                        if (newsFragment == null) {
                            newsFragment = NewsFragment.newInstance("商家");
                        }
                        setAppBarScroll(true);
                        titleTv.setText(R.string.market);
                        transaction.replace(R.id.fragment_content, newsFragment);
                        break;
                    case 3:
                        if (myFragment == null) {
                            myFragment = MyFragment.newInstance("我的");
                        }
                        titleTv.setText(R.string.mine);
                        appbarl.setExpanded(false);
                        setAppBarScroll(false);
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

    private void setAppBarScroll(boolean flg) {
        appBarUtil.forbidAppBarScroll(!flg);
        fragmentContent.setNestedScrollingEnabled(flg);
    }

}
