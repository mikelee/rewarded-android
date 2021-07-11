package dev.mikelee.rewarded;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import dev.mikelee.rewarded.todo.Todo;
import dev.mikelee.rewarded.todo.TodoDao;

@androidx.room.Database(entities = Todo.class, version = 1)
public abstract class Database extends RoomDatabase {
    private static Database database;

    public abstract TodoDao todoDao();

    public static synchronized Database getInstance(Context context) {
        if (database == null) {
            database = Room
                    .databaseBuilder(context.getApplicationContext(), Database.class, "RewardedDB")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return database;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            new PopulateDbAsyncTask(database).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private TodoDao todoDao;

        private PopulateDbAsyncTask(Database appDb) {
            todoDao = appDb.todoDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            todoDao.insert(new Todo("first", true, 9));
            todoDao.insert(new Todo("second", false, 9));
            todoDao.insert(new Todo("third", false, 9));
            return null;
        }
    }
}
