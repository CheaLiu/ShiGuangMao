package com.yizhipin.teacher.mine.activity;

import android.os.Bundle;

import com.yizhipin.R;
import com.yizhipin.base.ui.activity.BaseMvpActivity;

import org.jetbrains.annotations.Nullable;

/**
 * Creator Qi
 * Date 2018/12/27
 * <p>收费设置</p>
 */
public class ChargingSettingActivity extends BaseMvpActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charging_setting);
    }

    @Override
    public void injectComponent() {

    }
}
