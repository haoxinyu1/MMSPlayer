package com.lodz.android.mmsplayerdemo

import android.os.Bundle
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import com.lodz.android.corekt.anko.bindView
import com.lodz.android.corekt.anko.getColorCompat
import com.lodz.android.corekt.anko.toastShort
import com.lodz.android.mmsplayerdemo.simple.SimpleVideoActivity
import com.lodz.android.mmsplayerdemo.widget.VideoActivity
import com.lodz.android.pandora.base.activity.BaseActivity

class MainActivity : BaseActivity() {

    private val mSimpleBtn by bindView<Button>(R.id.simple_btn)

    private val mWidgetBtn by bindView<Button>(R.id.widget_btn)

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun findViews(savedInstanceState: Bundle?) {
        super.findViews(savedInstanceState)
        getTitleBarLayout().setTitleName(R.string.app_name)
        getTitleBarLayout().setBackgroundColor(getColorCompat(R.color.colorPrimary))
    }

    override fun setListeners() {
        super.setListeners()
        getTitleBarLayout().setOnBackBtnClickListener {
            finish()
        }

        mSimpleBtn.setOnClickListener {
            mGetContentResult.launch("video/*")
        }

        mWidgetBtn.setOnClickListener {
            VideoActivity.start(this, getString(R.string.info_name), "https://static.www.tencent.com/uploads/2021/09/17/2a59c30bc897b54b072eb3d96c2af4e5.mp4")
        }
    }

    /** 单类型选择单文件回调 */
    private val mGetContentResult = registerForActivityResult(ActivityResultContracts.GetContent()) {
        if (it == null){
            toastShort("取消选择")
            return@registerForActivityResult
        }
        SimpleVideoActivity.start(this, it)
    }

    override fun initData() {
        super.initData()
        showStatusCompleted()
    }
}
