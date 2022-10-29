create table resume
(
    uuid      char(36) not null
        constraint resume_pk
            primary key,
    full_name text
);

alter table resume
    owner to postgres;

create table contact
(
    id          serial
        constraint contact_pk
            primary key,
    type        text     not null,
    value       text     not null,
    resume_uuid char(36) not null
        references resume
            on delete cascade
);

alter table contact
    owner to postgres;

create unique index contact_uuid_type_index
    on contact (resume_uuid, type);

create table section
(
    id          serial
        primary key,
    resume_uuid char(36) not null
        references resume
            on delete cascade,
    type        text     not null,
    content     text     not null
);

alter table section
    owner to postgres;

create unique index section_idx
    on section (resume_uuid, type);


