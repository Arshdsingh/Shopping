package shopping.agency.shopping;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.firebase.iid.FirebaseInstanceId;

import java.util.LinkedList;


import static android.content.Context.MODE_PRIVATE;

public class SqliteDatabaseDb extends SQLiteOpenHelper {
    public static int version = 1;
    String refreshedToken;

    public static String databasename = "Userdb";
    public static String title = "title";
    public static String product_id = "product_id";
    public static String pic = "pic";
    public static String price = "price";
    public static String id = "id";
    public static String noofitems = "noofitems";
    public static String usertable = "cartstable";
    public static String usertable1 = "orderstable";
    public static String description = "description";

    Context context;

    public SqliteDatabaseDb(Context context) {
        super(context, databasename, null, version);
        this.context = context;
        refreshedToken = FirebaseInstanceId.getInstance().getToken();

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //String sqlitequery="create table "+usertable+"(username varchar(155) not null, password varchar(155) not null, status varchar(155))";
        String sqlitequery = "create table " + usertable + "(title varchar(155), id varchar(155) , product_id varchar(155), price varchar(155),pic varchar(244),noofitems integer)";
        String sqlitequery1 = "create table " + usertable1 + " (orderid integer primary key autoincrement,username varchar(155),title varchar(155), id varchar(155) , product_id varchar(155), price varchar(155),pic varchar(244),noofitems integer)";

        sqLiteDatabase.execSQL(sqlitequery);
        sqLiteDatabase.execSQL(sqlitequery1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sqlitequery = "drop table " + usertable;
        String sqlitequery1 = "drop table " + usertable1;
        sqLiteDatabase.execSQL(sqlitequery);
        sqLiteDatabase.execSQL(sqlitequery1);

        onCreate(sqLiteDatabase);
    }

    public LinkedList<ProductItem> getCartList() {
        LinkedList<ProductItem> arrayList = new LinkedList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        boolean flag = false;
        Cursor cursor = sqLiteDatabase.query(usertable, null, null, null, null, null, null);
        if (cursor != null) {

            while (cursor.moveToNext()) {
                ProductItem item = new ProductItem();
                item.setId(cursor.getString(cursor.getColumnIndex(id)));
                item.setPrice(cursor.getString(cursor.getColumnIndex(price)));
                item.setTitle(cursor.getString(cursor.getColumnIndex(title)));
                item.setImageurl(cursor.getString(cursor.getColumnIndex(pic)));
                item.setProduct_id(cursor.getString(cursor.getColumnIndex(product_id)));
                item.setNoofitems(cursor.getInt(cursor.getColumnIndex(noofitems)));
                arrayList.add(item);
            }
        }
        return arrayList;
    }
    public void updateCartlist( ProductItem item,String product_id1) {
        LinkedList<ProductItem> arrayList = new LinkedList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        boolean flag = false;
        ContentValues contentValues=new ContentValues();
        contentValues.put(title, item.getTitle());
        contentValues.put(id, item.getId());
        contentValues.put(product_id, product_id1);
        contentValues.put(price, item.getPrice());
        contentValues.put(pic, item.getImageurl());
        contentValues.put(noofitems, item.getNoofitems());
        long ii = sqLiteDatabase.update(usertable,contentValues,product_id+"=? and id=?",new String[]{product_id1,item.getId()});
        System.out.println("ii = " + item.getNoofitems());
        sqLiteDatabase.close();
    }
    public void updateCartlist( ProductItem1 item,String product_id1) {
        LinkedList<ProductItem> arrayList = new LinkedList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        boolean flag = false;
        ContentValues contentValues=new ContentValues();
        contentValues.put(title, item.getTitle());
        contentValues.put(id, item.getId());
        contentValues.put(product_id, product_id1);
        contentValues.put(price, item.getPrice());
        contentValues.put(pic, item.getImageurl());
        contentValues.put(noofitems, item.getNoofitems());
        long ii = sqLiteDatabase.update(usertable,contentValues,product_id+"=? and id=?",new String[]{product_id1,item.getId()});
        System.out.println("ii = " + item.getNoofitems());
        sqLiteDatabase.close();
    }
    public void updateOrderlist( ProductItem item,String product_id1,String user) {
        LinkedList<ProductItem> arrayList = new LinkedList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        boolean flag = false;
        ContentValues contentValues=new ContentValues();
        contentValues.put(title, item.getTitle());
        contentValues.put(id, item.getId());
        contentValues.put(product_id, product_id1);
        contentValues.put(price, item.getPrice());
        contentValues.put(pic, item.getImageurl());
        contentValues.put(noofitems, item.getNoofitems());
        long ii = sqLiteDatabase.update(usertable1,contentValues,product_id+"=? and id=? and username=?",new String[]{product_id1,item.getId(),user});
        System.out.println("ii = " + item.getNoofitems());
        sqLiteDatabase.close();
    }
    public LinkedList<OrderItems> getUserOrderedList() {
        LinkedList<OrderItems> arrayList = new LinkedList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        boolean flag = false;
        Cursor cursor = sqLiteDatabase.query(usertable1, null, null, null, null, null, null);
        if (cursor != null) {

            while (cursor.moveToNext()) {
              ProductItem item = new ProductItem();
                item.setId(cursor.getString(cursor.getColumnIndex(id)));
                item.setPrice(cursor.getString(cursor.getColumnIndex(price)));
                item.setTitle(cursor.getString(cursor.getColumnIndex(title)));
                item.setImageurl(cursor.getString(cursor.getColumnIndex(pic)));
                item.setProduct_id(cursor.getString(cursor.getColumnIndex(product_id)));
                item.setNoofitems(cursor.getInt(cursor.getColumnIndex(noofitems)));

                OrderItems orderItems=new OrderItems();
                orderItems.setUsername(cursor.getString(cursor.getColumnIndex("username")));

                orderItems.setProductItems(item);
                arrayList.add(orderItems);
            }
        }
        return arrayList;
    }

    public long insert(String title1, String id1, String product_id1, String price1, String pic1, int n) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        long flag = 0;
        ContentValues contentValues = new ContentValues();
        contentValues.put(title, title1);
        contentValues.put(id, id1);
        contentValues.put(product_id, product_id1);
        contentValues.put(price, price1);
        contentValues.put(pic, pic1);
        contentValues.put(noofitems, n);
        flag = sqLiteDatabase.insert(usertable, null, contentValues);
        //   checkUserisValid();
        sqLiteDatabase.close();

        return flag;
    }

