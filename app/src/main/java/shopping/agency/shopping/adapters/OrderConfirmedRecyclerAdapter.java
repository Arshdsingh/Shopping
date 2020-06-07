package shopping.agency.shopping.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.LinkedList;

import shopping.agency.shopping.ItemClothActivity;
import shopping.agency.shopping.MyApplication;
import shopping.agency.shopping.OrderItems;
import shopping.agency.shopping.ProductItem;
import shopping.agency.shopping.R;
import shopping.agency.shopping.SqliteDatabaseDb;
import shopping.agency.shopping.WebLinks;


public class OrderConfirmedRecyclerAdapter extends RecyclerView.Adapter<OrderConfirmedRecyclerAdapter.MenuRecyclerAdapterView> {
    Context context;
    LinkedList<OrderItems> linkedList;
    FirebaseStorage storage = FirebaseStorage.getInstance();

    public OrderConfirmedRecyclerAdapter(Context context, LinkedList<OrderItems> linkedList)
    {
        this.context=context;
        this.linkedList=linkedList;
    }
         Integer counter=1,limit=10;
    @NonNull
    @Override
    public MenuRecyclerAdapterView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(context).inflate(R.layout.productorderitem, null);
        return new MenuRecyclerAdapterView(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MenuRecyclerAdapterView holder, final int position) {
    holder.title.setText(linkedList.get(position).getProductItems().getTitle()+"");
    holder.price.setText("$ "+linkedList.get(position).getProductItems().getPrice()+"/-");
        holder. addtocart.setVisibility(View.GONE);
    holder.delete.setVisibility(View.GONE);
        holder.delete.setVisibility(View.GONE);
        holder.plusminus.setVisibility(View.GONE);

        counter=1;limit=10;
          try {
              if(linkedList.get(position).getProductItems().getNoofitems()>1){
                  counter=  linkedList.get(position).getProductItems().getNoofitems();
              }
              else
              {
                  counter=1;
              }
          }
          catch (Exception e)
          {
              e.printStackTrace();
          }
        holder.itemcount.setText(counter+"");


        StorageReference storageReference = storage.getReferenceFromUrl(WebLinks.IMAGEPATH ).child(linkedList.get(position).getProductItems().getImageurl());
        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                System.out.println("uri = " + uri);
                DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                        .showImageForEmptyUri(R.drawable.svg1)
                        .showImageOnFail(R.drawable.svg1)
                        .showImageOnLoading(R.drawable.svg2)
                        .cacheInMemory(true)
                        .considerExifParams(true)
                        .bitmapConfig(Bitmap.Config.RGB_565)
                        .build();
                MyApplication.getInstance().getInstanceImage() .loadImage(uri.toString(),options, new SimpleImageLoadingListener() {
                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        super.onLoadingComplete(imageUri, view, loadedImage);
                        holder.imageView2.setImageBitmap(loadedImage);
                    }

                    @Override
                    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                        super.onLoadingFailed(imageUri, view, failReason);
                     holder.imageView2.setImageResource(R.drawable.svg2);
                    }

                    @Override
                    public void onLoadingStarted(String imageUri, View view) {
                        super.onLoadingStarted(imageUri, view);
                        holder.imageView2.setImageResource(R.drawable.svg2);
                    }
                });
            }

        });
        holder.add.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(counter>=10)
        {
            Toast.makeText(context, "Item can not be exceeded more than "+limit, Toast.LENGTH_SHORT).show();

        }
        else
        {
            ++counter;
        }
        holder.itemcount.setText(counter+"");
        ProductItem productItem=new ProductItem();
        productItem.setProduct_id(linkedList.get(position).getProductItems().getProduct_id());
        productItem.setId(linkedList.get(position).getProductItems().getId());
        productItem.setTitle(linkedList.get(position).getProductItems().getTitle());
        productItem.setPrice(linkedList.get(position).getProductItems().getPrice());
        productItem.setImageurl(linkedList.get(position).getProductItems().getImageurl());
        productItem.setNoofitems(counter);
        new SqliteDatabaseDb(context).updateCartlist(productItem,linkedList.get(position).getProductItems().getProduct_id());


    }

});
holder.remove.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(counter<=1)
        {
            Toast.makeText(context, "Minimum 1 item is required", Toast.LENGTH_SHORT).show();
        }
        else
        {
            --counter;
        }
        ProductItem productItem=new ProductItem();
        productItem.setProduct_id(linkedList.get(position).getProductItems().getProduct_id());
        productItem.setId(linkedList.get(position).getProductItems().getId());
        productItem.setTitle(linkedList.get(position).getProductItems().getTitle());
        productItem.setPrice(linkedList.get(position).getProductItems().getPrice());
        productItem.setImageurl(linkedList.get(position).getProductItems().getImageurl());
        productItem.setNoofitems(counter);
        new SqliteDatabaseDb(context).updateCartlist(productItem,linkedList.get(position).getProductItems().getProduct_id());

        holder.itemcount.setText(counter+"");
    }
});
    holder.linearLayout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(context, ItemClothActivity.class);
            intent.putExtra("product_id",linkedList.get(position).getProductItems().getProduct_id());
            intent.putExtra("id",linkedList.get(position).getProductItems().getId());
            intent.putExtra("title",linkedList.get(position).getProductItems().getTitle());
            intent.putExtra("price",linkedList.get(position).getProductItems().getPrice());
            intent.putExtra("imageurl",linkedList.get(position).getProductItems().getImageurl());

            context.startActivity(intent);
        }
    });
    holder.delete.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           new SqliteDatabaseDb(context).delete(linkedList.get(position).getProductItems().getId(),linkedList.get(position).getProductItems().getProduct_id());
            Toast.makeText(context, "Item removed from cart", Toast.LENGTH_SHORT).show();
            linkedList.remove(linkedList.get(position));
            notifyDataSetChanged();
        }
    });

        holder.addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SqliteDatabaseDb(context).insert(linkedList.get(position).getProductItems().getTitle(),linkedList.get(position).getProductItems().getId(),linkedList.get(position).getProductItems().getProduct_id(),linkedList.get(position).getProductItems().getPrice(),linkedList.get(position).getProductItems().getImageurl(),counter);
                Toast.makeText(context, "Item added to cart", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }
        });

        if(holder.title.getText().toString().contains("Nice Hat"))
        {
            holder.imageView2.setImageResource(R.drawable.hat);
        }
        else   if(holder.title.getText().toString().contains("Nice Sunglass"))
        {
            holder.imageView2.setImageResource(R.drawable.sunglass1);

        }        else   if(holder.title.getText().toString().contains("Beautiful Cap"))
        {
            holder.imageView2.setImageResource(R.drawable.cap1);

        }
    //holder.icon.setImageResource(linkedList.get(position).getIcon());
    }

    @Override
    public int getItemCount() {
        return linkedList.size();
    }

    public class MenuRecyclerAdapterView extends RecyclerView.ViewHolder{
      ImageView icon,imageView2;
      TextView title;
      TextView price,itemcount;
        LinearLayout linearLayout;
        Integer counter=1,limit=10;
        ImageView add,remove;
        Button delete,addtocart;
        LinearLayout plusminus;
        public    MenuRecyclerAdapterView(View view)
        {
            super(view);

            add=(ImageView)view.findViewById(R.id.add);
            remove=(ImageView)view.findViewById(R.id.remove);
            delete=(Button) view.findViewById(R.id.delete);
            addtocart=(Button) view.findViewById(R.id.addtocart);
            itemcount=(TextView) view.findViewById(R.id.itemcount);
            icon=(ImageView)view.findViewById(R.id.icon);
            imageView2=(ImageView)view.findViewById(R.id.imageView2);
            title=(TextView) view.findViewById(R.id.title);
            price=(TextView) view.findViewById(R.id.price);
            linearLayout=(LinearLayout) view.findViewById(R.id.layout);
            plusminus=(LinearLayout) view.findViewById(R.id.plusminus);

        }

    }
}
