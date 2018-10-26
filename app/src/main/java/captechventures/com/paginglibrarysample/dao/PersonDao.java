package captechventures.com.paginglibrarysample.dao;

import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import captechventures.com.paginglibrarysample.model.Person;

import java.util.List;

@Dao
public interface PersonDao {
 
    @Query("SELECT * FROM persons")
    LiveData<List<Person>> getAll();
 
    @Query("SELECT * FROM persons")
    DataSource.Factory<Integer, Person> getAllPaged();
 
    @Insert
    void insertAll(List<Person> persons);
 
    @Delete
    void delete(Person person);
}