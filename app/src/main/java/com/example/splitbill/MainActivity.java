package com.example.splitbill;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.splitbill.ApplicationClass.REQ_TIP;
import static com.example.splitbill.ApplicationClass.clearFields;
import static com.example.splitbill.ApplicationClass.hideViews;
import static com.example.splitbill.ApplicationClass.isValidFields;
import static com.example.splitbill.ApplicationClass.showViews;
import static com.example.splitbill.ApplicationClass.tip;

public class MainActivity extends AppCompatActivity {

    EditText etBillAmount, etNumPeople;
    TextView tvFeedback, tvTip;
    View frmInput, frmOutput;
    FloatingActionButton icFeedbackPositive, icFeedbackNegative;


    double billAmount;
    int numPeople;

    boolean isTipped = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frmInput = findViewById(R.id.frmPromptInput);
        frmOutput = findViewById(R.id.frmFeedback);

        icFeedbackPositive = findViewById(R.id.ic_feedback_positive);
        icFeedbackNegative = findViewById(R.id.ic_feedback_negative);

        tvTip = findViewById(R.id.tvTip);
        tvTip.setText(new StringBuilder().append("Tip: ").append(tip).append("%").toString());

        tvFeedback = findViewById(R.id.tvFeedback);

        etBillAmount = findViewById(R.id.et_bill);
        etNumPeople = findViewById(R.id.et_people);

        hideViews(frmOutput);

    }

    public void onClick_ChangeTip(View view) {

        switch (view.getId()) {
            case R.id.btnYes:
                //
                if (isValidFields(etBillAmount, etNumPeople)) {
                    showToast("Tip added!");
                    showViews(frmOutput);
                    hideViews(frmInput);

                    isTipped = true;
                    generateFeedback(isTipped);


                } else {
                    showToast("Please enter required fields");
                }

                break;

            case R.id.btnNo:
                //
                if (isValidFields(etBillAmount, etNumPeople)) {
                    showToast("Tip not added!");

                    showViews(frmOutput);
                    hideViews(frmInput);

                    isTipped = false;
                    generateFeedback(isTipped);

                } else {
                    showToast("Please enter required fields");
                }
                break;

            case R.id.btnChangeTip:
                startActivityForResult(new Intent(getApplicationContext(), ChangeTipActivity.class),
                        REQ_TIP);
                break;

            case R.id.btnNewBill:
                showViews(frmInput);
                clearFields(etNumPeople, etBillAmount);
                hideViews(frmOutput);
                break;
        }
    }

    private void generateFeedback(boolean isTipped) {

        billAmount = Double.parseDouble(etBillAmount.getText().toString());
        double tipAmount =(billAmount * tip) / 100;
        numPeople = Integer.parseInt(etNumPeople.getText().toString());
        String feedback;

        if (isTipped) {
            showViews(icFeedbackPositive);
            hideViews(icFeedbackNegative);
            feedback = "Total Bill without tip: R" + billAmount + "\n"
                    + "Tip: R" + tipAmount + "\n"
                    + "Total Bill with tip: R" + (billAmount + tipAmount) + "\n"
                    + "Split: R" + (billAmount + tipAmount) / numPeople;
        } else {
            showViews(icFeedbackNegative);
            hideViews(icFeedbackPositive);

            feedback = "Total Bill: R" + billAmount + "\n"
                    + "Split: R" + (billAmount / numPeople);
        }

        tvFeedback.setText(feedback);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_TIP) {
            tvTip.setText(new StringBuilder().append("Tip: ").append(tip).append("%").toString());
        }
    }
}
