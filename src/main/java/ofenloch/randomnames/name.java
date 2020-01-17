package ofenloch.randomnames;
/**
 * small helper class to handle the raw name data
 */
class name {
    /**
     * the actual name
     */
    public String theName = "";
    /**
     * this name's frequency in percent
     */
    public float theFrequency = 0;
    /**
     * this name'S cumulative frequency in percent
     */
    public float theCumulativeFrequency = 0;
    /**
     * this name's rank
     */
    public int theRank = 0;
    /**
     * gender
     */
    public gender theGender = gender.DIVERSE;

    public name(String Name, float Freq, float CumulFreq, int Rank, char Gender) {
        this.theName = Name;
        this.format();
        this.theFrequency = Freq;
        this.theCumulativeFrequency = CumulFreq;
        this.theRank = Rank;
        if (Gender == 'F') {
            this.theGender = gender.FEMALE;
        } else if (Gender == 'M') {
            this.theGender = gender.MALE;
        } else {
            this.theGender = gender.DIVERSE;
        }

    }

    public name(String Name, String Freq, String CumulFreq, String Rank, String Gender) {
        this.theName = Name;
        this.format();
        this.theFrequency = Float.parseFloat(Freq);
        this.theCumulativeFrequency = Float.parseFloat(CumulFreq);
        this.theRank = Integer.parseInt(Rank);
        if (Gender.compareTo("F") == 0) {
            this.theGender = gender.FEMALE;
        } else if (Gender.compareTo("M") == 0) {
            this.theGender = gender.MALE;
        } else {
            this.theGender = gender.DIVERSE;
        }
    }

    @Override
    public String toString() {
        return theName;
    }
    
    public String getName() {
        return theName;
    }
    private void format() {
        int len = theName.length();
        theName = theName.substring(0, 1).toUpperCase() + theName.substring(1, len).toLowerCase();

    }
} // class name
