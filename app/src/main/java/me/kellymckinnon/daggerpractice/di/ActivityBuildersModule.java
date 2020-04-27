package me.kellymckinnon.daggerpractice.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import me.kellymckinnon.daggerpractice.di.auth.AuthModule;
import me.kellymckinnon.daggerpractice.di.auth.AuthViewModelsModule;
import me.kellymckinnon.daggerpractice.ui.auth.AuthActivity;
import me.kellymckinnon.daggerpractice.ui.main.MainActivity;

@Module
public abstract class ActivityBuildersModule {

  @ContributesAndroidInjector(modules = {AuthViewModelsModule.class, AuthModule.class})
  abstract AuthActivity contributeAuthActivity();

  @ContributesAndroidInjector
  abstract MainActivity contributeMainActivity();
}
