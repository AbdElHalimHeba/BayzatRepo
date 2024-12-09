package com.bayzdelivery.service.strategy.impl;

import org.springframework.stereotype.Service;

import com.bayzdelivery.service.strategy.Distance;

@Service
public class DefaultDistance implements Distance {

	private static final Double EARTH_RADIUS_KM = 6371.0;

	@Override
	public Double calculateDistance(Double lat1, Double lng1, Double lat2, Double lng2) {
		Double lat1Rad = Math.toRadians(lat1);
		Double lng1Rad = Math.toRadians(lng1);
		Double lat2Rad = Math.toRadians(lat2);
		Double lng2Rad = Math.toRadians(lng2);

        // Haversine formula
		Double deltaLat = lat2Rad - lat1Rad;
		Double deltaLng = lng2Rad - lng1Rad;

		Double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
                   Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                   Math.sin(deltaLng / 2) * Math.sin(deltaLng / 2);

		Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Distance in km
        return EARTH_RADIUS_KM * c;
	}

}
