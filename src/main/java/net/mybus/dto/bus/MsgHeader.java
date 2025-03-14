package net.mybus.dto.bus;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MsgHeader {
    @JsonProperty("headerCd")
    private String headerCd;
    @JsonProperty("headerMsg")
    private String headerMsg;
    @JsonProperty("itemCount")
    private int itemCount;
}
