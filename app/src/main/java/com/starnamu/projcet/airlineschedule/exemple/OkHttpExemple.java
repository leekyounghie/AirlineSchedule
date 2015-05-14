package com.starnamu.projcet.airlineschedule.exemple;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by starnamu on 2015-05-12.
 */
public class OkHttpExemple {

    private final OkHttpClient client = new OkHttpClient();

    public void run() throws Exception {
        Request request = new Request.Builder()
                .url("http://publicobject.com/helloworld.txt")
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful())
            throw new IOException("Unexpected code " + response);

        Headers responseHeaders = response.headers();
        for (int i = 0; i < responseHeaders.size(); i++) {
            System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
        }

        System.out.println(response.body().string());

        Call call = client.newCall(request);

        //enqueue가 비동기
        //execute가 동기 통신
        call.enqueue(new Callback() {
            //faile
            @Override
            public void onFailure(Request request, IOException e) {

            }

            //Ok
            @Override
            public void onResponse(Response response) throws IOException {

            }
        });
    }
}
