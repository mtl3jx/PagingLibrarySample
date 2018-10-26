package captechventures.com.paginglibrarysample.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtil {

    private static final String TAG = SharedPreferencesUtil.class.getSimpleName();

    private static final String SHARED_PREFS = "SharedPreferences";
    private static final String INITIALIZED = "initialized";

    private SharedPreferencesUtil() {
    }

    public static boolean isInitialized(Context context) {
        return getSharedPreferences(context).getBoolean(INITIALIZED, false);
    }

    public static void setInitialized(Context context, boolean enabled) {
        getSharedPreferences(context).edit().putBoolean(INITIALIZED, enabled).apply();
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
    }

}