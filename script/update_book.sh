curl --location --request POST 'localhost:8080/buku' \
  --header 'Content-Type: application/json' \
  --data-raw '{
    "id": 10,
    "tajuk":"Tajuk buku ialah",
    "harga": 45.50
}'
