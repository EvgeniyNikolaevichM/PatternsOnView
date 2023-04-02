package lab_rab.lab2.exceptions;

public class NoSuchModelNameException extends Exception {
    public NoSuchModelNameException(String message){
        super("No such model name: " + message);
    }
}
