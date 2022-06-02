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

public class UserManagerActivity extends AppCompatActivity implements View.OnClickListener {
    private DBOpenHelper mDBOpenHelper;
    private RelativeLayout mRlUserManagerTop;
    private ImageView mIvUserManagerBack;
    private LinearLayout mLlUserManagerTwo;
    private EditText mEtUserManagerUsername;
    private EditText mEtUserManagerPassword;
    private Button mBtUserManagerGive;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_user_manager );
        initView();
        mDBOpenHelper=new DBOpenHelper( this );
    }
    private void initView(){
        mRlUserManagerTop=findViewById( R.id.rl_usergive_top );
        mIvUserManagerBack=findViewById( R.id.iv_usergive_back );
        mLlUserManagerTwo=findViewById( R.id.ll_usergive_two );
        mEtUserManagerUsername=findViewById( R.id.et_give_username );
        mEtUserManagerPassword=findViewById( R.id.et_manager_password );
        mBtUserManagerGive=findViewById( R.id.bt_give );

        mBtUserManagerGive.setOnClickListener( this );
        mIvUserManagerBack.setOnClickListener( this );
    }
    public void onClick(View view){
        if(view.getId()==R.id.iv_usergive_back){
            startActivity( new Intent( this,MainActivity.class ) );
            finish();
        }else{
            String name=mEtUserManagerUsername.getText().toString().trim();
            String password=mEtUserManagerPassword.getText().toString().trim();
            if(!TextUtils.isEmpty( name )&&!TextUtils.isEmpty( password )){
                ArrayList<User> data=mDBOpenHelper.getAllData();
                boolean flag=false;
                for(int i=0;i<data.size();i++){
                    User user=data.get(i);
                    if(name.equals( user.getName() )){
                        flag=true;
                        break;
                    }
                }
                if(flag){
                    Toast.makeText( this,"用户已存在，请重新输入",Toast.LENGTH_SHORT ).show();
                }else{
                    mDBOpenHelper.add(name,password);
                    startActivity( new Intent( this,MainActivity.class ) );
                    finish();
                    Toast.makeText( this,"授权成功",Toast.LENGTH_SHORT ).show();
                }
            }else{
                Toast.makeText( this,"用户名为空，授权失败",Toast.LENGTH_SHORT ).show();
            }
        }
    }
}
