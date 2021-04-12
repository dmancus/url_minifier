package com.test.URLMinifier.presentation.entities;

import lombok.Data;

import java.util.ArrayList;

@Data
public class URLMappingResponse {
    boolean success;
    ArrayList<String> errors;
}
