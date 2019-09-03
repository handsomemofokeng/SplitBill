package com.example.splitbill;

import android.app.Application;
import android.view.View;
import android.widget.EditText;

public class ApplicationClass extends Application {

    public static int tip;

    public static final int REQ_TIP = 100;

    @Override
    public void onCreate() {
        super.onCreate();
        tip = 10;
    }

    /**
     * This method checks if all field provided in parameters have values
     *
     * @param fields to be checked for validity
     * @return True if all fields have values, False if not.
     */
    public static boolean isValidFields(EditText... fields) {
        boolean isValid = true;
        for (EditText field : fields) {
            if (field.getText().toString().trim().isEmpty()) {
                field.setError("This field is required!");
                isValid = false;
            }
        }
        return isValid;
    }

    /**
     * This method clears text fields
     *
     * @param fields to be cleared
     */
    public static void clearFields(EditText... fields) {
        for (EditText field : fields) field.setText("");

    }

    /**
     * @param views to be hidden on given context
     */
    public static void hideViews(View... views) {
        for (View view : views) {
            view.setVisibility(View.GONE);
        }
    }

    /**
     * @param views to be shown on given context
     */
    public static void showViews(View... views) {
        for (View view : views) {
            view.setVisibility(View.VISIBLE);
        }
    }

}
