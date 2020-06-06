package com.example.xproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_signup.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        var retrofit = Retrofit.Builder()
            .baseUrl("http://10.10.0.72:8000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var signupservice: SignupService = retrofit.create(SignupService::class.java)

        register_addface.setOnClickListener {
            var textId = register_id.text.toString()
            var textPw = register_pass.text.toString()
            var textName = register_name.text.toString()
            signupservice.requestSignup(textId, textPw, textName).enqueue(object : Callback<Signup> {
                override fun onFailure(call: Call<Signup>, t: Throwable) {
                    Log.e("DEBUG", t.message)
                    var dialog = AlertDialog.Builder(this@SignupActivity)
                    dialog.setTitle("실패")
                    dialog.setMessage("통신에 실패했습니다..")
                    dialog.show()
                }
                override fun onResponse(call: Call<Signup>, response: Response<Signup>) {
                    var login = response.body()
                    Log.d("LOGIN","msg : "+login?.msg)
                    Log.d("LOGIN","code : "+login?.code)
                    var dialog = AlertDialog.Builder(this@SignupActivity)
                    dialog.setTitle("알람!")
                    dialog.setMessage("id = "+textId +" pw = "+textPw+" name = "+textName)
                    dialog.setMessage("code = " + login?.code + " msg = " + login?.msg)
                    dialog.show()
                }
            })

                val intent = Intent(this, AddFaceActivity::class.java)
                startActivity(intent)
            finish()
        }
    }
}