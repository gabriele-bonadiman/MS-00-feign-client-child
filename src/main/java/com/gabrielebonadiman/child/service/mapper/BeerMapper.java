package com.gabrielebonadiman.child.service.mapper;

import com.gabrielebonadiman.child.domain.*;
import com.gabrielebonadiman.child.service.dto.BeerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Beer and its DTO BeerDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BeerMapper extends EntityMapper<BeerDTO, Beer> {



    default Beer fromId(Long id) {
        if (id == null) {
            return null;
        }
        Beer beer = new Beer();
        beer.setId(id);
        return beer;
    }
}
