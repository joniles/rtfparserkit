package com.rtfparserkit.adnroid;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;


import com.rtfparserkit.adnroid.R;
import com.rtfparserkit.converter.text.StringTextConverter;
import com.rtfparserkit.parser.RtfStreamSource;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView test = findViewById(R.id.test);
        try {
            StringTextConverter converter = new StringTextConverter();
            converter.convert(new RtfStreamSource(getAssets().open("rtf_test.rtf")));
            test.setText(converter.getText());
        } catch (IOException e) {

        }
    }
}
