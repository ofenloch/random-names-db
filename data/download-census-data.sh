#!/bin/bash

mkdir -p us-census-1990

curl https://www2.census.gov/topics/genealogy/1990surnames/dist.all.last > us-census-1990/dist.all.last.txt

curl https://www2.census.gov/topics/genealogy/1990surnames/dist.female.first > us-census-1990/dist.female.first.txt

curl https://www2.census.gov/topics/genealogy/1990surnames/dist.male.first > us-census-1990/dist.male.first.txt

curl https://www2.census.gov/topics/genealogy/1990surnames/nam_meth.txt > us-census-1990/names.documentation.txt