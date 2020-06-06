package com.example.xproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var retrofit = Retrofit.Builder()
                .baseUrl("http://10.10.0.72:8000")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        // baseUrl은 내 local 주소
        var loginService: LoginService = retrofit.create(LoginService::class.java)

        // 로그인하는 경우
        button.setOnClickListener {
            var textId = editTextTextPersonName.text.toString()
            var textPw = editTextTextPassword.text.toString()

            loginService.requestLogin(textId, textPw).enqueue(object : Callback<Login> {
                override fun onFailure(call: Call<Login>, t: Throwable) {
                    Log.e("DEBUG", t.message)
                    var dialog = AlertDialog.Builder(this@MainActivity)
                    dialog.setTitle("실패")
                    dialog.setMessage("통신에 실패했습니다..")
                    dialog.show()
                }
                override fun onResponse(call: Call<Login>, response: Response<Login>) {
                    var login = response.body()
                    Log.d("LOGIN","msg : "+login?.msg)
                    Log.d("LOGIN","code : "+login?.code)
                    var dialog = AlertDialog.Builder(this@MainActivity)
                    dialog.setTitle("알람!")
                    dialog.setMessage("id = "+textId +" pw = "+textPw)
                    dialog.setMessage("code = " + login?.code + " msg = " + login?.msg)
                    dialog.show()
                }
            })
        }
        
        //회원가입 화면으로 가는 경우
        signup_button.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)

            startActivity(intent)
            finish()
        }
    }
}