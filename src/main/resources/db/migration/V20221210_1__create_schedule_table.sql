create table if not exists "schedule"(
    "schedule_id" uuid not null primary key,
    "date" date not null,
    "start_at" time without time zone not null,
    "end_at" time without time zone not null,
    "attendant_id" uuid not null,
    "description" varchar not null,
    "created_at" timestamp without time zone not null,
    "updated_at" timestamp without time zone
)