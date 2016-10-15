package com.adonisarifi.fybermobileofferapi.ui.activity;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.adonisarifi.fybermobileofferapi.R;
import com.adonisarifi.fybermobileofferapi.api.ApiParamsModel;
import com.adonisarifi.fybermobileofferapi.model.ResultResponsOffer;
import com.adonisarifi.fybermobileofferapi.ui.view.RequestContract;
import com.adonisarifi.fybermobileofferapi.ui.view.RequestPresenter;
import com.adonisarifi.fybermobileofferapi.util.Constants;
import com.adonisarifi.fybermobileofferapi.util.SupportMethod;
import com.adonisarifi.fybermobileofferapi.util.TextToSHA1;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class InputApiDataActivity extends AppCompatActivity implements RequestContract.View {


    @Bind(R.id.edittext_input_uid)
    EditText edittext_input_uid;
    @Bind(R.id.edittext_input_apikey)
    EditText edittext_input_apikey;
    @Bind(R.id.edittext_input_appid)
    EditText edittext_input_appid;
    @Bind(R.id.edittext_input_pub0)
    EditText edittext_input_pub0;
    @Bind(R.id.button_getoffer)
    Button button_getoffer;
    @Bind(R.id.textView_info)
    TextView textView_info;

    private RequestPresenter requestPresenter;

    private ProgressDialog progressDialog;

    private ArrayList<String> apiParamsArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_api_data);
        ButterKnife.bind(this);
        initControls();
        String test = edittext_input_apikey.getText().toString();

    }


    public void showProgresDialog(String message) {
        progressDialog = new ProgressDialog(this, android.support.v7.appcompat.R.style.Base_Theme_AppCompat_Light_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(message);
        progressDialog.show();
    }


    private void hideProgressDialog() {
        if (progressDialog!=null)
        progressDialog.dismiss();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void initControls() {
        requestPresenter = new RequestPresenter(this, this);
        //TODO Cleare before send to Fyber for review


    }

    @Override
    public void startRequestForData() {
        if (!validateInputeField()) {
            return;
        } else {
            requestPresenter.getResultResponsOfferObservable(getApiParamsModel());
        }
    }

    @Override
    public String getSha1(String fullUrl) {

        String stringSha1 = null;
        try {
            stringSha1 = TextToSHA1.getsha1(fullUrl);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return stringSha1;
    }

    public String getConcatenateAllParams(ArrayList<ApiParamsModel> apiParamsModels) {

        String stringConca = "";
        for (ApiParamsModel paramsModel : apiParamsModels) {
            stringConca += paramsModel.getParams_name() + "=" + paramsModel.getParams_value() + "&";
        }
        return stringConca;
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        if (active) {
            showProgresDialog(getString(R.string.please_wait));
        } else {
            hideProgressDialog();
        }
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean validateInputeField() {
        boolean valid = true;

        String uid = edittext_input_uid.getText().toString();
        String apikey = edittext_input_apikey.getText().toString();
        String appid = edittext_input_appid.getText().toString();
        String pub0 = edittext_input_pub0.getText().toString();

        if (uid.isEmpty()) {
            edittext_input_uid.setError("Enter uid");
            valid = false;
        } else {
            edittext_input_uid.setError(null);
        }

        if (apikey.isEmpty()) {
            edittext_input_apikey.setError("Enter Api Key");
            valid = false;
        } else {
            edittext_input_apikey.setError(null);
        }

        if (appid.isEmpty()) {
            edittext_input_appid.setError("Enter AppId");
            valid = false;
        } else {
            edittext_input_appid.setError(null);
        }

        if (pub0.isEmpty()) {
            edittext_input_pub0.setError("Enter pub0");
            valid = false;
        } else {
            edittext_input_pub0.setError(null);
        }
        return valid;
    }

    @Override
    public ArrayList<ApiParamsModel> getApiParamsModel() {
        ArrayList<ApiParamsModel> apiParamsModels = new ArrayList<>();
        apiParamsModels.add(new ApiParamsModel(Constants.PARAMS_UID, edittext_input_uid.getText().toString()));
        apiParamsModels.add(new ApiParamsModel(Constants.PARAMS_APP_ID, edittext_input_appid.getText().toString()));
        apiParamsModels.add(new ApiParamsModel(Constants.PARAMS_IP, Constants.IP));
        apiParamsModels.add(new ApiParamsModel(Constants.PARAMS_LOCALE, Constants.LOCALE));
        apiParamsModels.add(new ApiParamsModel(Constants.PARAMS_DEVICE_ID, SupportMethod.getDeviceId(this)));
        apiParamsModels.add(new ApiParamsModel(Constants.PARAMS_PUB0, Constants.Pub0));
        // current time
        long unixTime = System.currentTimeMillis() / 1000L;
        apiParamsModels.add(new ApiParamsModel(Constants.PARAMAS_TIMESTAMP, String.valueOf(unixTime)));

        //Order all request alphabetically
        apiParamsModels.iterator();
        Collections.sort(apiParamsModels, new Comparator<ApiParamsModel>() {
            @Override
            public int compare(ApiParamsModel apiParamsModel, ApiParamsModel t1) {
                return apiParamsModel.getParams_name().compareTo(t1.getParams_name());
            }
        });

        //Concatenate all request parameters
        String concatenateString = getConcatenateAllParams(apiParamsModels);

        //Concatenate the resulting string with your API Key
        String fullUrlString = concatenateString + Constants.API_KEY;
        String sha1 = getSha1(fullUrlString);
        apiParamsModels.add(new ApiParamsModel(Constants.PARAMS_HASHKEY, sha1));
        return apiParamsModels;
    }

    @Override
    public void openMainActivity(ResultResponsOffer resultResponsOffer) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(Constants.RESULT_RESPONS_PARCABLE_KEY, resultResponsOffer);
        startActivity(intent);
    }

    @Override
    public void setPresenter(RequestContract.Presenter presenter) {

    }

    @OnClick(R.id.button_getoffer)
    public void setOnClick_getOffer() {
        startRequestForData();
    }

    @OnClick(R.id.textView_info)
    public void setOnClick_textView_info() {
        String supportUrl = "http://developer.fyber.com/content/current/android/offer-wall/offer-api/";
        startActivity(SupportMethod.openUrl(supportUrl));
    }

}
