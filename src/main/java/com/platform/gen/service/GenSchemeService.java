package com.platform.gen.service;

import com.platform.common.utils.IdGen;
import com.platform.common.utils.StringUtils;
import com.platform.gen.model.*;
import com.platform.gen.utils.GenUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface GenSchemeService {
    public GenScheme get(String id);

    public List<GenScheme> find(GenScheme genScheme);

    public String save(GenScheme genScheme);

    public void delete(GenScheme genScheme);

     String generateCode(GenScheme genScheme);

}
