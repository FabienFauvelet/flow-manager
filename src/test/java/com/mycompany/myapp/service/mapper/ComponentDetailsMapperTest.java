package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ComponentDetailsMapperTest {

    private ComponentDetailsMapper componentDetailsMapper;

    @BeforeEach
    public void setUp() {
        componentDetailsMapper = new ComponentDetailsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(componentDetailsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(componentDetailsMapper.fromId(null)).isNull();
    }
}
