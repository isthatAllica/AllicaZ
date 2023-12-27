package edu.whu.framework;

//异常封装
public class BootstrapException extends Exception {

    public enum ErrorType {FILE_NOTFOUND,PROP_READ_ERROR,CLASS_NOTFOUND,INITMETHOD_ERROR,CREATE_OBJECT_ERROR,METHOD_CALL_ERROR }
    private ErrorType errorType;

    public BootstrapException(ErrorType errorType, String message){
        super(message);
        this.errorType =errorType;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

}
