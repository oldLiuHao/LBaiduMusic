package com.a3g.lanou.lbaidumusic.fragment.musicFragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.a3g.lanou.lbaidumusic.MyApp;
import com.a3g.lanou.lbaidumusic.R;
import com.a3g.lanou.lbaidumusic.activity.WebActivity;
import com.a3g.lanou.lbaidumusic.adapter.HappyGirdRecommendAdapter;
import com.a3g.lanou.lbaidumusic.adapter.HotGirdRecommendAdapter;
import com.a3g.lanou.lbaidumusic.adapter.MVGirdRecommendAdapter;
import com.a3g.lanou.lbaidumusic.adapter.NewGirdARecommendAdapter;
import com.a3g.lanou.lbaidumusic.adapter.OriginalGirdRecommendAdapter;
import com.a3g.lanou.lbaidumusic.adapter.RecommendRecommendGirdAdapter;
import com.a3g.lanou.lbaidumusic.adapter.SingerGirdApapter;
import com.a3g.lanou.lbaidumusic.adapter.SpecialListRecommendAdapter;
import com.a3g.lanou.lbaidumusic.adapter.TodayListRecommendAdapter;
import com.a3g.lanou.lbaidumusic.bean.RecommendBean;
import com.a3g.lanou.lbaidumusic.fragment.BaseFragment;
import com.a3g.lanou.lbaidumusic.myinterface.CallBack;
import com.a3g.lanou.lbaidumusic.tools.MyBean;
import com.a3g.lanou.lbaidumusic.tools.MyUrl;
import com.a3g.lanou.lbaidumusic.tools.NetTool;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuHao on 17/2/13.
 */
public class RecommendFragment extends BaseFragment implements OnBannerClickListener, View.OnClickListener, AdapterView.OnItemClickListener {
    private GridView singerGird,recommendGird,newGird,hotGird,originalGird,mvGird,happyGird;
    private ListView totayList,specialList;
    private SingerGirdApapter singerGirdApapter;
    private RecommendBean recommendBean;
    private Banner banner;
    private List<String> bannerList;
    private ImageView ivRecommend,ivNew,ivHot, ivRadio,ivToday,ivOriginal,ivMv,ivHappy,ivSpecial,ivAdv;
    private TextView tvRecommend,tvNew,tvHot,tvRadio,tvToday,tvOriginal,tvMv,tvHappy,tvSpecial,tvNameAdv,tvAuthorAdv,tvMoreRecommend;
    private LinearLayout lilaAdv;
    private RecommendRecommendGirdAdapter recommendRecommendGirdAdapter;
    private NewGirdARecommendAdapter newGirdARecommendAdapter;
    private HotGirdRecommendAdapter hotGirdRecommendAdapter;
    private TodayListRecommendAdapter todayListRecommendAdapter;
    private OriginalGirdRecommendAdapter originalGirdRecommendAdapter;
    private MVGirdRecommendAdapter mvGirdRecommendAdapter;
    private HappyGirdRecommendAdapter happyGirdRecommendAdapter;
    private SpecialListRecommendAdapter specialListRecommendAdapter;
    private static final String TAG = "RecommendFragment";
    private Intent intent;
    private Bundle bundle;
    private FragmentManager fragmentManager;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        banner = (Banner) view.findViewById(R.id.banner_recommend);
        singerGird = (GridView) view.findViewById(R.id.gird_singer_recommend);

        ivRecommend = (ImageView) view.findViewById(R.id.iv_recommend_recommend);
        tvRecommend = (TextView) view.findViewById(R.id.tv_recommend_recommend);
        recommendGird = (GridView) view.findViewById(R.id.gird_recommend_recommend);

        ivNew = (ImageView) view.findViewById(R.id.iv_new_recommend);
        tvNew = (TextView) view.findViewById(R.id.tv_new_recommend);
        newGird = (GridView) view.findViewById(R.id.gird_new_recommend);

        ivHot = (ImageView) view.findViewById(R.id.iv_hot_recommend);
        tvHot = (TextView) view.findViewById(R.id.tv_hot_recommend);
        hotGird = (GridView) view.findViewById(R.id.gird_hot_recommend);

        ivRadio = (ImageView) view.findViewById(R.id.iv_radio_recommend);
        tvRadio = (TextView) view.findViewById(R.id.tv_radio_recommend);

