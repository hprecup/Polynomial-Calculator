package models;

public class Monom implements Comparable<Monom>{
    private Number coefficient;
    private int power;

    public Monom(Number coefficient, int power){
        this.coefficient = coefficient;
        this.power = power;
    }

    public Number getCoefficient(){
        return coefficient;
    }

    public int getPower(){
        return power;
    }

    public void setCoefficient(Number coefficient){
        this.coefficient = coefficient;
    }

    @Override
    public int compareTo(Monom m){
        return m.power-this.power;
    } // we override this function to sort polynomials decreasing by monoms power

    @Override
    public boolean equals(Object o){
        if(o==this)
            return true;
        if(!(o instanceof Monom))
            return false;
        if(power!=((Monom) o).getPower() || coefficient.floatValue()!=((Monom) o).getCoefficient().floatValue())
            return false;
        return true;
    }
}
