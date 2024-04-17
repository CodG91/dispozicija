package org.example.diplomska_idelek;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;



public class CsvLoader {
    private final ResourceLoader resourceLoader;

    public CsvLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public void loadCsv() throws IOException {

        Resource resource = resourceLoader.getResource("classpath:csv/Electric_Vehicle_Population_Data.csv");
        byte[] content = FileCopyUtils.copyToByteArray(resource.getInputStream());
        String csvContent = new String(content);

        System.out.println(csvContent);

    }
}
