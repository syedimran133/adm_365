package com.graph.adm.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.graph.adm.Fragment.Dashboard;
import com.graph.adm.Fragment.ToDoList;
import com.graph.adm.Utils.FlowOrganizer;
import com.graph.adm.databinding.LayoutWebviewBinding;

public class Chat   extends AppCompatActivity {

    private LayoutWebviewBinding binding;
    private WebView webView;
    private ProgressDialog progressDialog;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = LayoutWebviewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        webView = binding.webview;
        webView.setInitialScale(1);
        webView.getSettings().setAllowFileAccess(true);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        /*String data_html = "<!DOCTYPE html><html> <head> <meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"target-densitydpi=high-dpi\" /> <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"> <link rel=\"stylesheet\" media=\"screen and (-webkit-device-pixel-ratio:1.5)\" href=\"hdpi.css\" /></head> <body style=\"background:white;margin:0 0 0 0; padding:0 0 0 0;\"> <iframe src=\"https://web.powerva.microsoft.com/webchat/bots/955ad312-0411-4e11-8ad9-f791b0259fef\" frameBorder=\"0\" width=\"100%\" height=\"800px\" ></iframe> </body> </html> ";*/
        String data_html = "<!DOCTYPE html><html> <head> <meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"target-densitydpi=high-dpi\" /> <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"> <link rel=\"stylesheet\" media=\"screen and (-webkit-device-pixel-ratio:1.5)\" href=\"hdpi.css\" /></head> <body style=\"background:white;margin:0 0 0 0; padding:0 0 0 0;\"> <iframe src=\"https://web.powerva.microsoft.com/environments/Default-6953ed17-d2a1-4615-84a1-dbfcb7984eaa/bots/new_bot_955ad31204114e118ad9f791b0259fef/webchat\" frameBorder=\"0\" width=\"100%\" height=\"800px\" ></iframe> </body> </html>";
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        progressDialog = new ProgressDialog(Chat.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(Chat.this, "Error:" + description, Toast.LENGTH_SHORT).show();

            }
        });
        webView.loadDataWithBaseURL("", data_html, "text/html", "UTF-8", null);

        binding.backChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Chat.this.finish();
                Intent intent = new Intent(Chat.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ///Chat.this.finish();
        Intent intent = new Intent(Chat.this, MainActivity.class);
        startActivity(intent);
    }
}
