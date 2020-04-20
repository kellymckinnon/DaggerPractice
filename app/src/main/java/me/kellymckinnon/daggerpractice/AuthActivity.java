package me.kellymckinnon.daggerpractice;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import com.bumptech.glide.RequestManager;
import dagger.android.support.DaggerAppCompatActivity;
import javax.inject.Inject;

public class AuthActivity extends DaggerAppCompatActivity {

  private static final String TAG = "AuthActivity";

  @Inject
  Drawable logo;

  @Inject
  RequestManager requestManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_auth);
    setLogo();
  }

  private void setLogo() {
    requestManager.load(logo).into((ImageView) findViewById(R.id.login_logo));
  }
}
