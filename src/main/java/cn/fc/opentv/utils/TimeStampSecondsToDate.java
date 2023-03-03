package cn.fc.opentv.utils;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;

import cn.hutool.core.date.DateUtil;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * @author fucheng
 * @date 2023/2/10
 */
public class TimeStampSecondsToDate extends JsonDeserializer<Date> {
    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        Instant instant = Instant.ofEpochSecond(p.getIntValue());
        return DateUtil.date(instant);
    }
}
