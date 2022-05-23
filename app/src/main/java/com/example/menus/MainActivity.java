package com.example.menus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public final int MENU_SETTING=1,MENU_WIFI_SETTING=2,MENU_LOCATION_SETTING=3,MENU_CAST_SETTING=4;
    int optionId,contextItems;
    ImageView downloadMenu;
    CheckBox check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toast("MainActivity");

       initView();

    registerForContextMenu(downloadMenu);
    }
private void initView(){
    downloadMenu=findViewById(R.id.mainImg);
    check=findViewById(R.id.check);
}
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.download_menu,menu);
        menu.setHeaderTitle("Context Menu");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

     SubMenu subMenu=menu.addSubMenu(1,MENU_SETTING,1,"Setting");
        subMenu.add(1,MENU_WIFI_SETTING,2,"WIFI");
        subMenu.add(1,MENU_LOCATION_SETTING,3,"Location");
        subMenu.add(1,MENU_CAST_SETTING,4,"Cast");

        MenuItem menuItemHotspot=menu.add(2,5,5,"Hotspot");
        MenuItem menuItemAeroplaneMode=menu.add(3,6,6,"Aeroplane MOde");

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        menu.setGroupEnabled(2,check.isChecked());
       // menu.findItem(MENU_CAST_SETTING).setVisible(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        optionId=item.getItemId();

        switch (optionId){
            case MENU_SETTING:
                toast("Menu");
                break;
            case MENU_WIFI_SETTING:
                toast("WIF");
                break;
            case MENU_LOCATION_SETTING:
                toast("Location");
                break;
            case MENU_CAST_SETTING:
                toast("Cast");
                break;
            case 5:
                toast("Hotspot");
                break;
            case 6:
                toast("Aeroplane Mode");
                break;
        }
      return true;
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        contextItems=item.getItemId();
        switch (contextItems){
            case R.id.saveImg:
                toast("Save Image");
                break;
            case R.id.likeImg:
                toast("Like");
                break;
            case R.id.dislikeImage:
                toast("Dislike");
                break;
            case R.id.downloadImg:
                toast("Downloading..");
                break;
            case R.id.next:
                AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Next Activity");
                dialog.setMessage("Are sure go to next Activity?");
                dialog.setPositiveButton("Yas",new YesClickListener());
                dialog.setNegativeButton("No",new NoClickListener());
                dialog.show();
                break;
        }
        return super.onContextItemSelected(item);
    }
    private class YesClickListener implements DialogInterface.OnClickListener{
        @Override
        public void onClick(DialogInterface dialog, int which) {
            Intent intent=new Intent(MainActivity.this,NextActivity.class);
            startActivity(intent);
            toast("Next Activity");
        }
    }
    private class NoClickListener implements DialogInterface.OnClickListener{
        @Override
        public void onClick(DialogInterface dialog, int which) {
            toast("MainActivity");
        }
    }

    private void toast(String text){
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }
}