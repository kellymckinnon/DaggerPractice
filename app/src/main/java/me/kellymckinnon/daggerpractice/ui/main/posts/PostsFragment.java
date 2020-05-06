package me.kellymckinnon.daggerpractice.ui.main.posts;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dagger.android.support.DaggerFragment;
import java.util.List;
import javax.inject.Inject;
import me.kellymckinnon.daggerpractice.R;
import me.kellymckinnon.daggerpractice.models.Post;
import me.kellymckinnon.daggerpractice.ui.main.Resource;
import me.kellymckinnon.daggerpractice.viewmodels.ViewModelProviderFactory;

public class PostsFragment extends DaggerFragment {

  private static final String TAG = "PostsFragment";

  private PostsViewModel viewModel;
  private RecyclerView recyclerView;

  @Inject
  ViewModelProviderFactory providerFactory;

  @Inject
  PostsRecyclerAdapter postsRecyclerAdapter;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_post, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    recyclerView = view.findViewById(R.id.recycler_view);
    viewModel = ViewModelProviders.of(this, providerFactory).get(PostsViewModel.class);
    subscribeObservers();
    initRecyclerView();
  }

  private void initRecyclerView() {
    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    recyclerView.addItemDecoration(
        new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    recyclerView.setAdapter(postsRecyclerAdapter);
  }

  private void subscribeObservers() {
    LifecycleOwner lifeCycleOwner = getViewLifecycleOwner();

    // Necessary for fragments
    viewModel.observePosts().removeObservers(lifeCycleOwner);

    viewModel.observePosts().observe(lifeCycleOwner,
        new Observer<Resource<List<Post>>>() {
          @Override
          public void onChanged(Resource<List<Post>> listResource) {
            if (listResource == null) {
              return;
            }

            switch (listResource.status) {
              case LOADING:
                Log.d(TAG, "onChanged: LOADING...");
                break;
              case SUCCESS:
                Log.d(TAG, "onChanged: got posts...");
                postsRecyclerAdapter.setPosts(listResource.data);
                break;
              case ERROR:
                Log.e(TAG, "onChanged: ERROR" + listResource.message);
                break;
            }
          }
        });
  }
}
