package com.janissary.fundraising;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class CollectionBoxControllerValidationTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void assignBoxRequest_withCorrectData_returnsNotImplemented() throws Exception {
        String requestBody = """
            {
                "eventId": 1
            }
        """;

        mockMvc.perform(MockMvcRequestBuilders.post("/boxes/1/assign")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isNotImplemented());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"-1", "0", "abc", " "})
    public void assignBoxRequest_withInvalidId(String id) throws Exception {
        String requestBody = String.format("""
            {
                "eventId": %s
            }
        """, id);

        mockMvc.perform(MockMvcRequestBuilders.post("/boxes/1/assign")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void donateMoney_withCorrectData_returnsNotImplemented() throws Exception {
        String requestBody = """
            {
                "currency": "EUR",
                "amount": "1.00"
            }
        """;

        mockMvc.perform(MockMvcRequestBuilders.post("/boxes/1/donate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isNotImplemented());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"-1.0", "0.0", "0.001", "abc"})
    public void donateMoney_withInvalidAmount_returnsBadRequest(String amount) throws Exception {
        String requestBody = String.format("""
                {
                    "currency": "EUR",
                    "amount": %s
                }
                """, amount);

        mockMvc.perform(MockMvcRequestBuilders.post("/boxes/1/donate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"EU", "EURO", " "})
    public void donateMoney_withInvalidCurrency_returnsBadRequest(String currencyCode) throws Exception {
        String requestBody = String.format("""
                {
                    "currency": %s,
                    "amount": 1.00
                }
                """, currencyCode == null ? null : "\"" + currencyCode + "\"");

        mockMvc.perform(MockMvcRequestBuilders.post("/boxes/1/donate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest());
    }
}
