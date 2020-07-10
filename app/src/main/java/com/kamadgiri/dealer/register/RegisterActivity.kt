package com.kamadgiri.dealer.register

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.*
import com.google.gson.Gson
import com.kamadgiri.dealer.Config
import com.kamadgiri.dealer.R
import com.kamadgiri.dealer.dashboard.DashboardActivity
import com.kamadgiri.dealer.login.LoginActivity
import com.kamadgiri.dealer.model.login.LoginResponseModel
import com.kamadgiri.dealer.model.register.RegisterRequestModel
import com.kamadgiri.dealer.model.register.RegisterResponseModel
import com.kamadgiri.dealer.networkReq.ApiInterface
import com.kamadgiri.dealer.networkReq.RetrofitInstanceNative
import com.kamadgiri.dealer.utils.AppConstant
import com.kamadgiri.dealer.utils.ProjectPreference
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.security.AccessController

class RegisterActivity : AppCompatActivity() {
    lateinit var registerActivity: RegisterActivity
    lateinit var context: Context
    lateinit var activityTitleTV: TextView
    lateinit var backIV: ImageView
    lateinit var nameET: EditText
    lateinit var emailET: EditText
    lateinit var mobileET: EditText
    lateinit var stateET: EditText
    lateinit var districtET: EditText
    lateinit var cityET: EditText
    lateinit var villageET: EditText
    lateinit var vscET: EditText
    lateinit var submitBTN: Button
    lateinit var progressBar: ProgressBar
    lateinit var config: Config
    lateinit var registerRequestModel: RegisterRequestModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setupUI()
    }

    private fun setupUI() {
        registerActivity = this
        context = this
        config = Config()
registerRequestModel = RegisterRequestModel()
        /*activityTitleTV = findViewById(R.id.activityTitleTV)
        activityTitleTV.setText("Signup")
        backIV = findViewById(R.id.backIV)
        backIV.setOnClickListener(View.OnClickListener {
            finish()
        })*/

        progressBar = findViewById(R.id.progressBar)
        nameET = findViewById(R.id.nameET)
        emailET = findViewById(R.id.emailET)
        mobileET = findViewById(R.id.mobileET)
        stateET = findViewById(R.id.stateET)
        districtET = findViewById(R.id.districtET)
        cityET = findViewById(R.id.cityET)
        villageET = findViewById(R.id.villageET)
        vscET = findViewById(R.id.vscET)
        submitBTN = findViewById(R.id.submitBTN)
        submitBTN.setOnClickListener(View.OnClickListener {
            if(nameET.text.toString() == ""){
                config.customAlertOk(
                    AppConstant.INFO,
                    "Please enter name",
                    AppConstant.OK,
                    context
                )
                return@OnClickListener
            }
            if(emailET.text.toString()!= "") {
                if (!isValidEmail(emailET.text.toString())) {
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

            if(mobileET.text.toString() == ""){
                config.customAlertOk(
                    AppConstant.INFO,
                    "Please enter mobile number",
                    AppConstant.OK,
                    context
                )
                return@OnClickListener
            }
            if(stateET.text.toString() == ""){
                config.customAlertOk(
                    AppConstant.INFO,
                    "Please enter state name",
                    AppConstant.OK,
                    context
                )
                return@OnClickListener
            }
            /*if(districtET.text.toString() == ""){
                config.customAlertOk(
                    AppConstant.INFO,
                    "Please enter district name",
                    AppConstant.OK,
                    context
                )
                return@OnClickListener
            }*/

            if(cityET.text.toString() == ""){
                config.customAlertOk(
                    AppConstant.INFO,
                    "Please enter city",
                    AppConstant.OK,
                    context
                )
                return@OnClickListener
            }
            if(villageET.text.toString() == ""){
                config.customAlertOk(
                    AppConstant.INFO,
                    "Please enter village",
                    AppConstant.OK,
                    context
                )
                return@OnClickListener
            }

            if(vscET.text.toString() == ""){
                config.customAlertOk(
                    AppConstant.INFO,
                    "Please enter village code",
                    AppConstant.OK,
                    context
                )
                return@OnClickListener
            }

            registerRequestModel.name = nameET.text.toString()
            registerRequestModel.email = emailET.text.toString()
            registerRequestModel.mobileNumber = mobileET.text.toString()
            registerRequestModel.state = stateET.text.toString()
            registerRequestModel.district = districtET.text.toString()
            registerRequestModel.city = cityET.text.toString()
            registerRequestModel.village = villageET.text.toString()
            registerRequestModel.vscCode = vscET.text.toString()
Log.d("request",registerRequestModel.serialize())
            if(config.isNetworkAvailable(context)){
                getRegister()
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

    fun getRegister() {
        config.showProgress(progressBar,registerActivity)
        var apiInterface= RetrofitInstanceNative().retrofitInstance.create(ApiInterface::class.java)

        val call: Call<RegisterResponseModel> =
            apiInterface.getRegisterUser(registerRequestModel)

        call.enqueue(object : Callback<RegisterResponseModel?> {
            override fun onResponse(
                call: Call<RegisterResponseModel?>,
                response: Response<RegisterResponseModel?>
            ) {
                if (response.isSuccessful()) {
                    if (response.body()!!.statusCode == AppConstant.SUCCESS_CODE) {
                        Log.d(
                            "request_res",
                            "after otp " + Gson().toJson(response.body())
                        )
                        config.hideProgress(progressBar, registerActivity)
                        config.customAlertOk(
                            AppConstant.INFO,
                            response.body()!!.msg,
                            AppConstant.OK,
                            context, AppConstant.FINISH_CURRENT
                        )
                    }else{
                        config.hideProgress(progressBar, registerActivity)

                        config.customAlertOk(
                            AppConstant.INFO,
                            response.body()!!.msg,
                            AppConstant.OK,
                            context
                        )
                    }

                } else {
                    config.hideProgress(progressBar, registerActivity)

                    config.customAlertOk(
                        AppConstant.INFO,
                        response.body()!!.msg,
                        AppConstant.OK,
                        context
                    )
                }
            }

            override fun onFailure(
                call: Call<RegisterResponseModel?>,
                t: Throwable
            ) {
                config.hideProgress(progressBar, registerActivity)
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
