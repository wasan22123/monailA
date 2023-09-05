package jp.ac.meijou.android.monail2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import jp.ac.meijou.android.monail2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button.setOnClickListener(view -> {
            binding.text.setText(R.string.my_name);
        });

        binding.button2.setOnClickListener(view ->{
            binding.imageView.setImageResource(R.drawable.ic_androidred);
        });

        binding.button3.setOnClickListener(view ->{
            var text = binding.editText.getText().toString();
            binding.text.setText(text);
        });

        binding.editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                binding.text.setText(s.toString());
            }
        });

        binding.text.setText(R.string.app_name);

    }


}