    public long insertOrder(String title1, String id1, String product_id1, String price1, String pic1, int n, String username) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        long flag = 0;
        ContentValues contentValues = new ContentValues();
        contentValues.put(title, title1);
        contentValues.put(id, id1);
        contentValues.put(product_id, product_id1);
        contentValues.put(price, price1);
        contentValues.put(pic, pic1);
        contentValues.put(noofitems, n);
        contentValues.put("username", username);

        flag = sqLiteDatabase.insert(usertable1, null, contentValues);
        //   checkUserisValid();
        System.out.println("orderinserted = " );
        sqLiteDatabase.close();

        return flag;
    }

    public long delete(String id, String product_id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        long flag = 0;
        ContentValues contentValues = new ContentValues();
        flag = sqLiteDatabase.delete(usertable, "id= ? and product_id= ? ", new String[]{id, product_id});
        System.out.println("username1 = " + getUsername());
        // System.out.println("password = " + password1);
        sqLiteDatabase.close();
        SharedPreferences.Editor editor = context.getSharedPreferences("userdata", MODE_PRIVATE).edit();
        // editor.putString("password",password1);
        editor.commit();
        return flag;
    }

    public long deleteAll() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        long flag = 0;
        ContentValues contentValues = new ContentValues();
        flag = sqLiteDatabase.delete(usertable, null, null);
        System.out.println("username1 = " + getUsername());
        // System.out.println("password = " + password1);
        sqLiteDatabase.close();
        SharedPreferences.Editor editor = context.getSharedPreferences("userdata", MODE_PRIVATE).edit();
        // editor.putString("password",password1);
        editor.commit();
        return flag;
    }

    public String getUsername() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String usernamedatabase = "";
        Cursor cursor = sqLiteDatabase.query(usertable, null, null, null, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {

                // usernamedatabase=cursor.getString(cursor.getColumnIndex(username));
            }
        }
        return usernamedatabase;
    }
}
