package io.jafka.jeos.exception;

import java.util.Arrays;

public class EosError {

    private Integer code;

    private String name;

    private String what;

    private EosErrorDetails[] details;

    private EosError(){

    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWhat() {
        return what;
    }

    public void setWhat(String what) {
        this.what = what;
    }

    public EosErrorDetails[] getDetails() {
        return details;
    }

    public void setDetails(EosErrorDetails[] details) {
        this.details = details;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("EosError [code=");
        builder.append(code);
        builder.append(", name=");
        builder.append(name);
        builder.append(", what=");
        builder.append(what);
        builder.append(", details=");
        builder.append(Arrays.toString(details));
        builder.append("]");
        return builder.toString();
    }
    
}
