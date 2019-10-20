package com.shiburagi.pinterestclone.buider

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import com.shiburagi.imageloader.ImageListFragment
import com.shiburagi.pinterestclone.ImagePreviewActivity


/**
 * a single method to generate/create ImageList's fragment
 * for multiple activity
 */
fun buildImageList(activity: Activity): ImageListFragment {
    val jsonUrls: Array<String> =
        arrayOf( "https://pastebin.com/raw/wgkJgazE","https://pastebin.com/raw/JVy8BtM1");

    return ImageListFragment()
        .setOnRequest { length -> jsonUrls[((length / 10) % 2)] } // return url to call for next images
        .setOnItemClickListener { view, image ->
            val intent = Intent(activity, ImagePreviewActivity::class.java);
            intent.putExtra(ImagePreviewActivity.IMAGE, image);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                view.transitionName = ImagePreviewActivity.IMAGE_TRANSITION_NAME;
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    activity,
                    view,
                    ViewCompat.getTransitionName(view)!!
                )
                activity.startActivity(intent, options.toBundle());
            } else {
                activity.startActivity(intent);
            }
        } // to handle click event on every image.
        .setMemorySize(activity, 10.0, 10.0);
}