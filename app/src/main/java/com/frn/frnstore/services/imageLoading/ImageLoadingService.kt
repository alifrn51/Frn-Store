package com.frn.frnstore.services.imageLoading

import com.frn.frnstore.views.FrnSimpleDraweeView

interface ImageLoadingService {

    fun loadImage(imageView:FrnSimpleDraweeView , urlImage:String)

}