package com.hong.hongbaseframe;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.hong.hongbaseframe.activity.BaseActivity;
import com.hong.hongbaseframe.activity.LoginActivity;
import com.hong.hongbaseframe.adapter.FragmentTabAdapter;
import com.hong.hongbaseframe.fragment.Fragment1;
import com.hong.hongbaseframe.fragment.Fragment2;
import com.hong.hongbaseframe.fragment.Fragment3;
import com.hong.hongbaseframe.fragment.Fragment4;
import com.hong.hongbaseframe.util.AppManager;
import com.hong.hongbaseframe.util.CommonUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 作者：李智宏 on 2016/5/27 09:41
 * 描述：主界面
 */
public class MainActivity extends BaseActivity {
    @Bind(R.id.content)
    FrameLayout content;
    @Bind(R.id.rb_1)
    RadioButton rb1;
    @Bind(R.id.rb_2)
    RadioButton rb2;
    @Bind(R.id.rb_3)
    RadioButton rb3;
    @Bind(R.id.rb_4)
    RadioButton rb4;
    @Bind(R.id.main_radio)
    RadioGroup mainRadio;
    private long exitTime = 0;
    /**
     * FragMent的适配器
     */
    private FragmentTabAdapter mTabAdapter;
    /**
     * FragMent的集合
     */
    private List<Fragment> mFragMents;

    @Override
    protected int setContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        startActivity(LoginActivity.class);
        mFragMents = new ArrayList<Fragment>();
        mFragMents.add(new Fragment1());
        mFragMents.add(new Fragment2());
        mFragMents.add(new Fragment3());
        mFragMents.add(new Fragment4());
        FragmentManager manager = getSupportFragmentManager();
        mTabAdapter = new FragmentTabAdapter(manager, mFragMents, R.id.content, mainRadio);
        ((RadioButton) mainRadio.getChildAt(0)).setChecked(true);
        //当某个FragMent 需要先登录才能进去的时候，调用此事件（index=FragMent的下标）即对按钮的监听事件
        mTabAdapter.setOnRgsExtraCheckedChangedListener(new FragmentTabAdapter.OnRgsExtraCheckedChangedListener() {
            @Override
            public void OnRgsExtraCheckedChanged(RadioGroup radioGroup, int checkedId,
                                                 int index) {
                //	                if(index == 2 && TextUtils.isEmpty(UserInfoUtils.getUser(getApplicationContext()))){
                //	                    startActivityForResult(new Intent(getApplicationContext(),
                //	                            LoginActivity.class), 2);
                //	                }
            }
        });
    }

    @Override
    protected void setListner() {

    }

    @Override
    public void onSuccuss(String tag, String code, String msg, String resultData) {

    }

    @Override
    public void onFailure(String tag, String code, String msg) {
    }

    @Override
    public void onProgress(float progress) {
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                CommonUtil.StartToast(context, "再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                //退出程序
                AppManager.getInstance().killAllActivity();
            }
            return true;
        }
        return false;
    }
}
