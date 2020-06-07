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

import shopping.agency.shopping.MyApplication;
import shopping.agency.shopping.ProductItem;
import shopping.agency.shopping.ProductItem1;
import shopping.agency.shopping.ProductListsActivity;
import shopping.agency.shopping.R;
import shopping.agency.shopping.WebLinks;


public class ProductSubRecyclerAdapter extends RecyclerView.Adapter<ProductSubRecyclerAdapter.MenuRecyclerAdapterView> {
    Context context;
    LinkedList<ProductItem1> linkedList;
    FirebaseStorage storage = FirebaseStorage.getInstance();

    public ProductSubRecyclerAdapter(Context context, LinkedList<ProductItem1> linkedList)
    {
        this.context=context;
        this.linkedList=linkedList;
    }
         Integer counter=1,limit=10;
    @NonNull
    @Override
    public MenuRecyclerAdapterView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(context).inflate(R.layout.productitemlayout, null);
        return new MenuRecyclerAdapterView(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MenuRecyclerAdapterView holder, final int position) {
    holder.title.setText(linkedList.get(position).getTitle()+"");
    holder.price.setText("$ "+linkedList.get(position).getPrice()+"/-");
    holder.delete.setVisibility(View.GONE);

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

        holder.imageView2.setImageResource(linkedList.get(position).getImageurl());

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
       // holder.itemcount.setText(counter+"");


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
    }
});
    holder.linearLayout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(context, ProductListsActivity.class);
            intent.putExtra("category",linkedList.get(position).getCateogory());
            context.startActivity(intent);
        }
    });
    holder.delete.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
      /*      new SqliteDatabaseDb(context).delete(linkedList.get(position).getId(),linkedList.get(position).getProduct_id());
            Toast.makeText(context, "Item removed from cart", Toast.LENGTH_SHORT).show();
            linkedList.remove(linkedList.get(position));
            notifyDataSetChanged();*/
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
        Button delete;
        public    MenuRecyclerAdapterView(View view)
        {
            super(view);

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
