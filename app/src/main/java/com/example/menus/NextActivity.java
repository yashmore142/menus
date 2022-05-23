package com.example.menus;

import android.app.Activity;
import android.content.ClipData;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class NextActivity extends Activity {
    ListView listView;
    String[] contacts={"Yash","Pratik","Mayur","Shreyash","Akashay"};
    ArrayAdapter<String> arrayAdapter;
    int contactMenu;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.next_activity);
        initView();
        arrayAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,contacts);
        listView.setAdapter(arrayAdapter);
        registerForContextMenu(listView);
    }
    private void initView(){
        listView=findViewById(R.id.listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.contact_menu,menu);
        menu.setHeaderTitle("Contact Menu");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        contactMenu=item.getItemId();
        switch (contactMenu){
            case R.id.call:
                toast("Call");
                break;
            case R.id.sms:
                toast("SMS");
                break;
            case R.id.instagram:
                toast("Instagram");
                break;
            case R.id.whatsapp:
                toast("What's app");
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void toast(String text){
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();

    }
}
