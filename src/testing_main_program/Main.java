package testing_main_program;

import gui.CalcController;
import gui.CalcView;

public class Main {

    public static void main(String[] args) {
        CalcView a = new CalcView();
        a.setVisible(true);
        CalcController c = new CalcController(a);
        OperationTest test = new OperationTest();
    }
}
