package com.example.ashab.hcs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TextView;

public class MainActivity  extends AppCompatActivity implements OnClickListener {

    HcsManager hcsManager;

    Button btnSendEmail;
    Button btnSaveData;
    EditText electDaytimeCurrent;
    EditText electNightCurrent;
    EditText hcsCurrent;
    TextView paySum;
    TextView electDaytimePrevious;
    TextView electNightPrevious;

    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_ELECT_DAY = "electr_day";
    public static final String APP_ELECT_NIGHT = "electr_night";
    private SharedPreferences mSettings;

    class InnerDataLabel {
        private String strHcs = "";
        private String strDaytimeData = "";
        private String strNightData = "";
        private String strPreviousDaytimeData = "";
        private String strPreviousNightData = "";
    }

    InnerDataLabel dataLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hcsManager = new HcsManager();
        dataLabel = new InnerDataLabel();
        paySum = (TextView) findViewById(R.id.paySum);

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        electDaytimePrevious = (TextView) findViewById(R.id.electDaytimePrevious);

        electNightPrevious = (TextView) findViewById(R.id.electNightPrevious);


        btnSendEmail = (Button) findViewById(R.id.btnSendEmail);
        btnSendEmail.setOnClickListener(this);

        btnSaveData = (Button) findViewById(R.id.btnSaveData);
        btnSaveData.setOnClickListener(this);

        electDaytimeCurrent = (EditText) findViewById(R.id.electDaytimeCurrent);
        electDaytimeCurrent.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                dataLabel.strDaytimeData = s.toString();
                RefreshDataLabels();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });


        electNightCurrent = (EditText) findViewById(R.id.electNightCurrent);
        electNightCurrent.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                dataLabel.strNightData = s.toString();
                RefreshDataLabels();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        hcsCurrent = (EditText) findViewById(R.id.hcsCurrent);
        hcsCurrent.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                dataLabel.strHcs = s.toString();
                RefreshDataLabels();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mSettings.contains(APP_ELECT_DAY) && mSettings.contains(APP_ELECT_NIGHT)) {
            dataLabel.strPreviousDaytimeData = mSettings.getString(APP_ELECT_DAY, "");
            dataLabel.strPreviousNightData = mSettings.getString(APP_ELECT_NIGHT, "");
        }
        electDaytimePrevious.setText(dataLabel.strPreviousDaytimeData);
        electNightPrevious.setText(dataLabel.strPreviousNightData);
        hcsManager.SetDayPreviousDaytimeData(dataLabel.strPreviousDaytimeData);
        hcsManager.SetDayPreviousNightData(dataLabel.strPreviousNightData);
    }

    private void RefreshDataLabels() {
        System.out.println(dataLabel.strDaytimeData);

        System.out.println(dataLabel.strHcs);

        System.out.println(dataLabel.strNightData);

        hcsManager.SetCurrentDaytimeData(dataLabel.strDaytimeData);

        hcsManager.SetCurrentNightData(dataLabel.strNightData);

        hcsManager.SetHcs(dataLabel.strHcs);

        paySum.setText(hcsManager.innerCurrentData.sumData);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnSaveData:
                SharedPreferences.Editor editor = mSettings.edit();
                editor.putString(APP_ELECT_DAY, dataLabel.strDaytimeData);
                editor.putString(APP_ELECT_NIGHT, dataLabel.strNightData);
                editor.apply();
                break;
            case R.id.btnSendEmail:
                Intent intent = new Intent(this, LetterActivity.class);
                HcsLetter letter = hcsManager.GetLetter();
                intent.putExtra("email", letter.GetEmail());
                intent.putExtra("theme", letter.GetTheme());
                intent.putExtra("message", letter.GetMessage());
                intent.putExtra("detail", letter.GetDetail());
                startActivity(intent);
                break;
            default:
                break;
        }


    }

}
