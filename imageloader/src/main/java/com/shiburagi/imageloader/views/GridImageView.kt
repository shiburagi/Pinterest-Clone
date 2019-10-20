package com.shiburagi.imageloader.views

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import com.shiburagi.imageloader.R
import com.shiburagi.imageloader.entities.Image
import com.shiburagi.imageloader.service.NetworkService
import com.shiburagi.utility.px
import kotlinx.android.synthetic.main.image_view.view.*
import kotlin.math.roundToInt

/**
 * A view to build and display image info.
 */
class GridImageView(context: Context) : FrameLayout(context) {

    private val view: LinearLayout =
        LayoutInflater.from(context).inflate(R.layout.image_view, this, false) as LinearLayout;

    init {
        addView(view);
    }

    private var image: Image? = null;

    /**
     * pass @Image object to @GridImageView, then load the image.
     */
    fun setImage(image: Image): GridImageView {
        this.image = image
        view.cardView.setCardBackgroundColor(Color.parseColor(image.color));
        view.imageView.setImageSize(image) { widthPixels ->
            widthPixels / 2.0 - 8.px
        };

        NetworkService.instance.loadImage(image.urls?.small!!, view.imageView);
        return this;
    }

    /**
     * handle click event
     */
    fun setOnItemClickListener(onItemClickListener: (view: View, image: Image) -> Unit) {
        view.setOnClickListener {
            onItemClickListener(view.imageView, image!!)
        }

    }

    /**
     * retrieve image's height
     */
    val imageHeight: Int
        get() = view.imageView.layoutParams.height;
}


/**
 * to preset @ImageView's height and width, to avoid the transition looks weird
 */
fun ImageView.setImageSize(image: Image, calculateMaxWidth: (Double) -> Double) {
    scaleType = ImageView.ScaleType.CENTER_CROP;
    val display = context.resources.displayMetrics;
    val imageWidth = image.width;
    val imageHeight = image.height;
    val maxWidth = calculateMaxWidth(display.widthPixels.toDouble());
    val scale: Double = maxWidth / imageWidth.toDouble();
    imageView.layoutParams.height = (imageHeight * scale).roundToInt();
}

/**
 * to preset @ImageView's height and width, (short method)
 */
fun ImageView.setImageSize(image: Image) {
    setImageSize(image) { widthPixels ->
        widthPixels;
    }
}
