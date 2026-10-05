package vn.edu.ueh.thanhdnh.firebase_example;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
  FirebaseFirestore db;
  Button btAdd, btShow;
  EditText etTitle, etDescription, etImgCover;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EdgeToEdge.enable(this);
    setContentView(R.layout.activity_main);
    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
      Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
      return insets;
    });

    FirebaseApp.initializeApp(this);
    db = FirebaseFirestore.getInstance();
    btAdd = findViewById(R.id.btAdd);
    btShow = findViewById(R.id.btShow);
    etTitle = findViewById(R.id.etTitle);
    etDescription = findViewById(R.id.etDescription);
    etImgCover = findViewById(R.id.etImgCover);
    btAdd.setOnClickListener(this);
    btShow.setOnClickListener(this);
  }

  @Override
  public void onClick(View view) {
    if (view.getId() == R.id.btAdd) {
      String title = etTitle.getText().toString().trim();
      String desc = etDescription.getText().toString().trim();
      String img = etImgCover.getText().toString().trim();
      if (img.isEmpty()) {
        img = "https://raw.githubusercontent.com/td22042006/PhotoApp/master/images/sontung.jpg";
      }

      Article article = new Article(title, desc, img, 0);
      db.collection("articles").add(article);

      etTitle.setText("");
      etDescription.setText("");
      etImgCover.setText("");
    } else if (view.getId() == R.id.btShow) {
      Intent intent = new Intent(getBaseContext(), ShowDataActivity.class);
      startActivity(intent);
    }
  }
}
