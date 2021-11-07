package com.example.idrive.Util;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

public class ImageUtil {
    public static void checkCacheOrLoad(InMemoryCache<String, Bitmap> imageCache, ImageView imageView, String url) {
        Bitmap bitmap = imageCache.get(url);
        if(bitmap==null){
//            Log.i("DEBUG", "fetched for "+url);
            new ImageLoadTask(imageCache, url, imageView).execute();
        }else{
//            Log.i("DEBUG", "cached for "+url);
            imageView.setImageBitmap(bitmap);
        }
    }
}
