package me.kellymckinnon.daggerpractice.ui.auth;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.bumptech.glide.RequestManager;
import dagger.android.support.DaggerAppCompatActivity;
import javax.inject.Inject;
import me.kellymckinnon.daggerpractice.R;
import me.kellymckinnon.daggerpractice.models.User;
import me.kellymckinnon.daggerpractice.viewmodels.ViewModelProviderFactory;

public class AuthActivity extends DaggerAppCompatActivity implements OnClickListener {

  private static final String TAG = "AuthActivity";

  private AuthViewModel viewModel;

  private EditText userId;

  @Inject
  ViewModelProviderFactory providerFactory;

  @Inject
  Drawable logo;

  @Inject
  RequestManager requestManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_auth);
    userId = findViewById(R.id.user_id_input);

    findViewById(R.id.login_button).setOnClickListener(this);

    viewModel = ViewModelProviders.of(this, providerFactory).get(AuthViewModel.class);

    setLogo();
    subscribeObservers();
  }

  private void subscribeObservers() {
    viewModel.observeUser().observe(this, new Observer<User>() {
      @Override
      public void onChanged(User user) {
        if (user != null) {
          Log.d(TAG, "onChanged: " + user.getEmail());
        }
      }
    });
  }

  private void setLogo() {
    requestManager.load(logo).into((ImageView) findViewById(R.id.login_logo));
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.login_button:
        attemptLogin();
        break;
    }
  }

  private void attemptLogin() {
    if (TextUtils.isEmpty(userId.getText().toString())) {
      return;
    }

    viewModel.authenticateWithId(userId.getText().toString());
  }
}
