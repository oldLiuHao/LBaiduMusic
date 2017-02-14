package com.a3g.lanou.lbaidumusic.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.a3g.lanou.lbaidumusic.R;

/**
 * Created by liuHao on 17/2/11.
 */
public class MyFragment extends BaseFragment{

    private TextView tvLoginBottom;
    @Override
    protected int bindLayout() {
        return R.layout.fragment_my ;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        tvLoginBottom = (TextView) view.findViewById(R.id.tv_login_parent_bottom_my_fragment);
    }

    @Override
    protected void initData() {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(tvLoginBottom.getText().toString());
        ForegroundColorSpan buleSpan = new ForegroundColorSpan(Color.parseColor("#20ACFE"));
        spannableStringBuilder.setSpan(buleSpan,0,4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvLoginBottom.setText(spannableStringBuilder);
    }


    @Override
    protected void bindEvent() {

    }
}
