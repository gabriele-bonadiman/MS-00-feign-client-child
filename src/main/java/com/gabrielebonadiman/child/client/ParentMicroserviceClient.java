package com.gabrielebonadiman.child.client;


import com.gabrielebonadiman.child.service.dto.BeerDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@AuthorizedFeignClient(name = "parent")
public interface ParentMicroserviceClient {

    /**
     * value = Endpoint in parent microservice.
     * method = using GET
     *
     * To be aware that if I want to use the same entity (in this case Beer) I need to have both
     * the microservice with the same Entity declared.
     * Copy Beer.json file in .jhipster folder from child to parent (or viceversa) and edit microserviceName
     *
     */
    @RequestMapping(value = "/api/beers/getBeerByName/{name}", method = RequestMethod.GET)
    BeerDTO getBeerByName(@PathVariable("name") String name);

}
