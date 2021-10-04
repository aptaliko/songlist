TODO LIST
1. Create delete and update functions
2. Create predicates for new features
3. Check how the database is not deleted
4. Create other characteristics
   1. Region (3 levels)
5. Deny wrong format of json objects -> Handle validation exceptions correctly and check annotations on all entities
6. Pagination at search endpoint


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