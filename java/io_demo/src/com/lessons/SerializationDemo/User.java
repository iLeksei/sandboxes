package com.lessons.SerializationDemo;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {

  private String username;
  private String password;
  private transient ArrayList<String> todoList;

  public User(String username, String password, ArrayList<String> todoList) {
    this.username = username;
    this.password = password;
    this.todoList = todoList;
  }

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public User() {}

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public ArrayList<String> getTodoList() {
    return todoList;
  }

  public void setTodoList(ArrayList<String> todoList) {
    this.todoList = todoList;
  }

  @Override
  public String toString() {
    return String.format("Username: %s, Password: %s", this.username, this.password);
  }
}
