#curl --location --request PUT 'localhost:8080/buku' \
#  --header 'Content-Type: application/json' \
#  --data-raw '{
#    "id": 10,
#    "tajuk":"Taju buku ialah",
#    "harga": 45.0
#}'
curl --location --request PUT 'localhost:8080/buku' \
--header 'Content-Type: application/json' \
--data-raw '{
    "tajuk":"Taju buku ialah",
    "harga": 45.0
}'