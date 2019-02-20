package meh.example.root.itemwall.RetroFit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIinterface {
    @POST("AuthMobile/Login.php")
    @FormUrlEncoded
    Call<UserAuth> getUsers(@Field("mobile") String mobile, @Field("password") String pass);
    @POST("AuthMobile/Register.php")
    @FormUrlEncoded
    Call<UserAuth> getRegister(@Field("mobile") String mobile, @Field("password") String pass,@Field("email") String email,@Field("full_name") String name);
}