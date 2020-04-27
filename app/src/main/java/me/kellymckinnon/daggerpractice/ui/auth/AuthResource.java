package me.kellymckinnon.daggerpractice.ui.auth;

import androidx.annotation.Nullable;

public class AuthResource<T> {

  public final AuthStatus status;
  @Nullable public final T data;
  @Nullable public final String message;

  public AuthResource(AuthStatus status, @Nullable T data, @Nullable String message) {
    this.status = status;
    this.data = data;
    this.message = message;
  }

  public static <T> AuthResource<T> authenticated(@Nullable T data) {
    return new AuthResource<>(AuthStatus.AUTHENTICATED, data, null);
  }

  public static <T> AuthResource<T> error(String msg, @Nullable T data) {
    return new AuthResource<>(AuthStatus.ERROR, data, msg);
  }

  public static <T> AuthResource<T> loading(@Nullable T data) {
    return new AuthResource<>(AuthStatus.LOADING, data, null);
  }

  public static <T> AuthResource<T> logout() {
    return new AuthResource<>(AuthStatus.NOT_AUTHENTICATED, null, null);
  }

  public enum AuthStatus { AUTHENTICATED, ERROR, LOADING, NOT_AUTHENTICATED}
}
