package captechventures.com.paginglibrarysample.database;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import captechventures.com.paginglibrarysample.dao.PersonDao;
import captechventures.com.paginglibrarysample.model.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class InitializeDBContents extends AsyncTask<Object, Object, Void> {

    private static final String TAG = InitializeDBContents.class.getSimpleName();

    private Context context;
    private InitializeDBContentsListener mListener;
    private boolean success = false;

    private static final String NAMES_LIST_FILE = "sample-names-list.txt";

    public InitializeDBContents(Context context) {
        this.context = context;
        if (context instanceof InitializeDBContentsListener) {
            mListener = (InitializeDBContentsListener) context;
        } else {
            IllegalArgumentException iae = new IllegalArgumentException("Context Must Implement InitializeDBContentsListener");
            Log.e(TAG, iae.toString());
            throw iae;
        }
    }

    @Override
    protected Void doInBackground(Object... voids) {
        // initialize database with list of names from file
        try {
            List<Person> personList = new ArrayList<>();

            InputStream inputreader = context.getAssets().open(NAMES_LIST_FILE);
            BufferedReader buffreader = new BufferedReader(new InputStreamReader(inputreader));

            // read file line by line
            String line = buffreader.readLine();
            while (line != null) {
                Person person = new Person();
                person.setName(line);

                personList.add(person);

                line = buffreader.readLine();
            }
            buffreader.close();

            PersonDao personDao = AppDatabase.getInstance(context).personDao();
            personDao.insertAll(personList);

            success = true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        // pass result back to activity
        mListener.afterDBContentsInitialized(success);
    }

    public interface InitializeDBContentsListener {
        void afterDBContentsInitialized(boolean success);
    }

}
