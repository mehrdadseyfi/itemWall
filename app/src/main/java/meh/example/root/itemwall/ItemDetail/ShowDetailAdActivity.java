package meh.example.root.itemwall.ItemDetail;

import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import dmax.dialog.SpotsDialog;
import meh.example.root.itemwall.Auth.LogInActivity;
import meh.example.root.itemwall.Model.AllItemModel;
import meh.example.root.itemwall.R;
import meh.example.root.itemwall.RetroFit.APIClient;
import meh.example.root.itemwall.RetroFit.APIinterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ShowDetailAdActivity extends AppCompatActivity {
    TextView topic, location, price, persent,cate,contact;
    SpotsDialog dialog;
    String item_id;
    ImageView image_item2,image_item1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail_ad);
        importView();
        Intent intent = getIntent();
        item_id = intent.getStringExtra("item_id");
        apiGetAllItem();
    }
    private void importView() {
        topic = (TextView) findViewById(R.id.topic);
        cate = (TextView) findViewById(R.id.cate);
        contact = (TextView) findViewById(R.id.contact);
        location = (TextView) findViewById(R.id.location);
        price = (TextView) findViewById(R.id.price);
        persent = (TextView) findViewById(R.id.persent);
        image_item1= (ImageView) findViewById(R.id.image_item1);
        image_item2= (ImageView) findViewById(R.id.image_item2);
        dialog = new SpotsDialog(this, R.style.Custom);

    }
    public void apiGetAllItem() {
        dialog.show();
        String token = PreferenceManager.getDefaultSharedPreferences(this).getString("token","");
        APIinterface apIinterface = APIClient.getClient().create(APIinterface.class);

        Call<ItemDetail> call = apIinterface.getItemDetail(token,item_id);
        // dar sf gharar dadan
        call.enqueue(new Callback<ItemDetail>() {
            @Override
            public void onResponse(Call<ItemDetail> call, Response<ItemDetail> response) {
                dialog.dismiss();
                ////check connect succeful   status code
                if (response.isSuccessful()) {
                    Log.d("retrofitResponse", response.body().getStatus().toString());
                    if (response.body().getStatus().toString().equals("token_expire")) {

                        PreferenceManager.getDefaultSharedPreferences(ShowDetailAdActivity.this).edit().putBoolean("havetoken", false).apply();
                        startActivity(new Intent(ShowDetailAdActivity.this, LogInActivity.class));
                        Toast.makeText(ShowDetailAdActivity.this, "شما وارد نشده اید", Toast.LENGTH_SHORT).show();
                        finish();

                    } else if (response.body().getStatus().toString().equals("ok")) {
                        Picasso.get().load("https://mehrdadseyfi.ir/apiWall/AddItem/"+response.body().getItem().get(0).getImageUrl1()).into(image_item1);
                        Picasso.get().load("https://mehrdadseyfi.ir/apiWall/AddItem/"+response.body().getItem().get(0).getImageUrl2()).into(image_item2);
                        location.setText(response.body().getItem().get(0).getItemLocation());
                        cate.setText(response.body().getItem().get(0).getItemCatId());
                        price.setText(response.body().getItem().get(0).getItemPrice());
                        topic.setText(response.body().getItem().get(0).getItemTopic());
                        persent.setText(response.body().getItem().get(0).getItemPeresent());
                        contact.setText(response.body().getItemContact());


                    }


                } else if (response.code() == 404) {
                    Log.d("retrofitResponse", "404");
                }
            }

            @Override
            public void onFailure(Call<ItemDetail> call, Throwable throwable) {
                dialog.dismiss();

                Toast.makeText(ShowDetailAdActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
