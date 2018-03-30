package com.fujisoft.ic.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.fujisoft.ic.R;
import com.fujisoft.ic.activity.NoticeActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PropertyFragment extends Fragment {
    //    private Context mContext;
    @BindView(R.id.property_gv)
    GridView propertyGv;
    Unbinder unbinder;
    private List<Map<String, Object>> dataList;
    private SimpleAdapter adapter;

    public PropertyFragment newInstance(Context context) {
        PropertyFragment fragment = new PropertyFragment();
//        Bundle args = new Bundle();
//        args.putString("agrs1", param1);
//        fragment.setArguments(args);
//        mContext = context;
        return fragment;
    }

    public static PropertyFragment newInstance() {
        return new PropertyFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.property_fragment, container, false);
//        Bundle bundle = getArguments();
//        String agrs1 = bundle.getString("agrs1");
//        TextView tv = (TextView)view.findViewById(R.id.container);
//        tv.setText(agrs1);
        unbinder = ButterKnife.bind(this, view);
        //初始化数据
        initData();
        String[] from = {"img", "text"};

        int[] to = {R.id.img, R.id.text};

        adapter = new SimpleAdapter(getActivity(), dataList, R.layout.gridview_item, from, to);

        propertyGv.setAdapter(adapter);

        propertyGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int pos,
                                    long arg3) {
                TextView tv = view.findViewById(R.id.text);
                Intent intent= null;
                switch (pos){
                    case 0:
                        intent = new Intent(getActivity(), NoticeActivity.class);
                        break;
                    case 1:
                        intent = new Intent(getActivity(), NoticeActivity.class);
                        break;

                        default:
                            break;

                }
//                ActivityOptionsCompat optionsCompat =
//                        ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), tv, "GV_TITLE");
//                getActivity().startActivity(intent,optionsCompat.toBundle());
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    void initData() {
        //图标
        int icno[] = {R.drawable.notice, R.drawable.news, R.drawable.activity,
                R.drawable.pay, R.drawable.trouble, R.drawable.advice,
                R.drawable.rent, R.drawable.pay};
        //图标下的文字
        String name[] = {"园区公告", "园区新闻", "园区活动", "缴费中心",
                "故障报修", "投诉建议", "房屋租售","入驻企业"};
        dataList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < icno.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("img", icno[i]);
            map.put("text", name[i]);
            dataList.add(map);
        }
    }
}
