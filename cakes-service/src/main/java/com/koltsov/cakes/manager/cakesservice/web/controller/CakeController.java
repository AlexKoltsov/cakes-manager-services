package com.koltsov.cakes.manager.cakesservice.web.controller;

import com.koltsov.cakes.manager.cakesservice.data.Cake;
import com.koltsov.cakes.manager.web.dto.cake.CakeCreateDto;
import com.koltsov.cakes.manager.web.dto.cake.CakeDto;
import com.koltsov.cakes.manager.mapper.GenericMapper;
import com.koltsov.cakes.manager.service.CrudService;
import com.koltsov.cakes.manager.web.controller.CrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/cakes")
public class CakeController extends CrudController<Cake, Long, CakeDto, CakeCreateDto> {

    public CakeController(CrudService<Cake, Long> crudService,
                          GenericMapper<Cake, CakeDto, CakeCreateDto> mapper) {
        super(crudService, mapper);
    }
}
