package vn.edu.ueh.thanhdnh.firebase_example;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ArticleViewHolder extends RecyclerView.ViewHolder {
  private ImageView ivCover;
  private TextView txtTitle, txtDescription;
  private ArticleViewAdapter adapter;

  public ArticleViewHolder(@NonNull View itemView, ArticleViewAdapter adapter) {
    super(itemView);
    ivCover = itemView.findViewById(R.id.ivCover);
    txtTitle = itemView.findViewById(R.id.txt_title);
    txtDescription = itemView.findViewById(R.id.txt_description);
    this.adapter = adapter;
  }

  public ImageView getIvCover() {
    return ivCover;
  }

  public void setIvCover(ImageView ivCover) {
    this.ivCover = ivCover;
  }

  public TextView getTxtTitle() {
    return txtTitle;
  }

  public void setTxtTitle(TextView txtTitle) {
    this.txtTitle = txtTitle;
  }

  public TextView getTxtDescription() {
    return txtDescription;
  }

  public void setTxtDescription(TextView txtDescription) {
    this.txtDescription = txtDescription;
  }
}
