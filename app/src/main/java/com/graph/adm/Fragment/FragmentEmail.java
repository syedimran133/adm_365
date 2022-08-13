package com.graph.adm.Fragment;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.graph.adm.Utils.FlowOrganizer;
import com.graph.adm.databinding.LayoutWebviewBinding;

public class FragmentChat  extends Fragment {

    private LayoutWebviewBinding binding;
    private WebView webView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        binding = LayoutWebviewBinding.inflate(inflater, container, false);

      /*  mWebView = binding.webview;
        mWebView.loadUrl("https://google.com");

        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.setWebViewClient(new WebViewClient());*/
        webView = binding.webview;
        webView.setInitialScale(1);
        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON_DEMAND);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;

        //Log.e(SimpleBillsConstants.SIMPLE_BILLS, width + "-" + height);

        String data_html = "<!DOCTYPE html><html> <head> <meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"target-densitydpi=high-dpi\" /> <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"> <link rel=\"stylesheet\" media=\"screen and (-webkit-device-pixel-ratio:1.5)\" href=\"hdpi.css\" /></head> <body style=\"background:white;margin:0 0 0 0; padding:0 0 0 0;\"> <iframe src=\"https://web.powerva.microsoft.com/webchat/bots/955ad312-0411-4e11-8ad9-f791b0259fef\" frameBorder=\"0\" width=\"100%\" height=\"660px\" ></iframe> </body> </html> ";

        webView.loadDataWithBaseURL("http://vimeo.com", data_html, "text/html", "UTF-8", null);
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FlowOrganizer.getInstance().popUpBackTo(1);
            }
        });
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}