package cz.honestcity.model.subject;


import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class WatchedSubject {
    private long id;
    private Activity activity;
    private HonestyStatus honestyStatus;
    private String name;
}
