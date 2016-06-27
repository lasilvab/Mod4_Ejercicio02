package silvanet.com.mx.ejercicio2;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import silvanet.com.mx.ejercicio2.model.ModelUser;
import silvanet.com.mx.ejercicio2.service.ServiceTimer;
import silvanet.com.mx.ejercicio2.sql.ItemDataSource;
import silvanet.com.mx.ejercicio2.sql.UsersDataSource;
import silvanet.com.mx.ejercicio2.util.PreferenceUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mUser;
    private EditText mPassword;
    private EditText txtUser;
    private EditText txtPassword;
    private View loading;
    private PreferenceUtil preferenceUtil;
    private CheckBox chkRememberMe;
    private UsersDataSource userDataSource;
    private int lastTime=0;
    private String last_Time;
    private String shared_date="";
    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtUser = (EditText) findViewById(R.id.activity_main_user);
        txtPassword = (EditText) findViewById(R.id.activity_main_password);
        mUser= (EditText) findViewById(R.id.activity_main_user);
        mPassword= (EditText) findViewById(R.id.activity_main_password);
        findViewById(R.id.activity_main_login).setOnClickListener(this);
        loading=findViewById(R.id.progress);
        findViewById(R.id.btnRegisterLogin).setOnClickListener(this);
        chkRememberMe = (CheckBox) findViewById(R.id.chkRememberMe);
        userDataSource = new UsersDataSource(getApplicationContext());
        preferenceUtil= new PreferenceUtil(getApplicationContext());
        ModelUser modelUser =  preferenceUtil.getUser();

        //Valida de SharedPreferences
        if(modelUser!=null){
            if(modelUser.userName.trim().length()>0 && modelUser.password.trim().length()>0){
                txtUser.setText(modelUser.userName.toString());
                txtPassword.setText(modelUser.password.toString());
            }
            if(modelUser.time_stamp.trim().length()>0){
                lastTime = Integer.valueOf(modelUser.time_stamp.toString());
            }else {
                last_Time="";
            }
            if(modelUser.last_session.trim().length()>0){
                shared_date=modelUser.last_session.toString();
            }
        }

        /** chkRememberMe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d(ServiceTimer.TAG,"Esto esta Checkeado: "+isChecked);
            }
        });
        ((TextView)findViewById(R.id.txtDate))
                .setText(new SimpleDateFormat("dd-MMM-yy hh:mm").format(new Date()));
        */

    }
    
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.activity_main_login:
                processData();
                break;
            case R.id.btnRegisterLogin:
                launchRegister();
                break;
        }
    }

    private void launchRegister() {
        Intent intent=new Intent(getApplicationContext(),ActivityRegister.class);
        startActivity(intent);
    }

    private void processData() {
        final String user = mUser.getText().toString();
        final String pass = mPassword.getText().toString();
        loading.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loading.setVisibility(View.GONE);
                ModelUser modelUser = preferenceUtil.getUser();
                if(modelUser==null)
                {
                    Toast.makeText(getApplicationContext(),"Register please",Toast.LENGTH_SHORT).show();
                }else if(user.equals(modelUser.userName) && pass.equals(modelUser.password))
                {
                    Toast.makeText(getApplicationContext(),"Login",Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(getApplicationContext(),ActivityDetail.class);
                    intent.putExtra("key_user",user);
                    startActivity(intent);
                    startService(new Intent(getApplicationContext(), ServiceTimer.class));
                }
                else
                    Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        },1000*1);
    }
}