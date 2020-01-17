package ofenloch.randomnames;
/**
 * small helper class to handle the gender stuff
 */
class gender {

    private String gStr = "D";
    private char gChar = 'D';
    private int gInt = 0;

    public gender() {
        this.gStr = "D";
        this.gChar = 'D';
        this.gInt = 0;
    }

    public gender(char g) {
        if (g == 'F') {
            gInt = 1;
            gStr = "F";
            gChar = g;
        } else if (g == 'M') {
            gInt = 2;
            gStr = "M";
            gChar = g;
        }
    }

    public gender(int g) {
        if (g == 1) {
            gInt = 1;
            gStr = "F";
            gChar = 'F';
        } else if (g == 2) {
            gInt = 2;
            gStr = "M";
            gChar = 'M';
        }
    }

    @Override
    public String toString() {
        return gStr;
    }

    public char toChar() {
        return gChar;
    }

    public int toInt() {
        return gInt;
    }

    static final public gender DIVERSE = new gender(0);
    static final public gender FEMALE = new gender(1);
    static final public gender MALE = new gender(2);

} // class gender
