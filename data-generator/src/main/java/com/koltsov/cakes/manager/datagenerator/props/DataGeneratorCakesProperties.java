package com.koltsov.cakes.manager.datagenerator.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Size;
import java.util.List;

@Data
@Validated
@Component
@ConfigurationProperties(prefix = "data.generator.cakes")
public class DataGeneratorCakesProperties {
    @Size(min = 5, max = 100)
    private List<String> names;
}
