package com.translate.utils;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.Collection;

/**
 * Created by Administrator on 2015/9/9.
 */
public class Utils {
    private static int screenHeight = 0;

    public static void toastMsg( Context context , String sMsg )
    {
        Toast.makeText(context, sMsg, Toast.LENGTH_SHORT).show( );
    }

    public static boolean isEmptyOrNullStr( String str )
    {
        return TextUtils.isEmpty(str) || "".equals( str );
    }

    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dpValue * scale + 0.5F);
    }



    public static String getDeviceInfo(Context context) {
        try{
            org.json.JSONObject json = new org.json.JSONObject();
            android.telephony.TelephonyManager tm = (android.telephony.TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);

            String device_id = tm.getDeviceId();

            android.net.wifi.WifiManager wifi = (android.net.wifi.WifiManager) context.getSystemService(Context.WIFI_SERVICE);

            String mac = wifi.getConnectionInfo().getMacAddress();
            json.put("mac", mac);

            if( TextUtils.isEmpty(device_id) ){
                device_id = mac;
            }

            if( TextUtils.isEmpty(device_id) ){
                device_id = android.provider.Settings.Secure.getString(context.getContentResolver(),android.provider.Settings.Secure.ANDROID_ID);
            }

            json.put("device_id", device_id);

            return json.toString();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }



    public static Drawable getResourceDrawableBounded(Context context, int drawableResId, int bound) {
        Drawable drawable = null;

        try {
            drawable = context.getResources().getDrawable(drawableResId);
            drawable.setBounds(0, 0, bound, bound);
        } catch (Exception var5) {
        }

        return drawable;
    }


    public static void setBackgroundDrawable(View view, Drawable drawable) {
        if(Build.VERSION.SDK_INT < 16) {
            view.setBackgroundDrawable(drawable);
        } else {
            view.setBackground(drawable);
        }

    }
    public static ShapeDrawable generateCornerShapeDrawable(int color, int corner) {
        return generateCornerShapeDrawable(color, corner, corner, corner, corner);
    }
    public static ShapeDrawable generateCornerShapeDrawable(int color, int topLeftCorner, int topRightCorner, int bottomRightCorner, int bottomLeftCorner) {
        RoundRectShape shape = new RoundRectShape(new float[]{(float)topLeftCorner, (float)topLeftCorner, (float)topRightCorner, (float)topRightCorner, (float)bottomRightCorner, (float)bottomRightCorner, (float)bottomLeftCorner, (float)bottomLeftCorner}, (RectF)null, (float[])null);
        ShapeDrawable sd = new ShapeDrawable(shape);
        sd.getPaint().setColor(color);
        sd.getPaint().setStyle(Paint.Style.FILL);
        return sd;
    }
    public static StateListDrawable selectorClickSimple(Drawable normal, Drawable pressed) {
        StateListDrawable drawable = new StateListDrawable();
        drawable.addState(new int[]{16842919, 16842910}, pressed);
        drawable.addState(new int[0], normal);
        return drawable;
    }

    public static boolean isEmpty(CharSequence charSequence) {
        return null == charSequence || charSequence.length() <= 0;
    }
    public static boolean isEmpty(Collection collection) {
        return null == collection || collection.isEmpty();
    }


    public static <T extends View> T obtainView(View convertView, int id) {
        SparseArray holder = (SparseArray)convertView.getTag();
        if(holder == null) {
            holder = new SparseArray();
            convertView.setTag(holder);
        }

        View childView = (View)holder.get(id);
        if(childView == null) {
            childView = convertView.findViewById(id);
            holder.put(id, childView);
        }

        return (T) childView;
    }

    public static boolean isLetterDigitOrChinese(String str){
        String regex = "^[a-zA-Z]+$";

        return str.matches(regex);
    }


    public static int getScreenHeight(Context c) {
        if (screenHeight == 0) {
            WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            screenHeight = size.y;
        }

        return screenHeight;
    }
}
