package com.practice.bookservice.contract;

import com.practice.bookservice.BookServiceApplication;
import com.practice.bookservice.controller.BookController;
import com.practice.bookservice.entity.Book;
import com.practice.bookservice.service.BookService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMessageVerifier
public class BaseContract {

    @Autowired
    private BookController bookController;

    @MockBean
    private BookService mockedBookService;

    @Before
    void setup() {

        Mockito.when(mockedBookService.getBooks()).thenReturn(Arrays.asList(
                Book.builder().id(1).name("Test Book").author("Test author").createdAt(LocalDateTime.of(2021, Month.JUNE,19,0,0)).build()));

        RestAssuredMockMvc.standaloneSetup(bookController);
    }
}
