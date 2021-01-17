package com.alex.wang.lean.practice.ui.home;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {
    private ObservableBoolean mCameraIsGranted;
    private ObservableField<String> mScannerResult;

    public HomeViewModel() {
        mCameraIsGranted = new ObservableBoolean();
        mScannerResult = new ObservableField<>();

        mCameraIsGranted.set(false);
        mScannerResult.set("");
    }

    public ObservableBoolean getCameraIsGranted() {
        return mCameraIsGranted;
    }

    public void setCameraIsGranted(boolean cameraIsGranted) {
        mCameraIsGranted.set(cameraIsGranted);
    }

    public void setScannerResult(String scannerResult) {
        mScannerResult.set(scannerResult);
    }
}