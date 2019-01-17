package fractran;

public class FractranFraction {

    private FractranNumber numerator;
    private FractranNumber denominator;

    public FractranFraction(int numerator, int denominator) {
        this.numerator = new FractranNumber(numerator);
        this.denominator = new FractranNumber(denominator);
    }

    public FractranNumber multiply(FractranNumber number) {
        return number.multiply(this.numerator).divide(this.denominator);
    }

    public FractranNumber getNumerator() {
        return numerator;
    }

    public void setNumerator(FractranNumber numerator) {
        this.numerator = numerator;
    }

    public FractranNumber getDenominator() {
        return denominator;
    }

    public void setDenominator(FractranNumber denominator) {
        this.denominator = denominator;
    }

    public String toString() {
        return this.numerator.toString() + " / " + this.denominator.toString();
    }
}
