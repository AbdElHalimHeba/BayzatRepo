package com.bayzdelivery.service;

import java.time.Instant;

import org.springframework.data.domain.Page;

import com.bayzdelivery.model.response.CourierCommissionReportResponse;

public interface CourierReportService {

	Page<CourierCommissionReportResponse> findTopCourierByCommission(Instant startTime, Instant endTime, int page, int size);

}
