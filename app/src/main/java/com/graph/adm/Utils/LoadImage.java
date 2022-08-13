package com.graph.adm.Utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.graph.adm.R;

import java.io.InputStream;

public class LoadImage extends AsyncTask<String, Void, Bitmap> {

    private ImageView bmImage;
    private Context context;

    public LoadImage(Context context, ImageView bmImage) {
        this.bmImage = bmImage;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
        Utils.setProgressDialog(context);
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
        Utils.closeDilog();
        bmImage.setVisibility(View.VISIBLE);
        if (result != null)
            bmImage.setImageBitmap(result);
        else
            bmImage.setImageDrawable(context.getResources().getDrawable(R.drawable.jade_default));
    }
}