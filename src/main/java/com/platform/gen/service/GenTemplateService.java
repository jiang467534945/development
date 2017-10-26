package com.platform.gen.service;

import com.platform.gen.model.GenTable;
import com.platform.gen.model.GenTemplate;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GenTemplateService {
    List<GenTemplate> find(GenTemplate genTemplate);
    GenTemplate get(String id);
    void save(GenTemplate genTemplate);
    void delete(GenTemplate genTemplate);
}
