import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AirQualityData {
    private String air_data_type;
    private String air_place;
    private String air_pm2_5;
    private String air_voc;
    private String air_carbon;
    private String air_nitrogen;
    private String air_sulfur;
    private String air_longitude;
    private String air_latitude;
}
