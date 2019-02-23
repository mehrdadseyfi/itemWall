package meh.example.root.itemwall.AddItem;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import meh.example.root.itemwall.Model.Item;
import meh.example.root.itemwall.Model.ItemCatStatus;
import meh.example.root.itemwall.Model.ItemCateModel;
import meh.example.root.itemwall.R;

/**
 * Created by user on 15/06/2018.
 */

public class CateAdapter extends BaseAdapter {
  private   Context mContext;
   private   List<ItemCateModel> models;


    public CateAdapter(List<ItemCateModel> models, Context mContext) {

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
        View rowView = LayoutInflater.from(mContext).inflate(R.layout.cate_show, parent, false);


        TextView location=(TextView)rowView.findViewById(R.id.cate_name);
        location.setText(models.get(position).getCatName());

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
