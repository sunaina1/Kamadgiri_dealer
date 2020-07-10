package com.kamadgiri.dealer.dashboard

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.kamadgiri.dealer.Config
import com.kamadgiri.dealer.R
import com.kamadgiri.dealer.adapter.VolumeDetailsAdapter
import com.kamadgiri.dealer.model.login.UserItemModel
import com.kamadgiri.dealer.model.profile.UpdateProfileResponseModel
import com.kamadgiri.dealer.model.transactionVolume.TransactionItemModel
import com.kamadgiri.dealer.model.transactionVolume.TransactionVolumeRequestModel
import com.kamadgiri.dealer.model.transactionVolume.TransactionVolumeResponseModel
import com.kamadgiri.dealer.networkReq.ApiInterface
import com.kamadgiri.dealer.networkReq.RetrofitInstanceNative
import com.kamadgiri.dealer.utils.AppConstant
import com.kamadgiri.dealer.utils.ProjectPreference
import me.dm7.barcodescanner.zxing.ZXingScannerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.security.AccessController

class TransactionVolumeActivity : AppCompatActivity() {
    lateinit var transactionVolumeActivity: TransactionVolumeActivity
    lateinit var context: Context
    lateinit var activityTitleTV: TextView
    lateinit var noTrasactionDataTV: TextView
    lateinit var recyclerRL: RelativeLayout
    lateinit var backIV: ImageView
    lateinit var volumeRV: RecyclerView
    lateinit var progressBar: ProgressBar
    lateinit var config: Config
    lateinit var transactionVolumeRequestModel: TransactionVolumeRequestModel
    lateinit var volumeDetailsAdapter: VolumeDetailsAdapter
    lateinit var userId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction_volume)
        setupUI()
    }

    private fun setupUI() {
        transactionVolumeActivity = this
        context = this
        config = Config()
        userId = ProjectPreference.getSharedPreferenceData(AppConstant.LOGIN_RESPONSE_CONTENT, context)

        progressBar = findViewById(R.id.progressBar)
        noTrasactionDataTV = findViewById(R.id.noTrasactionDataTV)
        noTrasactionDataTV.visibility = View.GONE
            recyclerRL = findViewById(R.id.recyclerRL)
        recyclerRL.visibility = View.GONE

        transactionVolumeRequestModel = TransactionVolumeRequestModel()
        volumeRV = findViewById(R.id.volumeRV)
        activityTitleTV = findViewById(R.id.activityTitleTV)
        activityTitleTV.setText("Transaction Volume")
        backIV = findViewById(R.id.backIV)
        backIV.setOnClickListener(View.OnClickListener {
            finish()
        })

        if (config.isNetworkAvailable(context)) {
            transactionVolumeRequestModel.userId = userId
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
        config.showProgress(progressBar, transactionVolumeActivity)
        var apiInterface =
            RetrofitInstanceNative().retrofitInstance.create(ApiInterface::class.java)

        val call: Call<TransactionVolumeResponseModel> =
            apiInterface.getVolumeDetails(transactionVolumeRequestModel)

        call.enqueue(object : Callback<TransactionVolumeResponseModel?> {
            override fun onResponse(
                call: Call<TransactionVolumeResponseModel?>,
                response: Response<TransactionVolumeResponseModel?>
            ) {
                if (response.isSuccessful()) {
                    if (response.body()!!.statusCode == AppConstant.SUCCESS_CODE) {
                        Log.d(
                            "request_res",
                            "update profile response " + Gson().toJson(response.body())
                        )
                        config.hideProgress(progressBar, transactionVolumeActivity)
                        if(response.body()!!.transactions != null && response.body()!!.transactions.size > 0) {
                            noTrasactionDataTV.visibility = View.GONE
                            recyclerRL.visibility = View.VISIBLE
                            refreshRecordDetails(response.body()!!.transactions)
                        }

                        if(response.body()!!.transactions == null || response.body()!!.transactions.size <= 0) {
                            noTrasactionDataTV.visibility = View.VISIBLE
                            recyclerRL.visibility = View.GONE
                        }
                    } else {
                        config.hideProgress(progressBar, transactionVolumeActivity)

                        config.customAlertOk(
                            AppConstant.INFO,
                            response.body()!!.msg,
                            AppConstant.OK,
                            context
                        )
                    }

                } else {
                    config.hideProgress(progressBar, transactionVolumeActivity)

                    config.customAlertOk(
                        AppConstant.INFO,
                        response.body()!!.msg,
                        AppConstant.OK,
                        context
                    )
                }
            }

            override fun onFailure(
                call: Call<TransactionVolumeResponseModel?>,
                t: Throwable
            ) {
                config.hideProgress(progressBar, transactionVolumeActivity)
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

    private fun refreshRecordDetails(resultList: List<TransactionItemModel>) {
        volumeDetailsAdapter = VolumeDetailsAdapter(resultList, context)
        volumeRV.adapter = volumeDetailsAdapter
        volumeRV.layoutManager = LinearLayoutManager(this)
        volumeDetailsAdapter.notifyDataSetChanged()
    }
}
