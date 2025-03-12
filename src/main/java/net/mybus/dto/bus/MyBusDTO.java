package net.mybus.dto.bus;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyBusDTO <T> {
    @JsonProperty("comMsgHeader")
    private ComMsgHeader comMsgHeader;
    @JsonProperty("msgHeader")
    private MsgHeader msgHeader;
    @JsonProperty("msgBody")
    private MsgBody<T> msgBody;
}
