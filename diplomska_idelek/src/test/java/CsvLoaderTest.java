import org.example.diplomska_idelek.CsvLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.mockito.Mockito.when;

class CsvLoaderTest {

    @Mock
    private ResourceLoader resourceLoader;
    @Mock
    private Resource resource;

    private CsvLoader csvLoader;

    @BeforeEach
    void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);
        when(resourceLoader.getResource("classpath:csv/Electric_Vehicle_Population_Data.csv"))
                .thenReturn(resource);
        byte[] content = "csv content".getBytes();
        when(resource.getInputStream()).thenReturn(new ByteArrayInputStream(content));

        // Initialize CsvLoader with the mock ResourceLoader
        csvLoader = new CsvLoader(resourceLoader);
    }

    @Test
    void testLoadCsv() throws IOException {
        // Call the method under test
        csvLoader.loadCsv();

        // Add your assertions here to verify the behavior of loadCsv() method
    }
}
