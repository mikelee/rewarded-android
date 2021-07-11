package dev.mikelee.rewarded.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import dev.mikelee.rewarded.R;
//import dev.mikelee.rewarded.TodoAdapter;
import dev.mikelee.rewarded.todo.Todo;
import dev.mikelee.rewarded.todo.TodoAdapter;
import dev.mikelee.rewarded.todo.TodoViewModel;

public class TodoFragment extends Fragment {
    private TodoViewModel todoViewModel;

    public TodoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_todo, container, false);

        final TodoAdapter todoAdapter = new TodoAdapter();

        RecyclerView recyclerView = view.findViewById(R.id.todo_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(todoAdapter);


        // user getActivity inside onActivityCreated method to avoid null
        todoViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(TodoViewModel.class);
        todoViewModel.getAllTodos().observe(this, new Observer<List<Todo>>() {
            @Override
            public void onChanged(List<Todo> todos) {
                //update RecyclerView
                todoAdapter.setTodos(todos);
            }
        });

        FloatingActionButton todoAddButton = view.findViewById(R.id.todoAddButton);
        todoAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                todoViewModel.insert(new Todo("new todo", false, 9));
            }
        });

        return view;
    }
}