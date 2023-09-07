package jp.ac.meijou.android.monail2;

import androidx.appcompat.app.AppCompatActivity;

import android.net.ConnectivityManager;
import android.net.LinkAddress;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Bundle;

import java.util.stream.Collectors;

import jp.ac.meijou.android.monail2.databinding.ActivityConnect4Binding;
import jp.ac.meijou.android.monail2.databinding.ActivityNetwork4Binding;

public class ConnectivityActivity4 extends AppCompatActivity {

    private ActivityConnect4Binding binding;

    private ConnectivityManager connectivityManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConnect4Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        connectivityManager = getSystemService(ConnectivityManager.class);
        var currentNetwork = connectivityManager.getActiveNetwork();
        updateTransport(currentNetwork);
        updateIpAddresses(currentNetwork);
    }

    private void updateTransport(Network network){
        var caps =connectivityManager.getNetworkCapabilities(network);
        if(caps != null){
            String transport;
            if (caps.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                transport = "モバイル通信";
            } else if (caps.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                transport = "WiFi";
            } else {
                transport = "その他";
            }

            binding.textView5.setText(transport);
        }
    }

    private void  updateIpAddresses(Network network){
        var linkProps = connectivityManager.getLinkProperties(network);
        if(linkProps != null){
            var addresses = linkProps.getLinkAddresses().stream()
                    .map(LinkAddress::toString)
                    .collect(Collectors.joining("\n"));

            binding.textView6.setText(addresses);
        }
    }



}