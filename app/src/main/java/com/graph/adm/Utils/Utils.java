package com.graph.adm.Utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.text.format.DateUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.graph.adm.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Utils {
    public static AlertDialog dialog;

    public static Date getStringToDate(String dtStart) {
        //Date c = Calendar.getInstance().getTime()
        //String dtStart = "2010-10-15T09:27:37Z";
        Date date = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        try {
            date = format.parse(dtStart);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //Calendar cal = Calendar.getInstance();
        //cal.setTime(date);
        return date;
    }
    public static int getImf(String type) {
        int res = 0;
        if (type.equalsIgnoreCase("jpg") || type.equalsIgnoreCase("png") || type.equalsIgnoreCase("gif")) {
            res=R.drawable.ic_photo;
        } else if (type.equalsIgnoreCase("pdf")) {
            res=R.drawable.ic_pdf;
        } else if (type.equalsIgnoreCase("docx")){
            res=R.drawable.ic_docx;
        }else if (type.equalsIgnoreCase("pptx")){
            res=R.drawable.ic_pptx;
        }else if (type.equalsIgnoreCase("xlsx")){
            res=R.drawable.ic_xlsx;
        }else if (type.equalsIgnoreCase("html")){
            res=R.drawable.ic_link;
        }else{
            res=R.drawable.file_earmark;
        }
        return res;
    }
    public static String printDifference(Date startDate) {
        String str = "";
        try {
            long different = Calendar.getInstance().getTime().getTime() - startDate.getTime();
            long secondsInMilli = 1000;
            long minutesInMilli = secondsInMilli * 60;
            long hoursInMilli = minutesInMilli * 60;
            long daysInMilli = hoursInMilli * 24;
            long elapsedDays = different / daysInMilli;
            different = different % daysInMilli;
            long elapsedHours = different / hoursInMilli;
            different = different % hoursInMilli;
            long elapsedMinutes = different / minutesInMilli;
            different = different % minutesInMilli;
            long elapsedSeconds = different / secondsInMilli;
            //String str=elapsedDays+" days,"+elapsedHours+"  hours, "+elapsedMinutes+" minutes, "+elapsedSeconds+" seconds%n";
            str = elapsedDays + " days";
            if (elapsedDays == 0) {
                str = elapsedHours + " hours";
            }
        } catch (Exception e) {
        }
        return str;
    }

    public static void setProgressDialog(Context ctx) {
        Activity act = (Activity) ctx;
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
            builder.setCancelable(true);
            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View dialogView = inflater.inflate(R.layout.layout_progress, null);
            builder.setView(dialogView);
            dialog = builder.create();
            dialog.show();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            act.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int displayWidth = displayMetrics.widthPixels;
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.copyFrom(dialog.getWindow().getAttributes());
            int dialogWindowWidth = (int) (displayWidth * 0.5f);
            layoutParams.width = dialogWindowWidth;
            dialog.getWindow().setAttributes(layoutParams);
        } catch (Exception e) {
        }
    }
    public static String toTitleCase(String string) {

        // Check if String is null
        if (string == null) {

            return null;
        }

        boolean whiteSpace = true;

        StringBuilder builder = new StringBuilder(string); // String builder to store string
        final int builderLength = builder.length();

        // Loop through builder
        for (int i = 0; i < builderLength; ++i) {

            char c = builder.charAt(i); // Get character at builders position

            if (whiteSpace) {

                // Check if character is not white space
                if (!Character.isWhitespace(c)) {

                    // Convert to title case and leave whitespace mode.
                    builder.setCharAt(i, Character.toTitleCase(c));
                    whiteSpace = false;
                }
            } else if (Character.isWhitespace(c)) {

                whiteSpace = true; // Set character is white space

            } else {

                builder.setCharAt(i, Character.toLowerCase(c)); // Set character to lowercase
            }
        }

        return builder.toString(); // Return builders text
    }
    public static String getDateString(Date d, String fromt) {
        DateFormat dateFormat = new SimpleDateFormat(fromt);
        return dateFormat.format(d);
    }
    public static String getStringDateString(String d, String fromt) {
        Date date = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        try {
            date = format.parse(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateFormat dateFormat = new SimpleDateFormat(fromt);
        return dateFormat.format(date);
    }
    public static void closeDilog() {
        try {
            dialog.dismiss();
        } catch (Exception e) {
        }

    }
    public static void setupPieChart(PieChart pieChart, ArrayList<PieEntry> entries) {
        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(0);
        pieChart.setEntryLabelColor(Color.BLACK);
        // pieChart.setCenterText("Spending by Category");
        pieChart.setCenterTextSize(0);
        pieChart.getDescription().setEnabled(false);

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setEnabled(true);
        loadPieChartData(pieChart, entries);
    }

    public static ArrayList<PieEntry> getPieEntry(float f1, float f2,float f3, float f4, float f5) {
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(f1));
        entries.add(new PieEntry(f2));
        entries.add(new PieEntry(f3));
        entries.add(new PieEntry(f4));
        entries.add(new PieEntry(f5));
        return entries;
    }

    public static void loadPieChartData(PieChart pieChart, ArrayList<PieEntry> entries) {
        ArrayList<Integer> colors = new ArrayList<>();
        //
        colors.add(Color.parseColor("#008ac2"));
        colors.add(Color.parseColor("#33ad81"));
        colors.add(Color.parseColor("#ffcd4e"));
        colors.add(Color.parseColor("#f13b3b"));
        colors.add(Color.parseColor("#f2f2f2"));

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueTextSize(0f);
        pieChart.setData(data);
        pieChart.invalidate();
        pieChart.animateY(1400, Easing.EaseInOutQuad);
    }
    public static String getCalculatedDate(int days) {
        String dateFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'";
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat s = new SimpleDateFormat(dateFormat);
        cal.add(Calendar.DAY_OF_YEAR, days);
        return s.format(new Date(cal.getTimeInMillis()));
    }

    public static void downloadfile(String url, String name, Context ctx) {
        // Create request for android download manager
        Uri uri = Uri.parse(url);
        DownloadManager downloadManager = (DownloadManager) ctx.getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI |
                DownloadManager.Request.NETWORK_MOBILE);
        // set title and description
        request.setTitle(name + " Download");
        request.setDescription(name + " download.");
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        //set the local destination for download file to a path within the application's external files directory
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, name);
        request.setMimeType("*/*");
        downloadManager.enqueue(request);
    }
}
