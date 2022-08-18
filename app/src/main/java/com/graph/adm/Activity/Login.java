package com.graph.adm.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.graph.adm.R;
import com.graph.adm.Utils.AppSingle;
import com.graph.adm.Utils.MSGraphRequestWrapper;
import com.graph.adm.model.UserData;
import com.microsoft.graph.authentication.IAuthenticationProvider;
import com.microsoft.graph.concurrency.ICallback;
import com.microsoft.graph.core.ClientException;
import com.microsoft.graph.http.IHttpRequest;
import com.microsoft.graph.models.extensions.Drive;
import com.microsoft.graph.models.extensions.IGraphServiceClient;
import com.microsoft.graph.models.extensions.ProfilePhoto;
import com.microsoft.graph.models.extensions.User;
import com.microsoft.graph.requests.extensions.GraphServiceClient;
import com.microsoft.identity.client.AuthenticationCallback;
import com.microsoft.identity.client.IAccount;
import com.microsoft.identity.client.exception.MsalClientException;
import com.microsoft.identity.client.exception.MsalException;
import com.microsoft.identity.client.exception.MsalServiceException;
import com.microsoft.identity.client.ISingleAccountPublicClientApplication;
import com.microsoft.identity.client.*;
import com.microsoft.identity.client.exception.MsalUiRequiredException;

public class Login extends AppCompatActivity {

    private LinearLayout btn_login;
    private ISingleAccountPublicClientApplication mSingleAccountApp;
    public static IAccount mAccount;
    private LinearLayout progress, login_layout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        btn_login = (LinearLayout) findViewById(R.id.btn_login);
        progress = (LinearLayout) findViewById(R.id.progress_layout);
        login_layout = (LinearLayout) findViewById(R.id.login_layout);
        mSingleAccountApp = AppSingle.getInstance().getmSingleAccountApp();
        if (mSingleAccountApp != null)
            loadAccount();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSingleAccountApp == null) {
                    return;
                }
                mSingleAccountApp.signIn(Login.this, null, getScopes(), getAuthInteractiveCallback());

            }
        });

    }

    private String[] getScopes() {
        String[] arr = {"User.Read","Calendars.Read","Sites.Read.All","Sites.ReadWrite.All","Mail.Read","Files.ReadWrite","Files.ReadWrite.All","Sites.ReadWrite.All"};
        return arr;
    }

    private AuthenticationCallback getAuthInteractiveCallback() {
        return new AuthenticationCallback() {

            @Override
            public void onSuccess(IAuthenticationResult authenticationResult) {
                /* Successfully got a token, use it to call a protected resource - MSGraph */
                Log.d("TAG", "Successfully authenticated");
                Log.d("TAG", "ID Token: " + authenticationResult.getAccount().getClaims().get("id_token"));
                AppSingle.getInstance().setmAccessToken(authenticationResult.getAccessToken());
                mAccount = authenticationResult.getAccount();
                //getUserCallBack(authenticationResult);
                //getPhotoCallBack(authenticationResult);
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onError(MsalException exception) {
                /* Failed to acquireToken */
                Log.d("TAG", "Authentication failed: " + exception.toString());
                //displayError(exception);

                if (exception instanceof MsalClientException) {
                    /* Exception inside MSAL, more info inside MsalError.java */
                } else if (exception instanceof MsalServiceException) {
                    /* Exception when communicating with the STS, likely config issue */
                }
            }

            @Override
            public void onCancel() {
                /* User canceled the authentication */
                Log.d("TAG", "User cancelled login.");
            }
        };
    }
    private void loadAccount() {
        if (mSingleAccountApp == null) {
            return;
        }

        mSingleAccountApp.getCurrentAccountAsync(new ISingleAccountPublicClientApplication.CurrentAccountCallback() {
            @Override
            public void onAccountLoaded(@Nullable IAccount activeAccount) {
                // You can use the account data to update your UI or your app database.
                mAccount = activeAccount;
                try {
                    if (mAccount.getUsername().isEmpty()) {
                        login_layout.setVisibility(View.VISIBLE);
                        progress.setVisibility(View.GONE);
                    } else {
                        login_layout.setVisibility(View.GONE);
                        progress.setVisibility(View.VISIBLE);
                    }
                } catch (Exception e) {
                    login_layout.setVisibility(View.VISIBLE);
                    progress.setVisibility(View.GONE);
                }
                try {
                    mSingleAccountApp.acquireTokenSilentAsync(getScopes(), mAccount.getAuthority(), getAuthSilentCallback());
                    // mSingleAccountApp.acquireTokenSilentAsync(getScopes(), mAccount.getAuthority(), getAuthSilentCallback());
                } catch (Exception e) {
                }
                //updateUI();
            }

            @Override
            public void onAccountChanged(@Nullable IAccount priorAccount, @Nullable IAccount currentAccount) {
                if (currentAccount == null) {
                    // Perform a cleanup task as the signed-in account changed.
                    //showToastOnSignOut();
                }
            }

            @Override
            public void onError(@NonNull MsalException exception) {
                //displayError(exception);
                Toast.makeText(Login.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private SilentAuthenticationCallback getAuthSilentCallback() {
        return new SilentAuthenticationCallback() {

            @Override
            public void onSuccess(IAuthenticationResult authenticationResult) {
                Log.d("TAG", "Successfully authenticated");
                Log.d("AccessToken", authenticationResult.getAccessToken());
                AppSingle.getInstance().setmAccessToken(authenticationResult.getAccessToken());
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onError(MsalException exception) {
                /* Failed to acquireToken */
                Log.d("TAG", "Authentication failed: " + exception.toString());
                /// displayError(exception);

                if (exception instanceof MsalClientException) {
                    /* Exception inside MSAL, more info inside MsalError.java */
                } else if (exception instanceof MsalServiceException) {
                    /* Exception when communicating with the STS, likely config issue */
                } else if (exception instanceof MsalUiRequiredException) {
                    /* Tokens expired or no session, retry with interactive */
                }
            }
        };
    }
}
