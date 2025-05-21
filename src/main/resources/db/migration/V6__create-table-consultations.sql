create table consultations(

    id bigint primary key,
    doctor_id bigint not null,
    patient_id bigint not null,
    date_consultation timestamp not null,

    constraint fk_consultations_doctor_id foreign key(doctor_id) references doctors(id),
    constraint fk_consultations_patient_id foreign key(patient_id) references patients(id)

);