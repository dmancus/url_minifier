package com.test.URLMinifier.presentation;

import com.test.URLMinifier.model.entities.URLMap;
import com.test.URLMinifier.presentation.entities.URLMappingResponse;
import com.test.URLMinifier.presentation.exceptions.RedirectFailException;
import com.test.URLMinifier.service.URLMappingService;
import com.test.URLMinifier.service.exceptions.NoSuchMapEntryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;

@RestController
public class endpoints {

    @Autowired
    URLMappingService mappingService;

    @PostMapping("/url_mapping")
    public URLMappingResponse submitUrlMap(@RequestBody URLMap urlMap){
        URLMappingResponse response = new URLMappingResponse();
        try {
            mappingService.submitNewMapping(urlMap);
            response.setSuccess(true);
        }
        catch(Exception e){
            response.setSuccess(false);
            ArrayList<String> errors = new ArrayList<String>();
            errors.add(e.getMessage());
            response.setErrors(errors);
        }
        finally {
            return response;
        }
    }

    @GetMapping("url_mapping/{alias}")
    public String getMap(@PathVariable("alias") String alias){
        String url = mappingService.findUrlForAlias(alias);
        return url;
    }

    @DeleteMapping("/url_mapping")
    // Never offer something like this in prod, its just for play :)
    public void deleteAllAliases() {
        mappingService.deleteAllAliases();
    }


    @GetMapping("/url/{alias}")
    public ResponseEntity forwardRequest(@PathVariable("alias") String alias){
        try {
            String realUrl = mappingService.findUrlForAlias(alias);
            URI location = URI.create(realUrl);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setLocation(location);
            return new ResponseEntity<>(responseHeaders, HttpStatus.FOUND);
        }
        catch(NoSuchMapEntryException e){
            // Throw error
            throw new RedirectFailException(e.getMessage());
        }
    }
}
