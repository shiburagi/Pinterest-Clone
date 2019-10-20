package com.shiburagi.pinterestclone

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.shiburagi.imageloader.service.NetworkService
import com.shiburagi.imageloader.entities.Image
import com.shiburagi.imageloader.views.setImageSize
import com.shiburagi.pinterestclone.buider.buildImageList
import kotlinx.android.synthetic.main.activity_image_preview.*
import kotlinx.android.synthetic.main.content_image_preview.*

class ImagePreviewActivity : AppCompatActivity() {
    companion object {
        const val IMAGE = "IMAGE"
        const val IMAGE_TRANSITION_NAME = "IMAGE_TRANSITION_NAME"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_preview)


        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        initialize()

        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container_fragment,
                buildImageList(this).setListen(scrollView)
            )
            .commitNow()

    }

    /**
     * set value on certain views in this activity.
     */
    private fun initialize(){

        val image = intent.getSerializableExtra(IMAGE) as Image

        val service = NetworkService.instance

        service.loadImage(image.urls?.small!!, imageView)
        imageView.setBackgroundColor( Color.parseColor(image.color?:"#ffffff"))
        imageView.setImageSize(image)
        titleTextView.visibility = View.GONE
        usernameTextView.text = image.user?.username
        followerTextView.visibility= View.GONE
        descriptionTextView.text = ""
        service.loadImage(image.user?.profileImage?.medium!!, userImageView)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            imageView.transitionName=
                IMAGE_TRANSITION_NAME
        }


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}
