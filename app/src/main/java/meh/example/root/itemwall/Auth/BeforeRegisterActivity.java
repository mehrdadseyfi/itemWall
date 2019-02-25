package meh.example.root.itemwall.Auth;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import dmax.dialog.SpotsDialog;
import meh.example.root.itemwall.MainActivity;
import meh.example.root.itemwall.R;
import meh.example.root.itemwall.RetroFit.APIClient;
import meh.example.root.itemwall.RetroFit.APIinterface;
import meh.example.root.itemwall.RetroFit.UserAuth;
import meh.example.root.itemwall.SearchActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BeforeRegisterActivity extends AppCompatActivity {
    EditText mobile;
    Button enter;
    SpotsDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_register);
        mobile = (EditText) findViewById(R.id.mobile);
        enter = (Button) findViewById(R.id.enter);
        dialog = new SpotsDialog(this, R.style.Custom);
        logInClick();
    }

    private void logInClick() {

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mobile.getText().toString().equals("")) {
                    Toast.makeText(BeforeRegisterActivity.this, "تمام فیلد ها را پر کنید", Toast.LENGTH_SHORT).show();
                } else {
                    dialog.show();
                    Apiconnectcode(mobile.getText().toString());

                }
            }
        });
    }

    public void Apiconnectcode(final String mobileUser) {
        APIinterface apIinterface = APIClient.getClient().create(APIinterface.class);

        Call<UserAuth> call = apIinterface.getcodeVerify(mobileUser);
        // dar sf gharar dadan
        call.enqueue(new Callback<UserAuth>() {
            @Override
            public void onResponse(Call<UserAuth> call, Response<UserAuth> response) {
                ////check connect succeful   status code
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    Log.d("retrofitResponse", response.body().getStatus().toString());
                    if (response.body().getStatus().toString().equals("code_send")) {
                        Toast.makeText(BeforeRegisterActivity.this, "کدتایید برای شما ارسال شد", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(BeforeRegisterActivity.this, RegisterActivity.class);
                        intent.putExtra("mobile",mobileUser);
                        startActivity(intent);
                        finish();
                    } else if (response.body().getStatus().toString().equals("user_exist")) {
                        Toast.makeText(BeforeRegisterActivity.this, "کاربر موجود است", Toast.LENGTH_SHORT).show();

                    }
                } else if (response.code() == 404) {
                    dialog.dismiss();
                    Log.d("retrofitResponse", "404");
                }
            }

            @Override
            public void onFailure(Call<UserAuth> call, Throwable throwable) {
                dialog.dismiss();
                Toast.makeText(BeforeRegisterActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });
    }

}
