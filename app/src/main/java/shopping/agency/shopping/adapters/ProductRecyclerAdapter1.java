package shopping.agency.shopping.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
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

import com.nostra13.universalimageloader.core.DisplayImageOptions;

import java.util.LinkedList;

import shopping.agency.shopping.ItemActivity;
import shopping.agency.shopping.ProductItem;
import shopping.agency.shopping.ProductItem1;
import shopping.agency.shopping.ProductListsActivity;
import shopping.agency.shopping.R;
import shopping.agency.shopping.SqliteDatabaseDb;


public class ProductRecyclerAdapter1 extends RecyclerView.Adapter<ProductRecyclerAdapter1.MenuRecyclerAdapterView> {
    Context context;
    LinkedList<ProductItem1> linkedList;
    public ProductRecyclerAdapter1(Context context, LinkedList<ProductItem1> linkedList)
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
    holder.title.setText(linkedList.get(position).getTitle()+"");
    holder.price.setText("$ "+linkedList.get(position).getPrice()+"/-");
        holder.delete.setVisibility(View.GONE);

        holder.imageView2.setImageResource(linkedList.get(position).getImageurl());
        counter=1;limit=10;
          try {
              if(linkedList.get(position).getNoofitems()>1){
                  counter=  linkedList.get(position).getNoofitems();
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


        //  holder.imageView2.se(linkedList.get(position).getImageurl());
     /*   MyApplication.getInstance().getInstanceImage() .loadImage(WebLinks.IMAGEPATH +linkedList.get(position).getImageurl().replace(" ","%20")+"",options, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                super.onLoadingComplete(imageUri, view, loadedImage);
                holder.imageView2.setImageBitmap(loadedImage);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                super.onLoadingFailed(imageUri, view, failReason);
                holder.imageView2.setImageResource(R.drawable.svg1);
            }

            @Override
            public void onLoadingStarted(String imageUri, View view) {
                super.onLoadingStarted(imageUri, view);
                holder.imageView2.setImageResource(R.drawable.svg2);
            }
        });*/
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
        ProductItem1 productItem=new ProductItem1();
        productItem.setProduct_id(linkedList.get(position).getProduct_id());
        productItem.setId(linkedList.get(position).getId());
        productItem.setTitle(linkedList.get(position).getTitle());
        productItem.setPrice(linkedList.get(position).getPrice());
        productItem.setImageurl(linkedList.get(position).getImageurl());
        productItem.setNoofitems(counter);
        new SqliteDatabaseDb(context).updateCartlist(productItem,linkedList.get(position).getProduct_id());

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

        holder.itemcount.setText(counter+"");
        ProductItem1 productItem=new ProductItem1();
        productItem.setProduct_id(linkedList.get(position).getProduct_id());
        productItem.setId(linkedList.get(position).getId());
        productItem.setTitle(linkedList.get(position).getTitle());
        productItem.setPrice(linkedList.get(position).getPrice());
        productItem.setImageurl(linkedList.get(position).getImageurl());
        productItem.setNoofitems(counter);
        new SqliteDatabaseDb(context).updateCartlist(productItem,linkedList.get(position).getProduct_id());

    }
});
    holder.linearLayout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(context, ItemActivity.class);
            intent.putExtra("product_id",linkedList.get(position).getProduct_id());
            intent.putExtra("id",linkedList.get(position).getId());
            intent.putExtra("title",linkedList.get(position).getTitle());
            intent.putExtra("price",linkedList.get(position).getPrice());
            intent.putExtra("imageurl",linkedList.get(position).getImageurl());
            intent.putExtra("noofitems",linkedList.get(position).getNoofitems());

            context.startActivity(intent);
        }
    });
    holder.delete.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           new SqliteDatabaseDb(context).delete(linkedList.get(position).getId(),linkedList.get(position).getProduct_id());
            Toast.makeText(context, "Item removed from cart", Toast.LENGTH_SHORT).show();
            linkedList.remove(linkedList.get(position));
            notifyDataSetChanged();
        }
    });
        holder.addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SqliteDatabaseDb(context).insert(linkedList.get(position).getTitle(),linkedList.get(position).getId(),linkedList.get(position).getProduct_id(),linkedList.get(position).getPrice(),linkedList.get(position).getImageurl()+"",counter);
                Toast.makeText(context, "Item added to cart", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
                ProductItem1 productItem=new ProductItem1();
                productItem.setProduct_id(linkedList.get(position).getProduct_id());
                productItem.setId(linkedList.get(position).getId());
                productItem.setTitle(linkedList.get(position).getTitle());
                productItem.setPrice(linkedList.get(position).getPrice());
                productItem.setImageurl(linkedList.get(position).getImageurl());
                productItem.setNoofitems(counter);
                new SqliteDatabaseDb(context).updateCartlist(productItem,linkedList.get(position).getProduct_id());

            }
        });
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
        public    MenuRecyclerAdapterView(View view)
        {
            super(view);
            addtocart=(Button) view.findViewById(R.id.addtocart);

            add=(ImageView)view.findViewById(R.id.add);
            remove=(ImageView)view.findViewById(R.id.remove);
            delete=(Button) view.findViewById(R.id.delete);
            itemcount=(TextView) view.findViewById(R.id.itemcount);
            icon=(ImageView)view.findViewById(R.id.icon);
            imageView2=(ImageView)view.findViewById(R.id.imageView2);
            title=(TextView) view.findViewById(R.id.title);
            price=(TextView) view.findViewById(R.id.price);
            linearLayout=(LinearLayout) view.findViewById(R.id.layout);

        }

    }
}
