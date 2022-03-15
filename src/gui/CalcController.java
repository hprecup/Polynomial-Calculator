package gui;

import models.Polynomial;
import static operations.ConvertPolynom.*;
import static operations.Operation.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;


public class CalcController {
    private CalcView c_view;

    public CalcController(CalcView view){ // in the constructor we added ActionListeners to all of our buttons
        c_view = view;
        view.addResetListener(new ResetListener());
        view.addAdditionListener(new AdditionListener());
        view.addSubstractionActionListener(new SubstractionListener());
        view.addMultiplicationListener(new MultiplicationListener());
        view.addDerivationListener(new DerivationListener());
        view.addIntegrationListener(new IntegrationListener());
        view.addDivisionListener(new DivisionListener());
    }

    class ResetListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            c_view.reset();
        }
    }

    class AdditionListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            Polynomial polynomial1 = returnPolynom(c_view.read1());
            Polynomial polynomial2 = returnPolynom(c_view.read2());
            Polynomial result = addPolynomials(polynomial1, polynomial2);
            result.sortPolynomial();
            c_view.writePolynom(getResultText(result));
        }
    }

    class SubstractionListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            Polynomial polynomial1 = returnPolynom(c_view.read1());
            Polynomial polynomial2 = returnPolynom(c_view.read2());
            Polynomial result = substractPolynomials(polynomial1, polynomial2);
            result.sortPolynomial();
            c_view.writePolynom(getResultText(result));
        }
    }

    class MultiplicationListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            Polynomial polynomial1 = returnPolynom(c_view.read1());
            Polynomial polynomial2 = returnPolynom(c_view.read2());
            Polynomial result = multiplyPolynomials(polynomial1, polynomial2);
            result.sortPolynomial();
            c_view.writePolynom(getResultText(result));
        }
    }

    class DivisionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String res = "";
            Polynomial polynomial1 = returnPolynom(c_view.read1());
            Polynomial polynomial2 = returnPolynom(c_view.read2());
            LinkedList<Polynomial> result = dividePolynomials(polynomial1, polynomial2);
            if (result.size() == 2) { // we have the result and the rest
                result.getFirst().sortPolynomial();
                result.getLast().sortPolynomial();
                res = getResultText(result.getFirst());
                res += " , rest: ";
                res += getResultText(result.getLast());
            } else
                if(result.size()==1){ // case when grade(polynom1)<grade(polynom2) -> result =0, rest=polynom1
                    res = "0 , rest: ";
                    res += getResultText(result.getFirst());
                }
                else
                    res = "Error, division by 0!";
            c_view.writePolynom(res);
        }
    }

    class DerivationListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            Polynomial polynomial1 = returnPolynom(c_view.read1());
            Polynomial result = derivatePolynomial(polynomial1);
            result.sortPolynomial();
            c_view.writePolynom(getResultText(result));
        }
    }

    class IntegrationListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            Polynomial polynomial1 = returnPolynom(c_view.read1());
            Polynomial result = integratePolynomial(polynomial1);
            result.sortPolynomial();
            c_view.writePolynom(getResultText(result));
        }
    }

}
