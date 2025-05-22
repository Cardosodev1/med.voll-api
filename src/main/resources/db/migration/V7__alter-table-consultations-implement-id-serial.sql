create sequence consultations_id_seq;

alter table consultations
alter column id set default nextval('consultations_id_seq');