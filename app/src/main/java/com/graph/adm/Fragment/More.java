package com.graph.adm.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.graph.adm.Activity.Login;
import com.graph.adm.Utils.AppSingle;
import com.graph.adm.Utils.FlowOrganizer;
import com.graph.adm.databinding.LayoutMoreBinding;
import com.microsoft.identity.client.ISingleAccountPublicClientApplication;
import com.microsoft.identity.client.exception.MsalException;

public class More extends Fragment {

    private LayoutMoreBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = LayoutMoreBinding.inflate(inflater, container, false);
        binding.btnFeedback.setOnClickListener(v -> {
            FlowOrganizer.getInstance().add(new Feedback(), true);
            //Intent intent = new Intent(getActivity().getBaseContext(), Cat.class);
            //startActivity(intent);
        });
        binding.btnReportAbuse.setOnClickListener(v -> FlowOrganizer.getInstance().add(new ReportAbuse(), true));
        binding.btnHelp.setOnClickListener(v -> FlowOrganizer.getInstance().add(new Help(), true));
        binding.btnContactus.setOnClickListener(v -> FlowOrganizer.getInstance().add(new ContactUs(), true));
        binding.btnLogout.setOnClickListener(v -> {
            if (AppSingle.getInstance().getmSingleAccountApp() == null) {
                return;
            }

            /**
             * Removes the signed-in account and cached tokens from this app (or device, if the device is in shared mode).
             */
            AppSingle.getInstance().getmSingleAccountApp().signOut(new ISingleAccountPublicClientApplication.SignOutCallback() {
                @Override
                public void onSignOut() {
                    Login.mAccount = null;
                    Intent intent = new Intent(getContext(), Login.class);
                    startActivity(intent);
                }

                @Override
                public void onError(@NonNull MsalException exception) {
                    //displayError(exception);
                }
            });

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
