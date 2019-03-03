package meh.example.root.itemwall.AddItem;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.MySSLSocketFactory;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import meh.example.root.itemwall.Auth.LogInActivity;
import meh.example.root.itemwall.MainActivity;
import meh.example.root.itemwall.Model.ItemCatStatus;
import meh.example.root.itemwall.Model.ItemCateModel;
import meh.example.root.itemwall.R;
import meh.example.root.itemwall.RetroFit.APIClient;
import meh.example.root.itemwall.RetroFit.APIinterface;
import meh.example.root.itemwall.RetroFit.UserAuth;
import meh.example.root.itemwall.SSlCertificate;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class AddItemActivityActivity extends AppCompatActivity implements IPickResult {
    PickSetup setup;
    String urlPhoto = "no";
    int Imageshow = 0;
    String path1 = "";
    String path2 = "";
    EditText topic, location, price, persent;
    Button send;
    SpotsDialog dialog;
     Spinner sp1;
     String cateId="املاک";
     List<String> list;
     List<String> list_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_activity);
        importView();
        dialog.show();
        apiGetCate();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                apiAddItem();
            }
        });


        imagePickerStyle();
        ImageView add_imageOne = (ImageView) findViewById(R.id.imageView);
        add_imageOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Imageshow = R.id.imageView;
                PickImageDialog.build(setup).show(AddItemActivityActivity.this);

            }
        });
        ImageView add_imagetwo = (ImageView) findViewById(R.id.imageView2);
        add_imagetwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Imageshow = R.id.imageView2;

                PickImageDialog.build(setup).show(AddItemActivityActivity.this);

            }
        });
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                // TODO Auto-generated method stub
//                Toast.makeText(getBaseContext(), list.get(position), Toast.LENGTH_SHORT).show();
                cateId=list_id.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
    }

    private void importView() {
        topic = (EditText) findViewById(R.id.topic);
        location = (EditText) findViewById(R.id.location);
        price = (EditText) findViewById(R.id.price);
        persent = (EditText) findViewById(R.id.persent);
        send = (Button) findViewById(R.id.send);
        dialog = new SpotsDialog(this, R.style.Custom);
        sp1 = (Spinner) findViewById(R.id.spinner1);

    }

    @SuppressLint("WrongConstant")
    private void imagePickerStyle() {
        setup = new PickSetup()
                .setTitle(getResources().getString(R.string.title_upload_photo))
                .setProgressText(getResources().getString(R.string.progress_text))
                .setCancelText(getResources().getString(R.string.cancel_upload))
                .setFlip(true)
                .setMaxSize(500)
                .setCameraButtonText(getResources().getString(R.string.camera_fa))
                .setGalleryButtonText(getResources().getString(R.string.gallery_fa))
                .setIconGravity(Gravity.LEFT)
                .setButtonOrientation(LinearLayoutCompat.VERTICAL)
                .setWidth(240).setHeight(320);

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

                        PreferenceManager.getDefaultSharedPreferences(AddItemActivityActivity.this).edit().putBoolean("havetoken", false).apply();
                        startActivity(new Intent(AddItemActivityActivity.this, LogInActivity.class));
                        Toast.makeText(AddItemActivityActivity.this, "شما وارد نشده اید", Toast.LENGTH_SHORT).show();
                        finish();

                    } else if (response.body().getStatus().toString().equals("ok")) {

                        Log.d("showResponse", response.body().getCate().get(0).getCatName());

                        list = new ArrayList<String>();
                        list_id = new ArrayList<String>();
                        for (int i = 0; i < response.body().getCate().size(); i++) {
                            list.add(response.body().getCate().get(i).getCatName());
                            list_id.add(response.body().getCate().get(i).getCatId());
                        }
                        ArrayAdapter<String> adp1 = new ArrayAdapter<String>(AddItemActivityActivity.this,
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

                Toast.makeText(AddItemActivityActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });
    }

    private void apiAddItem() {
        final String url = "https://mehrdadseyfi.ir/apiWall/AddItem/AddItem.php";
        String token = PreferenceManager.getDefaultSharedPreferences(this).getString("token", "notHave");

        final RequestParams params = new RequestParams();
        File myFile1 = new File(path1);
        File myFile2 = new File(path2);
        try {
            params.put("image1", myFile1);
            params.put("image2", myFile2);
        } catch (FileNotFoundException e) {
            Log.d("serverErsponse", String.valueOf(e));

        }

        params.put("token", token);
        params.put("topic", topic.getText().toString());
        params.put("cateid", cateId);
        params.put("location", location.getText().toString());
        params.put("price", price.getText().toString());
        params.put("peresent", persent.getText().toString());


        AsyncHttpClient asycnHttpClient = new AsyncHttpClient();
        //<editor-fold desc="ssl">
        MySSLSocketFactory socketFactory = SSlCertificate.Ssl();
        asycnHttpClient.setSSLSocketFactory(socketFactory);

        asycnHttpClient.post(url, params, new TextHttpResponseHandler() {

            @Override
            public void onFailure(int i, cz.msebera.android.httpclient.Header[] headers, String s, Throwable throwable) {
                Log.d("serverErsponse", s);
                dialog.dismiss();
                Toast.makeText(AddItemActivityActivity.this, "عدم ارتباط با سرور", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int i, cz.msebera.android.httpclient.Header[] headers, String s) {
                ItemCatStatus statusService = new Gson().fromJson(s, ItemCatStatus.class);
                Log.d("serverErsponse", s);
                if (statusService.getStatus().equals("ok")) {
                    Toast.makeText(AddItemActivityActivity.this, "اگهی با موفقیت درج شد", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddItemActivityActivity.this, MainActivity.class));
                    finish();

                } else if (statusService.getStatus().equals("token_expire")) {
                    PreferenceManager.getDefaultSharedPreferences(AddItemActivityActivity.this).edit().putBoolean("havetoken", false).apply();
                    startActivity(new Intent(AddItemActivityActivity.this, LogInActivity.class));
                    Toast.makeText(AddItemActivityActivity.this, "شما وارد نشده اید", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }

            @Override
            public void onFinish() {
                dialog.dismiss();

                Log.d("serverErsponse", "end");

            }
        });


    }


    public boolean isStoragePermissionGranted() {
        String TAG = "as";
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG, "Permission is granted");
                return true;
            } else {

                Log.v(TAG, "Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                }, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG, "Permission is granted");
            return true;
        }
    }

    @Override
    public void onPickResult(PickResult pickResult) {
        if (pickResult.getError() == null) {

            //If you want the Uri.
            //Mandatory to refresh image from Uri.
            //getImageView().setImageURI(null);
            ImageView showImage = (ImageView) findViewById(Imageshow);
//            String s = getRealPathFromURI(r.getUri());
            if (isStoragePermissionGranted()) {
                urlPhoto = pickResult.getPath();
                if (Imageshow == R.id.imageView) {
                    path1 = urlPhoto;
                } else {
                    path2 = urlPhoto;
                }
                Toast.makeText(this, urlPhoto, Toast.LENGTH_SHORT).show();

            }
            Bitmap bitmap;
            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(pickResult.getUri()));
                //  ImageView showImage = (ImageView) findViewById(R.id.show_photo);
                showImage.setImageBitmap(bitmap);
                //send this encoded string to server
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            //showImage.setImageURI(r.getUri());

            //Setting the real returned image.
            //getImageView().setImageURI(r.getUri());

            //If you want the Bitmap.

            //Image path
            //r.getPath();
        } else {
            //Handle possible errors
            Toast.makeText(this, pickResult.getError().getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
