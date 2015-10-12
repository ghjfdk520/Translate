package com.translate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.translate.utils.Utils;
import com.translate.widget.RapidFloating.RFACLabelItem;
import com.translate.widget.RapidFloating.RapidFloatingActionButton;
import com.translate.widget.RapidFloating.RapidFloatingActionContentLabelList;
import com.translate.widget.RapidFloating.RapidFloatingActionHelper;
import com.translate.widget.RapidFloating.RapidFloatingActionLayout;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements RapidFloatingActionContentLabelList.OnRapidFloatingActionContentLabelListListener{
    private ListView listView;

    private CardView input;

    private RapidFloatingActionLayout rfaLayout;
    private RapidFloatingActionButton rfaButton;
    private RapidFloatingActionHelper rfabHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogUtils.e("run1");
        rfaLayout = (RapidFloatingActionLayout) findViewById(R.id.label_list_sample_rfal);
        rfaButton = (RapidFloatingActionButton) findViewById(R.id.label_list_sample_rfab);
        RunMethod();
//        new SQLdm(this).openDatabase();
//        TranslateProtocol.youdaoTransLate("大哥", new HttpCallBack() {
//            @Override
//            public void onGeneralSuccess(String result, long flag) {
//                LogUtils.e(result);
//            }
//
//            @Override
//            public void onGeneralError(String e, long flag) {
//
//            }
//        });
//
//        LogUtils.e(Utils.getDeviceInfo(this));
//
//
//
//        listView = (ListView) findViewById(R.id.list);
//        input = (CardView) findViewById(R.id.input);
//        BaseAdapter baseAdapter = new BaseAdapter() {
//            @Override
//            public int getCount() {
//                return 10;
//            }
//
//            @Override
//            public Object getItem(int position) {
//                return null;
//            }
//
//            @Override
//            public long getItemId(int position) {
//                return 0;
//            }
//
//            @Override
//            public View getView(int position, View convertView, ViewGroup parent) {
//                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.cardview,null);
//                return view;
//            }
//        };
//        listView.setAdapter(baseAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
        //actionsCreator.destory();
    }

    public void RunMethod(){
        RapidFloatingActionContentLabelList rfaContent = new RapidFloatingActionContentLabelList(this);
        rfaContent.setOnRapidFloatingActionContentLabelListListener(this);
        List<RFACLabelItem> items = new ArrayList<>();
        items.add(new RFACLabelItem<Integer>()
                        .setResId(R.mipmap.ja)
                        .setIconNormalColor(getResources().getColor(R.color.md_red_a200))
                        .setIconPressedColor(0XFFF5466E)
                        .setWrapper(0)
        );
        items.add(new RFACLabelItem<Integer>()
//                        .setResId(R.mipmap.ico_test_c)
                        .setDrawable(getResources().getDrawable(R.mipmap.fr))
                        .setIconNormalColor(0xff4e342e)
                        .setIconPressedColor(0xff3e2723)
                        .setWrapper(1)
        );
        items.add(new RFACLabelItem<Integer>()
                        .setResId(R.mipmap.ru)
                        .setIconNormalColor(0xff056f00)
                        .setIconPressedColor(0xff0d5302)
                        .setLabelColor(0xff056f00)
                        .setWrapper(2)
        );
        items.add(new RFACLabelItem<Integer>()
                        .setResId(R.mipmap.kr)
                        .setIconNormalColor(0xff283593)
                        .setIconPressedColor(0xff1a237e)
                        .setLabelColor(0xff283593)
                        .setWrapper(3)
        );

        items.add(new RFACLabelItem<Integer>()
                        .setResId(R.mipmap.zh)
                        .setIconNormalColor(0xffd84315)
                        .setIconPressedColor(0xffbf360c)
                        .setWrapper(4)
        );
        rfaContent
                .setItems(items)
                .setIconShadowRadius(Utils.dip2px(this, 5))
                .setIconShadowColor(0xff888888)
                .setIconShadowDy(Utils.dip2px(this, 5))
        ;

        rfabHelper = new RapidFloatingActionHelper(
                this,
                rfaLayout,
                rfaButton,
                rfaContent
        ).build();

    }

    @Override
    public void onRFACItemLabelClick(int position, RFACLabelItem item) {
        rfabHelper.toggleContent();
    }

    @Override
    public void onRFACItemIconClick(int position, RFACLabelItem item) {
        Toast.makeText(this,"hahha"+position,Toast.LENGTH_LONG).show();
        rfabHelper.toggleContent();
    }
}
