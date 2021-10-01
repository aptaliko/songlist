TODO LIST
1. Create delete and update functions
2. Check how the database is not deleted
3. Create other characteristics
4. Add validations on New DTOs and deny wrong format of json objects
5. Pagination at search endpoint


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