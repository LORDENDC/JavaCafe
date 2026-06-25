package exception;

// Exceção lançada quando não há estoque suficiente
public class OutOfStockException extends Exception {

    // construtor
    public OutOfStockException(String message) {
        super(message);
    }
}
