package org.example;

import execution.Executor;
import execution.impl.ExecutorImpl;

import javax.xml.bind.JAXBException;
import java.sql.SQLException;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        final Logger log = Logger.getLogger(Main.class.getSimpleName());
        try {
            Executor executor = new ExecutorImpl();
            executor.execute("public");
        } catch (JAXBException | SQLException exception) {
            log.severe(exception.getMessage());
        }

    }
}