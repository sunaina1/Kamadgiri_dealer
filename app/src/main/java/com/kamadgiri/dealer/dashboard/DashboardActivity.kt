package com.kamadgiri.dealer.dashboard

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.kamadgiri.dealer.R

class DashboardActivity : AppCompatActivity() {
    lateinit var dashboardActivity: DashboardActivity
    lateinit var context: Context
    lateinit var activityTitleTV: TextView
    lateinit var backIV: ImageView
    lateinit var transactionRL: RelativeLayout
    lateinit var volumeRL: RelativeLayout
    lateinit var profileRL: RelativeLayout
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
        volumeRL = findViewById(R.id.volumeRL)
        volumeRL.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, TransactionVolumeActivity::class.java)
            context.startActivity(intent)
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}
