package com.lancer.easyschedule;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;


import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.Response;



public class LoginActivity extends AppCompatActivity {

    private HttpConnection mConnection = HttpConnection.getInstance();

    private EditText etUserName;
    private EditText etUserPassword;
    //private String userName;
    //private String userPassword;
    private List<Cookie> cookieList;
    private String type = "STU";
    private String state1 = "dDw1OTgzNjYzMjM7dDw7bDxpPDE+O2k8Mz47aTw1Pjs+O2w8dDxwPGw8VGV4dDs+O2w86YeN5bqG5aSn5a2mOz4+Ozs+O3Q8cDxsPFRleHQ7PjtsPFw8c2NyaXB0IHR5cGU9InRleHQvamF2YXNjcmlwdCJcPgpcPCEtLQpmdW5jdGlvbiBvcGVuV2luTG9nKHRoZVVSTCx3LGgpewp2YXIgVGZvcm0scmV0U3RyXDsKZXZhbCgiVGZvcm09J3dpZHRoPSIrdysiLGhlaWdodD0iK2grIixzY3JvbGxiYXJzPW5vLHJlc2l6YWJsZT1ubyciKVw7CnBvcD13aW5kb3cub3Blbih0aGVVUkwsJ3dpbktQVCcsVGZvcm0pXDsgLy9wb3AubW92ZVRvKDAsNzUpXDsKZXZhbCgiVGZvcm09J2RpYWxvZ1dpZHRoOiIrdysicHhcO2RpYWxvZ0hlaWdodDoiK2grInB4XDtzdGF0dXM6bm9cO3Njcm9sbGJhcnM9bm9cO2hlbHA6bm8nIilcOwppZih0eXBlb2YocmV0U3RyKSE9J3VuZGVmaW5lZCcpIGFsZXJ0KHJldFN0cilcOwp9CmZ1bmN0aW9uIHNob3dMYXkoZGl2SWQpewp2YXIgb2JqRGl2ID0gZXZhbChkaXZJZClcOwppZiAob2JqRGl2LnN0eWxlLmRpc3BsYXk9PSJub25lIikKe29iakRpdi5zdHlsZS5kaXNwbGF5PSIiXDt9CmVsc2V7b2JqRGl2LnN0eWxlLmRpc3BsYXk9Im5vbmUiXDt9Cn0KZnVuY3Rpb24gc2VsVHllTmFtZSgpewogIGRvY3VtZW50LmFsbC50eXBlTmFtZS52YWx1ZT1kb2N1bWVudC5hbGwuU2VsX1R5cGUub3B0aW9uc1tkb2N1bWVudC5hbGwuU2VsX1R5cGUuc2VsZWN0ZWRJbmRleF0udGV4dFw7Cn0KZnVuY3Rpb24gd2luZG93Lm9ubG9hZCgpewoJdmFyIHNQQz13aW5kb3cubmF2aWdhdG9yLnVzZXJBZ2VudCt3aW5kb3cubmF2aWdhdG9yLmNwdUNsYXNzK3dpbmRvdy5uYXZpZ2F0b3IuYXBwTWlub3JWZXJzaW9uKycgU046TlVMTCdcOwp0cnl7ZG9jdW1lbnQuYWxsLnBjSW5mby52YWx1ZT1zUENcO31jYXRjaChlcnIpe30KdHJ5e2RvY3VtZW50LmFsbC50eHRfZHNkc2RzZGpramtqYy5mb2N1cygpXDt9Y2F0Y2goZXJyKXt9CnRyeXtkb2N1bWVudC5hbGwudHlwZU5hbWUudmFsdWU9ZG9jdW1lbnQuYWxsLlNlbF9UeXBlLm9wdGlvbnNbZG9jdW1lbnQuYWxsLlNlbF9UeXBlLnNlbGVjdGVkSW5kZXhdLnRleHRcO31jYXRjaChlcnIpe30KfQpmdW5jdGlvbiBvcGVuV2luRGlhbG9nKHVybCxzY3IsdyxoKQp7CnZhciBUZm9ybVw7CmV2YWwoIlRmb3JtPSdkaWFsb2dXaWR0aDoiK3crInB4XDtkaWFsb2dIZWlnaHQ6IitoKyJweFw7c3RhdHVzOiIrc2NyKyJcO3Njcm9sbGJhcnM9bm9cO2hlbHA6bm8nIilcOwp3aW5kb3cuc2hvd01vZGFsRGlhbG9nKHVybCwxLFRmb3JtKVw7Cn0KZnVuY3Rpb24gb3Blbldpbih0aGVVUkwpewp2YXIgVGZvcm0sdyxoXDsKdHJ5ewoJdz13aW5kb3cuc2NyZWVuLndpZHRoLTEwXDsKfWNhdGNoKGUpe30KdHJ5ewpoPXdpbmRvdy5zY3JlZW4uaGVpZ2h0LTMwXDsKfWNhdGNoKGUpe30KdHJ5e2V2YWwoIlRmb3JtPSd3aWR0aD0iK3crIixoZWlnaHQ9IitoKyIsc2Nyb2xsYmFycz1ubyxzdGF0dXM9bm8scmVzaXphYmxlPXllcyciKVw7CnBvcD1wYXJlbnQud2luZG93Lm9wZW4odGhlVVJMLCcnLFRmb3JtKVw7CnBvcC5tb3ZlVG8oMCwwKVw7CnBhcmVudC5vcGVuZXI9bnVsbFw7CnBhcmVudC5jbG9zZSgpXDt9Y2F0Y2goZSl7fQp9CmZ1bmN0aW9uIGNoYW5nZVZhbGlkYXRlQ29kZShPYmopewp2YXIgZHQgPSBuZXcgRGF0ZSgpXDsKT2JqLnNyYz0iLi4vc3lzL1ZhbGlkYXRlQ29kZS5hc3B4P3Q9IitkdC5nZXRNaWxsaXNlY29uZHMoKVw7Cn0KXFwtLVw+Clw8L3NjcmlwdFw+Oz4+Ozs+O3Q8O2w8aTwxPjs+O2w8dDw7bDxpPDA+Oz47bDx0PHA8bDxUZXh0Oz47bDxcPG9wdGlvbiB2YWx1ZT0nU1RVJyB1c3JJRD0n5a2m5Y+3J1w+5a2m55SfXDwvb3B0aW9uXD4KXDxvcHRpb24gdmFsdWU9J1RFQScgdXNySUQ9J+W4kOWPtydcPuaVmeW4iFw8L29wdGlvblw+Clw8b3B0aW9uIHZhbHVlPSdTWVMnIHVzcklEPSfluJDlj7cnXD7nrqHnkIbkurrlkZhcPC9vcHRpb25cPgpcPG9wdGlvbiB2YWx1ZT0nQURNJyB1c3JJRD0n5biQ5Y+3J1w+6Zeo5oi357u05oqk5ZGYXDwvb3B0aW9uXD4KOz4+Ozs+Oz4+Oz4+Oz4+Oz7p2B9lkx+Yq/jf62i+iqicmZx/xg==";
    private String state2 = "CAA0A5A7";
    private String course;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init(){
        EditText userName = (EditText) findViewById(R.id.et_userName);
        EditText password = (EditText) findViewById(R.id.et_password);
        etUserName = (EditText) findViewById(R.id.et_userName);
        etUserPassword = (EditText) findViewById(R.id.et_password);
        getCookies();
    }

