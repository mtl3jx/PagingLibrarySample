package captechventures.com.paginglibrarysample.adapter;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import captechventures.com.paginglibrarysample.R;
import captechventures.com.paginglibrarysample.model.Person;

public class PersonAdapter extends PagedListAdapter<Person, PersonAdapter.PersonViewHolder> {

    private final Context context;

    public PersonAdapter(Context context) {
        super(Person.DIFF_CALLBACK);
        this.context = context;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new PersonViewHolder(LayoutInflater.from(context).inflate(R.layout.item_person, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder personViewHolder, int i) {
        Person person = getItem(i);

        if (person != null) {
            personViewHolder.bindTo(person);
        } else {
            personViewHolder.clear();
        }
    }

    public class PersonViewHolder extends RecyclerView.ViewHolder {
        public TextView name;

        PersonViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.person_name);
        }

        void bindTo(Person person) {
            itemView.setTag(person.getId());
            name.setText(person.getName());
        }

        void clear() {
            itemView.invalidate();
            name.invalidate();
        }
    }

}