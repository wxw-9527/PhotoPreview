package com.rouxinpai.photopreview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import kotlin.properties.Delegates

/**
 * author : Saxxhw
 * email  : xingwangwang@cloudinnov.com
 * time   : 2023/5/8 15:41
 * desc   :
 */
class ImageViewerActivity() : AppCompatActivity(R.layout.image_viewer_activity), OnClickListener {

    companion object {

        // 参数传递标志
        private const val ARG_INDEX = "arg_index"
        private const val ARG_IMAGE_URL_LIST = "arg_image_url_list"

        // 默认值
        private const val sDefaultIndex = 0

        /**
         * 启动[ImageViewerActivity]页
         */
        @JvmStatic
        fun start(context: Context, imageUrl: String) {
            val starter = Intent(context, ImageViewerActivity::class.java)
            val bundle = bundleOf(
                ARG_INDEX to sDefaultIndex, ARG_IMAGE_URL_LIST to listOf(imageUrl)
            )
            starter.putExtras(bundle)
            context.startActivity(starter)
        }

        /**
         * 启动[ImageViewerActivity]页
         */
        @JvmStatic
        fun start(context: Context, index: Int, imageUrlList: List<String>) {
            val starter = Intent(context, ImageViewerActivity::class.java)
            val bundle = bundleOf(ARG_INDEX to index, ARG_IMAGE_URL_LIST to imageUrlList)
            starter.putExtras(bundle)
            context.startActivity(starter)
        }
    }

    // 下标
    private var mIndex: Int by Delegates.notNull()

    // 图片链接
    private lateinit var mImageUrlList: List<String>

    // 返回按钮
    private lateinit var mBtnBack: AppCompatImageView

    // 视图切换控件实例
    private lateinit var mViewPager: ViewPager2

    // 指示器控件实例
    private lateinit var mTvIndicator: AppCompatTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 解析参数
        val bundle = intent?.extras
        if (bundle != null) {
            mIndex = bundle.getInt(ARG_INDEX)
            mImageUrlList = bundle.getStringArrayList(ARG_IMAGE_URL_LIST).orEmpty()
        }
        // 实例化控件
        mBtnBack = findViewById(R.id.btn_back)
        mViewPager = findViewById(R.id.view_pager)
        mTvIndicator = findViewById(R.id.tv_indicator)
        // 绑定监听事件
        mBtnBack.setOnClickListener(this)
        mViewPager.registerOnPageChangeCallback(mPageChangeCallback)
        // 绑定适配器
        mViewPager.adapter = PagerAdapter(this, mImageUrlList)
        mViewPager.setCurrentItem(mIndex, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        mViewPager.unregisterOnPageChangeCallback(mPageChangeCallback)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_back -> finish()
        }
    }

    // 页卡切换事件监听
    private val mPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {

        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            mTvIndicator.text = buildString {
                append(position + 1)
                append("/")
                append(mImageUrlList.size)
            }
        }
    }

    /**
     *
     */
    private class PagerAdapter(
        fragmentActivity: FragmentActivity,
        private val imageUrlList: List<String>
    ) : FragmentStateAdapter(fragmentActivity) {

        override fun getItemCount(): Int {
            return imageUrlList.size
        }

        override fun createFragment(position: Int): Fragment {
            return ImageViewerFragment.newInstance(imageUrlList[position])
        }
    }
}