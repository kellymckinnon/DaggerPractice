package me.kellymckinnon.daggerpractice.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import me.kellymckinnon.daggerpractice.di.auth.AuthModule;
import me.kellymckinnon.daggerpractice.di.auth.AuthScope;
import me.kellymckinnon.daggerpractice.di.auth.AuthViewModelsModule;
import me.kellymckinnon.daggerpractice.di.main.MainFragmentBuildersModule;
import me.kellymckinnon.daggerpractice.di.main.MainModule;
import me.kellymckinnon.daggerpractice.di.main.MainScope;
import me.kellymckinnon.daggerpractice.di.main.MainViewModelsModule;
import me.kellymckinnon.daggerpractice.ui.auth.AuthActivity;
import me.kellymckinnon.daggerpractice.ui.main.MainActivity;

@Module
public abstract class ActivityBuildersModule {

  @AuthScope
  @ContributesAndroidInjector(modules = {AuthViewModelsModule.class, AuthModule.class})
  abstract AuthActivity contributeAuthActivity();

  @MainScope
  @ContributesAndroidInjector(modules = {MainViewModelsModule.class,
      MainFragmentBuildersModule.class,
      MainModule.class})
  abstract MainActivity contributeMainActivity();
}
