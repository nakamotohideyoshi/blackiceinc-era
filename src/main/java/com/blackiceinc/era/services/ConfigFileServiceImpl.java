package com.blackiceinc.era.services;

import com.blackiceinc.era.persistence.erau.model.ConfigFile;
import com.blackiceinc.era.persistence.erau.repository.ConfigFileRepository;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;

@Service
public class ConfigFileServiceImpl implements ConfigFileService {

    public static final int PAGE_0 = 0;
    public static final int PAGE_SIZE_25 = 25;


    private static Logger log = LoggerFactory.getLogger(ConfigFileServiceImpl.class);
    @Value("${storage-path}")
    private String storagePath;

    private ConfigFileRepository configFileRepository;

    @Autowired
    public ConfigFileServiceImpl(ConfigFileRepository configFileRepository) {
        this.configFileRepository = configFileRepository;
    }

    @Override
    public Page<ConfigFile> findConfigFilesByParams(Integer page, Integer length, String name) {
        int pageNumber = (page != null) ? page : PAGE_0;
        int pageSize = (length != null) ? length : PAGE_SIZE_25;

        Page<ConfigFile> byFileNameContaining;
        if (name != null && !name.isEmpty()) {
            byFileNameContaining = configFileRepository.findByFileNameContainingOrderByModifiedDesc(name, new PageRequest(pageNumber, pageSize));
        } else {
            byFileNameContaining = configFileRepository.findAllByOrderByModifiedDesc(new PageRequest(pageNumber, pageSize));
        }
        return byFileNameContaining;
    }

    @Override
    public ConfigFile save(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String filePath = storagePath + fileName;

        //store file to disk
        FileUtils.copyInputStreamToFile(file.getInputStream(), new File(filePath));

        ConfigFile configFile = configFileRepository.findOneByFileName(fileName);
        if (configFile != null) {
            // overwrite file and update
        } else {
            // create file
            configFile = new ConfigFile();
            configFile.setFileName(fileName);
            configFile.setFilePath(filePath);
            configFile.setCreated(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        }

        configFile.setStatus("NULL");
        configFile.setModified(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        configFile = configFileRepository.save(configFile);

        return configFile;
    }

    @Override
    public void delete(long id) {
        ConfigFile configFile = configFileRepository.getOne(id);
        configFileRepository.delete(id);
        if (!FileUtils.deleteQuietly(new File(configFile.getFilePath()))) {
            log.warn("File {} could not be deleted.", configFile.getFilePath());
        }
    }

}
