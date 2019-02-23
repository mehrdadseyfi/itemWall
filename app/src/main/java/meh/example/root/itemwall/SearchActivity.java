package meh.example.root.itemwall;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import meh.example.root.itemwall.Auth.LogInActivity;
import meh.example.root.itemwall.Model.ItemCatStatus;
import meh.example.root.itemwall.RetroFit.APIClient;
import meh.example.root.itemwall.RetroFit.APIinterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {
    EditText topic, location, price, persent;
    Button send;
    Spinner sp1;
    SpotsDialog dialog;
    List<String> list;
    String cateId="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        importView();
        dialog.show();
        apiGetCate();
                  Toast.makeText(getBaseContext(), cateId, Toast.LENGTH_SHORT).show();

        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                // TODO Auto-generated method stub
                cateId=list.get(position);
//                Toast.makeText(getBaseContext(), cateId, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();

        Intent intent = new Intent(SearchActivity.this, MainActivity.class);
        intent.putExtra("topic", topic.getText().toString());
        intent.putExtra("cateid", cateId);
        intent.putExtra("location", location.getText().toString());
        intent.putExtra("price", price.getText().toString());
        intent.putExtra("peresent", persent.getText().toString());
        startActivity(intent);
            }
        });
    }
    private void importView() {
        topic = (EditText) findViewById(R.id.topic);
        location = (EditText) findViewById(R.id.location);
        price = (EditText) findViewById(R.id.price);
        persent = (EditText) findViewById(R.id.persent);
        send = (Button) findViewById(R.id.send);
        sp1 = (Spinner) findViewById(R.id.spinner1);
        dialog = new SpotsDialog(this, R.style.Custom);

    }
    public void apiGetCate() {
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

                        PreferenceManager.getDefaultSharedPreferences(SearchActivity.this).edit().putBoolean("havetoken", false).apply();
                        startActivity(new Intent(SearchActivity.this, LogInActivity.class));
                        Toast.makeText(SearchActivity.this, "شما وارد نشده اید", Toast.LENGTH_SHORT).show();
                        finish();

                    } else if (response.body().getStatus().toString().equals("ok")) {

                        Log.d("showResponse", response.body().getCate().get(0).getCatName());

                        list = new ArrayList<String>();
                        list.add("");
                        for (int i = 0; i < response.body().getCate().size(); i++) {

                            list.add(response.body().getCate().get(i).getCatName());
                        }
                        ArrayAdapter<String> adp1 = new ArrayAdapter<String>(SearchActivity.this,
                                android.R.layout.simple_list_item_1, list);
                        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        sp1.setAdapter(adp1);

                    }


                } else if (response.code() == 404) {
                    Log.d("retrofitResponse", "404");
                }
            }

            @Override
            public void onFailure(Call<ItemCatStatus> call, Throwable throwable) {
                dialog.dismiss();

                Toast.makeText(SearchActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });
    }

}
