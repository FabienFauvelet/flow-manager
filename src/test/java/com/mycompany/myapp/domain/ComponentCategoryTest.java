package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class ComponentCategoryTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ComponentCategory.class);
        ComponentCategory componentCategory1 = new ComponentCategory();
        componentCategory1.setId(1L);
        ComponentCategory componentCategory2 = new ComponentCategory();
        componentCategory2.setId(componentCategory1.getId());
        assertThat(componentCategory1).isEqualTo(componentCategory2);
        componentCategory2.setId(2L);
        assertThat(componentCategory1).isNotEqualTo(componentCategory2);
        componentCategory1.setId(null);
        assertThat(componentCategory1).isNotEqualTo(componentCategory2);
    }
}
