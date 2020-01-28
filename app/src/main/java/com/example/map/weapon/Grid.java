package com.example.map.weapon;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.map.Adapter.GridAdapter;
import com.example.map.Model.GridLayoutModel;
import com.example.map.R;

import java.util.ArrayList;

public class Grid extends AppCompatActivity {

    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    ArrayList<GridLayoutModel> weaponsGridList;
    ArrayList<GridLayoutModel> gunsGridList;
    ArrayList<GridLayoutModel> attachmentGridList;
    String gridType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weaponlayout);

        Bundle bundle = getIntent().getExtras();
        gridType = bundle.getString("gridtype");

        recyclerView = findViewById(R.id.gridsRecycler);
        weaponsGridList=new ArrayList<>();

        weaponsGridList.add(new GridLayoutModel("Gun","https://i.imgur.com/zgfiWXw.png"));
        weaponsGridList.add(new GridLayoutModel("Melee","https://i.imgur.com/RkjDRHo.png"));
        weaponsGridList.add(new GridLayoutModel("Ammunition","https://i.imgur.com/kYwffmf.png"));
        weaponsGridList.add(new GridLayoutModel("Equipment","https://i.imgur.com/UgKsdyJ.png"));
        weaponsGridList.add(new GridLayoutModel("Throwable","https://i.imgur.com/PEVJRyw.png"));
        weaponsGridList.add(new GridLayoutModel("Consumable","https://i.imgur.com/W2jC0Gl.png"));
        weaponsGridList.add(new GridLayoutModel("Attachment","https://i.imgur.com/0SIZrf9.png"));

        gunsGridList = new ArrayList<>();

        gunsGridList.add(new GridLayoutModel("Assault Rifle","https://i.imgur.com/ZKo0HYi.png"));
        gunsGridList.add(new GridLayoutModel("Sub Machine Gun","https://i.imgur.com/3WFHpZc.png"));
        gunsGridList.add(new GridLayoutModel("Sniper","https://i.imgur.com/RgshBpg.png"));
        gunsGridList.add(new GridLayoutModel("Light Machine Gun","https://i.imgur.com/0ZV7QKx.png"));
        gunsGridList.add(new GridLayoutModel("Shotgun","https://i.imgur.com/noorpoy.png"));
        gunsGridList.add(new GridLayoutModel("Pistol","https://i.imgur.com/anC8K5e.png"));

        attachmentGridList = new ArrayList<>();

        attachmentGridList.add(new GridLayoutModel("Muzzle Mod","https://i.imgur.com/z4KejvC.png"));
        attachmentGridList.add(new GridLayoutModel("Lower Rail","https://i.imgur.com/zwg3hwj.png"));
        attachmentGridList.add(new GridLayoutModel("Upper Rail","https://i.imgur.com/uczpZlB.png"));
        attachmentGridList.add(new GridLayoutModel("Magazine","https://i.imgur.com/KEx35Ut.png"));
        attachmentGridList.add(new GridLayoutModel("Stock","https://i.imgur.com/kXETUY6.png"));

        GridAdapter gridsAdapter = new GridAdapter(this,weaponsGridList);

        if(gridType.contains("Gun"))
            gridsAdapter = new GridAdapter(this, gunsGridList);
        else if(gridType.contains("Attachment"))
            gridsAdapter = new GridAdapter(this, attachmentGridList);
        gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setAdapter(gridsAdapter);
    }
}
