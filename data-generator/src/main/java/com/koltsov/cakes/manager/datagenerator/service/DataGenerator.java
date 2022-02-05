package com.koltsov.cakes.manager.datagenerator.service;

import com.koltsov.cakes.manager.datagenerator.data.CakeManagerService;
import com.koltsov.cakes.manager.datagenerator.web.dto.GenerateDataRequest;
import com.koltsov.cakes.manager.datagenerator.web.dto.GenerateDataResponse;

public interface DataGenerator {

    GenerateDataResponse generate(GenerateDataRequest generateDataRequest);

    CakeManagerService service();
}
