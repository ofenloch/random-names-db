# RandomNames - Generate SQL INSERT statement with a list of random names

## Building

Run `mvn clean dependency:copy-dependencies package` in the project's root folder. This builds the 
program, runs the tests and 'packages' the program (if the tests were successful). In directory *target/* 
there will be a jar file *randomnames-1.0-SNAPSHOT.jar* (the version number may be different) and a folder 
*dependency/* with all required jar files to run the program. See script *data/runApp.sh* on how to use the jar file.

## Usage



## Get The Data

Since I don't know how the copyright of the census data looks like, I do not include the files into this 
repository. But I do include a bash script to download the files: **data/download-census-data.sh**.

## Frequently Occurring Surnames from Census 1990 – Names Files

These lists were downloaded from <https://www.census.gov/topics/population/genealogy/data/1990_census/1990_census_namefiles.html>.

Each of the three files, (dist.all.last.txt, dist.male.txt, and dist.female.txt) contain four items of data. The four items are:

* A "Name"
* Frequency in percent
* Cumulative Frequency in percent
* Rank

In the file (dist.all.last.txt) one entry appears as:

    MOORE 0.312 5.312 9

In our search area sample, MOORE ranks 9th in terms of frequency. 5.312 percent of the sample population is covered by MOORE and the 8 names occurring more frequently than MOORE. The surname, MOORE, is possessed by 0.312 percent of our population sample.

For further info, please read the file (name_meth.txt).

There are some lines of java code java using the Census Data to generate lsits of random names, gender and date of birth.

## Use the Data in Java (and then SQL)

The program **randomnames** reads the three files from the census and generates INSERT statements with randomly 
combined last, first and middle names - divers, female and male. Further, dates of birth are randomly 
generated. Currently we have three of the generated files in the repository:

* -rw-rw-r-- 1 ofenloch ofenloch  14M 2019-12-18--21-24-23 randomnames100000.sql
* -rw-rw-r-- 1 ofenloch ofenloch 2,7M 2019-12-18--21-24-31 randomnames20000.sql
* -rw-rw-r-- 1 ofenloch ofenloch 3,2K 2019-12-18--21-41-49 randomnames20.sql
