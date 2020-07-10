package com.kamadgiri.dealer.networkReq;





import com.kamadgiri.dealer.model.addTransaction.AddTransactionRequestModel;
import com.kamadgiri.dealer.model.login.LoginRequestModel;
import com.kamadgiri.dealer.model.login.LoginResponseModel;
import com.kamadgiri.dealer.model.profile.GetProfileResponseModel;
import com.kamadgiri.dealer.model.profile.UpdateProfileRequestModel;
import com.kamadgiri.dealer.model.profile.UpdateProfileResponseModel;
import com.kamadgiri.dealer.model.register.RegisterRequestModel;
import com.kamadgiri.dealer.model.register.RegisterResponseModel;
import com.kamadgiri.dealer.model.transactionVolume.TransactionVolumeRequestModel;
import com.kamadgiri.dealer.model.transactionVolume.TransactionVolumeResponseModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

public interface ApiInterface {

    //Get Profile
    @GET("api/dealership/user/get")
        // TODO Register fragment onCreate ()
    Call<GetProfileResponseModel> getUserProfile(@HeaderMap Map<String, String> map);

    @POST("api/dealership/user/login")
        // TODO Register fragment onCreate ()
    Call<LoginResponseModel> getLogin(@Body LoginRequestModel loginRequestModel);

    @POST("api/dealership/user/register")
        // TODO Register fragment onCreate ()
    Call<RegisterResponseModel> getRegisterUser(@Body RegisterRequestModel registerRequestModel);

    @POST("api/dealership/user/update")
        // TODO Register fragment onCreate ()
    Call<UpdateProfileResponseModel> getUpdateProfile(@Body UpdateProfileRequestModel registerRequestModel);

    @POST("api/dealership/transaction/all")
        // TODO Register fragment onCreate ()
    Call<TransactionVolumeResponseModel> getVolumeDetails(@Body TransactionVolumeRequestModel transactionVolumeRequestModel);

    @POST("api/dealership/transaction/new")
        // TODO Register fragment onCreate ()
    Call<TransactionVolumeResponseModel> addTransaction(@Body AddTransactionRequestModel addTransactionRequestModel);

   /* @POST("users/phone/auth")
        // TODO Register fragment onCreate ()
    Call<UserAuthenticateResponseModel> getLogin(@Body LoginRequestModel initApiReq);

    @POST("users/login")
        // TODO Register fragment onCreate ()
    Call<LoginResponseModel> getLoginUser(@Body LoginRequestModel initApiReq);

    @POST("organization/balance")
        // TODO Register fragment onCreate ()
    Call<OrgBalResponseModel> getOrgBalance(@Body OrgBalRequestModel orgBalRequestModel);



    @POST("emptaint/add")
        // TODO Register fragment onCreate ()
    Call<UploadRecordResponseModel> getUploadRecord(@Body UploadRequestModel uploadRequestModel);

    @POST("emptaint/emp/record")
        // TODO Register fragment onCreate ()
    Call<SearchRecordResponseModel> getSearchRecord(@Body SearchRecordRequestModel searchRecordRequestModel);

    @POST("emptaint/emp/record/details")
        // TODO Register fragment onCreate ()
    Call<RecordDetailsResponseModel> getRecordDetails(@Body RecordDetailsRequestModel recordDetailsRequestModel);

    @POST("users/details")
        // TODO Register fragment onCreate ()
    Call<ProfileResponseModel> getProfileDetails(@Body ProfileRequestModel recordDetailsRequestModel);


*/
}

