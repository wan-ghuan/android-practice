package com.alex.wang.lean.practice.ui.home;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.alex.wang.lean.practice.R;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class HomeFragment extends Fragment implements ZXingScannerView.ResultHandler {
    private static final int REQUEST_PERMISSION_CODE = 1;

    private HomeBinding mBinding;
    private HomeViewModel mViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[] { Manifest.permission.CAMERA }, REQUEST_PERMISSION_CODE);
        } else {
            mViewModel.setCameraIsGranted(true);
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);

        mBinding.setViewModel(mViewModel);
        mBinding.setLifecycleOwner(this);

        mBinding.scannerView.setResultHandler(this);

        return mBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();

        if (mViewModel.getCameraIsGranted().get()) {
            mBinding.scannerView.startCamera();
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        if (mViewModel.getCameraIsGranted().get()) {
            mBinding.scannerView.stopCamera();
        }
    }

    @Override
    public void handleResult(Result rawResult) {
        mViewModel.setScannerResult(rawResult.getText());

        if (mViewModel.getCameraIsGranted().get()) {
            mBinding.scannerView.resumeCameraPreview(this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_PERMISSION_CODE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mViewModel.getCameraIsGranted().set(true);
                    mBinding.scannerView.startCamera();
                } else {
                    mViewModel.getCameraIsGranted().set(false);
                }
                break;
            default:
                break;
        }
    }
}
