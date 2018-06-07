insert into HIRERENTITY(SSN, BIRTHDATE)
  select '123456789', '1982-10-07'
  where not exists(select null from HIRERENTITY where ssn = '123456789');

insert into HIRERENTITY(SSN, BIRTHDATE, DATEOFDEATH)
  select '987654321', '2000-01-01', '2018-01-01'
  where not exists(select null from HIRERENTITY where ssn = '987654321');