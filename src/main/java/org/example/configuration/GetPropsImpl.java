package org.example.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.Logger;

@Data
@AllArgsConstructor
public class GetPropsImpl implements GetProps {

    private static final Logger log;

    static {
        log = Logger.getLogger(GetPropsImpl.class.getName());
    }

    @Override
    public PropertyConfig getConn() {
        Properties property = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get("src/main/resources/property.properties"))) {
            property.load(in);
        } catch (IOException ex) {
            log.severe(ex.getMessage());
        }
        return new PropertyConfig(property.getProperty("url"), property.getProperty("user"), property.getProperty("password"));
    }
}
