package net.mybus.dto.bus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComMsgHeader {
    private String requestMsgID;
    private String responseMsgID;
    private String responseTime;
    private String returnCode;
    private String successYN;
    private String errMsg;
}
