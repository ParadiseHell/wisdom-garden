package com.chengtao.wisdomgarden.adapter

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chengtao.wisdomgarden.entity.WisdomGardenFile
import com.jaeger.ninegridimageview.NineGridImageViewAdapter

/**
 * Author : ChengTao(chengtaolearn@163.com)
 * Date : 11/2/17
 * Time : 8:29 PM
 * Description :
 */
class ImageAdapter : NineGridImageViewAdapter<WisdomGardenFile>() {
  override fun onDisplayImage(context: Context?, imageView: ImageView?, file: WisdomGardenFile?) {
    if (context != null && imageView != null && file != null) {
      Glide.with(context).asBitmap().load(file.url).into(imageView)
    }
  }
}