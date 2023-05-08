package com.rouxinpai.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.rouxinpai.photopreview.ImageViewerActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.tv).setOnClickListener {
            ImageViewerActivity.start(this, 1, listOf(
                "https://img1.baidu.com/it/u=3155988012,1977937542&fm=253&fmt=auto&app=138&f=JPG?w=640&h=343",
                "https://img0.baidu.com/it/u=652041139,3023980007&fm=253&fmt=auto&app=138&f=JPG?w=460&h=649"
            ))
        }
    }
}