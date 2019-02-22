package meh.example.root.itemwall.RetroFit;

import meh.example.root.itemwall.Model.ItemCatStatus;
import meh.example.root.itemwall.Model.ItemCateModel;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface APIinterface {
    @POST("AuthMobile/Login.php")
    @FormUrlEncoded
    Call<UserAuth> getUsers(@Field("mobile") String mobile, @Field("password") String pass);
    @POST("AuthMobile/Register.php")
    @FormUrlEncoded
    Call<UserAuth> getRegister(@Field("mobile") String mobile, @Field("password") String pass,@Field("email") String email,@Field("full_name") String name);
   @POST("AddItem/CateApi.php")
Call<ItemCatStatus> getCategoery(@Field("token") String token);
    @Multipart
    @POST("AddItem/AddItem.php")
    Call<ItemCatStatus> addItem(@Field("token") String token,@Field("topic") String topic,@Field("cateid") String cateid,@Field("location") String location,@Field("price") String price,@Field("peresent") String peresent ,@Part MultipartBody.Part image1,@Part MultipartBody.Part image2);
}