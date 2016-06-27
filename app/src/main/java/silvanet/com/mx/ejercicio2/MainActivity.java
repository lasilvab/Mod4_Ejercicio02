package silvanet.com.mx.ejercicio2;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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
        final String password = mPassword.getText().toString();
        loading.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loading.setVisibility(View.GONE);

                // Usuario y Password no sean vacios
                if (!TextUtils.isEmpty(user) && !TextUtils.isEmpty(password)) {
                    List<ModelUser> modelUserList = userDataSource.getUser(user, password);
                    if (!modelUserList.isEmpty()) {
                        date = new SimpleDateFormat("dd-MM-yyyy hh:mm").format(new Date());
                        if (chkRememberMe.isChecked()) {
                            preferenceUtil.saveUser(new ModelUser(modelUserList.get(0).id, user, password, date, "0", String.valueOf(lastTime)));
                        } else {
                            preferenceUtil.saveUser(new ModelUser(0, "", "", date, "0", String.valueOf(lastTime)));
                        }
                        Toast.makeText(getApplicationContext(), getResources().getText(R.string.msj_login), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), ActivityDetail.class);
                        intent.putExtra("id", modelUserList.get(0).id);
                        intent.putExtra("usuario", modelUserList.get(0).userName);
                        intent.putExtra("pwd", modelUserList.get(0).password);
                        intent.putExtra("ischk", chkRememberMe.isChecked());
                        if (!TextUtils.isEmpty(shared_date)) {
                            intent.putExtra("date", shared_date);
                        } else {
                            intent.putExtra("date", date);
                        }
                        intent.putExtra("count_val", lastTime);
                        intent.putExtra("new_date", date);
                        startActivity(intent);
                        startService(new Intent(getApplicationContext(), ServiceTimer.class));
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), getResources().getText(R.string.msj_error_user), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), getResources().getText(R.string.msj_error_login), Toast.LENGTH_LONG).show();
                }
            }
        }, 1000 * 3);

    }
}