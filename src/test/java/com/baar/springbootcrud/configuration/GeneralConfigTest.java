package com.baar.springbootcrud.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.SpecVersion;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.internal.InheritingConfiguration;
import org.modelmapper.internal.TypeResolvingList;
import org.modelmapper.spi.NameTokenizer;
import org.modelmapper.spi.ValueReader;
import org.modelmapper.spi.ValueWriter;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GeneralConfigTest {
    /**
     * Method under test: {@link GeneralConfig#modelMapper()}
     */
    @Test
    void testModelMapper() {

        // Arrange and Act
        ModelMapper actualModelMapperResult = (new GeneralConfig()).modelMapper();

        // Assert
        Configuration configuration = actualModelMapperResult.getConfiguration();
        List<ValueReader<?>> valueReaders = configuration.getValueReaders();
        assertTrue(valueReaders instanceof TypeResolvingList);
        List<ValueWriter<?>> valueWriters = configuration.getValueWriters();
        assertTrue(valueWriters instanceof TypeResolvingList);
        assertNull(configuration.getPropertyCondition());
        assertEquals(15, configuration.getConverters().size());
        assertEquals(Configuration.AccessLevel.PUBLIC, configuration.getFieldAccessLevel());
        assertEquals(Configuration.AccessLevel.PUBLIC, configuration.getMethodAccessLevel());
        assertTrue(actualModelMapperResult.getTypeMaps().isEmpty());
        NameTokenizer expectedSourceNameTokenizer = configuration.getDestinationNameTokenizer();
        assertSame(expectedSourceNameTokenizer, configuration.getSourceNameTokenizer());
        assertSame(valueReaders, ((InheritingConfiguration) configuration).valueAccessStore.getValueReaders());
        assertSame(valueWriters, ((InheritingConfiguration) configuration).valueMutateStore.getValueWriters());
    }

    /**
     * Method under test: {@link GeneralConfig#springOpenAPI()}
     */
    @Test
    void testSpringOpenAPI() {

        // Arrange and Act
        OpenAPI actualSpringOpenAPIResult = (new GeneralConfig()).springOpenAPI();

        // Assert
        assertEquals("3.0.1", actualSpringOpenAPIResult.getOpenapi());
        Info info = actualSpringOpenAPIResult.getInfo();
        License license = info.getLicense();
        assertEquals("Apache 2.0", license.getName());
        assertEquals("Spring boot with MySQL CRUD application", info.getDescription());
        assertEquals("UserDetails API", info.getTitle());
        ExternalDocumentation externalDocs = actualSpringOpenAPIResult.getExternalDocs();
        assertEquals("UserDetails Documentation", externalDocs.getDescription());
        assertEquals("http://springdoc.org", license.getUrl());
        assertEquals("https://github.com/mowoh931/spring-boot-crud?tab=readme-ov-file", externalDocs.getUrl());
        assertEquals("v0.0.1", info.getVersion());
        assertNull(actualSpringOpenAPIResult.getComponents());
        assertNull(actualSpringOpenAPIResult.getPaths());
        assertNull(info.getContact());
        assertNull(info.getSummary());
        assertNull(info.getTermsOfService());
        assertNull(license.getIdentifier());
        assertNull(actualSpringOpenAPIResult.getSecurity());
        assertNull(actualSpringOpenAPIResult.getServers());
        assertNull(actualSpringOpenAPIResult.getTags());
        assertNull(actualSpringOpenAPIResult.getWebhooks());
        assertNull(externalDocs.getExtensions());
        assertNull(actualSpringOpenAPIResult.getExtensions());
        assertNull(info.getExtensions());
        assertNull(license.getExtensions());
        assertEquals(SpecVersion.V30, actualSpringOpenAPIResult.getSpecVersion());
    }
}
