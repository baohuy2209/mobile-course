package com.mobile_course.asset_shared_preferences;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.mobile_course.adapters.FontsAdapter;
import com.mobile_course.asset_shared_preferences.databinding.ActivityMainBinding;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FontsAdapter adapter;
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loadFonts();
        events();
    }

    private void events() {
        binding.lvFonts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedFont = (String) adapter.getItem(i);
                Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/" + selectedFont);
                binding.txtContent.setTypeface(tf);
                playAudio();
            }
        });
    }

    private void playAudio() {
        try {
            AssetFileDescriptor descriptor = getAssets().openFd("musics/ting.mp3");
            MediaPlayer player = new MediaPlayer();
            player.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            player.prepare();
            player.start();
            descriptor.close();

            // Giải phóng khi phát xong
            player.setOnCompletionListener(MediaPlayer::release);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadFonts(){
        try {
            String[] fonts = getAssets().list("fonts");
            // Lọc bỏ các file rác ._* của macOS nếu có
            ArrayList<String> fontList = new ArrayList<>();
            for (String f : fonts) {
                if (!f.startsWith(".")) fontList.add(f);
            }

            adapter = new FontsAdapter(MainActivity.this, R.layout.item_fonts, fontList);
            binding.lvFonts.setAdapter(adapter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}