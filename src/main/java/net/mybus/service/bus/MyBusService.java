package net.mybus.service.bus;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.mybus.dto.bus.BusRouteArriveInfo;
import net.mybus.dto.bus.BusStop;
import net.mybus.dto.bus.MyBusDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.beans.factory.annotation.Value;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class MyBusService {
    @Value("${api.service.key}")
    private String SERVICE_KEY;
    private final WebClient webClient;
    /**
     * 명칭에 검색어를 포함하는 버스정류소 리스트를 조회함
     * @param stSrch 검색어
     * @return MyBusDTO<BusStop> BusStop List 를 body 에 포함하는 반환객체, BusStop 의 BusRouteArriveInfoList 정보 없음
     */
    public Mono<MyBusDTO<BusStop>> getBusStopSearchResult(String stSrch){
        return webClient.get()
                .uri(uriBuilder ->uriBuilder.path("/stationinfo/getStationByName")
                        .queryParam("serviceKey", SERVICE_KEY)
                        .queryParam("stSrch", stSrch)
                        .queryParam("resultType", "json")
                        .build()
                )
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<MyBusDTO<BusStop>>() {});
    }
    /**
     * 버스정류소 명칭 검색 결과 리스트로 정류소별 노선별 버스 도착정보 리스트를 조회함*
     * @param stSrch 검색어
     * @return MyBusDTO<BusStop> BusStop List 를 body 에 포함하는 반환객체, BusStop 의 BusRouteArriveInfoList 정보를 포함함
     */
    public Mono<MyBusDTO<BusStop>> getBusStationWithStationArriveInfo(String stSrch){
        return getBusStopSearchResult(stSrch)
                .flatMap(myBusDTO -> {
                    List<BusStop> stationList = myBusDTO.getMsgBody().getItemList();
                    return Flux.fromIterable(stationList)
                            .flatMap(busStop -> getBusStopArriveInfo(busStop.getArsId())
                                    .map(busStopArriveInfoResult->{
                                        List<BusRouteArriveInfo> busStopArriveInfoList = busStopArriveInfoResult.getMsgBody().getItemList();
                                        busStop.setBusRouteArriveInfoList(busStopArriveInfoList);
                                        return busStop;
                                    })
                            )
                            .collectList()
                            .map(updatedStationList->{
                                myBusDTO.getMsgBody().setItemList(updatedStationList);
                                return myBusDTO;
                            });
                });
    }

    /**
     * 버스정류소 id로 해당 정류소의 노선별 도착정보를 조회함
     * @param arsId
     * @return MyBusDTO<BusRouteArriveInfo> BusRouteArriveInfo List 를 body 에 포함하는 반환객체
     */
    public Mono<MyBusDTO<BusRouteArriveInfo>> getBusStopArriveInfo(String arsId){
        return webClient.get()
                .uri(uriBUilder -> uriBUilder
                        .path("/stationinfo/getStationByUid")
                        .queryParam("arsId",arsId)
                        .queryParam("serviceKey",SERVICE_KEY)
                        .queryParam("resultType","json")
                        .build()
                )
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<MyBusDTO<BusRouteArriveInfo>>(){});
    }
}
