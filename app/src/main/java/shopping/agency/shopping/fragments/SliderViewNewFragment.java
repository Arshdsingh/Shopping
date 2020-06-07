package shopping.agency.shopping.fragments;

import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;


import com.google.android.material.tabs.TabLayout;

import java.util.HashMap;

import shopping.agency.shopping.R;
import shopping.agency.shopping.adapters.SliderCustomPagerAdapter;


public class SliderViewNewFragment extends Fragment {


    public SliderViewNewFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view= inflater.inflate(R.layout.fragment_slider_view_new, container, false);
        AutoScrollViewPager viewPager = (AutoScrollViewPager) view.findViewById(R.id.photos_viewpager);
        viewPager.startAutoScroll();
        viewPager.setInterval(3000);
        viewPager.setCycle(true);
        viewPager.setStopScrollWhenTouch(true);

        HashMap<String,String> file_maps = new HashMap<String, String>();

        file_maps.put("first","https://previews.123rf.com/images/mikalaimanyshau/mikalaimanyshau1601/mikalaimanyshau160100083/50304057-colourful-shopping-vector-flat-banner-for-your-business-web-sites-etc-quality-design-illustrations-e.jpg");
        file_maps.put("second","https://cdn1.vectorstock.com/i/1000x1000/38/65/online-shopping-banner-template-vector-20863865.jpg");
        file_maps.put("third","https://cdn4.vectorstock.com/i/1000x1000/19/03/online-shopping-banner-horizontal-concept-vector-16891903.jpg");
        file_maps.put("fourth","https://cdn1.vectorstock.com/i/1000x1000/09/80/online-shopping-banner-vector-17230980.jpg");
       // viewPager.setAdapter(new SliderCustomer(getContext(),file_maps));
        viewPager.setAdapter(new SliderCustomPagerAdapter(getContext(),file_maps));
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager, true);
       return view;
    }





}
