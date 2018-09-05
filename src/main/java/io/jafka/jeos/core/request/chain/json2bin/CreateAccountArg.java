package io.jafka.jeos.core.request.chain.json2bin;

import java.util.Map;

import io.jafka.jeos.core.response.chain.account.RequiredAuth;
import lombok.Data;

/**
 * 
 * @author adyliu (imxylz@gmail.com)
 * @since 2018年9月5日
 */
@Data
public class CreateAccountArg{

    private String creator;
    private String name;
    private RequiredAuth owner;
    private RequiredAuth active;

}
