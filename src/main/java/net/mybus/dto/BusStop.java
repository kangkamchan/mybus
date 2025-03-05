package net.mybus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusStop {
    private String stId;
    private String stNm;
    private String tmX;
    private String tmY;
    private String posX;
    private String posY;
    private String arsId;
    private List<BusRouteArriveInfo> busRouteArriveInfoList;
}
