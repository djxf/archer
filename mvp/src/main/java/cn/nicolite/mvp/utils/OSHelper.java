package cn.nicolite.mvp.utils;

import android.os.Build;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;

/**
 * Created by nicolite on 2018/5/31.
 * email nicolite@nicolite.cn
 * 系统辅助类，用于判断系统类型
 */
public class OSHelper {
    private static final String TAG = "OSHelper";
    private static final String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";
    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
    private static final String KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage";

    private static final String KEY_EMUI_VERSION_CODE = "ro.build.version.emui";
    private static final String KEY_EMUI_API_LEVEL = "ro.build.hw_emui_api_level";

    public static boolean isFlyme() {
        try {
            final Method method = Build.class.getMethod("hasSmartBar");
            return method != null;
        } catch (final Exception e) {
            return false;
        }
    }

    public static boolean isEMUI() {
        return isPropertiesExist(KEY_EMUI_VERSION_CODE, KEY_EMUI_API_LEVEL);
    }

    public static boolean isMIUI() {
        return isPropertiesExist(KEY_MIUI_VERSION_CODE, KEY_MIUI_VERSION_NAME, KEY_MIUI_INTERNAL_STORAGE);
    }

    public static int getMIUIVersion() {
        String version = getProperty(KEY_MIUI_VERSION_NAME);
        int versionCode = 0;
        if (!TextUtils.isEmpty(version)) {
            version = version.replace("V", "")
                    .replace("v", "")
                    .trim();
            if (TextUtils.isDigitsOnly(version)) {
                versionCode = Integer.parseInt(version);
            }
        }
        return versionCode;
    }

    private static boolean isPropertiesExist(String... keys) {
        if (keys == null || keys.length == 0) {
            return false;
        }
        for (String key : keys) {
            String value = getProperty(key);
            if (value != null)
                return true;
        }
        return false;
    }

    private static String getProperty(String name) {
        String line = null;
        BufferedReader input = null;
        try {
            Process p = Runtime.getRuntime().exec("getprop " + name);
            input = new BufferedReader(new InputStreamReader(p.getInputStream()), 1024);
            line = input.readLine();
            input.close();
        } catch (IOException ex) {
            LogUtils.d(TAG, "Unable to read prop " + name, ex);
            return null;
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return line;
    }
}
