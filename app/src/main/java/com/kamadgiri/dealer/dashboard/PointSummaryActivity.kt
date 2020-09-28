package com.kamadgiri.dealer.dashboard

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.kamadgiri.dealer.Config
import com.kamadgiri.dealer.R
import com.kamadgiri.dealer.adapter.PointSummaryAdapter
import com.kamadgiri.dealer.model.pointSummary.PointItemModel
import com.kamadgiri.dealer.model.pointSummary.PointSummaryResponseModel
import com.kamadgiri.dealer.networkReq.ApiInterface
import com.kamadgiri.dealer.networkReq.RetrofitInstanceNative
import com.kamadgiri.dealer.utils.AppConstant
import com.kamadgiri.dealer.utils.ProjectPreference
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.security.AccessController

class PointSummaryActivity : AppCompatActivity() {
    lateinit var pointSummaryActivity: PointSummaryActivity
    lateinit var context: Context
    lateinit var activityTitleTV: TextView
    lateinit var noTrasactionDataTV: TextView
    lateinit var recyclerRL: RelativeLayout
    lateinit var backIV: ImageView
    lateinit var pointsRV: RecyclerView
    lateinit var progressBar: ProgressBar
    lateinit var config: Config
    lateinit var pointSummaryResponseModel: PointSummaryResponseModel
    lateinit var pointSummaryAdapter: PointSummaryAdapter
    lateinit var userId: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_point_summary)
        setupUI()
    }

    private fun setupUI() {
        pointSummaryActivity = this
        context = this
        config = Config()
        userId = ProjectPreference.getSharedPreferenceData(AppConstant.LOGIN_RESPONSE_CONTENT, context)

        progressBar = findViewById(R.id.progressBar)
        noTrasactionDataTV = findViewById(R.id.noTrasactionDataTV)
        noTrasactionDataTV.visibility = View.GONE
        recyclerRL = findViewById(R.id.recyclerRL)
        recyclerRL.visibility = View.GONE

       // transactionVolumeRequestModel = TransactionVolumeRequestModel()
        pointsRV = findViewById(R.id.pointsRV)
        activityTitleTV = findViewById(R.id.activityTitleTV)
        activityTitleTV.setText("Point Summary")
        backIV = findViewById(R.id.backIV)
        backIV.setOnClickListener(View.OnClickListener {
            finish()
        })

        if (config.isNetworkAvailable(context)) {
           // transactionVolumeRequestModel.userId = userId
            getVolumeDetails()
        } else {
            config.customAlertOk(
                AppConstant.INFO,
                "Internet connection is not available.Please try again.",
                AppConstant.OK,
                context
            )
            return
        }

    }

    fun getVolumeDetails() {
        config.showProgress(progressBar, pointSummaryActivity)
        var apiInterface =
            RetrofitInstanceNative().retrofitInstance.create(ApiInterface::class.java)
        var url  = AppConstant.POINT_SUMMARY + userId
        val call: Call<PointSummaryResponseModel> =
            apiInterface.getPointSummary(url)

        call.enqueue(object : Callback<PointSummaryResponseModel?> {
            override fun onResponse(
                call: Call<PointSummaryResponseModel?>,
                response: Response<PointSummaryResponseModel?>
            ) {
                if (response.isSuccessful()) {
                    if (response.body()!!.statusCode == AppConstant.SUCCESS_CODE) {
                        Log.d(
                            "request_res",
                            "update profile response " + Gson().toJson(response.body())
                        )
                        config.hideProgress(progressBar, pointSummaryActivity)
                        if(response.body()!!.list != null && response.body()!!.list.size > 0) {
                            noTrasactionDataTV.visibility = View.GONE
                            recyclerRL.visibility = View.VISIBLE
                            refreshRecordDetails(response.body()!!.list)
                        }

                        if(response.body()!!.list == null || response.body()!!.list.size <= 0) {
                            noTrasactionDataTV.visibility = View.VISIBLE
                            recyclerRL.visibility = View.GONE
                        }
                    } else {
                        config.hideProgress(progressBar, pointSummaryActivity)

                        config.customAlertOk(
                            AppConstant.INFO,
                            response.body()!!.msg,
                            AppConstant.OK,
                            context
                        )
                    }

                } else {
                    config.hideProgress(progressBar, pointSummaryActivity)

                    config.customAlertOk(
                        AppConstant.INFO,
                        "Internal server error",
                        AppConstant.OK,
                        context
                    )
                }
            }

            override fun onFailure(
                call: Call<PointSummaryResponseModel?>,
                t: Throwable
            ) {
                config.hideProgress(progressBar, pointSummaryActivity)
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

    private fun refreshRecordDetails(resultList: List<PointItemModel>) {
        pointSummaryAdapter = PointSummaryAdapter(resultList, context)
        pointsRV.adapter = pointSummaryAdapter
        pointsRV.layoutManager = LinearLayoutManager(this)
        pointSummaryAdapter.notifyDataSetChanged()
    }
}
