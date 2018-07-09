package com.example.zgfei.recyclelisttest;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(new RecycleViewAdapter(this, getData()));

        setRecyclerView();
    }

    private List<String> getData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add("item:" + i);
        }
        return list;
    }

    private void setRecyclerView() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE:
                        Log.e("recyclerView", "SCROLL_STATE_IDLE"); // 静止

                        showItem(recyclerView);
                        break;
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        Log.e("recyclerView", "SCROLL_STATE_DRAGGING"); // 手指拖动
                        break;
                    case RecyclerView.SCROLL_STATE_SETTLING:
                        Log.e("recyclerView", "SCROLL_STATE_SETTLING"); // 手指离开，自由滑动
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//                Log.e("recyclerView", "dx:" + dx + "  dy:" + dy);

            }
        });
    }

    private void showItem(RecyclerView recyclerView) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        int visibleItemPosition = layoutManager.findFirstCompletelyVisibleItemPosition();
        int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
        int lastCompletelyVisibleItemPosition = layoutManager.findLastCompletelyVisibleItemPosition();
        int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
        layoutManager.getChildAt(0);

        Log.e("firstCompleteVisible:", visibleItemPosition + "");
        Log.e("firstVisible:", firstVisibleItemPosition + "");
        Log.e("lastCompletelyVisible:", lastCompletelyVisibleItemPosition + "");
        Log.e("lastVisibleItem:", lastVisibleItemPosition + "");

        for (int i = firstVisibleItemPosition; i <= lastVisibleItemPosition; i++) {
            View view = layoutManager.getChildAt(i - firstVisibleItemPosition);
            TextView textView = view.findViewById(R.id.tv_item);
            Log.e("text view:", textView.getText().toString());

            if (null == view) {
                continue;
            }
            int[] location = new int[2];
            view.getLocationOnScreen(location);
            Rect localRect = new Rect();
            view.getLocalVisibleRect(localRect);
            int showHeight = localRect.bottom - localRect.top; // 获取每一个显示的高度值
            Log.e("show Height:", showHeight + ""); // default height 720
        }

    }
}
