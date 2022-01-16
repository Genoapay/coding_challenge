package com.latitude.genoapay.codingchallenge.mapper;

import com.latitude.genoapay.codingchallenge.model.GetMaxProfitRequest;
import com.latitude.genoapay.codingchallenge.model.GetMaxProfitResponse;
import com.latitude.genoapay.codingchallenge.model.StockResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

/**
 * @author ritesh
 * Using mapstruct for mapping StockResult object into GetMaxProfitResponse, mapping LocalDateTime into OffsetDateTime
 */
@Mapper
public interface StockResultMapper {

    StockResultMapper INSTANCE = Mappers.getMapper(StockResultMapper.class);

    //maps OffsetDateTime to LocalDateTime
    default OffsetDateTime map(LocalDateTime localDateTime) {
        final ZoneId zone = ZoneId.of("Pacific/Auckland");
        ZoneOffset zoneOffSet = zone.getRules().getOffset(localDateTime);
        return localDateTime.atOffset(zoneOffSet);
    }

    //maps Stockresult and GetMaxProfitRequest into GetMaxProfitResponse
    @Mapping(target = "stockRequest", source = "getMaxProfitRequest")
    GetMaxProfitResponse toMaxProfitResponse(StockResult stockResult, GetMaxProfitRequest getMaxProfitRequest);
}
