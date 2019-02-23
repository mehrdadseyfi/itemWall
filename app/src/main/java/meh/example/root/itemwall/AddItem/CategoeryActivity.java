package meh.example.root.itemwall.AddItem;

import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import meh.example.root.itemwall.Auth.LogInActivity;
import meh.example.root.itemwall.MainActivity;
import meh.example.root.itemwall.Model.Item;
import meh.example.root.itemwall.Model.ItemCatStatus;
import meh.example.root.itemwall.Model.ItemCateModel;
import meh.example.root.itemwall.R;
import meh.example.root.itemwall.RetroFit.APIClient;
import meh.example.root.itemwall.RetroFit.APIinterface;
import meh.example.root.itemwall.ShowAllItem.ItemAdapter;
import meh.example.root.itemwall.ShowAllItem.ItemDetailActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoeryActivity extends AppCompatActivity {
    SpotsDialog dialog;
    ListView listItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoery);
        dialog = new SpotsDialog(this, R.style.Custom);
        listItem=(ListView)findViewById(R.id.list_cate);

        apiGetCate();
    }
    public void apiGetCate() {
        dialog.show();
        String token = PreferenceManager.getDefaultSharedPreferences(this).getString("token","");
        APIinterface apIinterface = APIClient.getClient().create(APIinterface.class);

        Call<ItemCatStatus> call = apIinterface.getCategoery(token);
        // dar sf gharar dadan
        call.enqueue(new Callback<ItemCatStatus>() {
            @Override
            public void onResponse(Call<ItemCatStatus> call, Response<ItemCatStatus> response) {
                dialog.dismiss();
                ////check connect succeful   status code
                if (response.isSuccessful()) {
                    Log.d("retrofitResponse", response.body().getStatus().toString());
                    if (response.body().getStatus().toString().equals("token_expire")) {

                        PreferenceManager.getDefaultSharedPreferences(CategoeryActivity.this).edit().putBoolean("havetoken", false).apply();
                        startActivity(new Intent(CategoeryActivity.this, LogInActivity.class));
                        Toast.makeText(CategoeryActivity.this, "شما وارد نشده اید", Toast.LENGTH_SHORT).show();
                        finish();

                    } else if (response.body().getStatus().toString().equals("ok")) {

                        Log.d("showResponse", response.body().getCate().get(0).getCatName());

                        showListView(response.body().getCate(),CategoeryActivity.this);


                    }


                } else if (response.code() == 404) {
                    Log.d("retrofitResponse", "404");
                }
            }

            @Override
            public void onFailure(Call<ItemCatStatus> call, Throwable throwable) {
                dialog.dismiss();

                Toast.makeText(CategoeryActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });
    }
    public void showListView(final List<ItemCateModel> models, Context context)   {


        CateAdapter adapter = new CateAdapter(models, context);
        listItem.setAdapter(adapter);
        listItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(CategoeryActivity.this, ItemDetailActivity.class);
                intent.putExtra("serviceId", models.get(i).getCatId());
                startActivity(intent);

            }
        });

    }

}
