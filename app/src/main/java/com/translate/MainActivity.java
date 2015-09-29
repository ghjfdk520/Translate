package com.translate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.apkfuns.logutils.LogUtils;
import com.translate.connector.HttpCallBack;
import com.translate.connector.protocol.TranslateProtocol;
import com.translate.database.SQLdm;


public class MainActivity extends AppCompatActivity {
    private ListView listView;

    private CardView input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new SQLdm(this).openDatabase();

        TranslateProtocol.youdaoTransLate("大哥", new HttpCallBack() {
            @Override
            public void onGeneralSuccess(String result, long flag) {
                LogUtils.e(result);
            }

            @Override
            public void onGeneralError(String e, long flag) {

            }
        });




        listView = (ListView) findViewById(R.id.list);
        input = (CardView) findViewById(R.id.input);
        BaseAdapter baseAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return 10;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.cardview,null);
                return view;
            }
        };
        listView.setAdapter(baseAdapter);

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
}
