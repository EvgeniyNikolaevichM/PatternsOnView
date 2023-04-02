package lab_rab.lab2;

import lab_rab.lab2.interfaces.Visitor;

public class PrintVisitor implements Visitor {
    @Override
    public void visit(Car car) {
        for (int i = 0; i < car.getModelsSize(); i++) {
            System.out.print(car.getModel(i) + " ");
        }
    }

    @Override
    public void visit(Motorcycle motorcycle) {
        for (int i=0; i < motorcycle.getModelsSize(); i++) {
            System.out.println(motorcycle.getModel(i) + " ");
        }
    }
}
    