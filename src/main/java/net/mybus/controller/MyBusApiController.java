package net.mybus.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.mybus.dto.ApiResponse;
import net.mybus.dto.BusStop;
import net.mybus.dto.MyBusDTO;
import net.mybus.service.MyBusService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/buses")
@RequiredArgsConstructor
@Log4j2
public class MyBusApiController {
    private final MyBusService myBusService;
    @GetMapping("/bus-stops/{search-keyword}")
    public ApiResponse<?> searchBusStops(@PathVariable("search-keyword") String searchKeyword){
        try{
            MyBusDTO<BusStop> searchResult = myBusService.getBusStationWithStationArriveInfo(searchKeyword).block();
            log.info("searchResult : {}", searchResult);
            return ApiResponse.success("정류소 검색 조회 성공", searchResult);
        }catch(Exception e){
            log.error(e);
            return ApiResponse.error("정류소 검색 간 오류 발생");
        }
    }
}
