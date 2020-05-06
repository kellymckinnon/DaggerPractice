package me.kellymckinnon.daggerpractice.ui.main.posts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import java.util.ArrayList;
import java.util.List;
import me.kellymckinnon.daggerpractice.R;
import me.kellymckinnon.daggerpractice.models.Post;

public class PostsRecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {

  private List<Post> posts = new ArrayList<>();

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.layout_post_list_item, parent, false);
    return new PostViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    ((PostViewHolder) holder).bind(posts.get(position));
  }

  @Override
  public int getItemCount() {
    return posts.size();
  }

  public void setPosts(List<Post> posts) {
    this.posts = posts;
    notifyDataSetChanged();
  }

  class PostViewHolder extends ViewHolder {

    TextView title;

    PostViewHolder(@NonNull View itemView) {
      super(itemView);
      title = itemView.findViewById(R.id.title);
    }

    void bind(Post post) {
      title.setText(post.getTitle());
    }
  }
}
