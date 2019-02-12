package io.jafka.jeos.exception;

public class EosApiException extends RuntimeException {

    private ErrorCode eosErrorCode;
    private EosErrorDetails[] details;

    public EosApiException(ErrorCode eosErrorCode) {
        this.eosErrorCode = eosErrorCode;
    }

    public EosApiException(String message, ErrorCode eosErrorCode) {
        super(message);
        this.eosErrorCode = eosErrorCode;
    }

    public EosApiException(Throwable cause, ErrorCode eosErrorCode) {
        super(cause);
        this.eosErrorCode = eosErrorCode;
    }

    public EosApiException(Throwable cause) {
        super(cause);
    }

    public EosApiException(String message, Throwable cause, ErrorCode eosErrorCode) {
        super(message, cause);
        this.eosErrorCode = eosErrorCode;
    }

    public EosApiException(String message, EosApiErrorCode eosErrorCode, EosErrorDetails[] details) {
        super(message);
        this.eosErrorCode = eosErrorCode;
        this.details = details;
    }

    public EosErrorDetails[] getDetails() {
        return details;
    }

    public void setDetails(EosErrorDetails[] details) {
        this.details = details;
    }

    public ErrorCode getEosErrorCode() {
        return eosErrorCode;
    }

    public void setEosErrorCode(ErrorCode eosErrorCode) {
        this.eosErrorCode = eosErrorCode;
    }

}
