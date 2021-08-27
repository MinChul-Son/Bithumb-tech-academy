package net.michul.api.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ItemController.class)
class ItemControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ItemService itemService;

    /**
     * 목 객체 생성
     */
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(
                new ItemController(itemService))
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .build();
    }

    @Test
    @DisplayName("컨트롤러 테스트")
    void home() throws Exception {
        String hello = "Hello";
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @Test
    @DisplayName("Find By Id Test")
    void findByIdTest() throws Exception {
        String itemBrand = "갤럭시";
        String itemName = "플립";
        String itemColor = "흰색";

        mockMvc.perform(get("/items")
                .param("itemBrand", itemBrand)
                .param("itemName", itemName)
                .param("itemColor", itemColor))
                .andExpect(jsonPath("$.itemBrand", is("갤럭시")))
                .andExpect(jsonPath("$.itemName", is("플립")))
                .andExpect(jsonPath("$.itemColor", is("흰색")))
                .andExpect(status().isOk())
                .andDo(print());

    }
}