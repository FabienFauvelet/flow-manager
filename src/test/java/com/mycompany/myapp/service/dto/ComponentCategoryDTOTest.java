package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class ComponentCategoryDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ComponentCategoryDTO.class);
        ComponentCategoryDTO componentCategoryDTO1 = new ComponentCategoryDTO();
        componentCategoryDTO1.setId(1L);
        ComponentCategoryDTO componentCategoryDTO2 = new ComponentCategoryDTO();
        assertThat(componentCategoryDTO1).isNotEqualTo(componentCategoryDTO2);
        componentCategoryDTO2.setId(componentCategoryDTO1.getId());
        assertThat(componentCategoryDTO1).isEqualTo(componentCategoryDTO2);
        componentCategoryDTO2.setId(2L);
        assertThat(componentCategoryDTO1).isNotEqualTo(componentCategoryDTO2);
        componentCategoryDTO1.setId(null);
        assertThat(componentCategoryDTO1).isNotEqualTo(componentCategoryDTO2);
    }
}
