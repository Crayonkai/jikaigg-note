package com.jikaigg.note.user.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "partten")
@Component
public class ParttenConfig {
    private String dateformat;
    private String envsharedvalue;
}
