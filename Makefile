NAME = donadoio

DB_SCRIPTS_DIR = ./psql_scripts/

db:
	@ docker create --name postgres-donado -e POSTGRES_PASSWORD=7502976 -p 5432:5432 postgres:11.5-alpine
	@ docker start postgres-donado

run-db:
	@ docker exec -it postgres-donado psql -U postgres

db-scripts:
	@ docker cp $(DB_SCRIPTS_DIR)create_tables.sql postgres-donado:/create_tables.sql
	@ docker cp $(DB_SCRIPTS_DIR)insert_data.sql postgres-donado:/insert_data.sql
	@ docker exec -it postgres-donado psql -d api -f create_tables.sql -U postgres
	@ docker exec -it postgres-donado psql -d api -f insert_data.sql -U postgres
