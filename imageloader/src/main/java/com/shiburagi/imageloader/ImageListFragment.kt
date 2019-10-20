package com.shiburagi.imageloader

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.google.android.material.snackbar.Snackbar
import com.shiburagi.imageloader.entities.Image
import com.shiburagi.imageloader.model.ImageListViewModel
import com.shiburagi.imageloader.views.GridImageView
import com.shiburagi.utility.byte
import com.shiburagi.utility.isOnline
import kotlinx.android.synthetic.main.image_list_fragment.*

/**
 * Main Fragment to handle image list
 */
class ImageListFragment : Fragment() {
    private lateinit var viewModel: ImageListViewModel

    companion object {
        private var builder: GlideBuilder? = null;
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (onRequest == null)
            throw  Exception("OnRequest not initialize");

        return inflater.inflate(R.layout.image_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ImageListViewModel();
        if (!isOnline(context!!)) {
            Snackbar.make(parentLayout, R.string.no_internet_connection, Snackbar.LENGTH_LONG)
                .show();
        }

        // to help the fragment detect if the list react to the end of list
        scrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { _, _, _, _, _ ->
            val diff1 = parentLayout.bottom - (scrollView.height + scrollView.scrollY)
            if (diff1 == 0) {
                viewModel.loadMore(onRequest!!(leftLayout.childCount + rightLayout.childCount));
            }

        })

        // to listen every data post to model
        viewModel.getData(onRequest!!(0)).observe(this, Observer {

            var leftHeight = leftLayout.height;
            var rightHeight = rightLayout.height;
            it.forEach{image->
                val view = GridImageView(context!!).setImage(image);
                if (leftHeight>rightHeight) {
                    rightLayout.addView(view);
                    rightHeight+=view.imageHeight;
                }
                else {
                    leftLayout.addView(view);
                    leftHeight+=view.imageHeight;

                }

                if (onItemClickListener != null)
                    view.setOnItemClickListener(onItemClickListener!!)
            }
        })
    }

    private var onItemClickListener: ((View, Image) -> Unit)? = null;
    /**
     * handle click event
     */
    fun setOnItemClickListener(onItemClickListener: (View, Image) -> Unit): ImageListFragment {
        this.onItemClickListener = onItemClickListener;
        return this;
    }

    /**
     * a method to request/ get a url to call for next load
     */
    private var onRequest: ((Int) -> String)? = null;
    fun setOnRequest(onRequest: (Int) -> String): ImageListFragment {
        this.onRequest = onRequest;
        return this;

    }

    /**
     * a method to set the size of cache and memory of images.
     * - the passing parameter value in MB
     */
    @SuppressLint("VisibleForTests")
    fun setMemorySize(
        context: Context,
        memoryCacheSize: Double,
        bitmapPoolMaxSize: Double
    ): ImageListFragment {
        if (builder == null) {
            val builder = GlideBuilder()
            builder.setMemoryCache(LruResourceCache(memoryCacheSize.byte.toLong()))
            builder.setBitmapPool(LruBitmapPool(bitmapPoolMaxSize.byte.toLong()))

            builder.setLogLevel(Log.VERBOSE)
            Glide.init(context, builder);
            ImageListFragment.builder = builder;
        } else {
            val builder = builder!!;

            builder.setMemoryCache(LruResourceCache(memoryCacheSize.byte.toLong()))
            builder.setBitmapPool(LruBitmapPool(bitmapPoolMaxSize.byte.toLong()))


        }
        return this;

    }
}
