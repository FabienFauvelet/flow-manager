package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class ComponentDetailsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ComponentDetails.class);
        ComponentDetails componentDetails1 = new ComponentDetails();
        componentDetails1.setId(1L);
        ComponentDetails componentDetails2 = new ComponentDetails();
        componentDetails2.setId(componentDetails1.getId());
        assertThat(componentDetails1).isEqualTo(componentDetails2);
        componentDetails2.setId(2L);
        assertThat(componentDetails1).isNotEqualTo(componentDetails2);
        componentDetails1.setId(null);
        assertThat(componentDetails1).isNotEqualTo(componentDetails2);
    }
}
