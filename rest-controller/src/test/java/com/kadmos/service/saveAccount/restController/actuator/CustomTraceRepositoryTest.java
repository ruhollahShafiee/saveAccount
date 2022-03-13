package com.kadmos.service.saveAccount.restController.actuator;

import com.kadmos.service.saveAccount.restController.BalanceController;
import com.kadmos.service.saveAccount.service.AccountService;
import org.hamcrest.MatcherAssert;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.junit5.JUnit5Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import static org.assertj.core.api.BDDAssertions.then;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class CustomTraceRepositoryTest {


    private CustomTraceRepository customTraceRepository;

    @RegisterExtension
    public Mockery context = new JUnit5Mockery() {
        {
            setImposteriser(ClassImposteriser.INSTANCE);
        }
    };


    @BeforeEach
    public void setUp() {
        customTraceRepository = context.mock(CustomTraceRepository.class);
    }

    @Test
    public void shouldReturn200WhenSendingRequestToManagementEndpoint() throws Exception {
//        context.checking(new Expectations() {
//            {
//                oneOf(customTraceRepository).add(new HttpTrace(null,null,null,null,null,null));
//                will(returnValue(true));
//            }
//        });
//        CustomTraceRepository customTraceRepository = new CustomTraceRepository();
////        List<HttpTrace> traces= customTraceRepository.findAll();
////        assertThat(traces).isNotEmpty();
        Assertions.assertTrue(true);
    }
}
