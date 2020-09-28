package com.kamadgiri.dealer.utils;

import android.content.Context;

import com.kamadgiri.dealer.dashboard.PointSummaryActivity;

public interface AppConstant {
    String HEADER_TOKEN = "Authorization";
    String FINISH_CURRENT = "finishCurrent";
    String NETWORK_ERROR = "Network Error";
    String LOGIN_RESPONSE_CONTENT = "loginResponse";
    String TOKEN = "token";
    String OK = "Ok";
    String SEARCH_RECORD = "SEARCH_RECORD";
    String Purchased_Record = "PURCHASED_REQUEST";
    String UPLOAD_RECORD = "UPLOAD_RECORD";
    int SUCCESS_CODE = 200;
    int FAILURE_CODE = 404;
    String INFO = "Info";
    String DATE_FORMATE = "dd/MM/yyyy";

    String POINT_SUMMARY = "http://148.72.213.116:3000/api/wallet/userhistory/get/";




}
