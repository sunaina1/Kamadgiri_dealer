package com.kamadgiri.dealer

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.kamadgiri.dealer.dashboard.DashboardActivity
import com.kamadgiri.dealer.login.LoginActivity
import com.kamadgiri.dealer.utils.AppConstant
import com.kamadgiri.dealer.utils.ProjectPreference

class SplashActivity : AppCompatActivity() {
   // lateinit var loginResultModel:LoginResultModel
    lateinit var context:Context
    lateinit var progressBar:ProgressBar
    lateinit var loginResponse : String
    lateinit var config: Config
    lateinit var splashActivity: SplashActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context=this
        splashActivity= this
        config = Config()
        setContentView(R.layout.activity_splash)
        progressBar = findViewById(R.id.progressBar)
       loginResponse= ProjectPreference.getSharedPreferenceData(
            AppConstant.LOGIN_RESPONSE_CONTENT,
            context
        );
        config.showProgress(progressBar,splashActivity)

        Handler().postDelayed({
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)

            if(loginResponse!=""){
                config.hideProgress(progressBar,splashActivity)
                //finish()
                val intent = Intent(context, DashboardActivity::class.java)
                context.startActivity(intent)
                finish()
            }else{
                config.hideProgress(progressBar,splashActivity)
                //finish()
                val intent = Intent(context, LoginActivity::class.java)
                startActivity(intent)
                finish()

            }
        }, 2000)
    }


}
