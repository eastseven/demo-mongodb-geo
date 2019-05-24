package cn.eastseven.demo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author d7
 */
@Data
@Document("t_location")
public class LocationEntity implements Serializable {

    public LocationEntity() {
        this.coordinates = new Double[2];
    }

    public LocationEntity(String name, Double x, Double y) {
        this();
        this.name = name;
        this.coordinates[0] = x;
        this.coordinates[1] = y;
    }

    @Id
    private String id;

    private String name;

    /**
     * 坐标
     */
    @GeoSpatialIndexed
    private Double[] coordinates;
}