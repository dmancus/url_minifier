# Overview
An quick little experimental URL Minifier Project.  URL Minifier at its core needs to do two things:
1. Store new "aliases"
2. Return 302/301 redirect if an alias is found

# Domain Model
Domain model should be quite simple, I think we basically have one aggregate to define: `URLMap`

- `URLMap` - Basically a simple Map<String,String> that links an alias or short url to a full URL
    - We need to be able to store new URLMap entries
    - We need to be able to lookup URLMap entries and then route people to the correct URL
    - We will need handling for a "NotFound" behavior
  
# Quick Start
## To run the server
```gradlew bootRun```

## To compile and then run server
```
gradlew bootJar
java -Dspring.profiles.active=test -jar build/libs/URLMinifier-0.0.1-SNAPSHOT.jar
```
    
