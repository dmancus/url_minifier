package com.test.URLMinifier.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="url_map")
@AllArgsConstructor
@NoArgsConstructor
public class URLMap {
    @Id
    String shortUrl;
    String url;
    LocalDateTime dateCreated;
}
