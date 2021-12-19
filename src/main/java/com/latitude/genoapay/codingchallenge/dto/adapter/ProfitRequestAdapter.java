package com.latitude.genoapay.codingchallenge.dto.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.latitude.genoapay.codingchallenge.dto.ProfitRequest;
import com.latitude.genoapay.codingchallenge.dto.validator.ProfitJsonValidator;
import com.latitude.genoapay.codingchallenge.dto.validator.ProfitRequestNotNullValidator;
import com.latitude.genoapay.codingchallenge.dto.validator.ProfitRequestPriceValidator;
import com.latitude.genoapay.codingchallenge.dto.validator.ProfitRequestTimeValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProfitRequestAdapter extends TypeAdapter<ProfitRequest> {

    private static final Logger logger = LoggerFactory.getLogger(ProfitRequestAdapter.class);

    public static final String IDENTIFIER = "identifier";
    public static final String START_TIME = "startTime";
    public static final String END_TIME = "endTime";
    public static final String PRICE_LIST = "priceList";

    private final LocalDateTimeAdapter localDateTimeAdapter = new LocalDateTimeAdapter();
    private final ProfitJsonValidator validator;

    public ProfitRequestAdapter() {
        validator = new ProfitRequestNotNullValidator();
        validator.setValidatorSuccessor(new ProfitRequestTimeValidator())
                .setValidatorSuccessor(new ProfitRequestPriceValidator());
    }

    @Override
    public void write(JsonWriter jsonWriter, ProfitRequest profitRequest) throws IOException {
        if(profitRequest == null) {
            throw new IOException("Null profitRequest object.");
        }
        jsonWriter.beginObject();
        jsonWriter.name(IDENTIFIER).value(profitRequest.getIdentifier());
        jsonWriter.name(START_TIME);
        localDateTimeAdapter.write(jsonWriter, profitRequest.getStartTime());
        jsonWriter.name(END_TIME);
        localDateTimeAdapter.write(jsonWriter, profitRequest.getEndTime());
        jsonWriter.name(PRICE_LIST);
        jsonWriter.beginArray();
        for(Integer price : profitRequest.getPriceList()) {
            jsonWriter.value(price);
        }
        jsonWriter.endArray();
        jsonWriter.endObject();
    }

    @Override
    public ProfitRequest read(JsonReader jsonReader) throws IOException {
        ProfitRequest profitRequest = new ProfitRequest();
        try {
            jsonReader.beginObject();
            while (jsonReader.peek() != JsonToken.END_OBJECT) {
                String name = jsonReader.nextName();
                switch (name) {
                    case IDENTIFIER:
                        profitRequest.setIdentifier(jsonReader.nextString());
                        break;
                    case START_TIME:
                        profitRequest.setStartTime(localDateTimeAdapter.read(jsonReader));
                        break;
                    case END_TIME:
                        profitRequest.setEndTime(localDateTimeAdapter.read(jsonReader));
                        break;
                    case PRICE_LIST:
                        profitRequest.setPriceList(readPriceList(jsonReader));
                        break;
                    default:
                        throw new IOException("Invalid field name: " + name);
                }
            }

            jsonReader.endObject();
            validator.validate(profitRequest);
        } catch (IOException exception) {
            logger.warn(exception.getMessage(), exception);
            throw exception;
        }
        return profitRequest;
    }

    private List<Integer> readPriceList(JsonReader jsonReader) throws IOException {
        List<Integer> priceList = new ArrayList<>();
        jsonReader.beginArray();
        while(jsonReader.peek() != JsonToken.END_ARRAY) {
            if(jsonReader.peek() == JsonToken.NUMBER) {
                priceList.add(jsonReader.nextInt());
            }
            else if(jsonReader.peek() == JsonToken.NULL) {
                priceList.add(null);
                jsonReader.nextNull();
            }
            else {
                throw new IOException("Invalid value in price list.");
            }
        }
        jsonReader.endArray();
        return priceList;
    }
}
