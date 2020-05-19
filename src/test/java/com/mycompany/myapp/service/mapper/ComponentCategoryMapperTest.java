package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ComponentCategoryMapperTest {

    private ComponentCategoryMapper componentCategoryMapper;

    @BeforeEach
    public void setUp() {
        componentCategoryMapper = new ComponentCategoryMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(componentCategoryMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(componentCategoryMapper.fromId(null)).isNull();
    }
}
