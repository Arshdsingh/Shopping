package shopping.agency.shopping;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedHashSet;
import java.util.LinkedList;

import shopping.agency.shopping.adapters.MyCartInterface;
import shopping.agency.shopping.adapters.OrderRecyclerAdapter;


public class OrderListFragment extends Fragment implements MyCartInterface {
    RecyclerView recyclerView;
    String product_id;
    Button placeorder;
    TextView totalprice;
    Context context;
        LinkedList<ProductItem> arr=new LinkedList<>();
MyCartInterface myCartInterface;
    public OrderListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle=getArguments();
        View view=inflater.inflate(R.layout.orderlistplace, container, false);;
        myCartInterface=this;
        TextView textView=(TextView)view.findViewById(R.id.name);
        totalprice=(TextView)view.findViewById(R.id.totalprice);
         placeorder=(Button) view.findViewById(R.id.placeorder);
        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        context=view.getContext();

           // textView.setText("Deliciousness jumping into the mouth");
        arr=new SqliteDatabaseDb(context).getCartList();
        String fontPath = "font/dancing.otf";
        // Loading Font Face
//        Typeface tf = Typeface.createFromAsset(context.getAssets(), fontPath);

        // Applying font
        //textView.setTypeface(tf);        // Inflate the layout for this fragment
        if(arr!=null)
        {
            if(arr.size()>0) {
                arr = new LinkedList<>(new LinkedHashSet<>(arr));
                recyclerView.setAdapter(new OrderRecyclerAdapter(context, arr,this));
            }else
            {
                view.findViewById(R.id.nocontent).setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            }
        }
        else {
            view.findViewById(R.id.nocontent).setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);

        }
        placeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (arr.size()>0) {
                  //  new SqliteDatabaseDb(context).deleteAll();
                  //  Toast.makeText(context, "Order placed successfully.", Toast.LENGTH_SHORT).show();
                   // ((Activity) context).finish();
                    if(getContext().getSharedPreferences("userdata",Context.MODE_PRIVATE).getString("username","")!=null)
                    {
                        if(getContext().getSharedPreferences("userdata",Context.MODE_PRIVATE).getString("username","").equalsIgnoreCase(""))
                        {
                            Intent intent=new Intent(context,LoginActivity.class);
                            intent.putExtra("products","proudctsicart");
                            context.startActivity(intent);

                        }
                        else {
                            context.startActivity(new Intent(context,AddressActivity.class));
                        }
                    }
                    else {
                        context.startActivity(new Intent(context,AddressActivity.class));

                    }

                }
                else {
                    Toast.makeText(context, "Cart is empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }


    @Override
    public void getData(LinkedList<ProductItem> cartar) {
    //this.arr=cartar;
        LinkedList<ProductItem> cartar1=new SqliteDatabaseDb(context).getCartList();
        double pricetotal=0;
        if(cartar1!=null) {
            if (cartar1.size() > 0) {
                for (int i=0;i<cartar1.size();i++)
                {
                    ProductItem item=cartar1.get(i);
                    System.out.println("item.getPrice() = " + item.getPrice());

                    try
                        {
                            pricetotal+=Double.parseDouble(item.getPrice())*item.getNoofitems();
                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                }
            }
        }
        totalprice.setText(pricetotal+"");
    }

    @Override
    public void onResume() {
        super.onResume();
        LinkedList<ProductItem> cartar1=new SqliteDatabaseDb(context).getCartList();

        getData(cartar1);
    }
}
