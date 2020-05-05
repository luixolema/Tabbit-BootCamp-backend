package com.tabit.dcm2.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.tabit.dcm2.repository.AbstractDbTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.time.format.DateTimeFormatter;

import static com.tabit.dcm2.config.JsonConfig.DATE_FORMAT;
import static com.tabit.dcm2.config.JsonConfig.DATE_TIME_FORMAT;

public abstract class AbstractRestIntegrationTest extends AbstractDbTest {
    private static final DateTimeFormatter LOCAL_DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

    @LocalServerPort
    protected int port;
    @Autowired
    protected TestRestTemplate restTemplate;
    protected ObjectMapper objectMapper = createObjectMapper();

    protected String getBaseUrl() {
        return "http://localhost:" + port;
    }

    protected <T> HttpEntity<T> createHttpEntity(T details, String authToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + authToken);
        return new HttpEntity<T>(details, headers);
    }

    protected <T> HttpEntity<T> createHttpEntity(String authToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + authToken);
        return new HttpEntity<T>(headers);
    }

    private ObjectMapper createObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(
                new SimpleModule()
                        .addSerializer(new LocalDateSerializer(LOCAL_DATE_FORMATTER))
                        .addSerializer(new LocalDateTimeSerializer(DATE_TIME_FORMATTER)))
                .registerModule(new Jdk8Module());
        return objectMapper;
    }

    protected void printJson(Object dto) {
        try {
            System.out.println(objectMapper.writeValueAsString(dto));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }


}
