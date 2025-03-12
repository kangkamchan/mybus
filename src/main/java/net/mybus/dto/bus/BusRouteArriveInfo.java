package net.mybus.dto.bus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusRouteArriveInfo {
    private String stId;
    private String stName;
    private String arsId;
    private String busRouteId;
    private String busRouteAbrv;
    private String rtNm;
    private String gpsX;
    private String gpsY;
    private String stationTp;
    private String firstTm;
    private String lastTm;
    private String term;
    private String routeType;
    private String nextBus;
    private String staOrd;
    private String vehId1;
    private String sectOrd1;
    private String stationNm1;
    private String traTime1;
    private String traSpd1;
    private String isArrive1;
    private String isLast1;
    private String busType1;
    private String vehId2;
    private String sectOrd2;
    private String stationNm2;
    private String traTime2;
    private String traSpd2;
    private String isArrive2;
    private String isLast2;
    private String busType2;
    private String adirection;
    private String arrmsg1;
    private String arrmsg2;
    private String arrmsgSec1;
    private String arrmsgSec2;
    private String isFullFlag1;
    private String isFullFlag2;
    private String nxtStn;
    private String posX;
    private String posY;
    private String rerdieDiv1;
    private String rerdieDiv2;
    private String rerdieNum1;
    private String rerdieNum2;
    private String sectNm;
    private String deTourAt;
}
