package com.jimmy.www.viewpageranalyze;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String text = getArguments().getString("text");

        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(text);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(),FirstActivity.class));
            }
        });

    }
}
