package com.geekbrains.geek.market;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class SecurityTest {
    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser(username = "User", roles = "USER")
    public void checkAuthorizedAccessToCart() throws Exception {
        mvc.perform(get("/api/v1/cart"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    public void checkUnauthorizedAccessToCart() throws Exception {
        mvc.perform(get("/api/v1/cart"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = "not_admin_user")
    public void checkProfileOnlyAvailableToAdmins() throws Exception {
        mvc.perform(get("/api/v1/profile"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }
}
