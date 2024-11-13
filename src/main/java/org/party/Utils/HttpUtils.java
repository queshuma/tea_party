package org.party.Utils;

import com.alibaba.fastjson2.JSON;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;

/**
 * Description:
 * Author: chentao
 * Date: 02 10月 2024
 *
 * @version 1.0
 */
public final class HttpUtils {
    /**
     * http Get Request
     */
    public String HttpGet(Map<String, String> parmsMap, String url) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = null;
        if (Objects.isNull(parmsMap)) {
            request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();
        } else {
            StringBuffer parmsStr = new StringBuffer();
            parmsStr.append("?");
            for (String name:parmsMap.keySet()) {
                parmsStr.append(URLEncoder.encode(name, StandardCharsets.UTF_8) + "=" + URLEncoder.encode(parmsMap.get(name), StandardCharsets.UTF_8) + "&");
            }
            String parms = parmsStr.substring(0, parmsStr.length()-1);
            request = HttpRequest.newBuilder()
                    .uri(URI.create(url + parms))
                    .GET()
                    .build();
        }
        return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
    }

//    /**
//     * http Get Request Async
//     */
//    public HttpGetAsync() {
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("https://api.weixin.qq.com/sns/jscode2session"))
//                .build();
//        client.sendAsync(request, BodyHandlers.ofFile(Paths.get("/ tmp/ f")))
//                .thenApply(HttpResponse::body)
//                .thenAccept(System. out::println);
//    }
}
