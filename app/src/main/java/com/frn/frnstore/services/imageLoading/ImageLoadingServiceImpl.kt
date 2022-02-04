package com.frn.frnstore.services.imageLoading

import com.facebook.drawee.view.SimpleDraweeView
import com.frn.frnstore.views.FrnSimpleDraweeView
import java.lang.IllegalStateException

class ImageLoadingServiceImpl :ImageLoadingService{
    override fun loadImage(imageView: FrnSimpleDraweeView, urlImage: String) {
        if (imageView is SimpleDraweeView)
            imageView.setImageURI(urlImage)
        else
            throw IllegalStateException("ImageView must be instance of SimpleDraweeView is library fresco")
    }
}