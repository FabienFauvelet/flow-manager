package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class ComponentDetailsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ComponentDetailsDTO.class);
        ComponentDetailsDTO componentDetailsDTO1 = new ComponentDetailsDTO();
        componentDetailsDTO1.setId(1L);
        ComponentDetailsDTO componentDetailsDTO2 = new ComponentDetailsDTO();
        assertThat(componentDetailsDTO1).isNotEqualTo(componentDetailsDTO2);
        componentDetailsDTO2.setId(componentDetailsDTO1.getId());
        assertThat(componentDetailsDTO1).isEqualTo(componentDetailsDTO2);
        componentDetailsDTO2.setId(2L);
        assertThat(componentDetailsDTO1).isNotEqualTo(componentDetailsDTO2);
        componentDetailsDTO1.setId(null);
        assertThat(componentDetailsDTO1).isNotEqualTo(componentDetailsDTO2);
    }
}
