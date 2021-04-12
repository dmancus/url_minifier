package com.test.URLMinifier.service;

import com.test.URLMinifier.infrastructure.URLMapRepository;
import com.test.URLMinifier.model.entities.URLMap;
import com.test.URLMinifier.service.exceptions.ShortUrlAlreadyExistsException;
import com.test.URLMinifier.service.exceptions.NoSuchMapEntryException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Log4j2
@Service
public class URLMappingService {

    @Autowired
    URLMapRepository repo;

    public String findUrlForAlias(String alias) throws NoSuchMapEntryException{

        Optional<URLMap> map = repo.findById(alias);
        URLMap resultMap = map.orElseThrow(() -> new NoSuchMapEntryException("Cannot find any URL for alias " + alias));

        return resultMap.getUrl();
    }

    public void deleteAllAliases(){
        repo.deleteAll();
    }

    public void submitNewMapping(URLMap urlMap){
        if ( urlMap.getDateCreated() == null){
            urlMap.setDateCreated(LocalDateTime.now());
        }
        try {
            Optional<URLMap> map = repo.findById(urlMap.getShortUrl());
            if ( map.isEmpty() ) {
                repo.save(urlMap);
            }
            else{
                throw new ShortUrlAlreadyExistsException(
                        String.format("Cannot submit another alias for %s as it is already mapped to %s",
                                urlMap.getShortUrl(), urlMap.getUrl()));
            }
        }
        catch(Exception e){
            // Not expected, need to see why this happens
            log.warn(String.format("Cannot insert urlMap for: %s due to error %s",
                    urlMap.getShortUrl(),e.getMessage()));
            throw e;
        }
    }
}
