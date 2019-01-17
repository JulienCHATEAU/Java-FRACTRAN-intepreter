package fractran;

import util.GestionFichiers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Fractran {

    public List<FractranFraction> code;

    public Fractran() {
        this.code = new ArrayList<>();
    }

    public Fractran(List<FractranFraction> code) {
        this.code = code;
    }

    public void addCodeInstruction(FractranFraction instruction) {
        this.code.add(instruction);
    }

    public FractranNumber execute(FractranNumber in) {
        FractranNumber number = new FractranNumber(in.integerValue());
        boolean multiplyFound = true;
        int currentFractionIndex = 0;
        while (multiplyFound) {
            multiplyFound = false;
            currentFractionIndex = 0;
            while (!multiplyFound && currentFractionIndex < this.code.size()) {
                FractranNumber multiply = this.code.get(currentFractionIndex).multiply(number);
                if (multiply.isInteger()) {
                    number = multiply;
                    multiplyFound = true;
                } else {
                    currentFractionIndex++;
                }
            }
            if (number.isPowerOfTwo()) {
                System.out.println("exec : "+number.getExponent(2));
//                System.out.println("exec : "+number.toString());
            }
        }
        return number;
    }

    public void readCodeFromFile(String filePath) throws IOException {
        GestionFichiers gf = new GestionFichiers(new File(filePath));
        gf.ouvrir();
        String line = gf.lire();
        while (line != null) {
            String[] splitedLine = line.split("/");
            this.code.add(new FractranFraction(Integer.valueOf(splitedLine[0]), Integer.valueOf(splitedLine[1])));
            line = gf.lire();
        }
        gf.fermer();
    }


    public String toString() {
        StringBuffer str = new StringBuffer("{ ");
        for (FractranFraction fraction : this.code) {
            str.append(fraction.toString()).append(";\n");
        }
        str.delete(str.length()-2, str.length()).append(" }");
        return str.toString();
    }


    //STATIC


    public static List<Integer> primeNumbers = new ArrayList<>();

    public static void fillPrimeNumbers(int primeNumberCount) {
        primeNumbers.clear();
        int i = 0;
        int number = 2;
        while (i < primeNumberCount) {
            if (isPrimeNumber(number)) {
                primeNumbers.add(number);
                i++;
            }
            number++;
        }
    }

    public static int getPrimeNumber(int index) {
        return Fractran.primeNumbers.get(index);
    }

    public static boolean isPrimeNumber(int number) {
        boolean isPrime = true;
        for (int j = 2; j <= number-1; j++) {
            if (number % j == 0) {
                isPrime = false;
            }
        }
        return isPrime;
    }

    static {
        Fractran.fillPrimeNumbers(100);
    }

    public static void main(String[] args) {
//        FractranNumber number = new FractranNumber(15);
//        System.out.println(number.toString());
//        FractranFraction fraction = new FractranFraction(4, 3);
//        FractranNumber res = fraction.multiply(number);
//        System.out.println(res.toString());
//        System.out.println(res.isInteger());

//        FractranFraction fraction = new FractranFraction(3, 2);
//        FractranFraction fraction2 = new FractranFraction(4, 3);
        Fractran fractran = new Fractran();
        try {
//            fractran.readCodeFromFile("/home-reseau/jchateau/IdeaProjects/Fractran/src/code/pigame");
            fractran.readCodeFromFile("/home-reseau/jchateau/IdeaProjects/Fractran/src/code/prime_numbers");
//            fractran.readCodeFromFile("/home-reseau/jchateau/IdeaProjects/Fractran/src/code/multiplication");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(fractran.toString() + "\n");
//        fractran.addCodeInstruction(fraction);
//        fractran.addCodeInstruction(fraction2);
        FractranNumber res = fractran.execute(new FractranNumber(2));
        System.out.println(res.toString());
        System.out.println(res.integerValue());
    }

}
