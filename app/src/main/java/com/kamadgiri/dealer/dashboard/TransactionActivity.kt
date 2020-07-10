package com.kamadgiri.dealer.dashboard

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import com.google.zxing.Result
import com.kamadgiri.dealer.Config
import com.kamadgiri.dealer.R
import com.kamadgiri.dealer.model.addTransaction.AddTransactionRequestModel
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

class TransactionActivity : AppCompatActivity(), ZXingScannerView.ResultHandler {
    lateinit var transactionActivity: TransactionActivity
    lateinit var context: Context
    lateinit var activityTitleTV: TextView
    lateinit var backIV: ImageView
    lateinit var content_frame: FrameLayout
    lateinit var mScannerView: ZXingScannerView
    private val PERMISSION_REQUEST_CODE = 1000
    lateinit var text: TextView
    lateinit var progressBar: ProgressBar
    lateinit var config: Config
    lateinit var userId: String
    lateinit var addTransactionRequestModel: AddTransactionRequestModel
    lateinit var productNameTV: TextView
    lateinit var batchNumberTV: TextView
    lateinit var cropTV: TextView
    lateinit var packetsInsideTV: TextView
    lateinit var packetsWeightTV: TextView
    lateinit var bagWeightTV: TextView
    lateinit var productIdTV: TextView
    lateinit var lotNumberTV: TextView
    lateinit var labelNumberTV: TextView
    lateinit var submitButton: Button
    lateinit var transactionRL: RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)
        setupUI()
    }

    private fun setupUI() {
        transactionActivity = this
        context = this
        config = Config()
        userId = ProjectPreference.getSharedPreferenceData(AppConstant.LOGIN_RESPONSE_CONTENT, context)
        addTransactionRequestModel = AddTransactionRequestModel()
        progressBar = findViewById(R.id.progressBar)
        activityTitleTV = findViewById(R.id.activityTitleTV)
        activityTitleTV.setText("Transaction")
        backIV = findViewById(R.id.backIV)
        backIV.setOnClickListener(View.OnClickListener {
            finish()
        })
        content_frame = findViewById(R.id.content_frame)
        mScannerView = ZXingScannerView(this)
        content_frame.addView(mScannerView)
        productNameTV = findViewById(R.id.productNameTV) 
        batchNumberTV = findViewById(R.id.batchNumberTV) 
        cropTV = findViewById(R.id.cropTV) 
        packetsInsideTV = findViewById(R.id.packetsInsideTV) 
        packetsWeightTV = findViewById(R.id.packetsWeightTV) 
        bagWeightTV = findViewById(R.id.bagWeightTV)
        labelNumberTV = findViewById(R.id.labelNumberTV)
        productIdTV = findViewById(R.id.productIdTV)
        lotNumberTV = findViewById(R.id.lotNumberTV)
        transactionRL = findViewById(R.id.transactionRL)
        transactionRL.visibility = View.GONE
        submitButton = findViewById(R.id.submitButton)


        submitButton.setOnClickListener(View.OnClickListener {
            submitTrasactionData()
        })
    }


    fun submitTrasactionData() {
        config.showProgress(progressBar, transactionActivity)
        var apiInterface =
            RetrofitInstanceNative().retrofitInstance.create(ApiInterface::class.java)

        val call: Call<TransactionVolumeResponseModel> =
            apiInterface.addTransaction(addTransactionRequestModel)

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
                        config.hideProgress(progressBar, transactionActivity)
                        config.customAlertOk(
                            AppConstant.INFO,
                            response.body()!!.msg,
                            AppConstant.OK,
                            context,AppConstant.FINISH_CURRENT
                        )
                    } else {
                        config.hideProgress(progressBar, transactionActivity)

                        config.customAlertOk(
                            AppConstant.INFO,
                            response.body()!!.msg,
                            AppConstant.OK,
                            context
                        )
                    }

                } else {
                    config.hideProgress(progressBar, transactionActivity)

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
                config.hideProgress(progressBar, transactionActivity)
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

    fun CameraPermission(activity: TransactionActivity?) {
        if (Build.VERSION.SDK_INT >= 23) {
            val permission: Int =
                ContextCompat.checkSelfPermission(transactionActivity, Manifest.permission.CAMERA)
            if (permission != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(
                    arrayOf(Manifest.permission.CAMERA),
                   PERMISSION_REQUEST_CODE
                )
            } else {
                CallCamera()
            }
        } else {
            CallCamera()
        }
    }
    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        // check if the request code is same as what is passed  here it is 2
        if (requestCode == 55) {
            if (data != null) {

            }
        }
        /*if (requestCode == 13) {
            CallCamera();
        }*/
// qr code result
    }
    private fun CallCamera() {

        mScannerView.setResultHandler(this)
        mScannerView.startCamera()
    }

    override fun handleResult(result: Result?) {
        var transactionData = result?.text
        Log.d("qrcode",transactionData);
        var transactionResult =  transactionData!!.split("/")
        transactionRL.visibility = View.VISIBLE
        labelNumberTV.setText(transactionResult[0])
        cropTV.setText(transactionResult[1])
        lotNumberTV.setText(transactionResult[2])
        productNameTV.setText(transactionResult[3])
        productIdTV.setText(transactionResult[4])
        batchNumberTV.setText(transactionResult[5])
        packetsWeightTV.setText(transactionResult[6])
        packetsInsideTV.setText(transactionResult[7])
        bagWeightTV.setText(transactionResult[8])


        addTransactionRequestModel.labelNo = labelNumberTV.text.toString()
        addTransactionRequestModel.crop = cropTV.text.toString()
        addTransactionRequestModel.lotNo = lotNumberTV.text.toString()
        addTransactionRequestModel.productName = productNameTV.text.toString()
        addTransactionRequestModel.productId = productIdTV.text.toString()
        addTransactionRequestModel.batchNo = batchNumberTV.text.toString()
        addTransactionRequestModel.packetWeight = packetsWeightTV.text.toString()
        addTransactionRequestModel.packetsInside = packetsInsideTV.text.toString()
        addTransactionRequestModel.bagWeight = bagWeightTV.text.toString()
        addTransactionRequestModel.userId = userId

        val handler = Handler()
        handler.postDelayed(
            { mScannerView.resumeCameraPreview(transactionActivity) },
            1000
        )
    }

    override fun onResume() {
        super.onResume()
        CameraPermission(transactionActivity)
    }

    override fun onPause() {
        super.onPause()
        mScannerView.stopCamera()
    }
}
