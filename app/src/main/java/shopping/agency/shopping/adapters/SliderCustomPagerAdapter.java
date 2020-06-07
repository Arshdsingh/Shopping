package shopping.agency.shopping.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.viewpager.widget.PagerAdapter;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.ArrayList;
import java.util.HashMap;

import shopping.agency.shopping.MyApplication;
import shopping.agency.shopping.R;


public class SliderCustomPagerAdapter extends PagerAdapter {
    Context context;
    HashMap<String,String> images;
    LayoutInflater layoutInflater;


    public SliderCustomPagerAdapter(Context context,  HashMap<String,String> images) {
        this.context = context;
        this.images = images;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = layoutInflater.inflate(R.layout.item, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
        //imageView.setImageResource(images[position]);
        if(images!=null)
        {
            ArrayList<String> arrayList=new ArrayList<>(images.keySet());
                              String imageurl=  images.get(arrayList.get(position));
            //  Picasso.with(context).load(imageurl).resize(250, 250).centerCrop().placeholder(R.drawable.logo).into( imageView);

          DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                    .resetViewBeforeLoading(true)
                    .showImageForEmptyUri(R.drawable.bestshopping)
                    .showImageOnFail(R.drawable.bestshopping)
                    .showImageOnLoading(R.drawable.bestshopping)
                    .cacheInMemory(true)
                    .considerExifParams(true)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .build();
            //  MyApplication.getInstance().getInstanceImage().displayImage(linkedList.get(position).getImageurl()+"",holder.icon,options);
            MyApplication.getInstance().getInstanceImage() .loadImage(imageurl,options, new SimpleImageLoadingListener() {
                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    super.onLoadingComplete(imageUri, view, loadedImage);
                    imageView.setBackgroundDrawable(new BitmapDrawable(loadedImage));
                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                    super.onLoadingFailed(imageUri, view, failReason);
                    imageView.setBackgroundResource(R.drawable.bestshopping);
                }

                @Override
                public void onLoadingStarted(String imageUri, View view) {
                    super.onLoadingStarted(imageUri, view);
                    imageView.setBackgroundResource(R.drawable.bestshopping);
                }
            });

        }
        container.addView(itemView);

        //listening to image click
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(context, "you clicked image " + (position + 1), Toast.LENGTH_LONG).show();
            }
        });

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}