package me.kellymckinnon.daggerpractice.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import me.kellymckinnon.daggerpractice.di.auth.AuthViewModelsModule;
import me.kellymckinnon.daggerpractice.ui.auth.AuthActivity;

@Module
public abstract class ActivityBuildersModule {

  @ContributesAndroidInjector(modules = {AuthViewModelsModule.class})
  abstract AuthActivity contributeAuthActivity();
}