        ivToday = (ImageView) view.findViewById(R.id.iv_today_recommend);
        tvToday = (TextView) view.findViewById(R.id.tv_today_recommend);
        totayList = (ListView) view.findViewById(R.id.list_today_recommend);

        ivOriginal = (ImageView) view.findViewById(R.id.iv_original_recommend);
        tvOriginal = (TextView) view.findViewById(R.id.tv_original_recommend);
        originalGird = (GridView) view.findViewById(R.id.gird_original_recommend);

        ivMv = (ImageView) view.findViewById(R.id.iv_mv_recommend);
        tvMv = (TextView) view.findViewById(R.id.tv_mv_recommend);
        mvGird = (GridView) view.findViewById(R.id.gird_mv_recommend);

        ivHappy = (ImageView) view.findViewById(R.id.iv_happy_recommend);
        tvHappy = (TextView) view.findViewById(R.id.tv_happy_recommend);
        happyGird = (GridView) view.findViewById(R.id.gird_happy_recommend);

        ivSpecial = (ImageView) view.findViewById(R.id.iv_special_recommend);
        tvSpecial = (TextView) view.findViewById(R.id.tv_special_recommend);
        specialList = (ListView) view.findViewById(R.id.list_special_recommend);

        lilaAdv = (LinearLayout)view.findViewById(R.id.lila_adv_recommend);
        ivAdv = (ImageView) view.findViewById(R.id.iv_adv_recommend);
        tvNameAdv = (TextView) view.findViewById(R.id.tv_name_adv_recommend);
        tvAuthorAdv = (TextView) view.findViewById(R.id.tv_author_adv_recommend);
        tvMoreRecommend = (TextView) view.findViewById(R.id.tv_more_recommend_recommend);
    }

    @Override
    protected void initData() {
        fragmentManager = getActivity().getSupportFragmentManager();
        recommendNet();
        tvMoreRecommend.setOnClickListener(this);
    }

    private void setSingerGird() {
        singerGirdApapter = new SingerGirdApapter(getContext());
        singerGird.setAdapter(singerGirdApapter);
        singerGirdApapter.setRecommendBean(recommendBean);
    }

    private void setBanner() {
        bannerList = new ArrayList<>();
        for (int i = 0; i < recommendBean.getResult().getFocus().getResult().size(); i++) {
            bannerList.add(recommendBean.getResult().getFocus().getResult().get(i).getRandpic());
        }
        banner.setImageLoader(new MyImageLoader());
        banner.setImages(bannerList);
        banner.isAutoPlay(true);
        banner.setDelayTime(5000);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.start();
        banner.setOnBannerClickListener(this);
    }

    private void recommendNet() {
        NetTool.getInstance().startRequset(MyUrl.RECOMMEDN_URL, RecommendBean.class, new CallBack<RecommendBean>() {
            @Override
            public void onSucced(RecommendBean response) {
                recommendBean = response;
                setBanner();

                setSingerGird();
                if (recommendBean.getResult().getMix_2()!=null){
                    Glide.with(MyApp.getContext()).load(recommendBean.getResult().getMix_2().getResult().get(0).getPic()).into(ivAdv);
                tvNameAdv.setText(recommendBean.getResult().getMix_2().getResult().get(0).getTitle());
                    tvAuthorAdv.setText(recommendBean.getResult().getMix_2().getResult().get(0).getDesc());
                }else lilaAdv.setVisibility(View.GONE);


                Glide.with(MyApp.getContext()).load(recommendBean.getModule().get(3).getPicurl()).into(ivRecommend);
                tvRecommend.setText(recommendBean.getModule().get(3).getTitle());

                setRecommendGird();

                Glide.with(MyApp.getContext()).load(recommendBean.getModule().get(5).getPicurl()).into(ivNew);
                tvNew.setText(recommendBean.getModule().get(5).getTitle());

                setNewGird();

                Glide.with(MyApp.getContext()).load(recommendBean.getModule().get(6).getPicurl()).into(ivHot);
                tvHot.setText(recommendBean.getModule().get(6).getTitle());

                setHotGird();

                Glide.with(MyApp.getContext()).load(recommendBean.getModule().get(8).getPicurl()).into(ivRadio);
                tvRadio.setText(recommendBean.getModule().get(8).getTitle());

                Glide.with(MyApp.getContext()).load(recommendBean.getModule().get(9).getPicurl()).into(ivToday);
                tvToday.setText(recommendBean.getModule().get(9).getTitle());

                setTodayList();

                Glide.with(MyApp.getContext()).load(recommendBean.getModule().get(10).getPicurl()).into(ivOriginal);
                tvOriginal.setText(recommendBean.getModule().get(10).getTitle());

                setOriginalGird();

                Glide.with(MyApp.getContext()).load(recommendBean.getModule().get(11).getPicurl()).into(ivMv);
                tvMv.setText(recommendBean.getModule().get(11).getTitle());

                setMVGird();

                Glide.with(MyApp.getContext()).load(recommendBean.getModule().get(12).getPicurl()).into(ivHappy);
                tvHappy.setText(recommendBean.getModule().get(12).getTitle());

                setHappyGird();

                Glide.with(MyApp.getContext()).load(recommendBean.getModule().get(13).getPicurl()).into(ivSpecial);
                tvSpecial.setText(recommendBean.getModule().get(13).getTitle());

                setSpecialList();


            }

            @Override
            public void onFailure(Throwable e) {

            }
        });
    }

    private void setSpecialList() {
        specialListRecommendAdapter = new SpecialListRecommendAdapter(getContext());
        specialList.setAdapter(specialListRecommendAdapter);
        specialListRecommendAdapter.setRecommendBean(recommendBean);
        specialList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                toWeb();
                bundle = new Bundle();
                bundle.putParcelable(MyBean.TO_WEB,recommendBean.getResult().getMod_7().getResult().get(position));
                intent.putExtra(MyBean.TO_WEB,bundle);
                startActivity(intent);
            }
        });
    }

    private void setHappyGird() {
        happyGirdRecommendAdapter = new HappyGirdRecommendAdapter(getContext());
        happyGird.setAdapter(happyGirdRecommendAdapter);
        happyGirdRecommendAdapter.setRecommendBean(recommendBean);
    }

    private void setMVGird() {
        mvGirdRecommendAdapter = new MVGirdRecommendAdapter(getContext());
        mvGird.setAdapter(mvGirdRecommendAdapter);
        mvGirdRecommendAdapter.setRecommendBean(recommendBean);
    }

    private void setOriginalGird() {
        originalGirdRecommendAdapter = new OriginalGirdRecommendAdapter(getContext());
        originalGird.setAdapter(originalGirdRecommendAdapter);
        originalGirdRecommendAdapter.setRecommendBean(recommendBean);
    }

    private void setTodayList() {
        todayListRecommendAdapter = new TodayListRecommendAdapter(getContext());
        totayList.setAdapter(todayListRecommendAdapter);
        todayListRecommendAdapter.setRecommendBean(recommendBean);
    }

    private void setHotGird() {
        hotGirdRecommendAdapter = new HotGirdRecommendAdapter(getContext());
        hotGird.setAdapter(hotGirdRecommendAdapter);
        hotGirdRecommendAdapter.setRecommendBean(recommendBean);
    }

    private void setNewGird() {
        newGirdARecommendAdapter = new NewGirdARecommendAdapter(getContext());
        newGird.setAdapter(newGirdARecommendAdapter);
        newGirdARecommendAdapter.setRecommendBean(recommendBean);
    }

    private void setRecommendGird() {
        recommendRecommendGirdAdapter = new RecommendRecommendGirdAdapter(getContext());
        recommendGird.setAdapter(recommendRecommendGirdAdapter);
        recommendRecommendGirdAdapter.setRecommendBean(recommendBean);
    }

    @Override
    protected void bindEvent() {
        tvRecommend.setOnClickListener(this);
        recommendGird.setOnItemClickListener(this);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.gird_recommend_recommend:


                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.frame_layout_main,PlaySongListFragment.newInstance(
                        recommendBean.getResult().getDiy().getResult().get(position).getListid()));
                fragmentTransaction.commit();
                break;


        }
    }


    private void toWeb() {
        intent = new Intent(getActivity(), WebActivity.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_more_recommend_recommend:
                Intent intent = new Intent(MyBean.TO_SONG_LIST);
                getActivity().sendBroadcast(intent);
                Log.e(TAG, "onClick: " );
                break;
        }
    }



    class MyImageLoader extends ImageLoader{

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }
    @Override
    public void OnBannerClick(int position) {
        toWeb();
        bundle = new Bundle();
        bundle.putParcelable(MyBean.TO_WEB,recommendBean.getResult().getFocus().getResult().get(position-1));
        intent.putExtra(MyBean.TO_WEB, bundle);
        startActivity(intent);
    }
}
