package com.audition.configuration;

import com.audition.common.logging.AuditionLogger;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@AllArgsConstructor
public class LoggingInterceptor implements ClientHttpRequestInterceptor {

    private AuditionLogger auditionLogger;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
        throws IOException {
        Logger logger = auditionLogger.getLogger();
        logRequest(logger, request, body);
        ClientHttpResponse response = execution.execute(request, body);
        logResponse(logger, response);
        return response;
    }

    private void logRequest(Logger logger, HttpRequest request, byte[] body) {
        auditionLogger.info(logger, "Request Method: " + request.getMethod());
        auditionLogger.info(logger, "Request URI: " + request.getURI());
        auditionLogger.debug(logger, "Request Body: " + new String(body, StandardCharsets.UTF_8));
    }

    private void logResponse(Logger logger, ClientHttpResponse response) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(
            new InputStreamReader(response.getBody(), StandardCharsets.UTF_8));
        String line;
        StringBuilder responseBody = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null) {
            responseBody.append(line);
        }
        auditionLogger.info(logger, "Response Status Code: " + response.getStatusCode());
        auditionLogger.debug(logger, "Response Body: " + responseBody.toString());
    }
}

