package com.example.justloginregistertest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class DataGetActivity extends AppCompatActivity implements View.OnClickListener{
    private DBOpenHelper mDBOpenHelper;
    private LinearLayout mLlDatagetTop;
    private ImageView mIvDataGetBack;
    private LinearLayout mLlDataGetBody;
    private EditText mEtDataGetUsername;
    private Button mBtDataGet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_data_get );
        initView();
        mDBOpenHelper=new DBOpenHelper( this );
    }
    private void initView(){
        mLlDatagetTop=findViewById( R.id.ll_dataget_top );
        mIvDataGetBack=findViewById( R.id.iv_dataget_back );
        mLlDataGetBody=findViewById( R.id.ll_dataget_body );
        mEtDataGetUsername=findViewById( R.id.et_dataget_username );
        mBtDataGet=findViewById( R.id.bt_dataget );

        mIvDataGetBack.setOnClickListener( this );
        mBtDataGet.setOnClickListener( this );
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_dataget_back:
                startActivity( new Intent( this,MainActivity.class ) );
                finish();
                break;
            case R.id.bt_dataget:
                String username=mEtDataGetUsername.getText().toString().trim();
                User user = null;
                if(!TextUtils.isEmpty( username )){
                    ArrayList<User> data=mDBOpenHelper.getAllData();
                    boolean flag=false;
                    for(int i=0;i<data.size();i++){
                        user=data.get( i );
                        if(username.equals( user.getName() )){
                            flag=true;
                            break;
                        }
                    }
                    if(flag){
                        AlertDialog.Builder builder=new AlertDialog.Builder(
                                DataGetActivity.this);
                        builder.setTitle( "所查询的用户信息" );
                        builder.setMessage( "用户名："+username+"\n"+"密  码："+user.getPassword() );
                        builder.setCancelable( false );
                        builder.setPositiveButton( "知道了！", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        } );
                        builder.create().show();
                    }else{
                        Toast.makeText( this,"用户不存在，请重新输入",Toast.LENGTH_SHORT ).show();
                    }
                }else{
                    Toast.makeText( this,"请输入你的用户名",Toast.LENGTH_SHORT ).show();
                }
                break;
            default:
                break;
        }
    }
}
