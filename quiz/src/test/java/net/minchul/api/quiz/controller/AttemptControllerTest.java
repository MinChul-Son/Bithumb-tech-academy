package net.minchul.api.quiz.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.minchul.api.quiz.domain.Attempt;
import net.minchul.api.quiz.domain.Quiz;
import net.minchul.api.quiz.domain.User;
import net.minchul.api.quiz.service.QuizService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static net.minchul.api.quiz.controller.AttemptController.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(AttemptController.class)
class AttemptControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    QuizService quizService;

    private JacksonTester<Attempt> jsonResult;
    private JacksonTester<ResultResponse> jsonResponse;

    @BeforeEach
    void setUp() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

//    @Test
//    void postResult() throws Exception {
//        genericParameterizedTest(true);
//    }
//
//    private void genericParameterizedTest(final boolean correct) throws Exception {
//        given(quizService.checkAttempt(any(Attempt.class))).willReturn(correct);
//        User user = new User("John", "happy-john");
//        Quiz quiz = new Quiz(50, 70);
//        Attempt attempt = new Attempt(user, quiz, 3500, correct);
//        MockHttpServletResponse response = mvc.perform(post("/results")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(jsonResult.write(attempt).getJson()))
//                .andReturn().getResponse();
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString())
//                .isEqualTo(jsonResult
//                        .write(new Attempt(
//                                attempt.getUser(),
//                                attempt.getQuiz(),
//                                attempt.getResultAttempt(),
//                                correct)).getJson());
//    }

    @Test
    void getStatistics() {
    }

    @Test
    void getResultById() {
    }
}