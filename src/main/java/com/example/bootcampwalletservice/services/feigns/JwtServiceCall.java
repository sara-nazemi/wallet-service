package com.example.bootcampwalletservice.services.feigns;

import com.example.bootcampwalletservice.models.dtoes.feigns.IdentityResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "feign", url = "http://localhost:8082")
public interface JwtServiceCall {

    @RequestMapping(method = RequestMethod.POST, value = "/getusernamefromtoken", produces = "application/json")
    IdentityResponse getUsernameFromToken(@RequestHeader String token, @RequestHeader("lang") String lang);
}
