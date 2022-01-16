package com.latitude.genoapay.codingchallenge.mapper;

import com.latitude.genoapay.codingchallenge.model.GetMaxProfitRequest;
import com.latitude.genoapay.codingchallenge.model.Stock;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

/**
 * @author ritesh
 * Using mapstruct for mapping GetMaxProfitRequest into Stock, mapping LocalDatetime into offsetdatetime
 */
@Mapper
public interface StockMapper {

    StockMapper INSTANCE = Mappers.getMapper(StockMapper.class);

    //maps OffsetDateTime to LocalDateTime
    default LocalDateTime map(OffsetDateTime offsetDateTime) {
        return offsetDateTime.toLocalDateTime();
    }

    //maps GetMaxProfitRequest to Stock
    Stock stockRequestToStock(GetMaxProfitRequest stockRequest);
}
