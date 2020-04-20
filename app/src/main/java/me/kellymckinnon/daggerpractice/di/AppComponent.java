package me.kellymckinnon.daggerpractice.di;

import android.app.Application;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import me.kellymckinnon.daggerpractice.BaseApplication;

@Component(modules =
    {AndroidSupportInjectionModule.class, ActivityBuildersModule.class})
public interface AppComponent extends AndroidInjector<BaseApplication> {

  @Component.Builder
  interface Builder {

    // Used to bind a particular instance of object to the component at time of its construction
    @BindsInstance
    Builder application(Application application);

    AppComponent build();
  }

}
