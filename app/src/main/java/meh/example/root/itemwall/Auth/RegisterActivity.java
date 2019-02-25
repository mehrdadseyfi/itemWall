package meh.example.root.itemwall.Auth;

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
import meh.example.root.itemwall.R;
import meh.example.root.itemwall.RetroFit.APIClient;
import meh.example.root.itemwall.RetroFit.APIinterface;
import meh.example.root.itemwall.RetroFit.UserAuth;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    Button  register;
    EditText mobile, pass,email,name,code;
    SpotsDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        importview();
        RegisterClick();
    }
    private void importview() {
        register = (Button) findViewById(R.id.register);

        mobile = (EditText) findViewById(R.id.mobile);
        code = (EditText) findViewById(R.id.code);
        pass = (EditText) findViewById(R.id.password);
        email = (EditText) findViewById(R.id.email);
        name = (EditText) findViewById(R.id.name);

        dialog = new SpotsDialog(this, R.style.Custom);

    }
    private void RegisterClick() {

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mobile.getText().toString().equals("") && pass.getText().toString().equals("")&& email.getText().toString().equals("")&& name.getText().toString().equals("")) {
                    Toast.makeText(RegisterActivity.this, "تمام فیلد ها را پر کنید", Toast.LENGTH_SHORT).show();
                } else {
                    dialog.show();
                    ApiconnectUserName(mobile.getText().toString(), pass.getText().toString(),email.getText().toString(),name.getText().toString(),code.getText().toString());

                }
            }
        });
    }

    public void ApiconnectUserName(String mobile, String pass,String email,String name,String code) {
        APIinterface apIinterface = APIClient.getClient().create(APIinterface.class);

        Call<UserAuth> call = apIinterface.getRegister(mobile, pass,email,name,code);
        // dar sf gharar dadan
        call.enqueue(new Callback<UserAuth>() {
            @Override
            public void onResponse(Call<UserAuth> call, Response<UserAuth> response) {
                ////check connect succeful   status code
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    Log.d("retrofitResponse", response.body().getStatus().toString());
                    if (response.body().getStatus().toString().equals("user_register")) {

                        Toast.makeText(RegisterActivity.this, "ثبت نام با موفقیت انجام شد", Toast.LENGTH_SHORT).show();


                    } else if (response.body().getStatus().toString().equals("code_wrong")) {

                        Toast.makeText(RegisterActivity.this, "کد وارد شده اشتباه است", Toast.LENGTH_SHORT).show();
                    } else {

                        Toast.makeText(RegisterActivity.this, "تمام اطلاعات را وارد نمایید", Toast.LENGTH_SHORT).show();

                    }


                } else if (response.code() == 404) {
                    dialog.dismiss();
                    Log.d("retrofitResponse", "404");
                }
            }

            @Override
            public void onFailure(Call<UserAuth> call, Throwable throwable) {
                dialog.dismiss();
                Toast.makeText(RegisterActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });
    }

}
