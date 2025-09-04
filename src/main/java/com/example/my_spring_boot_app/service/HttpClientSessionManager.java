package com.example.my_spring_boot_app.service;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.cookie.BasicCookieStore;
import org.apache.hc.client5.http.cookie.CookieStore;
import org.apache.hc.client5.http.protocol.HttpClientContext;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Manages an HTTP session for a single user/client,
 * similar to Python's requests.Session.
 * It handles cookies and connection pooling automatically.
 */
@Service
public class HttpClientSessionManager {

    private final CloseableHttpClient httpClient;
    private final HttpClientContext context;

    public HttpClientSessionManager() {
        // Create a cookie store to manage session cookies
        CookieStore cookieStore = new BasicCookieStore();
        
        // Create a context to hold the cookie store
        this.context = HttpClientContext.create();
        this.context.setCookieStore(cookieStore);

        // Configure connection pooling for efficiency
        var connectionManager = PoolingHttpClientConnectionManagerBuilder.create().build();

        // Build the HTTP client with the cookie store and connection manager
        this.httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .setDefaultCookieStore(cookieStore)
                .build();
    }

    /**
     * Executes an HTTP GET request with the session.
     * @param url The URL to send the GET request to.
     * @return The response body as a String.
     * @throws IOException If an I/O error occurs.
     */
    public String get(String url) throws IOException {
        ClassicHttpRequest httpGet = ClassicRequestBuilder.get(url).build();
        return httpClient.execute(httpGet, context, response -> {
            int status = response.getCode();
            if (status >= 200 && status < 300) {
                return EntityUtils.toString(response.getEntity());
            } else {
                throw new IOException("Unexpected response status: " + status);
            }
        });
    }

    /**
     * Executes an HTTP POST request with the session.
     * @param url The URL to send the POST request to.
     * @param body The request body as a String.
     * @return The response body as a String.
     * @throws IOException If an I/O error occurs.
     */
    public String post(String url, String body) throws IOException {
        ClassicHttpRequest httpPost = ClassicRequestBuilder.post(url)
                .addHeader("Content-Type", "application/json")
                .setEntity(body)
                .build();
        return httpClient.execute(httpPost, context, response -> {
            int status = response.getCode();
            if (status >= 200 && status < 300) {
                return EntityUtils.toString(response.getEntity());
            } else {
                throw new IOException("Unexpected response status: " + status);
            }
        });
    }
    
    /**
     * Closes the underlying HTTP client.
     * It's good practice to call this when the application shuts down.
     * @throws IOException If an I/O error occurs.
     */
    public void close() throws IOException {
        httpClient.close();
    }
}
