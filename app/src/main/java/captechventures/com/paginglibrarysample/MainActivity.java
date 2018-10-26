package captechventures.com.paginglibrarysample;

import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import captechventures.com.paginglibrarysample.adapter.PersonAdapter;
import captechventures.com.paginglibrarysample.model.Person;
import captechventures.com.paginglibrarysample.viewmodel.PersonsViewModel;

public class MainActivity extends AppCompatActivity {

    PersonsViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(PersonsViewModel.class);
        PersonAdapter adapter = new PersonAdapter(MainActivity.this);

        RecyclerView recipeRecyclerView = findViewById(R.id.name_list);
        recipeRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recipeRecyclerView.setAdapter(adapter);

        subscribeUi(adapter);
    }

    private void subscribeUi(PersonAdapter adapter) {
        viewModel.getPersons().observe(this, (PagedList<Person> names) -> {
            if (names != null) {
                adapter.submitList(names);
            }
        });
    }

}