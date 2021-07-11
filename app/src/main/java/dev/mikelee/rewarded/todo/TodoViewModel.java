package dev.mikelee.rewarded.todo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import dev.mikelee.rewarded.Repository;

public class TodoViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<List<Todo>> allTodos;


    public TodoViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        allTodos = repository.getAllTodos();
    }

    public void insert(Todo todo) {
        repository.insert(todo);
    }
    public void update(Todo todo) {
        repository.update(todo);
    }
    public void delete(Todo todo) {
        repository.delete(todo);
    }

    public void deleteAllTodos() {
        repository.deleteAllTodos();
    }

    public LiveData<List<Todo>> getAllTodos() {
        return allTodos;
    }
}
