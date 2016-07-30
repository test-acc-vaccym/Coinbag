package uk.co.richyhbm.coinbag.balances;

import android.util.Log;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import okhttp3.Request;
import okhttp3.Response;

//Balance fetcher for EtherChain Info
public class EtherChain extends Balance {
    public EtherChain() {
        super(10);
    }

    @Override
    protected double getBalance(String address) throws IOException {
        //Fetches the address balance and returns it in Ether
        Request req = new Request.Builder().url("https://etherchain.org/api/account/" + address).build();
        Response res = client.newCall(req).execute();

        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<EtherChainResponse> jsonAdapter = moshi.adapter(EtherChainResponse.class);

        EtherChainResponse ethResponse = jsonAdapter.fromJson(res.body().string());
        if(ethResponse == null || ethResponse.status != 1 || ethResponse.data.length == 0)
            return -1;

        DecimalFormat df = new DecimalFormat("#.########");
        df.setRoundingMode(RoundingMode.HALF_DOWN);
        Double ether = ethResponse.data[0].balance * Math.pow(10, -18);
        return Double.parseDouble(df.format(ether));
    }

    static class EtherChainResponse {
        public int status;
        public Address[] data;
        static class Address {
            public String address;
            public double balance;
            public String code;
        }
    }
}
