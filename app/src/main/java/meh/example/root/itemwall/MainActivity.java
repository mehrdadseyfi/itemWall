package meh.example.root.itemwall;

import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import dmax.dialog.SpotsDialog;
import meh.example.root.itemwall.AddItem.AddItemActivityActivity;
import meh.example.root.itemwall.AddItem.CategoeryActivity;
import meh.example.root.itemwall.Auth.LogInActivity;
import meh.example.root.itemwall.Auth.MyAdActivity;
import meh.example.root.itemwall.ItemDetail.ShowDetailAdActivity;
import meh.example.root.itemwall.Model.AllItemModel;
import meh.example.root.itemwall.Model.Item;
import meh.example.root.itemwall.RetroFit.APIClient;
import meh.example.root.itemwall.RetroFit.APIinterface;
import meh.example.root.itemwall.ShowAllItem.ItemAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener  {
    SpotsDialog dialog;
    ListView listItem;
    SwipeRefreshLayout swipeLayout;
    Button addItem,cate,search,exit,myAd;
    String item_topic="";
    String item_cate_id="";
    String item_location="";
    String item_peresent="";
    String item_price="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        SwipeRefresh();

        addItem=(Button)findViewById(R.id.add_item);
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddItemActivityActivity.class));

            }
        });
        cate=(Button)findViewById(R.id.cate);
        cate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CategoeryActivity.class));

            }
        });
        search=(Button)findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SearchActivity.class));

            }
        });
        exit=(Button)findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PreferenceManager.getDefaultSharedPreferences(MainActivity.this).edit().putBoolean("havetoken", false).apply();
                Toast.makeText(MainActivity.this, "شما خارج شدید", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, LogInActivity.class));
                finish();
            }
        });
 myAd=(Button)findViewById(R.id.myAd);
        myAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(MainActivity.this, MyAdActivity.class));
            }
        });



        dialog = new SpotsDialog(this, R.style.Custom);
        listItem=(ListView)findViewById(R.id.list_item);

        //search
        Intent intent = getIntent();
        item_topic = intent.getStringExtra("topic");
        item_cate_id = intent.getStringExtra("cateid");
        item_location = intent.getStringExtra("location");
        item_peresent = intent.getStringExtra("price");
        item_price = intent.getStringExtra("peresent");
        Toast.makeText(this, item_cate_id, Toast.LENGTH_SHORT).show();

        //



        apiGetAllItem();



    }

    private void SwipeRefresh() {
        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener((SwipeRefreshLayout.OnRefreshListener) MainActivity.this);
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

        Call<AllItemModel> call = apIinterface.getAllItem(token,item_topic,item_cate_id,item_location,item_price,item_peresent);
        // dar sf gharar dadan
        call.enqueue(new Callback<AllItemModel>() {
            @Override
            public void onResponse(Call<AllItemModel> call, Response<AllItemModel> response) {
                dialog.dismiss();
                ////check connect succeful   status code
                if (response.isSuccessful()) {
                    Log.d("retrofitResponse", response.body().getStatus().toString());
                    if (response.body().getStatus().toString().equals("token_expire")) {

                        PreferenceManager.getDefaultSharedPreferences(MainActivity.this).edit().putBoolean("havetoken", false).apply();
                        startActivity(new Intent(MainActivity.this, LogInActivity.class));
                        Toast.makeText(MainActivity.this, "شما وارد نشده اید", Toast.LENGTH_SHORT).show();
                        finish();

                    } else if (response.body().getStatus().toString().equals("ok")) {

                        showListView(response.body().getItem(),MainActivity.this);


                    }


                } else if (response.code() == 404) {
                    Log.d("retrofitResponse", "404");
                }
            }

            @Override
            public void onFailure(Call<AllItemModel> call, Throwable throwable) {
                dialog.dismiss();

                Toast.makeText(MainActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });
    }

    public void showListView(final List<Item> models, Context context)   {


            ItemAdapter adapter = new ItemAdapter(models, context);
             listItem.setAdapter(adapter);
        listItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, ShowDetailAdActivity.class);
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

