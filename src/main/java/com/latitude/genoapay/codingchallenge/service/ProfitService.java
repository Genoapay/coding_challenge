package com.latitude.genoapay.codingchallenge.service;

import com.latitude.genoapay.codingchallenge.dto.ProfitResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfitService {

    /**
     * Calculate the max profit based on the price List.
     * Fundamentally, it is DP problem. The transition formula is max(previousMax, currentMax).
     * @param priceList the stock price list, each node stands for the price at index-th trading minutes.
     *
     * @return profitResponse including
     */
    public ProfitResponse calculateMaxProfit(List<Integer> priceList) {
        ProfitResponse profitResponse = new ProfitResponse();
        if(priceList.size() == 1) {
            profitResponse.setMaxProfit(0);
            profitResponse.setBuyValue(priceList.get(0));
            profitResponse.setSellValue(priceList.get(0));
            return profitResponse;
        }
        int localMax = 0;
        int max = 0;
        int firstIndex = getFirstPriceIndex(priceList);
        int startIndex = firstIndex;
        int endIndex = firstIndex;
        int localStart = firstIndex;
        int localEnd = firstIndex;
        for (int i = 1; i < priceList.size(); i++) {
            // null value means the price of the minute not present
            if(priceList.get(i) == null) {
                continue;
            }
            // get last non-null price
            localMax += priceList.get(i) - getLastPrice(priceList, i);
            if(localMax < 0) {
                localMax = 0;
                localStart = i;
            }
            localEnd = i;
            if(max < localMax) {
                max = localMax;
                startIndex = localStart;
                endIndex = localEnd;
            }
        }

        profitResponse.setMaxProfit(max);
        profitResponse.setBuyValue(priceList.get(startIndex));
        profitResponse.setSellValue(priceList.get(endIndex));
        return profitResponse;
    }

    private int getLastPrice(List<Integer> priceList, int index) {
        if(index < 1) {
            return 0;
        }

        for (int i = index - 1; i >=0; i--) {
            if(priceList.get(i) != null) {
                return priceList.get(i);
            }
        }

        return priceList.get(index);
    }

    private int getFirstPriceIndex(List<Integer> priceList) {
        for(int i = 0; i < priceList.size(); i++) {
            if(priceList.get(i) != null) {
                return i;
            }
        }
        return 0;
    }
}
