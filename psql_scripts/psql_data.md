# Login info
JDBC URL: `jdbc:postgresql://localhost:5432/site_api`
docker container name: postgres-donado
database name: site_api
db password: 7502976

# create docker db container and make db
```bash
docker create --name postgres-donado -e POSTGRES_PASSWORD=7502976 -p 5432:5432 postgres:11.5-alpine
docker exec -it postgres-donado psql -U postgres
psql> create database site_api;
```
# Copy scripts to docker container and run them
```bash
docker cp create_tables.sql postgres-donado:/create_tables.sql
docker cp insert_data.sql postgres-donado:/insert_data.sql
docker exec -it postgres-donado psql -d conference_app -f create_tables.sql -U postgres
```