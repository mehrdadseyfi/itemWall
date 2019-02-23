package meh.example.root.itemwall.RetroFit;

import meh.example.root.itemwall.Model.AllItemModel;
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
    Call<UserAuth> getRegister(@Field("mobile") String mobile, @Field("password") String pass, @Field("email") String email, @Field("full_name") String name);

    @POST("AddItem/CateApi.php")
    @FormUrlEncoded
    Call<ItemCatStatus> getCategoery(@Field("token") String token);

    @POST("AllItemShow.php")
    @FormUrlEncoded
    Call<AllItemModel> getAllItem(@Field("token") String token,@Field("item_topic") String item_topic,@Field("item_cate_id") String item_cate_id,@Field("item_location") String item_location,@Field("item_price") String item_price,@Field("item_peresent") String item_peresent);

}