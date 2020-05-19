package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class ArchitectureComponentTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ArchitectureComponent.class);
        ArchitectureComponent architectureComponent1 = new ArchitectureComponent();
        architectureComponent1.setId(1L);
        ArchitectureComponent architectureComponent2 = new ArchitectureComponent();
        architectureComponent2.setId(architectureComponent1.getId());
        assertThat(architectureComponent1).isEqualTo(architectureComponent2);
        architectureComponent2.setId(2L);
        assertThat(architectureComponent1).isNotEqualTo(architectureComponent2);
        architectureComponent1.setId(null);
        assertThat(architectureComponent1).isNotEqualTo(architectureComponent2);
    }
}
