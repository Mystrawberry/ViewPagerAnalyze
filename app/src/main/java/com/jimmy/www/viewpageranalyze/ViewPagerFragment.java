package com.jimmy.www.viewpageranalyze;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * <pre>
 *     author : Jimmy.tsang
 *     e-mail : jimmytsangfly@gmail.com
 *     time   : 2017/08/30
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class ViewPagerFragment extends Fragment {
    public ViewPagerFragment() {

    }


    public static ViewPagerFragment newInstance(String text) {

        Bundle args = new Bundle();
        args.putString("text", text);

        ViewPagerFragment fragment = new ViewPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_pager_fragment, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String text = getArguments().getString("text");

        final ImageView imageView = (ImageView) view.findViewById(R.id.id_fate_iv_nc);
        ViewCompat.setRotation(imageView,-100);

//        Matrix imageMatrix = imageView.getImageMatrix();
//        imageMatrix.setRotate(180);
//        imageMatrix.setTranslate(200,200);





        final    Button btn = (Button) view.findViewById(R.id.btn);
      //  ViewCompat.setRotation(btn, 20);
        ViewCompat.setRotation(btn,-100);

        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(text);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   getActivity().startActivity(new Intent(getActivity(),FirstActivity.class));
                startAnim(view, imageView);
            }
        });


    }

    private void startAnim(View view, View tagerView) {


        ArrayList<Animator> animList = new ArrayList<>();
        AnimatorSet animatorSet = new AnimatorSet();

        float rotation =  tagerView.getRotation();

        // 中心点的定义  point ,RotationX 等信息
        ObjectAnimator ob = ObjectAnimator.ofFloat(tagerView, View.ROTATION,rotation , 100);
        ob.setRepeatCount(ValueAnimator.INFINITE);
        ob.setRepeatMode(ValueAnimator.REVERSE);

        // pivotX 中心点的意思：如果要保持控件右边不动的话，就使用控件宽度
        // 如果要保持中间不动的话则除以二
        //如果左边的是0
        int ivRight = tagerView.getWidth();
        ObjectAnimator obPoint = ObjectAnimator.ofFloat(tagerView, "pivotX", ivRight/2 , ivRight/2 );
        obPoint.setRepeatCount(ValueAnimator.INFINITE);
        obPoint.setRepeatMode(ValueAnimator.REVERSE);

        // pivotY 默认中心点是控件高度的一半
        ObjectAnimator obPointY = ObjectAnimator.ofFloat(tagerView, "pivotY", 0, 0);
        obPointY.setRepeatCount(ValueAnimator.INFINITE);
        obPointY.setRepeatMode(ValueAnimator.REVERSE);


        animList.add(ob);
        animList.add(obPoint);
        animList.add(obPointY);

        animatorSet.playTogether(animList);
        animatorSet.setDuration(500);
        animatorSet.start();
    }
}
