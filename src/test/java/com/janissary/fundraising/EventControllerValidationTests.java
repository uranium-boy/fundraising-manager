package com.janissary.fundraising;

import com.janissary.fundraising.service.impl.EventServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class EventControllerValidationTests {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EventServiceImpl eventService;

    @Test
    public void createEvent_withCorrectData_returnsCreated() throws Exception {
        String requestBody = """
            {
                "name": "Test Event",
                "defaultCurrencyCode": "EUR"
            }
        """;

        mockMvc.perform(MockMvcRequestBuilders.post("/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = " ")
    public void createEvent_withInvalidName_returnsBadRequest(String name) throws Exception {
        String requestBody = String.format("""
            {
                "name": %s,
                "defaultCurrencyCode": "EUR"
            }
        """, name == null ? null : "\"" + name + "\"");

        mockMvc.perform(MockMvcRequestBuilders.post("/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"EU", "EURO", "   "})
    public void createEvent_withInvalidCurrency_returnsBadRequest(String currencyCode) throws Exception {
        String requestBody = String.format("""
            {
                "name": "Test Event",
                "defaultCurrencyCode": %s
            }
        """, currencyCode == null ? null : "\"" + currencyCode + "\"");

        mockMvc.perform(MockMvcRequestBuilders.post("/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest());
    }
}
