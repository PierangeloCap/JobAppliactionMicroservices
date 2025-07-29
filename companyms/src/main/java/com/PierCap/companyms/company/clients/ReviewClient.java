package com.PierCap.companyms.company.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "REVIEWMS", url = "${reviewms.url}")
public interface ReviewClient {
    
    @GetMapping("/reviews/averageRating")
    Double getAverageRatingFotCompany(@RequestParam("companyId") Long companyId);
}
