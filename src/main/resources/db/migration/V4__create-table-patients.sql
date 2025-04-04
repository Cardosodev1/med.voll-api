  create table patients(

      id serial primary key,
      name varchar(100) not null,
      email varchar(100) not null unique,
      phone varchar(11) not null,
      cpf varchar(11) not null unique,
      street varchar(100) not null,
      neighborhood varchar(100) not null,
      zip_code varchar(9) not null,
      city varchar(100) not null,
      state char(2) not null,
      number varchar(20),
      complement varchar(100),
      active boolean default true not null

  );