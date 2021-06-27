//package com.example.demo.marketdataService;
//
//import com.example.demo.marketdataService.model.Rate;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import java.util.Map;
//
//@Component("sdsClient")
//@FeignClient(url = "https://api.coingecko.com/api/v3")
//public interface MarketDataClient {
//
//    @GetMapping("/exchange_rates")
//    Map<String, Rate> getRates();
//}
