package dev.mikelee.rewarded.todo;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Todo {

    @PrimaryKey(autoGenerate = true)
    public int todo_id;

    public String text;

    public Boolean completed;

    public int user_id;

    public Todo(String text, Boolean completed, int user_id) {
        this.text = text;
        this.completed = completed;
        this.user_id = user_id;
    }

    public void setTodo_id(int todo_id) {
        this.todo_id = todo_id;
    }

    public int getTodo_id() {
        return todo_id;
    }

    public String getText() {
        return text;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public int getUser_id() {
        return user_id;
    }
}
