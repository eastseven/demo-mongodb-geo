package cn.eastseven.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static org.junit.Assert.assertFalse;

/**
 * @author d7
 */
@Slf4j
public class LocationTest extends ApplicationTests {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void add() {
        /*List<String> addressList = Lists.newArrayList(
                "天府软件园A区,30.555416,104.077779",
                "天府软件园B区,30.550546,104.077348",
                "天府软件园C区,30.545671,104.078115",
                "天府软件园D区,30.545218,104.081243",
                "天府软件园E区,30.544147,104.074862",
                "天府软件园F区,30.545567,104.06912",
                "天府软件园G区,30.544794,104.061829"
        );

        for (String address : addressList) {
            String location = address.split(",")[0] + "_" + DateTime.now().toString("HH:mm:ss");
            String lat = address.split(",")[1];
            String lng = address.split(",")[2];
            dao.save(new LocationEntity(location, Double.parseDouble(lat), Double.parseDouble(lng)));
        }*/

        // 天府广场
        double x = 30.663316D;
        double y = 104.072363D;
        Circle circle = new Circle(x, y, 10);
        Query query = Query.query(Criteria.where("coordinates").within(circle));
        List<LocationEntity> results = mongoTemplate.find(query, LocationEntity.class);
        assertFalse(CollectionUtils.isEmpty(results));

        results.forEach(result -> log.debug(">>> {}", result.getName()));

        Point point = new Point(x, y);
        NearQuery nearQuery = NearQuery.near(point)
                //.spherical(false)
                //.distanceMultiplier(111)

                ;
        GeoResults<LocationEntity> geoResults = mongoTemplate.geoNear(nearQuery, LocationEntity.class);
        for (GeoResult<LocationEntity> r : geoResults) {
            log.debug("### {}, {}, {}", r.getContent(), r.getDistance().getValue(), r.getDistance().getUnit());
        }
    }
}
