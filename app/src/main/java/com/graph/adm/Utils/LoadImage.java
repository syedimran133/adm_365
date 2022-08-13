package com.graph.adm.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.graph.adm.R;

import java.io.InputStream;

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

    private ImageView bmImage;
    private Context context;

    public DownloadImageTask(Context context,ImageView bmImage) {
        this.bmImage = bmImage;
        this.context = context;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        if (result != null)
            bmImage.setImageBitmap(result);
        else
            bmImage.setImageDrawable(context.getResources().getDrawable(R.drawable.jade_default));
    }
}