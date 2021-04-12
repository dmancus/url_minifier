
## Create a URLMap
curl -X POST -H "Content-Type: application/json" http://localhost:9876/url_mapping -d '{"shortUrl":"g","url":"http://google.com"}'

curl -X POST -H "Content-Type: application/json" http://localhost:9876/url_mapping -d '{"shortUrl":"g","url":"http://espn.com"}'

## Check a Url Map
curl http://localhost:9876/url_mapping/g

## Try a real redirect
curl http://localhost:9876/url/g

curl --location-trusted http://localhost:9876/url/g

## Clean out the db
curl -X DELETE http://localhost:9876/url_mapping

# In a browser:
http://localhost:9876/url/g


