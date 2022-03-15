package testing_main_program;

import models.Monom;
import models.Polynomial;
import static operations.Operation.*;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class OperationTest {
    Polynomial polynomial1 = new Polynomial();
    Polynomial polynomial2 = new Polynomial();

    public OperationTest(){
        polynomial1.addMonom(new Monom(1,3));
        polynomial1.addMonom(new Monom(-12,2));
        polynomial1.addMonom(new Monom(38,1));
        polynomial1.addMonom(new Monom(-17,0));
        // x^3-12x^2+38x-17
        polynomial2.addMonom(new Monom(1,1));
        polynomial2.addMonom(new Monom(-7,0));
        // x-7
    }

    @org.junit.jupiter.api.Test
    void addPolynomsTest() {
        Polynomial result = new Polynomial();
        result.addMonom(new Monom(1,3));
        result.addMonom(new Monom(-12,2));
        result.addMonom(new Monom(39,1));
        result.addMonom(new Monom(-24,0));
        assertEquals(result, addPolynomials(this.polynomial1, this.polynomial2));
    }

    @org.junit.jupiter.api.Test
    void substractPolynomsTest() {
        Polynomial result = new Polynomial();
        result.addMonom(new Monom(1,3));
        result.addMonom(new Monom(-12,2));
        result.addMonom(new Monom(37,1));
        result.addMonom(new Monom(-10,0));
        assertEquals(result, substractPolynomials(polynomial1, polynomial2));
    }

    @org.junit.jupiter.api.Test
    void multiplyPolynomsTest() {
        Polynomial result = new Polynomial();
        result.addMonom(new Monom(1,4));
        result.addMonom(new Monom(-19,3));
        result.addMonom(new Monom(122,2));
        result.addMonom(new Monom(-283,1));
        result.addMonom(new Monom(119,0));
        assertEquals(result, multiplyPolynomials(polynomial1, polynomial2));
    }

    @org.junit.jupiter.api.Test
    void dividePolynomsTest() {
        Polynomial result = new Polynomial();
        result.addMonom(new Monom(1,2));
        result.addMonom(new Monom(-5,1));
        result.addMonom(new Monom(3,0));
        Polynomial rest = new Polynomial();
        rest.addMonom(new Monom(4,0));
        LinkedList<Polynomial> list = dividePolynomials(polynomial1, polynomial2);
        assertAll(
                ()-> assertEquals(result, list.getFirst()),
                ()-> assertEquals(rest, list.getLast())
        );
    }

    @org.junit.jupiter.api.Test
    void derivatePolynomTest() {
        Polynomial result = new Polynomial();
        result.addMonom(new Monom(3,2));
        result.addMonom(new Monom(-24,1));
        result.addMonom(new Monom(38,0));
        assertEquals(result, derivatePolynomial(polynomial1));
    }

    @org.junit.jupiter.api.Test
    void integratePolynomTest() {
        Polynomial result = new Polynomial();
        result.addMonom(new Monom(0.25,4));
        result.addMonom(new Monom(-4,3));
        result.addMonom(new Monom(19,2));
        result.addMonom(new Monom(-17,1));
        assertEquals(result, integratePolynomial(polynomial1));
    }
}