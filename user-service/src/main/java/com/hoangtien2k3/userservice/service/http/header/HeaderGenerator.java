package com.hoangtien2k3.userservice.service.http.header;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class HeaderGenerator {

    public HttpHeaders getHeadersSuccessGetMethod() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
        return httpHeaders;
    }

    public HttpHeaders getHeadersError() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/problem+json; charset=UTF-8");
        return httpHeaders;
    }

    public HttpHeaders getHeadersSuccessPostMethod(HttpServletRequest request, Long resourceId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        try {
            httpHeaders.setLocation(new URI(request.getRequestURI() + "/" + resourceId));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
        return httpHeaders;
    }
}