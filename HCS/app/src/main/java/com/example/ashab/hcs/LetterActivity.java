package com.example.ashab.hcs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LetterActivity extends Activity implements OnClickListener {

  /*  LetterActivity(HcsLetter letter){
        address = letter.GetEmail();
        subject = letter.GetTheme();
        message = letter.GetMessage();
    }*/
    private EditText receiver_Email;
    private EditText message_theme;
    private EditText message_text;
    private TextView detail_text;
    String address ="annashabaeva953@gmail.com";
    String theme ="";
    String message = "";
    String detail = "";

    private Button btnSendLetterFinaly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_email);

        receiver_Email = (EditText) findViewById(R.id.receiver_Email);
        message_theme = (EditText) findViewById(R.id.message_theme);
        message_text = (EditText) findViewById(R.id.message_text);
        btnSendLetterFinaly = (Button) findViewById(R.id.btnSendLetterFinaly);
        detail_text = (TextView) findViewById(R.id.detail_txt);
        btnSendLetterFinaly.setOnClickListener(this);

        Intent intent = getIntent();
        address = intent.getStringExtra("email");
        theme = intent.getStringExtra("theme");
        message = intent.getStringExtra("message");
        detail = intent.getStringExtra("detail");
        receiver_Email.setText(address);
        message_theme.setText(theme);
        message_text.setText(message);
        detail_text.setText(detail);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnSendLetterFinaly:
                Intent emailIntent = getPackageManager().getLaunchIntentForPackage("com.android.email");
                emailIntent.setType("plain/text");
                emailIntent.putExtra(Intent.EXTRA_EMAIL,  address );
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, theme);
                emailIntent.putExtra(Intent.EXTRA_TEXT, message);
                startActivity(emailIntent);
                break;
            default:
                break;
        }

    }



}