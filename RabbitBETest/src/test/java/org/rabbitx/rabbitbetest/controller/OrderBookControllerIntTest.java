package org.rabbitx.rabbitbetest.controller;

import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.rabbitx.rabbitbetest.models.NewTrade;
import org.rabbitx.rabbitbetest.models.TypeOfPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class OrderBookControllerIntTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Disabled("under construction")
    public void processATrade() throws Exception{
        MultiValueMap<String, String> params = new HttpHeaders();
        params.add("user","aerith");
        params.add("wallet", "zackwallet");

        mockMvc.perform(post("/orderBook/placeAPosition")
                        .contentType(MediaType.APPLICATION_JSON)
                        .params(params)
                        .content(createNewTrade()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Disabled("under construction")
    public void getOrderBook() throws Exception{
        mockMvc.perform(get("/orderBook/getOrderBook")
                        .param("user","AERITH")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    private String createNewTrade(){
        return new GsonBuilder().create().toJson(new NewTrade(1.0, TypeOfPosition.SHORT, "MARKET", false));
    }
}