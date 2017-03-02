package com.a3g.lanou.lbaidumusic.fragment;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.a3g.lanou.lbaidumusic.R;
import com.a3g.lanou.lbaidumusic.bean.SongBean;
import com.a3g.lanou.lbaidumusic.tools.MyBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuHao on 17/2/11.
 */
public class MyFragment extends BaseFragment implements View.OnClickListener {

    private TextView tvLoginBottom,tvMyMusicNumber;
    private RelativeLayout relaLocaMy;
    private ArrayList<SongBean> songBeen;
    private static final String TAG = "MyFragment";
    @Override
    protected int bindLayout() {
        return R.layout.fragment_my ;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        tvLoginBottom = (TextView) view.findViewById(R.id.tv_login_parent_bottom_my_fragment);
        relaLocaMy = (RelativeLayout) view.findViewById(R.id.rela_local_my_fragment);
        tvMyMusicNumber = (TextView) view.findViewById(R.id.tv_my_music_number_myFragment);
    }

    @Override
    protected void initData() {
        songBeen = new ArrayList<>();
        //获取本地歌曲数量
        getMusicData();
        tvMyMusicNumber.setText(songBeen.size()+"首");
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(tvLoginBottom.getText().toString());
        ForegroundColorSpan buleSpan = new ForegroundColorSpan(Color.parseColor("#20ACFE"));
        spannableStringBuilder.setSpan(buleSpan,0,4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvLoginBottom.setText(spannableStringBuilder);
    }


    @Override
    protected void bindEvent() {
        relaLocaMy.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rela_local_my_fragment:
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
               LocalFragment localFragment = new LocalFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(MyBean.LOCAL_SONG,songBeen);
                localFragment.setArguments(bundle);
                fragmentTransaction.add(R.id.frame_layout_main,localFragment);
                fragmentTransaction.commit();
        }
    }
    private void getMusicData() {
        //根据uri找到所有的音频信息
        Cursor cursor = getActivity().getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                null,null,null,null);
        if (cursor!=null&&cursor.moveToFirst()){
            do {
                //获取音乐名字
                String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                //歌手名字
                String singer = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                //获取歌曲的url
                String url =cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                //获取音乐类型0代表不是音乐
                int isMusic = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.IS_MUSIC));
                //获取音乐时长
                Long duringTime = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
                String Id=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));
                int id=Integer.valueOf(Id);
                String albumArt=getAlbumArt(id);

                Bitmap bitmap = BitmapFactory.decodeFile(albumArt);

                if(isMusic!=0&&duringTime/(60*1000)>1){
                    SongBean songBean = new SongBean(title,singer,url,bitmap,duringTime);
                    songBeen.add(songBean);
                }
            }while (cursor.moveToNext());

        }
        cursor.close();
    }

    private String getAlbumArt(int Id) {
        String mUriAlbums = "content://media/external/audio/albums";
        String[] projection = new String[] { "album_art" };
        Cursor cur =getActivity().getContentResolver().query(  Uri.parse(mUriAlbums + "/" + Integer.toString(Id)),  projection, null, null, null);
        String album_art = null;
        if (cur.getCount() > 0 && cur.getColumnCount() > 0)
        {  cur.moveToNext();
            album_art = cur.getString(0);
        }
        cur.close();
        return album_art;
    }

}
