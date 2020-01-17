-- list of 20 random names generated from the
-- US Census 1990 – Names Files
-- https://www.census.gov/topics/population/genealogy/data/1990_census/1990_census_namefiles.html

USE test;
-- define db, table, and column names:
SET @dbName="test";
SET @tableName="table1";
SET @lastName="last_name";
SET @firstName="first_name";
SET @middleNames="middle_names";
SET @birthDate="date_of_birth";
SET @gender="gender";
SET @randomSeed="25215029838";
INSERT INTO table1( last_name, first_name, middle_names, date_of_birth, gender) 
   VALUES("Constantino", "Jacque", "", '1993-11-11', 'F');
INSERT INTO table1( last_name, first_name, middle_names, date_of_birth, gender) 
   VALUES("Gearon", "Tanesha", "Rosanne", '1968-06-11', 'F');
INSERT INTO table1( last_name, first_name, middle_names, date_of_birth, gender) 
   VALUES("Mantsch", "Ahmed", "Chas", '1972-02-20', 'M');
INSERT INTO table1( last_name, first_name, middle_names, date_of_birth, gender) 
   VALUES("Plump", "Timika", "", '1983-07-26', 'F');
INSERT INTO table1( last_name, first_name, middle_names, date_of_birth, gender) 
   VALUES("Giannakopoulo", "Gemma", "Jacinda", '1984-11-18', 'F');
INSERT INTO table1( last_name, first_name, middle_names, date_of_birth, gender) 
   VALUES("Dingivan", "Pasquale", "Stefan", '1972-12-02', 'M');
INSERT INTO table1( last_name, first_name, middle_names, date_of_birth, gender) 
   VALUES("Laehn", "Breanna", "", '1978-06-25', 'F');
INSERT INTO table1( last_name, first_name, middle_names, date_of_birth, gender) 
   VALUES("Schlatter", "Marica", "Selma Janella", '1976-12-08', 'F');
INSERT INTO table1( last_name, first_name, middle_names, date_of_birth, gender) 
   VALUES("Setzler", "Jeremiah", "Antone Shari", '1968-08-07', 'M');
INSERT INTO table1( last_name, first_name, middle_names, date_of_birth, gender) 
   VALUES("Inabnit", "Hollie", "", '1958-12-30', 'F');
INSERT INTO table1( last_name, first_name, middle_names, date_of_birth, gender) 
   VALUES("Kyllonen", "Everette", "", '1989-07-31', 'M');
INSERT INTO table1( last_name, first_name, middle_names, date_of_birth, gender) 
   VALUES("Valenziano", "Twanda", "", '1980-01-01', 'F');
INSERT INTO table1( last_name, first_name, middle_names, date_of_birth, gender) 
   VALUES("Gierke", "Clint", "", '1964-02-08', 'M');
INSERT INTO table1( last_name, first_name, middle_names, date_of_birth, gender) 
   VALUES("Pintar", "Fausto", "Van Catrice", '1978-11-15', 'M');
INSERT INTO table1( last_name, first_name, middle_names, date_of_birth, gender) 
   VALUES("Auyer", "Palmer", "Harland", '1972-09-24', 'M');
INSERT INTO table1( last_name, first_name, middle_names, date_of_birth, gender) 
   VALUES("Sala", "Rosario", "", '1966-10-13', 'M');
INSERT INTO table1( last_name, first_name, middle_names, date_of_birth, gender) 
   VALUES("Marsaw", "Harrison", "", '1960-06-30', 'M');
INSERT INTO table1( last_name, first_name, middle_names, date_of_birth, gender) 
   VALUES("Baczewski", "Gil", "Rigoberto", '1965-11-30', 'M');
INSERT INTO table1( last_name, first_name, middle_names, date_of_birth, gender) 
   VALUES("Benzel", "Weston", "Ivan Ellie", '1970-11-29', 'M');
INSERT INTO table1( last_name, first_name, middle_names, date_of_birth, gender) 
   VALUES("Goliday", "Dwain", "", '1986-10-21', 'M');
