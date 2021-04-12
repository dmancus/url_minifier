package com.test.URLMinifier.service;

import com.test.URLMinifier.infrastructure.URLMapRepository;
import com.test.URLMinifier.model.entities.URLMap;
import com.test.URLMinifier.service.exceptions.NoSuchMapEntryException;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class URLMappingServiceTests {

    @InjectMocks
    URLMappingService mappingService;

    @Spy
    URLMapRepository repo;

    @Test
    void testFindUrlForAlias(){
        Mockito.when(repo.findById("testAliasExists")).
                thenReturn(Optional.of(new URLMap("testAliasExists", "http://alias", LocalDateTime.now())));
        String address = mappingService.findUrlForAlias("testAliasExists");

        assertEquals("http://alias",address);
    }

    @Test
    void testFindUrlForAliasNotExists(){
        Mockito.when(repo.findById("testAliasNotExists")).
                thenReturn(Optional.ofNullable(null));
        Exception exception = assertThrows(NoSuchMapEntryException.class, () -> {
            mappingService.findUrlForAlias("testAliasNotExists");
        });

        String expectedMessage = "Cannot find any URL for alias testAliasNotExists";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testDeleteAll(){
        mappingService.deleteAllAliases();
        verify(repo).deleteAll();
    }
}
