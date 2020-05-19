package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ArchitectureComponentMapperTest {

    private ArchitectureComponentMapper architectureComponentMapper;

    @BeforeEach
    public void setUp() {
        architectureComponentMapper = new ArchitectureComponentMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(architectureComponentMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(architectureComponentMapper.fromId(null)).isNull();
    }
}
