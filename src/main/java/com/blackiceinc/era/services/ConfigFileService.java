package com.blackiceinc.era.services;

import com.blackiceinc.era.persistence.erau.model.ConfigFile;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import java.io.IOException;

public interface ConfigFileService {
    Page<ConfigFile> findConfigFilesByParams(Integer page, Integer length, String name);

    ConfigFile save(MultipartFile file) throws IOException;

    void delete(long id);

    void exportConfiguration(Long id) throws IOException, OpenXML4JException, SAXException;

    void importConfiguration(Long id);
}
