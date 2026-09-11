# probe

## Running it

`./mvnw spring-boot:run` starts it.

## One schema, one role

```sql
create role probe login password '…';
create schema probe authorization probe;
```

## Building and checking

`./mvnw verify`, then `sh service-conventions/service-conventions-check`.
