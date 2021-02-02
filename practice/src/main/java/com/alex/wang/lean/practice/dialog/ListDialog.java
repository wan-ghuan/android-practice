package com.alex.wang.lean.practice.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.alex.wang.lean.practice.R;

import java.util.ArrayList;
import java.util.List;

public class ListDialog extends DialogFragment {
    private List<String> mItemList;
    private AdapterView.OnItemClickListener mItemClickListener;

    public ListDialog() {
        mItemList = new ArrayList<>();
        mItemClickListener = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_dialog, container);
        ListView listView = rootView.findViewById(R.id.list_view);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.list_dialog_item, mItemList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(mItemClickListener);
        return rootView;
    }

    public void setItemList(List<String> itemList) {
        this.mItemList = itemList;
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }
}
