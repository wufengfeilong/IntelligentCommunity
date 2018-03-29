package com.fujisoft.ic.util;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.view.ViewTreeObserver;

public class AppBarUtil {
    boolean forbidAppBarScroll;
    AppBarLayout appbarl;

    public AppBarUtil(AppBarLayout appbarl) {
        this.appbarl = appbarl;
    }

    public void forbidAppBarScroll(boolean forbid) {

        if (forbid == forbidAppBarScroll) {
            return;
        }
        if (forbid) {
            forbidAppBarScroll = true;
            if (ViewCompat.isLaidOut(appbarl)) {
                setAppBarDragCallback(new AppBarLayout.Behavior.DragCallback() {

                    @Override
                    public boolean canDrag(@NonNull AppBarLayout appBarLayout) {
                        return false;
                    }
                });
            } else {
                appbarl.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            appbarl.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        } else {
                            appbarl.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        }
                        setAppBarDragCallback(new AppBarLayout.Behavior.DragCallback() {

                            @Override
                            public boolean canDrag(@NonNull AppBarLayout appBarLayout) {
                                return false;
                            }
                        });
                    }
                });
            }
        } else {
            forbidAppBarScroll = false;
            if (ViewCompat.isLaidOut(appbarl)) {
                setAppBarDragCallback(null);
            } else {
                appbarl.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            appbarl.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        } else {
                            appbarl.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        }
                        setAppBarDragCallback(null);
                    }
                });
            }
        }
    }

    private void setAppBarDragCallback(AppBarLayout.Behavior.DragCallback dragCallback) {
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) appbarl.getLayoutParams();
        AppBarLayout.Behavior behavior = (AppBarLayout.Behavior) params.getBehavior();
        behavior.setDragCallback(dragCallback);
    }
}
