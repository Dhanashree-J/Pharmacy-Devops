import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dhanashreej_phms.login.Login;
import com.dhanashreej_phms.login.LoginService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoginService loginService;

    @Test
    public void testSuccessfulLogin() throws Exception {
        Login user = new Login();
        user.setUsername("Admin");
        user.setRole("Owner");

        when(loginService.log("Admin", "1234", "admin")).thenReturn(user);

        mockMvc.perform(post("/login")
                .param("username", "Admin")
                .param("password", "1234")
                .param("role", "Owner"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/dashboard"));  // Adjust this URL if you redirect based on role
    }

    @Test
    public void testFailedLogin() throws Exception {
        when(loginService.log("invalidUser", "wrongPass", "admin")).thenReturn(null);

        mockMvc.perform(post("/login")
                .param("username", "invalidUser")
                .param("password", "wrongPass")
                .param("role", "admin"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attributeExists("error"));
    }
}
