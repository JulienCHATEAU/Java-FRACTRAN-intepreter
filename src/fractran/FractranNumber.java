package fractran;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FractranNumber {

    private Map<Integer, Integer> primeFactors;

    public FractranNumber(int number) {
        this.primeFactors = new HashMap<>();
        this.primeFactorization(number);
    }

    public FractranNumber(Map<Integer, Integer> primeFactors) {
        this.primeFactors = new HashMap<>(primeFactors);
    }

    private void primeFactorization(int number) {
        if (Fractran.isPrimeNumber(number)) {
            this.primeFactors.put(number, 1);
        } else {
            int primeNumberIndex = 0;
            int currentPrimeNumber = 0;
            int currentExponent;
            while (number != 1) {
                currentExponent = 0;
                currentPrimeNumber = Fractran.getPrimeNumber(primeNumberIndex);
                while (number % currentPrimeNumber == 0) {
                    number /= currentPrimeNumber;
                    currentExponent++;
                }
                if (currentExponent > 0) {
                    this.primeFactors.put(currentPrimeNumber, currentExponent);
                }
                primeNumberIndex++;
            }
        }
    }

    public String toString() {
        StringBuffer str = new StringBuffer();
        for (int primeFactor : this.primeFactors.keySet()) {
            int exponent = this.primeFactors.get(primeFactor);
            if (primeFactor > 1 || this.primeFactors.size() == 1) {
                str.append(primeFactor + "^" + exponent).append(" * ");
            }
        }
        String res = str.toString();
        return res.substring(0, res.length()-3);
    }

    public int integerValue() {
        int res = 1;
        for (int primeFactor : this.primeFactors.keySet()) {
            int exponent = this.primeFactors.get(primeFactor);
            res *= Math.pow(primeFactor, exponent);
        }
        return res;
    }

    public FractranNumber divide(FractranNumber number) {
        FractranNumber res = new FractranNumber(this.primeFactors);
        Map<Integer, Integer> factors = number.getPrimeFactors();
        for (int primeFactor : factors.keySet()) {
            int exponentToSub = -factors.get(primeFactor);
            res.increaseFactorExponent(primeFactor, exponentToSub);
        }
        return res;
    }

    public FractranNumber multiply(FractranNumber number) {
        FractranNumber res = new FractranNumber(this.primeFactors);
        Map<Integer, Integer> factors = number.getPrimeFactors();
        for (int primeFactor : factors.keySet()) {
            int exponentToAdd = factors.get(primeFactor);
            res.increaseFactorExponent(primeFactor, exponentToAdd);
        }
        return res;
    }

    public void increaseFactorExponent(int factor, int toAdd) {
        if (!this.primeFactors.containsKey(factor)) {
            this.primeFactors.put(factor, toAdd);
        } else {
            int res = this.primeFactors.get(factor) + toAdd;
            if (res == 0) {
                this.primeFactors.remove(factor);
            } else {
                this.primeFactors.put(factor, res);
            }
        }
    }

    public Map<Integer, Integer> getPrimeFactors() {
        return primeFactors;
    }

    public int getExponent(int value) {
        Integer exponent = this.primeFactors.get(value);
        if (exponent == null) {
            exponent = -1;
        }
        return exponent;
    }

    public boolean isInteger() {
        boolean isInteger = true;
        for (int primeFactor : this.primeFactors.keySet()) {
            int exponent = this.primeFactors.get(primeFactor);
            if (exponent < 0 && primeFactor > 1) {
                isInteger = false;
                break;
            }
        }
        return isInteger;
    }

    public boolean isPowerOfTwo() {
        Set<Integer> keys = this.primeFactors.keySet();
        return (keys.size() == 1 && keys.contains(2)) || (keys.size() == 2 && keys.contains(2) && keys.contains(1));
    }
}
