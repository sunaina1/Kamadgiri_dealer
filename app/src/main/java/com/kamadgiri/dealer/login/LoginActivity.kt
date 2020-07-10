package com.kamadgiri.dealer.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.kamadgiri.dealer.Config
import com.kamadgiri.dealer.R
import com.kamadgiri.dealer.dashboard.DashboardActivity
import com.kamadgiri.dealer.model.login.LoginRequestModel
import com.kamadgiri.dealer.model.login.LoginResponseModel
import com.kamadgiri.dealer.networkReq.ApiInterface
import com.kamadgiri.dealer.networkReq.RetrofitInstanceNative
import com.kamadgiri.dealer.register.RegisterActivity
import com.kamadgiri.dealer.utils.AppConstant
import com.kamadgiri.dealer.utils.ProjectPreference
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.security.AccessController
import kotlin.math.log


class LoginActivity : AppCompatActivity() {
    lateinit var loginButton: Button
    lateinit var emailTV: TextView
    lateinit var passwordTV: TextView
    lateinit var activityTitleTV: TextView
    lateinit var registerTV: TextView
    lateinit var forgotPasswordTV: TextView
    lateinit var emailVerifiedTV: TextView
    lateinit var backIV: ImageView
    lateinit var loginActivity: LoginActivity
    lateinit var context: Context
    lateinit var loginRequestModel: LoginRequestModel
    lateinit var loginResponseModel: LoginResponseModel
    lateinit var config: Config
    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_layout)
        setupUI()
    }

    private fun setupUI(){
        loginActivity = this
        context = this
        loginRequestModel = LoginRequestModel()
        config = Config()
        progressBar = findViewById(R.id.progressBar)
        passwordTV = findViewById(R.id.passwordTV)
        emailTV = findViewById(R.id.emailTV)
        /*activityTitleTV = findViewById(R.id.activityTitleTV)
        activityTitleTV.setText("Login")
        backIV = findViewById(R.id.backIV)
        backIV.visibility = View.GONE
        backIV.setOnClickListener(View.OnClickListener {
            finish()
        })*/
        registerTV = findViewById(R.id.registerTV)


        registerTV.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, RegisterActivity::class.java)
            context.startActivity(intent)
        })


        loginButton = findViewById(R.id.loginButton)
        loginButton.setOnClickListener(View.OnClickListener {

            if(emailTV.text.toString()!= "") {
                if (!isValidEmail(emailTV.text.toString())) {
                    config.customAlertOk(
                        AppConstant.INFO,
                        "Please enter valid email address",
                        AppConstant.OK,
                        context
                    )
                    return@OnClickListener
                }
            }else{
                config.customAlertOk(
                    AppConstant.INFO,
                    "Please enter email address",
                    AppConstant.OK,
                    context
                )
                return@OnClickListener
            }

            if(passwordTV.text.toString() == ""){
                config.customAlertOk(
                    AppConstant.INFO,
                    "Please enter password",
                    AppConstant.OK,
                    context
                )
                return@OnClickListener
            }
          /*  val intent = Intent(context, DashboardActivity::class.java)
            context.startActivity(intent)*/
            loginRequestModel.email = emailTV.text.toString()
            loginRequestModel.password = passwordTV.text.toString()


            if(config.isNetworkAvailable(context)){
                getLogin()

            }else{
                config.customAlertOk(
                    AppConstant.INFO,
                    "Internet connection is not available.Please try again.",
                    AppConstant.OK,
                    context
                )
                return@OnClickListener
            }
        })


    }

    fun isValidEmail(target: CharSequence?): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

    fun getLogin() {
        config.showProgress(progressBar,loginActivity)
        var apiInterface= RetrofitInstanceNative().retrofitInstance.create(ApiInterface::class.java)

        val call: Call<LoginResponseModel> =
            apiInterface.getLogin(loginRequestModel)

        call.enqueue(object : Callback<LoginResponseModel?> {
            override fun onResponse(
                call: Call<LoginResponseModel?>,
                response: Response<LoginResponseModel?>
            ) {
                if (response.isSuccessful()) {
                      if (response.body()!!.statusCode == AppConstant.SUCCESS_CODE) {
                          Log.d(
                              "request_res",
                              "after otp " + Gson().toJson(response.body())
                          )
                          config.hideProgress(progressBar, loginActivity)
                          ProjectPreference.saveSharedPreferenceData(AppConstant.TOKEN,
                              response.body()!!.token, context);
                          ProjectPreference.saveSharedPreferenceData(AppConstant.LOGIN_RESPONSE_CONTENT,
                              response.body()!!.user._id, context);
                          val intent = Intent(loginActivity, DashboardActivity::class.java)
                          intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                          intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                          startActivity(intent)
                          finish()

                      }else{
                          config.hideProgress(progressBar, loginActivity)

                          config.customAlertOk(
                              AppConstant.INFO,
                              response.body()!!.msg,
                              AppConstant.OK,
                              context
                          )
                      }

                } else {
                    config.hideProgress(progressBar, loginActivity)

                    config.customAlertOk(
                        AppConstant.INFO,
                        response.body()!!.msg,
                        AppConstant.OK,
                        context
                    )
                }
            }

            override fun onFailure(
                call: Call<LoginResponseModel?>,
                t: Throwable
            ) {
                config.hideProgress(progressBar, loginActivity)
                if (AccessController.getContext() != null) {
                    config.customAlertOk(
                        AppConstant.INFO,
                        "Internal server error",
                        AppConstant.OK,
                        context
                    )
                }
            }
        })
    }
}
