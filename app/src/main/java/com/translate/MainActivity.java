package com.translate;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.otto.Subscribe;
import com.translate.actions.TranslateActionCreators;
import com.translate.adapter.LoctAdapter;
import com.translate.commponents.SuperActivity;
import com.translate.conf.Common;
import com.translate.stores.TranslateStore;
import com.translate.utils.Utils;
import com.translate.widget.RapidFloating.RFACLabelItem;
import com.translate.widget.RapidFloating.RapidFloatingActionButton;
import com.translate.widget.RapidFloating.RapidFloatingActionContentLabelList;
import com.translate.widget.RapidFloating.RapidFloatingActionHelper;
import com.translate.widget.RapidFloating.RapidFloatingActionLayout;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends SuperActivity implements RapidFloatingActionContentLabelList.OnRapidFloatingActionContentLabelListListener,View.OnClickListener {
    private RecyclerView listView;
    private EditText input_word;
    private ImageView bt_search;
    private CardView input;
    private LoctAdapter loctAdapter;
    private RapidFloatingActionLayout rfaLayout;
    private RapidFloatingActionButton rfaButton;
    private RapidFloatingActionHelper rfabHelper;
    private RapidFloatingActionContentLabelList rfaContent;
    private List<RFACLabelItem> items;
    private int[] normalColors;
    private int[] pressColors;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initListener();

    }

    public void init() {

        actionsCreator = TranslateActionCreators.getInstance();
        store = TranslateStore.get(dispatcher);
        input_word = (EditText) findViewById(R.id.input_word);
        bt_search = (ImageView) findViewById(R.id.bt_search);
        listView = (RecyclerView) findViewById(R.id.list);
        listView.setLayoutManager(new LinearLayoutManager(this));
        loctAdapter = new LoctAdapter();

        listView.setAdapter(loctAdapter);

        normalColors = getResources().getIntArray(R.array.normalColor);
        pressColors = getResources().getIntArray(R.array.pressedColor);

        rfaLayout = (RapidFloatingActionLayout) findViewById(R.id.label_list_sample_rfal);
        rfaButton = (RapidFloatingActionButton) findViewById(R.id.label_list_sample_rfab);

        items = new ArrayList<>();

        rfaContent = new RapidFloatingActionContentLabelList(this);
        rfaContent.setOnRapidFloatingActionContentLabelListListener(this);
        setRapidFolating();
    }


    public void initListener() {
        input_word.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String inputWord = s.toString().trim();
                if (Utils.isEmpty(inputWord))
                    return;
                if (!inputWord.contains(" "))
                    ((TranslateActionCreators) actionsCreator).localTranslate(inputWord);

            }
        });
        bt_search.setOnClickListener(this);
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

    public void reSetRapidFolating() {
        rfaButton.setButtonDrawableId(Common.getInstance().getRapidId());
        rfaButton.setNormalColor(normalColors[Common.getInstance().getTranslateId()]);
        rfaButton.setPressedColor(pressColors[Common.getInstance().getTranslateId()]);
        rfaButton.build();
    }

    public void setRapidFolating() {
        rfaButton.setButtonDrawableId(Common.getInstance().getRapidId());
        rfaButton.setNormalColor(normalColors[Common.getInstance().getTranslateId()]);
        rfaButton.setPressedColor(pressColors[Common.getInstance().getTranslateId()]);
        rfaButton.build();

        for (int i = 0; i < 6; i++) {

            items.add(new RFACLabelItem<Integer>()
                            .setResId(Common.getInstance().getRapidId(i))
                            .setIconNormalColor(normalColors[i])
                            .setIconPressedColor(pressColors[i])
                            .setWrapper(i)
            );

        }
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
        Common.getInstance().setTranslateId((Integer) item.getWrapper());

        rfabHelper.toggleContent();

        reSetRapidFolating();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_search:
                ((TranslateActionCreators) actionsCreator).TransLate(input_word.getText().toString());
                break;
        }
    }

    @Subscribe
    public void onTodoStoreChange(TranslateStore.TranslateStoreChangeEvent event) {
         if(event.isLocTranslate){
             updateUI();
         }
    }

    private void updateUI() {
        Toast.makeText(this,"uiUpdate",2000).show();
        loctAdapter.setItems(((TranslateStore)store).getDictBeans());
    }
}
//         RunMethod();
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