package com.example.petever.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class FileConfiguration {
    @Value("${file.folder.path}")
    private String saveFolderPath;
}
