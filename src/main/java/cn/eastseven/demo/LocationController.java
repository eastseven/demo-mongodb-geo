package cn.eastseven.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author d7
 */
@Slf4j
@RestController
@RequestMapping("location")
public class LocationController {

    @Autowired
    private LocationRepository locationRepository;

    @GetMapping("{id}")
    public Mono<LocationEntity> get(@PathVariable String id) {
        return locationRepository.findById(id);
    }

    @GetMapping
    public Flux<LocationEntity> list() {
        return locationRepository.findAll();
    }
}
