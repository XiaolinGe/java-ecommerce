package com.example;

import com.example.entity.User;
import com.example.service.UserService;
import com.example.web.UserController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

/**
 * https://www.petrikainulainen.net/programming/spring-framework/unit-testing-of-spring-mvc-controllers-normal-controllers/
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class TodoControllerTest {
 
    @MockBean
    private UserService todoServiceMock;

    @Autowired
    private MockMvc mvc;


    @Test
    public void findAll_ShouldAddTodoEntriesToModelAndRenderTodoListView() throws Exception {
        User first = new User().setId(1L).setName("Foo");
        User second = new User().setId(2L).setName("Bar");
        when(todoServiceMock.findAll()).thenReturn(Arrays.asList(first, second));
        mvc.perform(get("/user/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("/user/list"))
                .andExpect(forwardedUrl("/templates/user/list.ftl"))
                .andExpect(model().attribute("todos", hasSize(2)))
                .andExpect(model().attribute("todos", hasItem(
                        allOf(
                                hasProperty("id", is(1L)),
                                hasProperty("name", is("Foo"))
                        )
                )))
                .andExpect(model().attribute("todos", hasItem(
                        allOf(
                                hasProperty("id", is(2L)),
                                hasProperty("name", is("Bar"))
                        )
                )));
 
        verify(todoServiceMock, times(1)).findAll();
        verifyNoMoreInteractions(todoServiceMock);
    }
}