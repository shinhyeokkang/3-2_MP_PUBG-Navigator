package com.example.map;

import android.Manifest;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.map.Adapter.CategoryAdapter;
import com.example.map.Model.CategoryModel;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import com.stone.vega.library.VegaLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class Category extends AppCompatActivity {
    RecyclerView categoryRecycler;
    ArrayList<CategoryModel> categoryModelArrayList=new ArrayList<CategoryModel>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tip_layout);

        categoryRecycler = findViewById(R.id.category_recycler);

        requestPermissions();

        categoryModelArrayList.add(new CategoryModel("WEAPON","https://i.imgur.com/x8lKVDO.jpg","Know your weapons before you shoot"));
        categoryModelArrayList.add(new CategoryModel("MAP","https://i.imgur.com/yXNNRbm.jpg","Know your map before you land"));
        categoryRecycler.setLayoutManager(new VegaLayoutManager());

        final CategoryAdapter categoryAdapter = new CategoryAdapter(this, categoryModelArrayList);

        categoryRecycler.setAdapter(categoryAdapter);

    }

    private void requestPermissions() {

        Dexter.withActivity(this).withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.INTERNET)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if(report.areAllPermissionsGranted()){
                            return;
                        } else {
                            Toast.makeText(getApplicationContext(),"permission is not granted", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();

    }
}
