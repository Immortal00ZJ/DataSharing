package com.example.justloginregistertest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class UserDeleteActivity extends AppCompatActivity implements View.OnClickListener{
    private DBOpenHelper mDBOpenHelper;
    private LinearLayout mLlUserDeleteTop;
    private ImageView mIvUserDeleteBack;
    private LinearLayout mLlUserDeleteBody;
    private EditText mEtUserDeleteUsername;
    private EditText mEtUserDeletePassword;
    private Button mBtUserDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_user_delete );
        initView();
        mDBOpenHelper=new DBOpenHelper( this );
    }

    private void initView(){
        mLlUserDeleteTop=findViewById( R.id.ll_userdelete_top );
        mIvUserDeleteBack=findViewById( R.id.iv_userdelete_back );
        mLlUserDeleteBody=findViewById( R.id.ll_userdelete_body );
        mEtUserDeleteUsername=findViewById( R.id.et_userdelete_username );
        mEtUserDeletePassword=findViewById( R.id.et_userdelete_password );
        mBtUserDelete=findViewById( R.id.bt_userdelete );

        mIvUserDeleteBack.setOnClickListener( this );
        mBtUserDelete.setOnClickListener( this );
    }
    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.iv_userdelete_back){
            startActivity( new Intent( this,MainActivity.class ) );
            finish();
        }else{
            String username=mEtUserDeleteUsername.getText().toString().trim();
            String password=mEtUserDeletePassword.getText().toString().trim();
            if(!TextUtils.isEmpty( username )&&!TextUtils.isEmpty( password )){
                ArrayList<User> data=mDBOpenHelper.getAllData();
                boolean flag=false;
                for(int i=0;i<data.size();i++){
                    User user=data.get( i );
                    if(username.equals( user.getName() )&&password.equals( user.getPassword() )){
                        flag=true;
                        break;
                    }
                }
                if(flag){
                    mDBOpenHelper.delete( username);
                    startActivity( new Intent( this,MainActivity.class ) );
                    finish();
                    Toast.makeText( this,"已删除子用户",Toast.LENGTH_SHORT ).show();
                }else{
                    Toast.makeText( this,"用户名或密码不正确，请重新输入",Toast.LENGTH_SHORT ).show();
                }
            }else{
                Toast.makeText( this,"请输入你的用户名或密码",Toast.LENGTH_SHORT ).show();
            }
        }
    }
}
