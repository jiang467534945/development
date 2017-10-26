package com.platform.gen.service.impl;

import com.platform.common.utils.IdGen;
import com.platform.common.utils.StringUtils;
import com.platform.gen.dao.GenTableColumnDao;
import com.platform.gen.dao.GenTemplateDao;
import com.platform.gen.model.GenTemplate;
import com.platform.gen.service.GenTemplateService;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GenTemplateServiceImpl implements GenTemplateService {
    @Autowired
    private GenTemplateDao genTemplateDao;
    @Override
    public List<GenTemplate> find(GenTemplate genTemplate) {
        return genTemplateDao.findList(genTemplate);
    }

    @Override
    public GenTemplate get(String id) {
        return genTemplateDao.get(id);    }

    @Override
    public void save(GenTemplate genTemplate) {
        if (genTemplate.getContent()!=null){
            genTemplate.setContent(StringEscapeUtils.unescapeHtml4(genTemplate.getContent()));
        }
        if (StringUtils.isBlank(genTemplate.getId())){
            genTemplate.setId(IdGen.uuid());
            genTemplateDao.saveData(genTemplate);
        }else{
            genTemplateDao.update(genTemplate);
        }

    }

    @Override
    public void delete(GenTemplate genTemplate) {
        genTemplateDao.deleteData(genTemplate);

    }
}
