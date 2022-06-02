package com.example.justloginregistertest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends Activity implements View.OnClickListener {
    private Button mBtMainActivitylogout;
    private Button mBtMainActivityusergive;
    private Button mBtMainActivityuserdelete;
    private Button mBtMainActivitydataupdate;
    private Button mBtMainActivitydatadelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        // 初始化控件对象
        mBtMainActivitylogout=findViewById( R.id.bt_main_logout );
        // 绑定点击监听器
        mBtMainActivitylogout.setOnClickListener(this);
        mBtMainActivityusergive=findViewById( R.id.bt_main_usergive );
        mBtMainActivityusergive.setOnClickListener( this );
        mBtMainActivityuserdelete=findViewById( R.id.bt_main_userdelete );
        mBtMainActivityuserdelete.setOnClickListener( this );
        mBtMainActivitydataupdate=findViewById( R.id.bt_main_dataupdate );
        mBtMainActivitydataupdate.setOnClickListener( this );
        mBtMainActivitydatadelete=findViewById( R.id.bt_main_dataget );
        mBtMainActivitydatadelete.setOnClickListener( this );
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_main_logout:
                startActivity(new Intent(this, loginActivity.class));
                finish();
                break;
            case R.id.bt_main_usergive:
                startActivity( new Intent( this,UserManagerActivity.class ) );
                finish();
                break;
            case R.id.bt_main_userdelete:
                startActivity( new Intent( this,UserDeleteActivity.class ) );
                finish();
                break;
            case R.id.bt_main_dataupdate:
                startActivity( new Intent( this,DataManagerActivity.class ) );
                finish();
                break;
            case R.id.bt_main_dataget:
                startActivity( new Intent( this,DataGetActivity.class ) );
                finish();
                break;
            default:
                break;
        }
    }
}
