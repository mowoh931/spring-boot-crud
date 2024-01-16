package com.baar.springbootcrud.api;

import com.baar.springbootcrud.dto.PersonDto;
import com.baar.springbootcrud.model.Person;
import com.baar.springbootcrud.service.PersonServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {PersonApi.class})
@ExtendWith(SpringExtension.class)
@WebMvcTest(PersonApi.class)
class PersonApiTests {
    @Autowired
    private PersonApi personApi;

    @MockBean(name = "personService")
    private PersonServiceImpl personServiceImpl;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

    }

    /**
     * Method under test: {@link PersonApi#savePerson(PersonDto)}
     */
    @Test
    void testSavePerson() throws Exception {
        // Arrange
        Person person = new Person();
        person.setEmail("jane.doe@example.org");
        person.setFirstName("Jane");
        person.setGender("Gender");
        person.setId(1);
        person.setIpAddress("42 Main St");
        person.setLastName("Doe");
        when(personServiceImpl.savePerson(Mockito.<PersonDto>any())).thenReturn(person);

        PersonDto personDto = new PersonDto();
        personDto.setEmail("jane.doe@example.org");
        personDto.setFirstName("Jane");
        personDto.setGender("Gender");
        personDto.setId(1);
        personDto.setIpAddress("42 Main St");
        personDto.setLastName("Doe");
        String content = (new ObjectMapper()).writeValueAsString(personDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/persons/save/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(personApi)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"email\":\"jane.doe@example.org\",\"gender\":\"Gender\",\"ipAddress\":\"42"
                                        + " Main St\"}"));
    }

    /**
     * Method under test: {@link PersonApi#getPersons()}
     */
    @Test
    void testGetPersons() throws Exception {
        // Arrange
        when(personServiceImpl.getPersons()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/persons/get/all/persons");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(personApi)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link PersonApi#findByLastName(String)}
     */
    @Test
    void testFindByLastName() throws Exception {
        // Arrange
        Person person = new Person();
        person.setEmail("jane.doe@example.org");
        person.setFirstName("Jane");
        person.setGender("Gender");
        person.setId(1);
        person.setIpAddress("42 Main St");
        person.setLastName("Doe");
        when(personServiceImpl.findByLastName(Mockito.<String>any())).thenReturn(person);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/persons/get/person/lastName/{lastName}", "Doe");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(personApi)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"email\":\"jane.doe@example.org\",\"gender\":\"Gender\",\"ipAddress\":\"42"
                                        + " Main St\"}"));
    }

    /**
     * Method under test: {@link PersonApi#updatePersonEmail(Integer, String)}
     */
    @Test
    void testUpdatePersonEmail() throws Exception {
        // Arrange
        doNothing().when(personServiceImpl).updatePersonEmail(Mockito.<Integer>any(), Mockito.<String>any());
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.put("/api/persons/update/person/id/email")
                .param("email", "foo");
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("id", String.valueOf(1));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(personApi)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link PersonApi#deletePerson(Integer)}
     */
    @Test
    void testDeletePerson() throws Exception {
        // Arrange
        doNothing().when(personServiceImpl).deletePerson(Mockito.<Integer>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/persons/delete/person/id/{id}",
                1);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(personApi).build().perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Delete successfully completed "));
    }
}
