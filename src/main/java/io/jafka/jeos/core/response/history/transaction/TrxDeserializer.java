package io.jafka.jeos.core.response.history.transaction;

import java.io.IOException;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

/**
 * 
 * @author adyliu (imxylz@gmail.com)
 * @since 2018年9月14日
 */
public class TrxDeserializer extends StdDeserializer<Optional<Trx>> {

    public TrxDeserializer() {
        super(Trx.class);
    }

    @Override
    public Optional<Trx> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return Optional.ofNullable(p.isExpectedStartObjectToken() ? p.readValueAs(Trx.class) : null);
    }
}
