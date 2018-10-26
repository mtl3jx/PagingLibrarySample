package captechventures.com.paginglibrarysample.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import captechventures.com.paginglibrarysample.dao.PersonDao;
import captechventures.com.paginglibrarysample.database.AppDatabase;
import captechventures.com.paginglibrarysample.model.Person;
import org.jetbrains.annotations.NotNull;

public final class PersonsViewModel extends AndroidViewModel {

    private static final int INITIAL_LOAD_KEY = 0;
    private static final int PAGE_SIZE = 20;
    private static final int PREFETCH_DISTANCE = 5;

    //    private Executor executor;
    private AppDatabase appDatabase;
    private final PersonDao personDao;
    private LiveData<PagedList<Person>> liveResults;

    public PersonsViewModel(@NotNull Application application) {
        super(application);

        appDatabase = AppDatabase.getInstance(this.getApplication());
        personDao = appDatabase.personDao();
        DataSource.Factory factory = personDao.getAllPaged();

        LivePagedListBuilder pagedListBuilder = new LivePagedListBuilder(factory, PAGE_SIZE);
        liveResults = pagedListBuilder.build();

        // ATTEMPT #2
//        liveResults = factory.create(INITIAL_LOAD_KEY, new PagedList.Config.Builder()
//                .setPageSize(PAGE_SIZE)
//                .setPrefetchDistance(PREFETCH_DISTANCE)
//                .setEnablePlaceholders(true)
//                .build());

        // ATTEMPT #3
//        executor = Executors.newFixedThreadPool(5);
//        PagedList.Config pagedListConfig = (new PagedList.Config.Builder())
//                        .setEnablePlaceholders(false)
//                        .setInitialLoadSizeHint(10)
//                        .setPageSize(PAGE_SIZE).build();
//        liveResults = (new LivePagedListBuilder(factory, pagedListConfig))
//                .setFetchExecutor(executor)
//                .build();
    }

    public LiveData<PagedList<Person>> getPersons() {
        return liveResults;
    }
}