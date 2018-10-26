package captechventures.com.paginglibrarysample;

import android.app.Application;
import android.util.Log;
import captechventures.com.paginglibrarysample.database.InitializeDBContents;
import captechventures.com.paginglibrarysample.util.SharedPreferencesUtil;

public class MainApplication extends Application implements InitializeDBContents.InitializeDBContentsListener {

    private static final String TAG = MainApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "PagingLibrarySample started!");

        if (!SharedPreferencesUtil.isInitialized(getApplicationContext())) {
            // initialize database with list of names on first run
            InitializeDBContents task = new InitializeDBContents(getApplicationContext());
            task.execute();
        }
    }

    @Override
    public void afterDBContentsInitialized(boolean success) {
        SharedPreferencesUtil.setInitialized(getApplicationContext(), success);
    }
}
