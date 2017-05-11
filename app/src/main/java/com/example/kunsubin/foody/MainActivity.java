package com.example.kunsubin.foody;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.kunsubin.foody.GridViewAnGi.HeaderGridView;
import com.example.kunsubin.foody.Object.ObjectInfoUser;
import com.example.kunsubin.foody.Object.StaticData;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private TextView btnHuy;
    private BottomNavigationView bottomNavigationView;


    private ViewPager pager;
    private TabLayout tablayout;
    private LinearLayout tabBottom;
    private LinearLayout homeMain;

    private Fragment gallery;
    private Fragment search;
    private Fragment notify;
    private Fragment user;
    private FragmentManager fragmentManager;
    private  FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ánh xạ các view
        init();
        //tắt hiệu ứng bottomNavigationView nhảy lên khi chọn
        turnOffAnimation();
        //tạo hiệu ứng cuộn khi kéo chuột giữa hai tap
        viewPager();
        //chuyển tab ăn gì khi chọn tablayout ăn gì hoặc ở đâu
        selectTabLayout();

        //khởi tạo các lớp frament tường ứng 4 menu bottom
        gallery=new Gallery();
        search=new Search();
        notify=new Notify();
        user=new User();
        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        //add frament transaction
        fragmentTransaction.add(R.id.tabBottom,gallery);
        fragmentTransaction.add(R.id.tabBottom,search);
        fragmentTransaction.add(R.id.tabBottom,notify);
        fragmentTransaction.add(R.id.tabBottom,user);
        //ẩn các frament chưa dùng đến
        fragmentTransaction.hide(gallery);
        fragmentTransaction.hide(search);
        fragmentTransaction.hide(notify);
        fragmentTransaction.hide(user);
        fragmentTransaction.commit();

        //event click item bottom BottomNavigationView chuyển các tab qua lại khi nhấn icon bottom bar
        clickBottomNavigationView();

    }
    //ánh xạ các view
    public void init(){

        btnHuy=(TextView)findViewById(R.id.btnHuy);
        bottomNavigationView=(BottomNavigationView)findViewById(R.id.bottomNavigationView);


        tablayout=(TabLayout)findViewById(R.id.tab_parent_top_menu);

        tabBottom=(LinearLayout)findViewById(R.id.tabBottom);
        homeMain=(LinearLayout)findViewById(R.id.homeMain);
    }
    //tạo hiệu ứng cuộn khi kéo chuột giữa hai tap
    public void viewPager(){
        pager = (ViewPager) findViewById(R.id.view_pager);
        //chuyển qua lại giữa hai frament là ăn gì với ở đâu
        FragmentManager manager = getSupportFragmentManager();
        PagerAdapter adapter = new PagerAdapter(manager,this);
        pager.setAdapter(adapter);
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                //cuộn sang page 0 Odau tiến hành select Tab ở đâu
                if(position==0){
                    tablayout.getTabAt(0).select();
                    btnHuy.performClick();
                    btnHuy.setVisibility(View.GONE);
                    bottomNavigationView.setVisibility(View.VISIBLE);
                    return;
                }
                if(position==1){
                    //cuộn sang page 1 anGi tiến hành select Tab AnGi
                    tablayout.getTabAt(1).select();
                    btnHuy.performClick();
                    btnHuy.setVisibility(View.GONE);
                    bottomNavigationView.setVisibility(View.VISIBLE);
                    return;
                }
                return;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    //tắt hiệu ứng bottomNavigationView nhảy lên khi chọn
    public void turnOffAnimation(){
        //tắt các hiệu ứng của bottom navigation view
       BottomNavigationViewEx bottomNavigationView = (BottomNavigationViewEx) findViewById(R.id.bottomNavigationView);
        bottomNavigationView.enableAnimation(false);
        bottomNavigationView.enableShiftingMode(false);
        bottomNavigationView.enableItemShiftingMode(false);
        bottomNavigationView.setTextVisibility(false);
    }
    //event click menu foody
    public void onMenuFoodyClick(View view){
        //mở màn hình menu foody
        Intent intent1 = new Intent(this,MenuFoody.class);
        startActivity(intent1);
    }
    //event click menu plus
    public void onClickPlus(View view){
        //show modal menu plus
        BottomSheet3DialogFragment bottomSheetDialogFragment = new BottomSheet3DialogFragment();
        bottomSheetDialogFragment.show(getSupportFragmentManager(), bottomSheetDialogFragment.getTag());
    }
    //chuyển tab ăn gì khi chọn tablayout ăn gì hoặc ở đâu
    public void selectTabLayout(){
        tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                pager.setCurrentItem(tab.getPosition());
                btnHuy.performClick();
                btnHuy.setVisibility(View.GONE);
                bottomNavigationView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    //event click nut huy sổ ra khi nhấn các tab danh mục, địa điểm
    public void onClickHuy(View view){
        //set layout bên khu vực ăn gì
        HeaderGridView khungChinhAnGi=(HeaderGridView) findViewById(R.id.khungChinhAnGi);

        ListView listViewMoiNhatAnGi=(ListView)findViewById(R.id.listViewMoiNhatAnGi);
        ListView listViewDanhMucAnGi=(ListView)findViewById(R.id.listViewDanhMucAnGi);
        LinearLayout listViewDiaDiemAnGi=(LinearLayout)findViewById(R.id.listViewDiaDiemAnGi);
        LinearLayout layOutMoiNhatAnGi=(LinearLayout)findViewById(R.id.layOutMoiNhatAnGi);
        LinearLayout layOutDiaDiemAnGi=(LinearLayout)findViewById(R.id.layOutDiaDiemAnGi);
        LinearLayout layOutDanhMucAnGi=(LinearLayout)findViewById(R.id.layOutDanhMucAnGi);

        layOutMoiNhatAnGi.setBackgroundResource(R.drawable.my_button_bg);
        layOutDiaDiemAnGi.setBackgroundResource(R.drawable.my_button_bg);
        layOutDanhMucAnGi.setBackgroundResource(R.drawable.my_button_bg);

        listViewMoiNhatAnGi.setVisibility(View.GONE);
        listViewDanhMucAnGi.setVisibility(View.GONE);
        listViewDiaDiemAnGi.setVisibility(View.GONE);

        khungChinhAnGi.setVisibility(View.VISIBLE);

        //set layout bên khu vực ở đâu
        ListView khungChinhODau=(ListView) findViewById(R.id.khungChinhODau);

        ListView listViewMoiNhatODau=(ListView)findViewById(R.id.listViewMoiNhatODau);
        ListView listViewDanhMucODau=(ListView)findViewById(R.id.listViewDanhMucOdau);
        LinearLayout listViewDiaDiemODau=(LinearLayout)findViewById(R.id.listViewDiaDiemODau);
        LinearLayout layOutMoiNhatODau=(LinearLayout)findViewById(R.id.layOutMoiNhatODau);
        LinearLayout layOutDiaDiemODau=(LinearLayout)findViewById(R.id.layOutDiaDiemODau);
        LinearLayout layOutDanhMucODau=(LinearLayout)findViewById(R.id.layOutDanhMucODau);

        layOutMoiNhatODau.setBackgroundResource(R.drawable.my_button_bg);
        layOutDiaDiemODau.setBackgroundResource(R.drawable.my_button_bg);
        layOutDanhMucODau.setBackgroundResource(R.drawable.my_button_bg);

        listViewMoiNhatODau.setVisibility(View.GONE);
        listViewDanhMucODau.setVisibility(View.GONE);
        listViewDiaDiemODau.setVisibility(View.GONE);

        khungChinhODau.setVisibility(View.VISIBLE);


        btnHuy.setVisibility(View.GONE);
        bottomNavigationView.setVisibility(View.VISIBLE);
    }
    //event click item bottom BottomNavigationView chuyển các tab qua lại khi nhấn icon bottom bar
    public void  clickBottomNavigationView(){
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction=fragmentManager.beginTransaction();
                transaction.hide(gallery);
                transaction.hide(search);
                transaction.hide(notify);
                transaction.hide(user);

                switch (item.getItemId()) {
                    //màn hình chính khi chọn home
                    case R.id.menu_bottom_bar_home:
                        tabBottom.setVisibility(View.GONE);
                        homeMain.setVisibility(View.VISIBLE);
                        break;
                    case R.id.menu_bottom_bar_gallery:
                        tabBottom.setVisibility(View.VISIBLE);
                        homeMain.setVisibility(View.GONE);
                        transaction.show(gallery);
                        break;
                    case R.id.menu_bottom_bar_search:
                        tabBottom.setVisibility(View.VISIBLE);
                        homeMain.setVisibility(View.GONE);
                        transaction.show(search);
                        break;
                    case R.id.menu_bottom_bar_notify:
                        tabBottom.setVisibility(View.VISIBLE);
                        homeMain.setVisibility(View.GONE);
                        transaction.show(notify);
                        break;
                    case R.id.menu_bottom_bar_user:
                        tabBottom.setVisibility(View.VISIBLE);
                        homeMain.setVisibility(View.GONE);
                        transaction.show(user);
                        //check login chưa
                        if(StaticData.getObjectInfoUser()!=null){
                            TextView textHoTen=(TextView)findViewById(R.id.textHoTen);
                            CircleImageView avatarUser=(CircleImageView) findViewById(R.id.avatarUser);
                            TextView textXemHoatDong=(TextView)findViewById(R.id.textXemHoatDong);
                            LinearLayout dangXuat=(LinearLayout)findViewById(R.id.dangXuat);

                            ObjectInfoUser objectInfoUser= StaticData.getObjectInfoUser();
                            textHoTen.setText(objectInfoUser.getHoTen());
                            if(objectInfoUser.getHinh()!=null){
                                Glide.with(getApplicationContext()).load(objectInfoUser.getHinh()).into(avatarUser);
                            }
                            textXemHoatDong.setVisibility(View.VISIBLE);
                            dangXuat.setVisibility(View.VISIBLE);

                        }
                        break;
                    default:
                        break;
                }
                transaction.addToBackStack(null);
                transaction.commit();
                return true;
            }
        });
    }
}
