package com.example.justloginregistertest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class DataManagerActivity extends AppCompatActivity implements View.OnClickListener{
    private DBOpenHelper mDBOpenHelper;
    private RelativeLayout mRlDataManagerTop;
    private ImageView mIvDataManagerBack;
    private LinearLayout mLlDataManagerBody;
    private EditText mEtDataManagerUsername;
    private EditText mEtDataManagerOld;
    private EditText mEtDataManagerNew;
    private Button mBtDataManagerUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_data_manager );

        initView();
        mDBOpenHelper=new DBOpenHelper( this );
    }

    private void initView(){
        mRlDataManagerTop=findViewById( R.id.rl_datamanager_top );
        mIvDataManagerBack=findViewById( R.id.iv_datamanager_back );
        mLlDataManagerBody=findViewById( R.id.ll_datamanager_body );
        mEtDataManagerUsername=findViewById( R.id.et_datamanager_username );
        mEtDataManagerOld=findViewById( R.id.et_datamanager_old );
        mEtDataManagerNew=findViewById( R.id.et_datamanager_new );
        mBtDataManagerUpdate=findViewById( R.id.bt_datamanager_update );

        mIvDataManagerBack.setOnClickListener( this );
        mBtDataManagerUpdate.setOnClickListener( this );
    }
    public void onClick(View view){
        if(view.getId()==R.id.iv_datamanager_back){
            startActivity( new Intent( this,MainActivity.class ) );
            finish();
        }else{
            String username=mEtDataManagerUsername.getText().toString().trim();
            String old_password=mEtDataManagerOld.getText().toString().trim();
            String new_password=mEtDataManagerNew.getText().toString().trim();
            if(!TextUtils.isEmpty( username )&&!TextUtils.isEmpty( old_password )&&!TextUtils.isEmpty( new_password )){
                ArrayList<User> data=mDBOpenHelper.getAllData();
                boolean flag=false;
                for(int i=0;i<data.size();i++){
                    User user=data.get( i );
                    if(username.equals( user.getName() )&&old_password.equals( user.getPassword() )){
                        flag=true;
                        break;
                    }
                }
                if(flag){
                    if(old_password.equals( new_password )){
                        Toast.makeText( this,"新密码不能与近期的原密码相同",Toast.LENGTH_SHORT ).show();
                    }else{
                        mDBOpenHelper.updata( new_password );
                        startActivity( new Intent( this,MainActivity.class ) );
                        finish();
                        Toast.makeText( this,"传输成功",Toast.LENGTH_SHORT ).show();
                    }
                }else{
                    Toast.makeText( this,"用户名或密码不正确，请重新输入",Toast.LENGTH_SHORT ).show();
                }
            }else{
                Toast.makeText( this,"请输入你的用户名或密码",Toast.LENGTH_SHORT ).show();
            }
        }
    }
}
