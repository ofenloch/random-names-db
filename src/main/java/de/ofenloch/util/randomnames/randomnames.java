package de.ofenloch.util.randomnames;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.TreeMap;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.ofenloch.util.randomnames.gender;
import de.ofenloch.util.randomnames.name;

public class randomnames {

    static final long constantSeed = (0x23423 ^ 0x5DEECE66DL) & ((1L << 48) - 1);
    static Random random = new Random(constantSeed);
    // if you don't want to use the constant random seed to generate 'real' random
    // data, do something like this:
    // static final long constantSeed = 0;
    // static final Random random = new Random();

    protected static final Logger log = LogManager.getLogger(randomnames.class.getName());
    static public void main(String[] args) {

        String[] fileNames = { "./data/us-census-1990/dist.all.last.txt", "./data/us-census-1990/dist.female.first.txt",
                "./data/us-census-1990/dist.male.first.txt" };

        ArrayList<name> LastNameList = null;
        ArrayList<name> FirstNameListFemale = null;
        ArrayList<name> FirstNameListMale = null;
        try {
            // read the name list from the file
            LastNameList = randomnames.loadNames(fileNames[0], gender.DIVERSE);
            // generate a Java class
        } catch (Exception ex) {
            System.err.println("Caught exception while reading names from file \"" + fileNames[0] + "\":");
            System.err.println("  " + ex.getMessage());
            ex.printStackTrace();
            System.exit(1);
        }
        try {
            // read the name list from the file
            FirstNameListFemale = randomnames.loadNames(fileNames[1], gender.FEMALE);
            // generate a Java class
        } catch (Exception ex) {
            System.err.println("Caught exception while reading names from file \"" + fileNames[1] + "\":");
            System.err.println("  " + ex.getMessage());
            ex.printStackTrace();
            System.exit(1);
        }
        try {
            // read the name list from the file
            FirstNameListMale = randomnames.loadNames(fileNames[2], gender.MALE);
            // generate a Java class
        } catch (Exception ex) {
            System.err.println("Caught exception while reading names from file \"" + fileNames[2] + "\":");
            System.err.println("  " + ex.getMessage());
            ex.printStackTrace();
            System.exit(1);
        }

        final int nLastNames = LastNameList.size();
        final int nFirstNamesFemale = FirstNameListFemale.size();
        final int nFirstNamesMale = FirstNameListMale.size();

        int nNames = 10;
        String outputFileName = "randomnames10.sql";
        if (args.length > 0) {
            nNames = Integer.parseInt(args[0]);
            if (nNames > nLastNames) {
                System.out.println("WARNING: requested list size is larger than data pool's size");
            }
        }
        if (args.length > 1) {
            outputFileName = args[1];
        }
        // db, table, and column names
        String _dbName = "test";
        String _tableName = "table1";
        String _lastName = "last_name";
        String _firstName = "first_name";
        String _middleNames = "middle_names";
        String _gender = "gender";
        String _birthDate = "date_of_birth";

        StringBuilder sb = new StringBuilder();

        sb.append("-- list of " + nNames + " random names generated from the\n");
        sb.append("-- US Census 1990 – Names Files\n");
        sb.append(
                "-- https://www.census.gov/topics/population/genealogy/data/1990_census/1990_census_namefiles.html\n\n");
        sb.append("USE " + _dbName + ";\n");
        sb.append("-- define db, table, and column names:\n");
        sb.append("SET @dbName=\"" + _dbName + "\";\n");
        sb.append("SET @tableName=\"" + _tableName + "\";\n");
        sb.append("SET @lastName=\"" + _lastName + "\";\n");
        sb.append("SET @firstName=\"" + _firstName + "\";\n");
        sb.append("SET @middleNames=\"" + _middleNames + "\";\n");
        sb.append("SET @birthDate=\"" + _birthDate + "\";\n");
        sb.append("SET @gender=\"" + _gender + "\";\n");
        sb.append("SET @randomSeed=\"" + constantSeed + "\";\n");

        for (int i = 0; i < nNames; i++) {
            String firstName = "";
            String middleNames = "";

            int igender = random.nextInt(101);
            char cgender = 'D';
            if (igender == 1) {
                if (i % 2 == 1) {
                    firstName = FirstNameListFemale.get(random.nextInt(nFirstNamesFemale)).getName();
                    middleNames = FirstNameListMale.get(random.nextInt(nFirstNamesMale)).getName();
                } else {
                    firstName = FirstNameListMale.get(random.nextInt(nFirstNamesMale)).getName();
                    middleNames = FirstNameListFemale.get(random.nextInt(nFirstNamesFemale)).getName();
                }
            } else if (igender > 48) {
                firstName = FirstNameListFemale.get(random.nextInt(nFirstNamesFemale)).getName();
                cgender = 'F';
                if (i % 3 == 1) {
                    middleNames = FirstNameListFemale.get(random.nextInt(nFirstNamesFemale)).getName();
                }
                if (i % 5 == 2) {
                    middleNames = FirstNameListFemale.get(random.nextInt(nFirstNamesFemale)).getName() + " "
                            + FirstNameListFemale.get(random.nextInt(nFirstNamesFemale)).getName();
                }
            } else {
                firstName = FirstNameListMale.get(random.nextInt(nFirstNamesMale)).getName();
                cgender = 'M';
                if (i % 3 == 2) {
                    middleNames = FirstNameListMale.get(random.nextInt(nFirstNamesMale)).getName();
                }
                if (i % 5 == 3) {
                    middleNames = FirstNameListMale.get(random.nextInt(nFirstNamesMale)).getName() + " "
                            + FirstNameListFemale.get(random.nextInt(nFirstNamesFemale)).getName();
                }
            }
            String lastName = LastNameList.get(random.nextInt(nLastNames)).getName();
            sb.append("INSERT INTO " + _tableName + "( " + _lastName + ", " + _firstName + ", " + _middleNames + ", "
                    + _birthDate + ", " + _gender + ") \n");
            sb.append("   VALUES(\"" + lastName + "\", \"" + firstName + "\", \"" + middleNames + "\", '"
                    + randomnames.randomDate(1950, 2001) + "', '" + cgender + "');\n");
        } // for (int i = 0; i < nNames; i++)
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));
            writer.write(sb.toString());
            writer.close();
            System.out.println("Saved data in file \"" + outputFileName + "\"");
        } catch (Exception ex) {
            System.out.println("Caught exception while generating file \"" + outputFileName + "\"");
            ex.printStackTrace();
        }
    } // static public void main(String[] args)

    public static String randomDate(int minYear, int maxYear) {

        GregorianCalendar gc = new GregorianCalendar();
        int rndyear = randBetween(minYear, maxYear);
        gc.set(GregorianCalendar.YEAR, rndyear);
        int dayOfYear = randBetween(1, gc.getActualMaximum(GregorianCalendar.DAY_OF_YEAR));
        gc.set(GregorianCalendar.DAY_OF_YEAR, dayOfYear);

        int Year = gc.get(GregorianCalendar.YEAR);
        int Month = (gc.get(GregorianCalendar.MONTH) + 1);
        int Day = gc.get(GregorianCalendar.DAY_OF_MONTH);
        String sMonth = "" + Month;
        if (Month < 10) {
            sMonth = "0" + Month;
        }
        String sDay = "" + Day;
        if (Day < 10) {
            sDay = "0" + Day;
        }
        return "" + Year + "-" + sMonth + "-" + sDay;

    }

    public static int randBetween(int start, int end) {
        // return start + (int) Math.round(Math.random() * (end - start));
        // use the global random instead of Math.random()
        return random.nextInt(end - start + 1) + start;

    }

    /**
     * Loads all of the names in the given file into a TreeMap keyed by the name's
     * cumulative frequency.
     *
     * @param file path to the text file containing the names to load
     * @return a ArrayList of all names in the file
     */
    static public ArrayList<name> loadNames(final String file, final gender Gender) throws IOException {

        ArrayList<name> names = new ArrayList<name>();
        FileInputStream is = new FileInputStream(new File(file));

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line = reader.readLine();
            while (line != null) {
                String[] fields = line.split("\\s+");
                name newName = new name(fields[0], fields[1], fields[2], fields[3], Gender.toString());
                names.add(newName);
                line = reader.readLine();
            }
        }
        return names;
    }

    /**
     * re-initialize the randomizer
     * 
     * We need this for the tests.
     * 
     * Without re-initializing the randomizer, consecutive calls to main() and other
     * methods from the tests will use the same randomizer and thus generate
     * different data than multiple calls of main() form the shell.
     */
    static public void resetRandomizer() {
        random = new Random();
        if (constantSeed > 0) {
            random = new Random(constantSeed);
        }
    }

} // public class randomnames
