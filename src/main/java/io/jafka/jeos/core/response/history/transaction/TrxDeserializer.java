package io.jafka.jeos.core.response.history.transaction;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

/**
 * 
 * @author adyliu (imxylz@gmail.com)
 * @since 2018年9月14日
 */
public class TrxDeserializer extends StdDeserializer<Trx> {

    public TrxDeserializer() {
        super(Trx.class);
    }

    @Override
    public Trx deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return p.isExpectedStartObjectToken() ? p.readValueAs(Trx.class) : null;
    }
}
