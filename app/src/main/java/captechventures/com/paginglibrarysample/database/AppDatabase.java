package captechventures.com.paginglibrarysample.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import captechventures.com.paginglibrarysample.dao.PersonDao;
import captechventures.com.paginglibrarysample.database.sqlAsset.AssetSQLiteOpenHelperFactory;
import captechventures.com.paginglibrarysample.model.Person;

@Database(entities = Person.class, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    // constants
    private static final String DATABASE_NAME = "assignment4.db";

    // instances
    private static AppDatabase INSTANCE;
    private static final Object sLock = new Object();

    // methods
    public abstract PersonDao personDao();

    public static AppDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, DATABASE_NAME)
                        .openHelperFactory(new AssetSQLiteOpenHelperFactory())
                        .allowMainThreadQueries()
                        .build();
            }
            return INSTANCE;
        }
    }

}
