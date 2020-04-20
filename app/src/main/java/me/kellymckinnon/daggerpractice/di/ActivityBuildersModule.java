package me.kellymckinnon.daggerpractice.di;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import me.kellymckinnon.daggerpractice.AuthActivity;

@Module
public abstract class ActivityBuildersModule {

  @ContributesAndroidInjector
  abstract AuthActivity contributeAuthActivity();

  @Provides
  static String someString() {
    return "this is a test string";
  }
}
