package com.spring.clinicmedia.infrastructure.mapping;

import com.spring.clinicmedia.domain.model.enitity.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LocationMapper {

    public static List<String> toListStringLocations(List<Location> locations) {
        return locations
                .stream()
                .map(Location::getUrl)
                .collect(Collectors.toCollection(ArrayList::new));

    }
}
