package vn.edu.ueh.thanhdnh.firebase_example;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ArticleViewAdapter extends RecyclerView.Adapter<ArticleViewHolder> {
  private LayoutInflater mInflater;
  private List<Article> articles;

  public ArticleViewAdapter(Context context, List<Article> articles) {
    this.mInflater = LayoutInflater.from(context);
    this.articles = articles;
  }

  public void update(List<Article> articles) {
    this.articles = articles;
  }

  @NonNull
  @Override
  public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View customView = mInflater.inflate(R.layout.contact_list, parent, false);
    return new ArticleViewHolder(customView, this);
  }

  @Override
  public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
    Article currentArticle = articles.get(position);
    holder.getTxtTitle().setText(currentArticle.getTitle());
    holder.getTxtDescription().setText(currentArticle.getDescription());

    // Tải ảnh đại diện qua Glide (hỗ trợ URL internet và drawable)
    String img = currentArticle.getImgCover();
    if (img != null && !img.trim().isEmpty()) {
      int resId = holder.itemView.getContext().getResources().getIdentifier(
              img, "drawable", holder.itemView.getContext().getPackageName());
      if (resId != 0) {
        Glide.with(holder.itemView.getContext())
                .load(resId)
                .centerCrop()
                .placeholder(R.drawable.ic_cover)
                .error(R.drawable.ic_cover)
                .into(holder.getIvCover());
      } else {
        Glide.with(holder.itemView.getContext())
                .load(img)
                .centerCrop()
                .placeholder(R.drawable.ic_cover)
                .error(R.drawable.ic_cover)
                .into(holder.getIvCover());
      }
    } else {
      holder.getIvCover().setImageResource(R.drawable.ic_cover);
    }

    // Bấm vào xem chi tiết bài viết (mở ArticleDetailActivity)
    holder.itemView.setOnClickListener(v -> {
      Intent intent = new Intent(v.getContext(), ArticleDetailActivity.class);
      intent.putExtra("article", currentArticle);
      v.getContext().startActivity(intent);
    });
  }

  @Override
  public int getItemCount() {
    return articles.size();
  }
}
