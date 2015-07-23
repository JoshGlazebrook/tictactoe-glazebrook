package com.glazebrook.tictactoe.responses;

import com.codahale.metrics.MetricRegistryListener;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class BaseWebApiResponse {
    @JsonProperty
    private String error;

    @JsonProperty
    private Object data;

    public BaseWebApiResponse() {

    }

    public BaseWebApiResponse(Object data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public BaseWebApiResponse setError(String error) {
        this.error = error;
        return this;
    }

    public Object getData() {
        return data;
    }

    public BaseWebApiResponse setData(Object data) {
        this.data = data;
        return this;
    }
}
