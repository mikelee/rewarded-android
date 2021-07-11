package dev.mikelee.rewarded;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import dev.mikelee.rewarded.todo.Todo;
import dev.mikelee.rewarded.todo.TodoDao;

public class Repository {
    private TodoDao todoDao;
    private LiveData<List<Todo>> allTodos;

    public Repository(Application application) {
        Database database = Database.getInstance(application);
        todoDao = database.todoDao();
        allTodos = todoDao.getTodos();
    }

    public void insert(Todo todo) {
        new InsertTodoAsycTask(todoDao).execute(todo);
    }

    public void update(Todo todo) {
        new UpdateTodoAsycTask(todoDao).execute(todo);
    }

    public void delete(Todo todo) {
        new DeleteTodoAsycTask(todoDao).execute(todo);
    }

    public void deleteAllTodos() { new DeleteAllTodosAsycTask(todoDao).execute(); }


    public LiveData<List<Todo>> getAllTodos() {
        return allTodos;
    }


    private static class InsertTodoAsycTask extends AsyncTask<Todo, Void, Void> {
        private TodoDao todoDao;

        private InsertTodoAsycTask(TodoDao todoDao) {
            this.todoDao = todoDao;
        }

        @Override
        protected Void doInBackground(Todo... todos) {
            todoDao.insert(todos[0]);
            return null;
        }
    }

    private static class UpdateTodoAsycTask extends AsyncTask<Todo, Void, Void> {
        private TodoDao todoDao;

        private UpdateTodoAsycTask(TodoDao todoDao) {
            this.todoDao = todoDao;
        }

        @Override
        protected Void doInBackground(Todo... todos) {
            todoDao.update(todos[0]);
            return null;
        }
    }

    private static class DeleteTodoAsycTask extends AsyncTask<Todo, Void, Void> {
        private TodoDao todoDao;

        private DeleteTodoAsycTask(TodoDao todoDao) {
            this.todoDao = todoDao;
        }

        @Override
        protected Void doInBackground(Todo... todos) {
            todoDao.delete(todos[0]);
            return null;
        }
    }

    private static class DeleteAllTodosAsycTask extends AsyncTask<Void, Void, Void> {
        private TodoDao todoDao;

        private DeleteAllTodosAsycTask(TodoDao todoDao) {
            this.todoDao = todoDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            todoDao.deleteAllTodos();
            return null;
        }
    }
}
