package net.mybus.dto.bus;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MsgBody <T> {
    @JsonProperty("itemList")
    private List<T> itemList;
}
