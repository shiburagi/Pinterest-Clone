package com.shiburagi.pinterestclone

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shiburagi.pinterestclone.buider.buildImageList
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container_fragment,
               buildImageList(this)
            )
            .commitNow()
    }

}

