package com.graph.adm.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.graph.adm.Fragment.PhotoSingel;
import com.graph.adm.R;
import com.graph.adm.Utils.LoadImage;
import com.graph.adm.Utils.Utils;
import com.graph.adm.model.singalPhoto.SingalPhotoData;

public class ViewPhoto extends AppCompatActivity implements View.OnTouchListener {

    String fileName = "", fileUrl = "";
    ImageView inPhoto, back_view_img,btnDownload;
    TextView title;
    private float x1, x2;
    static final int MIN_DISTANCE = 150;
    SingalPhotoData imgs;
    int index;
    private static final String TAG = "Touch";
    @SuppressWarnings("unused")
    private static final float MIN_ZOOM = 1f, MAX_ZOOM = 1f;
    Matrix matrix = new Matrix();
    Matrix savedMatrix = new Matrix();
    static final int NONE = 0;
    static final int DRAG = 1;
    static final int ZOOM = 2;
    int mode = NONE;
    PointF start = new PointF();
    PointF mid = new PointF();
    float oldDist = 1f;

    @SuppressLint("ClickableViewAccessibility")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_view_photo);
        inPhoto = findViewById(R.id.in_photo);
        back_view_img = findViewById(R.id.back_view_img);
        btnDownload = findViewById(R.id.btn_download);
        title = findViewById(R.id.tv_title);
        fileName = getIntent().getStringExtra("FileName");
        fileUrl = getIntent().getStringExtra("FileURL");
        index = getIntent().getIntExtra("Index", 0);
        title.setText(fileName);
        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.downloadfile(PhotoSingel.userData.getValue().get(index).getMicrosoftGraphDownloadUrl(), PhotoSingel.userData.getValue().get(index).getName(), getApplicationContext());
            }
        });
        if (fileUrl != null) {
            new LoadImage(getApplicationContext(), inPhoto).execute(PhotoSingel.userData.getValue().get(index).getMicrosoftGraphDownloadUrl());
        } else {
            inPhoto.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.jade_default));
        }
        back_view_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewPhoto.this.finish();
            }
        });
        inPhoto.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        ImageView view = (ImageView) v;
        view.setScaleType(ImageView.ScaleType.MATRIX);
        float scale;
        dumpEvent(event);
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:   // first finger down only
                x1 = event.getX();
                matrix.set(view.getImageMatrix());
                savedMatrix.set(matrix);
                start.set(event.getX(), event.getY());
                Log.d(TAG, "mode=DRAG"); // write to LogCat
                mode = DRAG;
                break;

            case MotionEvent.ACTION_UP: // first finger lifted

            case MotionEvent.ACTION_POINTER_UP: // second finger lifted

                mode = NONE;
                x2 = event.getX();
                float deltaX = x2 - x1;
                if (Math.abs(deltaX) > MIN_DISTANCE)
                {
                    if (x2 > x1)
                    {
                        if (index != 0) {
                            index--;
                            inPhoto.setVisibility(View.GONE);
                            new LoadImage(getApplicationContext(), inPhoto).execute(PhotoSingel.userData.getValue().get(index).getMicrosoftGraphDownloadUrl());
                        } else {
                            Toast.makeText(ViewPhoto.this, "no more images", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        if (index != (PhotoSingel.userData.getValue().size() - 1)) {
                            index++;
                            inPhoto.setVisibility(View.GONE);
                            new LoadImage(getApplicationContext(), inPhoto).execute(PhotoSingel.userData.getValue().get(index).getMicrosoftGraphDownloadUrl());
                        } else {
                            Toast.makeText(ViewPhoto.this, "no more images", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;

            case MotionEvent.ACTION_POINTER_DOWN: // first and second finger down

                oldDist = spacing(event);
                Log.d(TAG, "oldDist=" + oldDist);
                if (oldDist > 5f) {
                    savedMatrix.set(matrix);
                    midPoint(mid, event);
                    mode = ZOOM;
                    Log.d(TAG, "mode=ZOOM");
                }
                break;

            case MotionEvent.ACTION_MOVE:
                if (mode == ZOOM) {
                    float newDist = spacing(event);
                    Log.d(TAG, "newDist=" + newDist);
                    if (newDist > 5f) {
                        matrix.set(savedMatrix);
                        scale = newDist / oldDist;
                        matrix.postScale(scale, scale, mid.x, mid.y);
                    }
                }
                break;
        }

        view.setImageMatrix(matrix);

        return true;
    }

    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }

    private void midPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }
    private void dumpEvent(MotionEvent event) {
        String names[] = {"DOWN", "UP", "MOVE", "CANCEL", "OUTSIDE", "POINTER_DOWN", "POINTER_UP", "7?", "8?", "9?"};
        StringBuilder sb = new StringBuilder();
        int action = event.getAction();
        int actionCode = action & MotionEvent.ACTION_MASK;
        sb.append("event ACTION_").append(names[actionCode]);

        if (actionCode == MotionEvent.ACTION_POINTER_DOWN || actionCode == MotionEvent.ACTION_POINTER_UP) {
            sb.append("(pid ").append(action >> MotionEvent.ACTION_POINTER_ID_SHIFT);
            sb.append(")");
        }

        sb.append("[");
        for (int i = 0; i < event.getPointerCount(); i++) {
            sb.append("#").append(i);
            sb.append("(pid ").append(event.getPointerId(i));
            sb.append(")=").append((int) event.getX(i));
            sb.append(",").append((int) event.getY(i));
            if (i + 1 < event.getPointerCount())
                sb.append(";");
        }

        sb.append("]");
        Log.d("Touch Events ---------", sb.toString());
    }

}
