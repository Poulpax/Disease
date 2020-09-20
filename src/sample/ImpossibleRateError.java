package sample;

class ImpossibleRateException extends Exception{
    String message;

    public ImpossibleRateException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
