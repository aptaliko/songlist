TODO LIST
1. Create other characteristics
   1. Region (3 levels)
2. Pagination at search endpoint

SET UP THE DB

Create a named volume:
docker volume create songlist-db

Start docker with specific image:
docker run --name songlist-psql \
-v songlist-db:/etc/songlist \
-p 5432:5432 \
-e POSTGRES_DB=songlist \
-e POSTGRES_PASSWORD=mysecretpassword \
-d postgres