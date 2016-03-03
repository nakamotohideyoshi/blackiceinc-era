package com.blackiceinc.era.services;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.xml.sax.SAXException;

import java.io.IOException;

public interface ConfigurationExportImportService {

    void exportConfiguration(Long id) throws IOException, OpenXML4JException, SAXException;

    void importConfiguration(Long id);

}
