package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class ArchitectureComponentDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ArchitectureComponentDTO.class);
        ArchitectureComponentDTO architectureComponentDTO1 = new ArchitectureComponentDTO();
        architectureComponentDTO1.setId(1L);
        ArchitectureComponentDTO architectureComponentDTO2 = new ArchitectureComponentDTO();
        assertThat(architectureComponentDTO1).isNotEqualTo(architectureComponentDTO2);
        architectureComponentDTO2.setId(architectureComponentDTO1.getId());
        assertThat(architectureComponentDTO1).isEqualTo(architectureComponentDTO2);
        architectureComponentDTO2.setId(2L);
        assertThat(architectureComponentDTO1).isNotEqualTo(architectureComponentDTO2);
        architectureComponentDTO1.setId(null);
        assertThat(architectureComponentDTO1).isNotEqualTo(architectureComponentDTO2);
    }
}
