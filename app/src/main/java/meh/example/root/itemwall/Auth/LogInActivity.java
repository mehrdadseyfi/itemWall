package meh.example.root.itemwall.Auth;

import android.content.Intent;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import dmax.dialog.SpotsDialog;
import meh.example.root.itemwall.MainActivity;
import meh.example.root.itemwall.R;
import meh.example.root.itemwall.RetroFit.APIClient;
import meh.example.root.itemwall.RetroFit.APIinterface;
import meh.example.root.itemwall.RetroFit.UserAuth;
import meh.example.root.itemwall.SplashActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogInActivity extends AppCompatActivity {
    Button logIn, register;
    EditText mobile, pass;
    TextView forgetPass;
    ProgressBar progressBar;
    SpotsDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        importview();
        logInClick();
        register=(Button)findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LogInActivity.this, BeforeRegisterActivity.class));

            }
        });

    }

    private void logInClick() {

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mobile.getText().toString().equals("") && pass.getText().toString().equals("")) {
                    Toast.makeText(LogInActivity.this, "تمام فیلد ها را پر کنید", Toast.LENGTH_SHORT).show();
                } else {
                    dialog.show();
                    ApiconnectUserName(mobile.getText().toString(), pass.getText().toString());

                }
            }
        });
    }

    private void importview() {
        logIn = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);

        mobile = (EditText) findViewById(R.id.mobile);
        pass = (EditText) findViewById(R.id.password);

        forgetPass = (TextView) findViewById(R.id.forget_pass);
        dialog = new SpotsDialog(this, R.style.Custom);

    }

    public void ApiconnectUserName(String mobile, String pass) {
        APIinterface apIinterface = APIClient.getClient().create(APIinterface.class);

        Call<UserAuth> call = apIinterface.getUsers(mobile, pass);
        // dar sf gharar dadan
        call.enqueue(new Callback<UserAuth>() {
            @Override
            public void onResponse(Call<UserAuth> call, Response<UserAuth> response) {
                ////check connect succeful   status code
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    Log.d("retrofitResponse", response.body().getStatus().toString());
                    if (response.body().getStatus().toString().equals("user_log_in")) {
                        PreferenceManager.getDefaultSharedPreferences(LogInActivity.this).edit().putString("token",response.body().getToken().toString()).apply();
                        PreferenceManager.getDefaultSharedPreferences(LogInActivity.this).edit().putBoolean("havetoken",true).apply();
                        startActivity(new Intent(LogInActivity.this, MainActivity.class));
                    } else if (response.body().getStatus().toString().equals("password_wrong")) {

                        Toast.makeText(LogInActivity.this, "رمز عبور اشتباه است", Toast.LENGTH_SHORT).show();
                    } else {

                        Toast.makeText(LogInActivity.this, "کاربر ثبت نام نکرده است", Toast.LENGTH_SHORT).show();

                    }
                } else if (response.code() == 404) {
                    dialog.dismiss();
                    Log.d("retrofitResponse", "404");
                }
            }

            @Override
            public void onFailure(Call<UserAuth> call, Throwable throwable) {
                dialog.dismiss();
                Toast.makeText(LogInActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });
    }
}
