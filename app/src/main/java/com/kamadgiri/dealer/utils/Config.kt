package com.kamadgiri.dealer

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.ConnectivityManager
import android.view.View
import android.view.WindowManager
import android.widget.ProgressBar
import com.kamadgiri.dealer.utils.AppConstant

import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class Config {
   fun isNetworkAvailable(context: Context): Boolean {
        try {
            if (context.applicationContext != null) {
                val connectivityManager =
                    context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val activeNetworkInfo = connectivityManager.activeNetworkInfo
                return activeNetworkInfo != null && activeNetworkInfo.isConnected
            }
            return false
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }
     fun showProgress(progressBar: ProgressBar,activity: Activity) {
        progressBar.visibility = View.VISIBLE
        progressBar.bringToFront()
        userInteractionDisable(activity);
    }

     fun hideProgress(progressBar: ProgressBar,activity: Activity) {
        progressBar.visibility = View.GONE
        userInteractionEnable(activity);
    }
    // method for custom alert  ok
    fun customAlertOk(
        title: String?,
        message: String?,
        actionText: String?,
        context: Context?
    ) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setNegativeButton(
            actionText,
            DialogInterface.OnClickListener { dialogInterface, i -> dialogInterface.dismiss() })
        builder.show()
    }

    // method for custom alert  ok
    fun customAlertOk(
        title: String?,
        message: String?,
        actionText: String?,
        context: Context,
        action: String
    ) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setNegativeButton(
            actionText,
            DialogInterface.OnClickListener { dialogInterface, i ->
                if (action.equals(AppConstant.FINISH_CURRENT, ignoreCase = true)) {
                    dialogInterface.dismiss()
                    (context as Activity).finish()
                }
            })
        builder.show()
    }

    fun customAlertOk(
        title: String?,
        message: String?,
        actionText: String?,
        context: Context,
        intent: Intent
    ) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setNegativeButton(
            actionText,
            DialogInterface.OnClickListener { dialogInterface, i ->
                /*if (action.equals(AppConstant.FINISH_CURRENT, ignoreCase = true)) {
                    dialogInterface.dismiss()
                    (context as Activity).finish()
                }*/
                dialogInterface.dismiss()

                (context as Activity).startActivity(intent)
            })
        builder.show()
    }

    fun userInteractionDisable(activity: Activity){
         activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    fun userInteractionEnable(activity: Activity){
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    fun currentDate(dateFormat: String?): String? {
        val cal = Calendar.getInstance(Locale.getDefault())
        val sdf = SimpleDateFormat(dateFormat)
        return sdf.format(cal.time)
    }

    fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }


/*    fun loadAnnouncementJSONFromAsset(activity: Activity): String? {
        var json: String? = null
        json = try {
            val `is`: InputStream = activity.getAssets().open("Announcements.json")
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            json = String(buffer, "")
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }*/
}