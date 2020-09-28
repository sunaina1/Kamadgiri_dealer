package com.kamadgiri.dealer.dashboard

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.kamadgiri.dealer.R
import com.kamadgiri.dealer.utils.AppConstant
import com.kamadgiri.dealer.utils.ProjectPreference

class DashboardActivity : AppCompatActivity() {
    lateinit var dashboardActivity: DashboardActivity
    lateinit var context: Context
    private var backPressedTime: Long = 0
    lateinit var activityTitleTV: TextView
    lateinit var backIV: ImageView
    lateinit var transactionRL: RelativeLayout
    lateinit var pointSummaryRL: RelativeLayout
    lateinit var volumeRL: RelativeLayout
    lateinit var profileRL: RelativeLayout
    lateinit var logoutButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        setupUI()
    }
    private fun setupUI() {
        dashboardActivity = this
        context = this
        activityTitleTV = findViewById(R.id.activityTitleTV)
        activityTitleTV.setText("Home")
        backIV = findViewById(R.id.backIV)
        backIV.setOnClickListener(View.OnClickListener {
            finish()
        })
        profileRL = findViewById(R.id.profileRL)
        profileRL.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, ProfileActivity::class.java)
            context.startActivity(intent)
        })
        transactionRL = findViewById(R.id.transactionRL)
        transactionRL.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, TransactionActivity::class.java)
            context.startActivity(intent)
        })
        pointSummaryRL = findViewById(R.id.pointSummaryRL)
        pointSummaryRL.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, PointSummaryActivity::class.java)
            context.startActivity(intent)
        })
        volumeRL = findViewById(R.id.volumeRL)
        volumeRL.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, TransactionVolumeActivity::class.java)
            context.startActivity(intent)
        })
        logoutButton = findViewById(R.id.logoutButton)
        logoutButton.setOnClickListener(View.OnClickListener {
            customAlertOkLogout(AppConstant.INFO,"Do you want to logout?",context)
        })
    }
    private fun customAlertOkLogout(
        title: String?,
        message: String?,
        context: Context?
    ) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setNegativeButton(
            "No",
            DialogInterface.OnClickListener { dialogInterface, i ->
                dialogInterface.dismiss()
            })
        builder.setPositiveButton(
            "Yes",
            DialogInterface.OnClickListener { dialogInterface, i ->
                dialogInterface.dismiss()
                ProjectPreference.removeSharedPreferenceData(AppConstant.LOGIN_RESPONSE_CONTENT,context)
                ProjectPreference.removeSharedPreferenceData(AppConstant.TOKEN,context)
                finish()

            })
        builder.show()
    }
    override fun onBackPressed() {
       // super.onBackPressed()
        val t = System.currentTimeMillis()
        if (t - backPressedTime > 2000) {
            backPressedTime = t
            Toast.makeText(
                this, "Press back again to exit",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            Log.d("onback","backpressed called")
            var intent = Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();

        }
    }
}
