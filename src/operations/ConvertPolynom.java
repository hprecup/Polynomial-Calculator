package operations;

import models.Monom;
import models.Polynomial;
import java.util.LinkedList;
import static java.lang.Math.abs;

public class ConvertPolynom {
    // this method transforms input String into Polynom type object
    public static Polynomial returnPolynom(String stringPolynom) {
        Polynomial polynomial = new Polynomial();
        String aux = stringPolynom.replace(" ", ""); // eliminating all the spaces
        String[] terms = aux.replace("*", "").split("(?=\\+|\\-)"); //splits our string into monoms
        int i, power = 1;
        Number coef=1;
        for (String term : terms) {
            i = 0;
            coef = 1; power = 1;
            boolean isX = false; // to verify if we have a power of "x" in our monom
            while ( i < term.length() ) {
                if (term.charAt(i) == 'x') {
                    if (i > 1 || (i == 1 && Character.isDigit(term.charAt(0)))) // we verify if x has a coefficient
                        coef = Float.parseFloat(term.substring(0, i));
                    else if (i == 1 && term.charAt(0) == '-')
                        coef = -1;
                    if (i != (term.length() - 1))
                        power = Integer.parseInt(term.substring(i+2));
                    isX = true;
                }
                i++;
            }
            if(!isX){ // if we don't find "x" in our monom, we parse it to float
                coef = Float.parseFloat(term);
                power = 0;
            }
            polynomial.addMonom(new Monom(coef, power));
        }
        return polynomial;
    }

    // this method transforms the result polynom into the result String
    public static String getResultText(Polynomial res){
        LinkedList<Monom> list = res.getList();
        String result="", coef="";
        if(list.isEmpty())
            result="0";
        else{
            boolean notFirst=false; // this is for checking if we're at the first monom, for not writing an additional "+" sign in case it is positive
            for(Monom current : list){
                int power = current.getPower(), intCoef = current.getCoefficient().intValue();
                Number c=current.getCoefficient();
                float floatCoef = current.getCoefficient().floatValue();
                coef = intCoef==floatCoef ? String.valueOf(intCoef) : String.format("%.2f", floatCoef); // transform the coefficient into
                if(notFirst){                                                                           // a String, based on its type
                    result+=" ";
                    if(floatCoef>0)
                        result+="+"; // we put "+" between the positive monoms inside the polynom
                    if(floatCoef==-1)
                        result+="-"; // in this case we need to put "-" because we don't consider coefficients that have absolute value of 1
                    result += power==0 ? (abs(floatCoef)!=1 ? coef:1) : (power==1 ? (abs(floatCoef)==1 ? "x" : coef+"x") : (abs(floatCoef)==1 ? "x^"+power : coef+"x^"+power) );
                    // if the power is 0, we show only the coefficient, else we verify the cases when the power is above 0 and the values of the coefficient
                    // power=0 -> "coef"; power=1,abs(coef)=1 -> "x" ; power=1,abs(coef)!=1 -> "coef*x" ; power>1,abs(coef)=1 -> "x^power" ; power>1,coef!=1 -> "coef*x^power"
                }else{
                    if(coef.equals("-1"))
                        result += "-";
                    result += power==0 ? (abs(floatCoef)!=1 ? coef:1) : (power==1 ? (abs(floatCoef)==1 ? "x" : coef+"x") : (abs(floatCoef)==1 ? "x^"+power : coef+"x^"+power) );
                    notFirst=true;
                }
            }
        }
        return result;
    }

}
