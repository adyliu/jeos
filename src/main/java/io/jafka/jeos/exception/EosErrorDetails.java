package io.jafka.jeos.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EosErrorDetails {

    private String message;

    private String file;

    private Integer lineNumber;

    private String method;

    private EosErrorDetails() {

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    @JsonProperty("line_number")
    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("EosErrorDetails [message=");
        builder.append(message);
        builder.append(", file=");
        builder.append(file);
        builder.append(", lineNumber=");
        builder.append(lineNumber);
        builder.append(", method=");
        builder.append(method);
        builder.append("]");
        return builder.toString();
    }
    
}
