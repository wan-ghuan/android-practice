package com.alex.wang.lean.practice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private static final String TAG = "ScannerActivity";
    private ZXingScannerView mScannerView;
    private static final int REQUEST_PERMISSION_CODE = 1;
    private boolean mCameraIsGranted = false;

    @Override
    public void handleResult(Result rawResult) {
        Log.i(TAG, rawResult.getText());
        Log.i(TAG, rawResult.getBarcodeFormat().toString());

        if (mCameraIsGranted) {
            mScannerView.resumeCameraPreview(this);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.CAMERA }, REQUEST_PERMISSION_CODE);
        } else {
            mCameraIsGranted = true;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        if (mCameraIsGranted) {
            mScannerView.setResultHandler(this);
            mScannerView.startCamera();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mCameraIsGranted) {
            mScannerView.stopCamera();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_PERMISSION_CODE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mCameraIsGranted = true;
                    onResume();
                } else {
                    mCameraIsGranted = false;
                    onPause();
                }
                break;
            default:
                break;
        }
    }
}