    private void getCookies(){
        final Request request = new Request.Builder()
                .url("http://202.202.1.176:8080/home.aspx")
                .build();
        Callback callback = new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                Log.i("call_fail", e.toString());
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {

                cookieList= mConnection.getCookies(request.url());
            }
        };
        mConnection.connectUrl(request,callback);
    }

    private void loginService(){
        String userName = etUserName.getText().toString();
        String userPassword = etUserPassword.getText().toString();
        String pwd = MD5.encrypt(userName+MD5.encrypt(userPassword).substring(0,30).toUpperCase()+10611).substring(0,30).toUpperCase();
        FormBody formBody = new FormBody.Builder()
                .add("__VIEWSTATE",state1)
                .add("__VIEWSTATEGENERATOR",state2)
                .add("aerererdsdxcxdfgfg","")
                .add("efdfdfuuyyuuckjg",pwd)
                .add("pcInfo","")
                .add("Sel_Type",type)
                .add("txt_dsdfdfgfouyy","")
                .add("txt_dsdsdsdjkjkjc",userName)
                .add("txt_ysdsdsdskgf","")
                .add("typeName","")
                .build();
        //创建一个request
        final Request request = new Request.Builder()
                .url("http://202.202.1.176:8080/_data/index_login.aspx")
                .post(formBody)
                .build();
        //创建一个callback
        Callback callback = new Callback(){
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                Log.i("call_fail", e.toString());
            }
            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    String s = response.body().string();
                    Log.d("Login",s);
                    if(s.contains("MAINFRM")){
                        mConnection.isLogin = true;
                        Log.d("Login","登录成功 ");
                    }
                    else {
                        mConnection.isLogin = false;
                        Log.d("Login","登录失败 ");
                    }
                    response.close();;
                }
            }
        };
        //使用异步加载连接url
        mConnection.connectUrl(request,callback);
    }

    private void courseService(){
        FormBody formBody = new FormBody.Builder()
                .add("px","1")
                .add("rad","on")
                .add("Sel_XNXQ","20171")
                .build();
        //创建一个request
        final Request request = new Request.Builder()
                .header("Referer","http://202.202.1.176:8080/znpk/Pri_StuSel.aspx")
                .url("http://202.202.1.176:8080/znpk/Pri_StuSel_rpt.aspx")
                .post(formBody)
                .build();
        //创建一个callback
        Callback callback = new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                Log.i("call_fail", e.toString());
            }
            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    course = response.body().string();
                    //从3020开始为课程信息
                    course = course.substring(3020);
                    Log.d("Course","导入课程 ");
                    Log.d("Course",course);
                    response.close();
                }
            }
        };
        //使用异步加载连接url
        mConnection.connectUrl(request,callback);
    }

    public void login(View view){
        loginService();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            return;
        }

        if(mConnection.isLogin){
            //等待提示
            Toast.makeText(this, "加载课程成功", Toast.LENGTH_SHORT).show();

            courseService();

            Intent i = new Intent(LoginActivity.this , MainActivity.class);
            i.putExtra("course",course);
            startActivity(i);
            finish();
        }
        else{
            Toast.makeText(this, "登录失败，账号或密码错误", Toast.LENGTH_SHORT).show();
        }
    }
}

