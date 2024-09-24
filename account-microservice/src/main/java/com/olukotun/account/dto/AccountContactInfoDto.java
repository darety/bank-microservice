package com.olukotun.account.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.List;

@ConfigurationProperties(prefix = "accounts")
public record AccountContactInfoDto(String message, HashMap<String, String> contactDetail, List<String> onCallSupport) {


}
