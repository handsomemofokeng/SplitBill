package com.example.splitbill;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.MessageFormat;

import static com.example.splitbill.ApplicationClass.tip;

public class ChangeTipActivity extends AppCompatActivity {

    SeekBar sbTip;
    TextView tvTip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_tip);

        tvTip = findViewById(R.id.tv_current_tip);
        tvTip.setText(MessageFormat.format("{0}%", tip));

        sbTip =findViewById(R.id.sbTip);
        sbTip.setProgress(tip);

        sbTip.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                tvTip.setText(new StringBuilder().append(progress).append("%").toString());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void onClick_SaveTip(View view) {
        tip = sbTip.getProgress();
        finish();

    }

}
