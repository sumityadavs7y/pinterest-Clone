package com.example.idrive.Util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.example.idrive.R;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {

    private String url;
    private ImageView imageView;
    InMemoryCache<String, Bitmap> imageCache;

    public ImageLoadTask(InMemoryCache<String, Bitmap> imageCache, String url, ImageView imageView) {
        this.url = url;
        this.imageView = imageView;
        this.imageCache = imageCache;
    }

    @Override
    protected Bitmap doInBackground(Void... params) {
        try {
            URL urlConnection = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlConnection
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            imageCache.put(url,myBitmap);
            return myBitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        super.onPostExecute(result);
        if(result==null){
            imageView.setImageResource(R.drawable.no_image_found);
        }else{
            imageView.setImageBitmap(result);
        }
    }

}