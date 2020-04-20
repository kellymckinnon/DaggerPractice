package me.kellymckinnon.daggerpractice.di;

import android.app.Application;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.RequestOptions;
import dagger.Module;
import dagger.Provides;
import me.kellymckinnon.daggerpractice.R;

@Module
public class AppModule {

  @Provides
  static RequestOptions provideRequestOptions() {
    return RequestOptions.placeholderOf(R.drawable.white_background).error(R.drawable.white_background);
  }

  // RequestOptions is available bc it is provided above
  @Provides
  static RequestManager provideGlideInstance(Application application, RequestOptions requestOptions) {
    return Glide.with(application).setDefaultRequestOptions(requestOptions);
  }

  @Provides
  static Drawable provideAppDrawable(Application application) {
    return ContextCompat.getDrawable(application, R.drawable.logo);
  }
}
