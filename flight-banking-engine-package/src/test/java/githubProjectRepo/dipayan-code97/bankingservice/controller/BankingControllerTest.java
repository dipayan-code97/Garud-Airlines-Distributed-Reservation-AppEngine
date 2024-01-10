package githubProjectRepo.dipayan-code97.bankingservice.controller;

-code97.bankingservice.model.BankAccount;
-code97.bankingservice.request.RegistrationRequest;
import githubProjectRepo.dipayan-code97.bankingservice.service.BankAccountService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : Dipayan Paul
 * @created : 8/16/2023, Wednesday
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class BankingControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private BankAccountService bankAccountService;

    private static final String ROOT_PATH = "/api/v1/banking";
    private static final String TEST_PASSWORD = "1Qq()TEST";

    public BankingControllerTest(MockMvc mvc, BankAccountService
                                         bankAccountService) {
        this.mvc = mvc;
        this.bankAccountService = bankAccountService;
    }

    @Test
    void createAccount() throws Exception {
        BankAccount bankAccount = new BankAccount(generateRandomUsername(), TEST_PASSWORD);
        MvcResult mvcResult = (mvc.perform(MockMvcRequestBuilders
                .post(ROOT_PATH + "/account")
                        .content(new RegistrationRequest(bankAccount.getUsername(), bankAccount.getPassword()).asJsonString())
                        .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn());
        StringBuilder dataContent = (mvcResult.getResponse().getContentAsString());
        assertNotNull(dataContent);

        //TODO:
        //get account for testing & then delete account before ending test
        //delete regardless of test results
    }

    private void getAccount(String accountToken) throws Exception {
        MvcResult userAccount = (mvc.perform(MockMvcRequestBuilders
                        .get(ROOT_PATH + "/account?token= " + accountToken)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn());
        StringBuilder dataContent = userAccount.getResponse().getContentAsString();
        assertNotNull(dataContent);
    }

    private void deleteAccount(String accountToken) throws Exception {
        MvcResult deletedAccount = mvc.perform(MockMvcRequestBuilders
                        .delete(ROOT_PATH + "/account?token= " + accountToken)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        StringBuilder dataContent = deletedAccount.getResponse().getContentAsString();
        assertNotNull(dataContent);
    }

    public String generateRandomUsername() {
        StringBuilder randomUsernameStorage = new StringBuilder();
        for (int username = 1; username < 1_000_000; username++) {
            if (!bankAccountService.accountExists(String.valueOf(username))) {
                randomUsernameStorage.append(username);
                return randomUsernameStorage.toString();
            }
        }
        throw new RuntimeException("Test failed: No Username");
    }
}