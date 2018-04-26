package com.gabrielebonadiman.child.service.impl;

import com.gabrielebonadiman.child.client.ParentMicroserviceClient;
import com.gabrielebonadiman.child.service.BeerService;
import com.gabrielebonadiman.child.domain.Beer;
import com.gabrielebonadiman.child.repository.BeerRepository;
import com.gabrielebonadiman.child.service.dto.BeerDTO;
import com.gabrielebonadiman.child.service.mapper.BeerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Beer.
 */
@Service
@Transactional
public class BeerServiceImpl implements BeerService {

    private final Logger log = LoggerFactory.getLogger(BeerServiceImpl.class);

    private final BeerRepository beerRepository;

    private final BeerMapper beerMapper;

    private final ParentMicroserviceClient parentMicroserviceClient;

    public BeerServiceImpl(BeerRepository beerRepository, BeerMapper beerMapper, ParentMicroserviceClient parentMicroserviceClient) {
        this.beerRepository = beerRepository;
        this.beerMapper = beerMapper;
        this.parentMicroserviceClient = parentMicroserviceClient;
    }

    /**
     * Save a beer.
     *
     * @param beerDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public BeerDTO save(BeerDTO beerDTO) {
        log.debug("Request to save Beer : {}", beerDTO);
        Beer beer = beerMapper.toEntity(beerDTO);
        beer = beerRepository.save(beer);
        return beerMapper.toDto(beer);
    }

    /**
     * Get all the beers.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<BeerDTO> findAll() {
        log.debug("Request to get all Beers");
        return beerRepository.findAll().stream()
            .map(beerMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one beer by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public BeerDTO findOne(Long id) {
        log.debug("Request to get Beer : {}", id);
        Beer beer = beerRepository.findOne(id);
        return beerMapper.toDto(beer);
    }

    /**
     * Delete the beer by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Beer : {}", id);
        beerRepository.delete(id);
    }

    /**
     * Get one beer by name.
     *
     * @param name the id of the entity
     * @return the entity
     */
    @Override
    public BeerDTO getBeerFromParent(String name) {
        BeerDTO beerDTO = parentMicroserviceClient.getBeerByName(name);
        if (beerDTO != null){
            return beerDTO;
        }
        else{
            return new BeerDTO();
        }
    }
}
