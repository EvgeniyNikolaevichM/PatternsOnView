package lab_rab.lab2.exceptions;

public class DuplicateModelNameException extends Exception {
    public DuplicateModelNameException(String message){
        super(String.format("Model with name %s already exists!", message));
    }
}
