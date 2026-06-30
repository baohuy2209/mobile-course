package com.nguyenbaohuy.self_learning_2;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.nguyenbaohuy.self_learning_2.databinding.ActivityReviewForMobileCourseBinding;

public class ReviewForMobileCourse extends AppCompatActivity {

    ActivityReviewForMobileCourseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityReviewForMobileCourseBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_review_for_mobile_course);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}