package me.kellymckinnon.daggerpractice;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import me.kellymckinnon.daggerpractice.di.DaggerAppComponent;

public class BaseApplication extends DaggerApplication {

  @Override
  protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
    return DaggerAppComponent.builder().application(this).build();
  }
}
