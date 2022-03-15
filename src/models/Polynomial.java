package models;

import java.util.*;

public class Polynomial {

    private LinkedList<Monom> list;
    public Polynomial() {
        list = new LinkedList<Monom>();
    }

    public void addMonom(Monom m){
        list.add(m);
    }

    public LinkedList<Monom> getList(){
        return list;
    }

    public void sortPolynomial(){
        Collections.sort(list);
    }

    public void modifyPower(int power, Number coef){ // we search for x^power, add the coefficients and update the list based on the result
        boolean isPower = false;
        for(Monom current : list)
            if(current.getPower()==power){
                int c = current.getCoefficient().intValue() + coef.intValue();
                if(c!=0)
                    current.setCoefficient(c);
                else
                    list.remove(current); // if the sum of both coefficients is zero, then we remove x^power from our polynom
                isPower = true;
                 break;
            }
        if(!isPower)
            list.add(new Monom(coef, power)); // if we don't find x^power, we add it into the list
    }

    @Override
    public boolean equals(Object o){
        if(o==this) return true;
        if(!(o instanceof Polynomial))
            return false;
        Polynomial p = (Polynomial) o;
        LinkedList<Monom> list2 = p.getList();
        if(list.size()!=list2.size())
            return false;
        Monom p1,p2;
        Iterator<Monom> i = list.iterator();
        Iterator<Monom> i2 = list2.iterator();
        while(i.hasNext()){
            p1 = i.next();
            p2 = i2.next();
            if(!p1.equals(p2))
                return false;
        }
        return true;
    }
}
