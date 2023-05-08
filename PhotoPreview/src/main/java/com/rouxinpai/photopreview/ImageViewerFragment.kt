package com.rouxinpai.photopreview

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.github.chrisbanes.photoview.PhotoView

/**
 * author : Saxxhw
 * email  : xingwangwang@cloudinnov.com
 * time   : 2023/5/8 15:45
 * desc   :
 */
class ImageViewerFragment : Fragment(R.layout.image_viewer_fragment) {

    companion object {

        // 参数传递标志
        private const val ARG_IMAGE_URL = "arg_image_url"

        /**
         * 生成[ImageViewerFragment]的实例
         */
        fun newInstance(imageUrl: String) = ImageViewerFragment().apply {
            arguments = bundleOf(ARG_IMAGE_URL to imageUrl)
        }
    }

    // 图片地址
    private lateinit var mImageUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 解析传递至本页的数据
        val bundle = arguments
        if (bundle != null) {
            mImageUrl = bundle.getString(ARG_IMAGE_URL).orEmpty()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 获取控件实例
        val photoView = view.findViewById<PhotoView>(R.id.photo_view)
        // 显示图片
        Glide.with(this)
            .load(mImageUrl)
            .into(photoView)
    }
}