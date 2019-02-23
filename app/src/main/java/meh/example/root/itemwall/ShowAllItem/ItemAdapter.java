package meh.example.root.itemwall.ShowAllItem;

import android.content.Context;
import android.content.res.Configuration;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


import meh.example.root.itemwall.Model.Item;
import meh.example.root.itemwall.R;

/**
 * Created by user on 15/06/2018.
 */

public class ItemAdapter extends BaseAdapter {
  private   Context mContext;
   private   List<Item> models;


    public ItemAdapter(List<Item> models, Context mContext) {

        this.mContext = mContext;

        this.models = models;

    }

    @Override

    public int getCount() {

        return models.size();

    }


    @Override

    public Object getItem(int position) {

        return null;

    }

    @Override

    public long getItemId(int position) {

        return position;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //<editor-fold desc="map to persian">

        //</editor-fold>

//        @SuppressLint("ViewHolder")
        View rowView = LayoutInflater.from(mContext).inflate(R.layout.item_show, parent, false);

        ImageView item_image=(ImageView)rowView.findViewById(R.id.item_image);

        Picasso.get().load("https://mehrdadseyfi.ir/apiWall/AddItem/"+models.get(position).getImageUrl1()).into(item_image);
        Log.d("imageUrl","https://mehrdadseyfi.ir/apiWall/AddItem/"+models.get(position).getImageUrl1());
        TextView topic=(TextView)rowView.findViewById(R.id.item_topic);
        topic.setText(models.get(position).getItemTopic()) ;
        TextView price=(TextView)rowView.findViewById(R.id.item_price);
        price.setText(models.get(position).getItemPrice()+"  "+"تومان") ;
        TextView location=(TextView)rowView.findViewById(R.id.item_location);
        location.setText(models.get(position).getItemLocation());

//        String url = "https://api.cedarmaps.com/v1/static/light/"+models.get(position).getLat()+","+models.get(position).getLang()+",17/600x270@2x?access_token=3327583d087bac7542ebacc4537c4e01dc9926ca&markers=marker-default|"+ models.get(position).getLat()+","+models.get(position).getLang();

        //String url = "http://maps.googleapis.com/maps/api/staticmap?zoom=16&size=800x350&markers=icon:https://api.upraiz.ir/marker.png|" + models.get(position).getLat() + "," + models.get(position).getLang();
//      Picasso.with(mContext).load(url).into(image_lesson);
//      addresss.setText(models.get(position).getAddress());
    //    getDataFromGoogle(Double.parseDouble(models.get(position).getLat()),Double.parseDouble(models.get(position).getLang()));
//
//
//        LinearLayout lesson_liner = (LinearLayout) rowView.findViewById(R.id.lesson_liner);
//        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
//        lesson_liner.startAnimation(animation);
//        lastPosition = position;

        return rowView;
    }



}
