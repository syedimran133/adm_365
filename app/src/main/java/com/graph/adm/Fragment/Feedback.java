package com.graph.adm.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.graph.adm.Utils.AppSingle;
import com.graph.adm.Utils.FlowOrganizer;
import com.graph.adm.Utils.MSGraphRequestWrapper;
import com.graph.adm.Utils.Utils;
import com.graph.adm.databinding.LayoutFeedbackBinding;
import com.graph.adm.model.todolist.getid.ToDoListGetIdData;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Feedback extends Fragment {

    private LayoutFeedbackBinding binding;
    Bitmap bitmap;
    private static final int REQUEST_PERMISSIONS = 100;
    private static final int PICK_IMAGE_REQUEST = 111;
    String name, f_path;
    String id, res_name;
    DataOutputStream dos = null;
    //String lineEnd = "\r\n";
    String boundary = "apiclient-" + System.currentTimeMillis();
    String twoHyphens = "--";
    int bytesRead, bytesAvailable, bufferSize;
    byte[] buffer;
    int maxBufferSize = 1024 * 1024;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = LayoutFeedbackBinding.inflate(inflater, container, false);
        binding.tvNameFeedback.setText(AppSingle.getInstance().getUserName());
        binding.tvEmailFeedback.setText(AppSingle.getInstance().getUserEmail());
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FlowOrganizer.getInstance().popUpBackTo(1);
            }
        });
        binding.btnBrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST);
            }
        });
        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FlowOrganizer.getInstance().add(new Dashboard());
            }
        });
        binding.tvSubmit.setOnClickListener(v -> {
            double s = 0;
            try {
                File img = new File(f_path);
                s = Utils.getSize(img.length());
            } catch (Exception e) {
            }

            if (binding.edPhoneNumberk.getText().toString().length() != 0) {
                if (binding.edFeedback.getText().toString().length() != 0) {
                    if (s != 0) {
                        if (s < 4) {
                            uploadImage();
                        } else {
                            Toast.makeText(getContext(), "Please upload the image below size 4 MP", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        showalert();
                    }
                } else {
                    Toast.makeText(getContext(), "Feedback empty", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getContext(), "Phone Number empty", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            f_path = getPathFromURI(filePath);
            name = f_path.split("/")[f_path.split("/").length - 1];
            try {
                //getting image from gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), filePath);
                //Setting image to ImageView
                //image.setImageBitmap(bitmap);
                binding.tvAttachment.setText(name);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = AppSingle.getInstance().getActivity().getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

    public void uploadImage() {
        Utils.setProgressDialog(getContext());
        String imgname = String.valueOf(Calendar.getInstance().getTimeInMillis()) + "." + name.split("\\.")[name.split("\\.").length - 1];
        //Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        final byte[] bitmapData = byteArrayOutputStream.toByteArray();
        JsonObjectRequest multipartRequest = new JsonObjectRequest(Request.Method.PUT, "https://graph.microsoft.com/v1.0/drives/b!g4tuFl5lWUiYETKZZdd4WeuiIhnVUSFEkc1UsB6UJqCuTlXSmxa4R5SWepVQmg0N/items/root:/" + imgname + ":/content", new JSONObject(), response -> {
            Log.e("Response Data ", response.toString());
            try {
                getIdCallBack(binding.tvEmailFeedback.getText().toString(),response.getString("id"),response.getString("name"));
            } catch (JSONException e) {
                e.printStackTrace();
                Utils.closeDilog();
            }
        }, error -> {
            Utils.closeDilog();
            Log.i("Error", error.getMessage());
        }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + AppSingle.getInstance().getmAccessToken());
                return headers;
            }

            @Override
            public byte[] getBody() {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                dos = new DataOutputStream(bos);
                try {
                    ByteArrayInputStream fileInputStream = new ByteArrayInputStream(bitmapData);
                    bytesAvailable = fileInputStream.available();

                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    buffer = new byte[bufferSize];
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                    while (bytesRead > 0) {
                        dos.write(buffer, 0, bufferSize);
                        bytesAvailable = fileInputStream.available();
                        bufferSize = Math.min(bytesAvailable, maxBufferSize);
                        bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                    }
                    return bos.toByteArray();

                } catch (IOException e) {
                    e.printStackTrace();
                    Utils.closeDilog();
                }
                return bitmapData;
            }
        };
        Volley.newRequestQueue(getContext()).add(multipartRequest);
    }
    private void getIdCallBack(String email,String res_id,String res_name) {
        Log.d("GURL :: ", "https://graph.microsoft.com/v1.0/sites/166e8b83-655e-4859-9811-329965d77859/lists('User Information List')/items?expand=fields(select=Id,Email)&$filter=fields/EMail eq '" + email.trim() + "'");
        MSGraphRequestWrapper.callGraphAPIUsingVolley(
                getContext(),
                "https://graph.microsoft.com/v1.0/sites/166e8b83-655e-4859-9811-329965d77859/lists('User Information List')/items?expand=fields(select=Id,Email)&$filter=fields/EMail eq '" + email.trim() + "'",
                AppSingle.getInstance().getmAccessToken(),
                response -> {
                    ToDoListGetIdData data = new Gson().fromJson(response.toString(), ToDoListGetIdData.class);
                    String ids = data.getValue().get(0).getFields().getId();
                    try {
                        postDataUsingVolley(ids,res_id,res_name);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Utils.closeDilog();
                    }
                },
                error -> {
                    Utils.closeDilog();
                });
    }
    private void postDataUsingVolley(String user_id,String res_id,String res_name) throws JSONException {
        String url = "https://graph.microsoft.com/v1.0/sites/166e8b83-655e-4859-9811-329965d77859/lists/eb1c2a2a-28ad-43f8-a3cf-7ae73762a6e5/items";
        RequestQueue queue = Volley.newRequestQueue(getContext());
        JSONObject parameters = new JSONObject();
        JSONObject param_fileds = new JSONObject();
        param_fileds.put("Title", "FeedBack");
        param_fileds.put("FeedbackType", "Comments");
        param_fileds.put("Email", binding.tvEmailFeedback.getText().toString());
        param_fileds.put("PhoneNumber", binding.edPhoneNumberk.getText().toString());
        param_fileds.put("Feedback", binding.edFeedback.getText().toString());
        param_fileds.put("UsernameLookupId", user_id);
        param_fileds.put("DocumentName", res_name);
        param_fileds.put("DocumentId", res_id);
        parameters.put("fields", param_fileds);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, parameters, response -> {
            Utils.closeDilog();
            Log.e("Response Data Feedback ", response.toString());
            Toast.makeText(getContext(), "successfully feedback uploaded", Toast.LENGTH_SHORT).show();
        }, error -> {
            Utils.closeDilog();
            Toast.makeText(getContext(), "Fail to get response = " + error.toString(), Toast.LENGTH_SHORT).show();
        }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Prefer", "HonorNonIndexedQueriesWarningMayFailRandomly");
                headers.put("Authorization", "Bearer " + AppSingle.getInstance().getmAccessToken());
                return headers;
            }
        };
        queue.add(request);
    }
    public void showalert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Are you sure you want to continue without attachment?")
                .setCancelable(false)
                .setPositiveButton("Yes", (dialog, id) -> {
                    Utils.setProgressDialog(getContext());
                    getIdCallBack(AppSingle.getInstance().getUserEmail(),"","");
                })
                .setNegativeButton("No", (dialog, id) -> dialog.cancel());
        AlertDialog alert = builder.create();
        alert.show();
    }
}
