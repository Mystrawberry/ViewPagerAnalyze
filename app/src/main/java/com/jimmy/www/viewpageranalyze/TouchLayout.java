package com.jimmy.www.viewpageranalyze;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.widget.FrameLayout;

/**
 * <pre>
 *     author : Jimmy.tsang
 *     e-mail : jimmytsangfly@gmail.com
 *     time   : 2017/08/31
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class TouchLayout extends FrameLayout {
    private static String TAG = "TouchLayout";

    private float mLastMotionX;
    private float mLastMotionY;
    private float mInitialMotionX;
    private float mInitialMotionY;
    private int mLastScrollX;

    private int mTouchSlop;

    public TouchLayout(@NonNull Context context) {
        super(context);
        init();
    }

    public TouchLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TouchLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        final ViewConfiguration configuration = ViewConfiguration.get(getContext());
        mTouchSlop = configuration.getScaledPagingTouchSlop();


        scrollTo(-200, getScrollY()); // 如果是正的200 呢，相加是怎么样的结果呢？
        int old = getScrollX();

        Log.e("old", "oldScrollX-->" + old);


    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // return super.onInterceptTouchEvent(ev);
        // 先拦截事件
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLastMotionX = mInitialMotionX = ev.getX(0);
                mLastMotionY = mInitialMotionY = ev.getY(0);
                break;

            case MotionEvent.ACTION_MOVE:
                requestParentDisallowInterceptTouchEvent(true);
                final float x = ev.getX(0);
                // 假设down的时候坐标点是60，那么move 获取的到x减去down开始的坐标点
                // 如果为负数则表示为左。反之为右
                final float xDiff = x - mLastMotionX;

                final float oldScrollX = getScrollX();
                final float scrollX = oldScrollX + xDiff;
                if (Math.abs(xDiff) > mTouchSlop) {
                    //左边是负数，右边是正数，所以这里取反，前面添加了-
                    // 如果想要使用这种方式的话，需要记录上次滑动结束的x
                    scrollTo((int) (xDiff + mLastScrollX), getScrollY());
                    mLastMotionX = x - mInitialMotionX > 0 ? mInitialMotionX + mTouchSlop :
                            mInitialMotionX - mTouchSlop;

                }

                break;

            case MotionEvent.ACTION_CANCEL:
                break;
        }

        //   return super.onTouchEvent(ev);
        return true;
    }


    private void requestParentDisallowInterceptTouchEvent(boolean disallowIntercept) {
        final ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(disallowIntercept);
        }
    }
}
