package operations;

import models.Monom;
import models.Polynomial;
import java.util.*;

public class Operation { // in this class we have all the algorithms for the operations between our polynomials

    public static Polynomial addPolynomials(Polynomial polynomial1, Polynomial polynomial2){
        return returnOperation(polynomial1, polynomial2, 1);
    }

    public static Polynomial substractPolynomials(Polynomial polynomial1, Polynomial polynomial2){
        return returnOperation(polynomial1, polynomial2, 2);
    }

    // this method is made to not write almost the same code again for substraction and addition
    private static Polynomial returnOperation(Polynomial polynomial1, Polynomial polynomial2, int operation){
        int coef;
        Polynomial result = new Polynomial();
        LinkedList<Monom> list1 = polynomial1.getList();
        LinkedList<Monom> list2 = polynomial2.getList();
        for(Monom elem1 : list1){
            boolean common = false;
            for(Monom elem2 : list2)
                if(elem1.getPower()==elem2.getPower()){ // find the monoms with the same power
                    common = true;
                    if(operation==1) // we add or substract the polynomials based of the specified operation
                        coef = elem1.getCoefficient().intValue() +elem2.getCoefficient().intValue();
                    else
                        coef = elem1.getCoefficient().intValue() -elem2.getCoefficient().intValue();
                    if(coef!=0)
                        result.addMonom(new Monom(coef, elem1.getPower()));
                    list2.remove(elem2);
                    break;
                }
            if(!common && elem1.getCoefficient().intValue()!=0) // add the monom from first polynom that has no common power in the second polynom
                result.addMonom(new Monom(elem1.getCoefficient(), elem1.getPower()));
        }
        if(operation==1) { // we added the rest of the polynom2 elements that had no common x powers with the polynom1
            for (Monom elem2 : list2)
                if (elem2.getCoefficient().intValue() != 0)
                    result.addMonom(new Monom(elem2.getCoefficient(), elem2.getPower()));
        } else
            for(Monom elem2 : list2)
                if(elem2.getCoefficient().intValue()!=0)
                    result.addMonom(new Monom( (-1*elem2.getCoefficient().intValue()), elem2.getPower()));
        return result;
    }

    public static Polynomial multiplyPolynomials(Polynomial polynomial1, Polynomial polynomial2){
        int power;
        Number coef;
        Polynomial result = new Polynomial();
        LinkedList<Monom> list1 =  polynomial1.getList();
        LinkedList<Monom> list2 =  polynomial2.getList();
        for(Monom elem1 : list1){ // multiply every monom from polynom1 with every monom from polynom2
            for(Monom elem2 : list2){
                coef = elem1.getCoefficient().intValue() * elem2.getCoefficient().intValue();
                power = elem1.getPower()+elem2.getPower();
                if(coef.intValue()!=0)
                    result.modifyPower(power, coef); // update the result list
            }
        }
        return result;
    }

    public static LinkedList<Polynomial> dividePolynomials(Polynomial polynomial1, Polynomial polynomial2){
        LinkedList<Polynomial> resultList = new LinkedList<Polynomial>();
        Polynomial result = new Polynomial();
        Polynomial rest = new Polynomial();
        polynomial1.sortPolynomial(); polynomial2.sortPolynomial();
        LinkedList<Monom> list1 = polynomial1.getList();
        LinkedList<Monom> list2 = polynomial2.getList();
        if(list2.getFirst().getPower()==0 && list2.getFirst().getCoefficient().intValue()==0)
            return resultList; // we make sure that the polynom2 is not 0
        if(list1.getFirst().getPower()==0 && list1.getFirst().getCoefficient().intValue()==0){
            resultList.add(polynomial1); // if polynom1 is 0, then the result is 0
            return resultList;
        }
        Monom monom2 = list2.getFirst();
        if(list1.getFirst().getPower()<monom2.getPower()){
            resultList.add(polynomial1); // if grade(polynom1)<grade(polynom2), then result is 0 and the rest is polynom1
            return resultList;
        }
        while(!list1.isEmpty() && list1.getFirst().getPower()>=list2.getFirst().getPower()) { // divide the polynoms until
            Monom monom1 = list1.getFirst();                                            // grade(polynom1)<grade(polynom2)
            Number coef = monom1.getCoefficient().intValue() / monom2.getCoefficient().intValue();
            if(coef.intValue()==0){ // when polynom1=int and polynom2=int and polynom1<polynom2
                result.addMonom(new Monom(0,0)); polynomial1 = polynomial2; break;
            }
            int power = monom1.getPower() - monom2.getPower();
            result.addMonom(new Monom(coef, power));
            Polynomial multipliedPol2 = new Polynomial();
            multipliedPol2.addMonom(result.getList().getLast());
            Polynomial dispenser = multiplyPolynomials(polynomial2, multipliedPol2); // dispenser=polynom2 * result of the division at this step
            polynomial1 = substractPolynomials(polynomial1, dispenser); // update the polynom1 with the remaining after every division
            list1 = polynomial1.getList();
            polynomial1.sortPolynomial();
        }
        resultList.add(result);
        resultList.add(polynomial1);
        return resultList;
    }

    public static Polynomial derivatePolynomial(Polynomial polynomial){
        int power, coef;
        Polynomial result = new Polynomial();
        LinkedList<Monom> list = polynomial.getList();
        for(Monom current : list){
            power = current.getPower();
            if(power>0){
                coef = current.getCoefficient().intValue();
                if(coef!=0)
                    result.addMonom(new Monom(coef*power, power-1));
            }
        }
        return result;
    }

    public static Polynomial integratePolynomial(Polynomial polynomial){
        int power;
        Number coef;
        Polynomial result = new Polynomial();
        LinkedList<Monom> list = polynomial.getList();
        for(Monom current : list){
            power = current.getPower();
            coef = current.getCoefficient();
            if(coef.floatValue()!=0)
                result.addMonom(new Monom(coef.floatValue()/(power+1), power+1));
        }
        return result;
    }

}
