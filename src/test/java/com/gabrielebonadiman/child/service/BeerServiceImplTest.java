package com.gabrielebonadiman.child.service;

import com.gabrielebonadiman.child.ChildApp;
import com.gabrielebonadiman.child.client.ParentMicroserviceClient;
import com.gabrielebonadiman.child.config.SecurityBeanOverrideConfiguration;
import com.gabrielebonadiman.child.service.dto.BeerDTO;
import com.gabrielebonadiman.child.web.rest.BeerResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

/**
 * Test class for the BeerResource REST controller.
 *
 * @see BeerResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ChildApp.class, SecurityBeanOverrideConfiguration.class})
public class BeerServiceImplTest {


    @Autowired
    private BeerService beerService;

    @MockBean
    private ParentMicroserviceClient parentMicroserviceClient;

    private static final String DEFAULT_BEER_NAME = "Sinister";


    @Test
    public void getBeerFromParentNull() throws Exception {
        given(parentMicroserviceClient.getBeerByName(DEFAULT_BEER_NAME))
            .willReturn(new BeerDTO());
        BeerDTO beerDTO = beerService.getBeerFromParent(DEFAULT_BEER_NAME);
        assertThat(beerDTO).isNotNull();

    }

    @Test
    public void getBeerFromParentNotNull() throws Exception {
        given(parentMicroserviceClient.getBeerByName(DEFAULT_BEER_NAME))
            .willReturn(null);
        BeerDTO beerDTO = beerService.getBeerFromParent(DEFAULT_BEER_NAME);
        assertThat(beerDTO.getId()).isNull();
        assertThat(beerDTO.getAbv()).isNull();
        assertThat(beerDTO.getIbu()).isNull();
        assertThat(beerDTO.getIdentification()).isNull();
        assertThat(beerDTO.getName()).isNull();
        assertThat(beerDTO.getStyle()).isNull();
    }

}
