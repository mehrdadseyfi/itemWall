package meh.example.root.itemwall.AddItem;

import android.annotation.SuppressLint;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

import java.io.File;
import java.io.FileNotFoundException;

import meh.example.root.itemwall.Auth.LogInActivity;
import meh.example.root.itemwall.MainActivity;
import meh.example.root.itemwall.Model.ItemCatStatus;
import meh.example.root.itemwall.Model.ItemCateModel;
import meh.example.root.itemwall.R;
import meh.example.root.itemwall.RetroFit.APIClient;
import meh.example.root.itemwall.RetroFit.APIinterface;
import meh.example.root.itemwall.RetroFit.UserAuth;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddItemActivityActivity extends AppCompatActivity  implements IPickResult {
    PickSetup setup;
    String urlPhoto="no";
    int Imageshow=0;
    String path1="";
    String path2="";
EditText topic,location,price,persent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_activity);
        topic=(EditText)findViewById(R.id.topic);
        location=(EditText)findViewById(R.id.location);
        price=(EditText)findViewById(R.id.price);
        persent=(EditText)findViewById(R.id.persent);



        imagePickerStyle();
        ImageView add_imageOne = (ImageView) findViewById(R.id.imageView);
        add_imageOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Imageshow=R.id.imageView;
                PickImageDialog.build(setup).show(AddItemActivityActivity.this);

            }
        });
        ImageView add_imagetwo = (ImageView) findViewById(R.id.imageView2);
        add_imagetwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Imageshow=R.id.imageView2;

                PickImageDialog.build(setup).show(AddItemActivityActivity.this);

            }
        });
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
        String token = PreferenceManager.getDefaultSharedPreferences(this).getString("token", "notHave");

        APIinterface apIinterface = APIClient.getClient().create(APIinterface.class);

        Call<ItemCatStatus> call = apIinterface.getCategoery(token);
        // dar sf gharar dadan
        call.enqueue(new Callback<ItemCatStatus>() {
            @Override
            public void onResponse(Call<ItemCatStatus> call, Response<ItemCatStatus> response) {
                ////check connect succeful   status code
                if (response.isSuccessful()) {
                    Log.d("retrofitResponse", response.body().getStatus().toString());
                    if (response.body().getStatus().toString().equals("token_expire")) {

                        PreferenceManager.getDefaultSharedPreferences(AddItemActivityActivity.this).edit().putBoolean("havetoken",false).apply();
                        startActivity(new Intent(AddItemActivityActivity.this, LogInActivity.class));
                        Toast.makeText(AddItemActivityActivity.this, "شما وارد نشده اید", Toast.LENGTH_SHORT).show();

                    } else if (response.body().getStatus().toString().equals("ok")) {

                        Log.d("showResponse",response.body().getCate().get(0).getCatName());

                    }


                } else if (response.code() == 404) {
                    Log.d("retrofitResponse", "404");
                }
            }

            @Override
            public void onFailure(Call<ItemCatStatus> call, Throwable throwable) {
                Toast.makeText(AddItemActivityActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });
    }
    public void apiAddItem() {
        String token = PreferenceManager.getDefaultSharedPreferences(this).getString("token", "notHave");

        APIinterface apIinterface = APIClient.getClient().create(APIinterface.class);
        File image = new File(path1);
           File image2 = new File(path2);

        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), path1);
        RequestBody requestFile2 = RequestBody.create(MediaType.parse("multipart/form-data"), path2);

        MultipartBody.Part multipartBody =MultipartBody.Part.createFormData("file",image.getName(),requestFile);
        MultipartBody.Part multipartBody2 =MultipartBody.Part.createFormData("file",image.getName(),requestFile2);

        Call<ItemCatStatus> call = apIinterface.addItem(token,topic.getText().toString(),"10",location.getText().toString(),price.getText().toString(),persent.getText().toString(),multipartBody,multipartBody2);
        // dar sf gharar dadan
        call.enqueue(new Callback<ItemCatStatus>() {
            @Override
            public void onResponse(Call<ItemCatStatus> call, Response<ItemCatStatus> response) {
                ////check connect succeful   status code
                if (response.isSuccessful()) {
                    Log.d("retrofitResponse", response.body().getStatus().toString());
                    if (response.body().getStatus().toString().equals("token_expire")) {

                        PreferenceManager.getDefaultSharedPreferences(AddItemActivityActivity.this).edit().putBoolean("havetoken",false).apply();
                        startActivity(new Intent(AddItemActivityActivity.this, LogInActivity.class));
                        Toast.makeText(AddItemActivityActivity.this, "شما وارد نشده اید", Toast.LENGTH_SHORT).show();

                    } else if (response.body().getStatus().toString().equals("ok")) {

                        Log.d("showResponse",response.body().getCate().get(0).getCatName());

                    }


                } else if (response.code() == 404) {
                    Log.d("retrofitResponse", "404");
                }
            }

            @Override
            public void onFailure(Call<ItemCatStatus> call, Throwable throwable) {
                Toast.makeText(AddItemActivityActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();


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
                if(Imageshow==R.id.imageView){
                    path1=urlPhoto;
                }else{
                    path2=urlPhoto;
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
}
