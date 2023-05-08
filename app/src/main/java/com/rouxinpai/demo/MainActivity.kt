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
                "http://192.168.118.160:8888/cloud-test/2023/05/06/0d361dca-8ce7-4041-9699-716aa7922f3a.png",
                "http://192.168.118.160:8888/cloud-test/2023/05/06/87176d12-a818-46f2-a46d-d0d38d654f0b.png"
            ))
        }
    }
}