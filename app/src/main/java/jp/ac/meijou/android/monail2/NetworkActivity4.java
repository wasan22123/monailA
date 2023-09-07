package jp.ac.meijou.android.monail2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.Optional;

import jp.ac.meijou.android.monail2.databinding.ActivityNetwork4Binding;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkActivity4 extends AppCompatActivity {

    private final OkHttpClient okHttpClient = new OkHttpClient();

    private final Moshi moshi = new Moshi.Builder().build();

    private final JsonAdapter<Gist> gistJsonAdapter = moshi.adapter(Gist.class);

    private ActivityNetwork4Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNetwork4Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button4.setOnClickListener(view ->{
            var text =binding.editTextText2.getText().toString();//入力されたものをtextに入れる

            var url = Uri.parse("https://placehold.jp/46d3dd/ffffff/150x150.png")
                    .buildUpon()
                    .appendQueryParameter("text", text)//textを付け加える
                    .build()
                    .toString();
            getImage(url);
        });
    }
    private void getImage(String url){
            var request = new Request.Builder()
                    .url(url)
                    .build();

            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {

                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    var bitmap = BitmapFactory.decodeStream(response.body().byteStream());
                    runOnUiThread(() -> binding.imageFilterView.setImageBitmap(bitmap));
                }
            });
        }
    }
