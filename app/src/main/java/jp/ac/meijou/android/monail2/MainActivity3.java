package jp.ac.meijou.android.monail2;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import java.util.Optional;

import jp.ac.meijou.android.monail2.databinding.ActivityMain2Binding;
import jp.ac.meijou.android.monail2.databinding.ActivityMain3Binding;

public class MainActivity3 extends AppCompatActivity {

    private ActivityMain3Binding binding;
    private final ActivityResultLauncher<Intent> getActivityResult = registerForActivityResult(

        new ActivityResultContracts.StartActivityForResult(),
        result -> {
            switch (result.getResultCode()) {
                case RESULT_OK:
                    Optional.ofNullable(result.getData())
                            .map(data -> data.getStringExtra("ret"))
                            .map(text -> "Result: " + text)
                            .ifPresent(text -> binding.editTextText.setText(text));
                    break;
                case RESULT_CANCELED:
                    binding.editTextText.setText("Result: Canceled");
                    break;
                default:
                    binding.editTextText.setText("Result: Unknown(" + result.getResultCode() + ")");
                    break;
            }
        }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button10.setOnClickListener(view -> {
            var intent = new Intent(this, ActivityMain2Binding.class);
            startActivity(intent);
        });

        // camera
        binding.button10.setOnClickListener(view -> {
            var intent = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
            startActivity(intent);
        });

        // 暗黙的Intent
        binding.button11.setOnClickListener(view -> {
            var intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://www.yahoo.co.jp"));
            startActivity(intent);
        });



        binding.button10.setOnClickListener(view -> {
            var intent = new Intent(this, SubActivity.class);
            intent.putExtra("text", binding.textView3.getText().toString());
            startActivity(intent);
        });

    }
}