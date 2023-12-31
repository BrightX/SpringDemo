package com.bright.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author Bright Xu
 * @since 2023/12/31
 */
@Configuration
public class WebMvcDateConfig implements WebMvcConfigurer {
    /**
     * JavaScript 中最大的安全整数 <code>2<sup>53</sup> - 1</code>
     *
     * @see <a href="https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Number/MAX_SAFE_INTEGER">MDN - MAX_SAFE_INTEGER</a>
     */
    public static final long MAX_SAFE_INTEGER = 0x1FFFFFFFFFFFFFL;
    /**
     * JavaScript 中最小的安全整数 <code>-(2<sup>53</sup> - 1)</code>
     *
     * @see <a href="https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Number/MIN_SAFE_INTEGER">MDN - MIN_SAFE_INTEGER</a>
     */
    public static final long MIN_SAFE_INTEGER = -0x1FFFFFFFFFFFFFL;
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("H[:m[:s[.SSS]]]");
    private static final String[] DATE_PARSE_PATTERNS = new String[]{
        "yyyy-MM-dd HH:mm:ss",
        "yyyy/MM/dd HH:mm:ss",
        "yyyy年MM月dd日 HH时mm分ss秒",
        "yyyy-MM-dd",
        "yyyy/MM/dd",
        "yyyy.MM.dd",
        "HH:mm:ss",
        "HH时mm分ss秒",
        "yyyy-MM-dd HH:mm",
        "yyyy/MM/dd HH:mm",
        "yyyy.MM.dd HH:mm",
        "yyyyMMddHHmmss",
        "yyyyMMddHHmmssSSS",
        "yyyyMMdd",
        "EEE, dd MMM yyyy HH:mm:ss z",
        "EEE MMM dd HH:mm:ss zzz yyyy",
        "yyyy-MM-dd'T'HH:mm:ss'Z'",
        "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
        "yyyy-MM-dd'T'HH:mm:ssZ",
        "yyyy-MM-dd'T'HH:mm:ss.SSSZ",
    };

    private static Date parseDate(String str) {
        try {
            return DateUtils.parseDate(str, null, DATE_PARSE_PATTERNS);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static final JsonSerializer<Long> SERIALIZER = new JsonSerializer<Long>() {
        @Override
        public void serialize(Long value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            if (value == null) {
                gen.writeNull();
                return;
            }
            // 是否是 JavaScript 安全整数
            boolean isSafeInteger = MIN_SAFE_INTEGER <= value && value <= MAX_SAFE_INTEGER;
            if (isSafeInteger) {
                gen.writeNumber(value);
                return;
            }
            gen.writeString(value.toString());
        }
    };

    private static final JsonDeserializer<Date> DATE_DESERIALIZER = new JsonDeserializer<Date>() {
        @Override
        public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            return parseDate(p.getText());
        }
    };

    /**
     * 解决JavaScript在 json 反序列化时 long 类型缺失精度问题
     *
     * @return Jackson2ObjectMapperBuilderCustomizer
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return jacksonObjectMapperBuilder -> {
            jacksonObjectMapperBuilder.serializerByType(Long.TYPE, SERIALIZER);
            jacksonObjectMapperBuilder.serializerByType(Long.class, SERIALIZER);
            jacksonObjectMapperBuilder.deserializerByType(Date.class, DATE_DESERIALIZER);
        };
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(String.class, Date.class, WebMvcDateConfig::parseDate);
        registry.addConverter(String.class, LocalTime.class, source -> {
            if (source.isEmpty()) {
                return null;
            }
            return LocalTime.parse(source, DTF);
        });
        registry.addConverter(String.class, LocalDate.class, source -> {
            if (source.isEmpty()) {
                return null;
            }
            return LocalDate.parse(source);
        });
        registry.addConverter(String.class, LocalDateTime.class, source -> {
            if (source.isEmpty()) {
                return null;
            }
            return parseDate(source).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        });
    }
}
