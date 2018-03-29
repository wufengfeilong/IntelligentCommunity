package com.fujisoft.ic.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.fujisoft.ic.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NeighbourFragment extends Fragment {
    @BindView(R.id.neighbour_gv)
    GridView neighbourGv;
    Unbinder unbinder;
    private List<Map<String, Object>> dataList;
    private SimpleAdapter adapter;

    public static NeighbourFragment newInstance(String param1) {
        NeighbourFragment fragment = new NeighbourFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    public static PropertyFragment newInstance() {
        return new PropertyFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.neighbour_fragment, container, false);

        unbinder = ButterKnife.bind(this, view);

        //初始化数据
        initData();
        String[] from = {"img", "text"};

        int[] to = {R.id.img, R.id.text};

        adapter = new SimpleAdapter(getActivity(), dataList, R.layout.gridview_item, from, to);

        neighbourGv.setAdapter(adapter);

        neighbourGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {


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
        int icno[] = {R.drawable.notice, R.drawable.news, R.drawable.pay,
                R.drawable.trouble};
        //图标下的文字
        String name[] = {"邻里圈子", "社区活动", "挪车信息", "二手闲置"};
        dataList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < icno.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("img", icno[i]);
            map.put("text", name[i]);
            dataList.add(map);
        }
    }
}
