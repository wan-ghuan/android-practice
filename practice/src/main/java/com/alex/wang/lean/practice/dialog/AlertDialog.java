package com.alex.wang.lean.practice.dialog;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.alex.wang.lean.practice.R;

public class AlertDialog extends DialogFragment {
    private String mAlertText;
    private Drawable mAlertIcon;

    public AlertDialog() {
    }

    public AlertDialog(String mAlertText, Drawable mAlertIcon) {
        this.mAlertText = mAlertText;
        this.mAlertIcon = mAlertIcon;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.alert_dialog, container);
        ImageView alertIcon = rootView.findViewById(R.id.alert_icon);
        alertIcon.setImageDrawable(mAlertIcon);
        TextView alertText = rootView.findViewById(R.id.alert_text);
        alertText.setText(mAlertText);
        return rootView;
    }

    public void setAlertText(String mAlertText) {
        this.mAlertText = mAlertText;
    }

    public void setAlertIcon(Drawable mAlertIcon) {
        this.mAlertIcon = mAlertIcon;
    }
}
