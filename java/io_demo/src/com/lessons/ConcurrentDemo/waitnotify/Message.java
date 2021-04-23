package com.lessons.ConcurrentDemo.waitnotify;

public class Message {
  private String message;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message.toUpperCase();
  }
}
