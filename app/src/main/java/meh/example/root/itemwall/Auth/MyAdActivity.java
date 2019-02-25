package meh.example.root.itemwall.Auth;

import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import dmax.dialog.SpotsDialog;
import meh.example.root.itemwall.ItemDetail.ShowDetailAdActivity;
import meh.example.root.itemwall.Model.AllItemModel;
import meh.example.root.itemwall.Model.Item;
import meh.example.root.itemwall.R;
import meh.example.root.itemwall.RetroFit.APIClient;
import meh.example.root.itemwall.RetroFit.APIinterface;
import meh.example.root.itemwall.ShowAllItem.ItemAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyAdActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    SpotsDialog dialog;
    ListView listItem;
    SwipeRefreshLayout swipeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ad);
        dialog = new SpotsDialog(this, R.style.Custom);
        listItem=(ListView)findViewById(R.id.list_item);

        apiGetAllItem();
    SwipeRefresh();
    }
    private void SwipeRefresh() {
        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener((SwipeRefreshLayout.OnRefreshListener) MyAdActivity.this);
        swipeLayout.setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        //
    }


    public void apiGetAllItem() {
        dialog.show();
        String token = PreferenceManager.getDefaultSharedPreferences(this).getString("token","");
        APIinterface apIinterface = APIClient.getClient().create(APIinterface.class);

        Call<AllItemModel> call = apIinterface.getMyItem(token);
        // dar sf gharar dadan
        call.enqueue(new Callback<AllItemModel>() {
            @Override
            public void onResponse(Call<AllItemModel> call, Response<AllItemModel> response) {
                dialog.dismiss();
                ////check connect succeful   status code
                if (response.isSuccessful()) {
                    Log.d("retrofitResponse", response.body().getStatus().toString());
                    if (response.body().getStatus().toString().equals("token_expire")) {

                        PreferenceManager.getDefaultSharedPreferences(MyAdActivity.this).edit().putBoolean("havetoken", false).apply();
                        startActivity(new Intent(MyAdActivity.this, LogInActivity.class));
                        Toast.makeText(MyAdActivity.this, "شما وارد نشده اید", Toast.LENGTH_SHORT).show();
                        finish();

                    } else if (response.body().getStatus().toString().equals("ok")) {

                        showListView(response.body().getItem(),MyAdActivity.this);


                    }


                } else if (response.code() == 404) {
                    Log.d("retrofitResponse", "404");
                }
            }

            @Override
            public void onFailure(Call<AllItemModel> call, Throwable throwable) {
                dialog.dismiss();

                Toast.makeText(MyAdActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });
    }

    public void showListView(final List<Item> models, Context context)   {


        ItemAdapter adapter = new ItemAdapter(models, context);
        listItem.setAdapter(adapter);
        listItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MyAdActivity.this, ShowDetailAdActivity.class);
                intent.putExtra("item_id", models.get(i).getItemId());
                startActivity(intent);

            }
        });

    }


    @Override public void onRefresh() {
        swipeLayout.setRefreshing(false);
        apiGetAllItem();
    }
}
