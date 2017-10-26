package com.platform.upms.service.impl;

import com.platform.common.base.impl.BaseServiceImpl;
import com.platform.common.page.Page;
import com.platform.common.redis.RedisUtil;
import com.platform.common.utils.StringUtils;
import com.platform.upms.dao.UpmsMenuDao;
import com.platform.upms.model.UpmsMenu;
import com.platform.upms.model.UpmsOrg;
import com.platform.upms.model.ZNode;
import com.platform.upms.service.UpmsMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UpmsMenuServiceImpl extends BaseServiceImpl<UpmsMenu> implements UpmsMenuService {

    @Autowired
    UpmsMenuDao upmsMenuDao;

    @Override
    public List<UpmsMenu> list(UpmsMenu upmsMenu) {
      List<UpmsMenu> list = upmsMenuDao.selectList(upmsMenu);
        return list;
    }

    @Override
    public UpmsMenu getMenu() {
        UpmsMenu upmsMenu =upmsMenuDao.getMenu();
        return upmsMenu;
    }

    @Override
    public List<ZNode> findZnodeList(UpmsOrg upmsOrg) {
        return upmsMenuDao.findZnodeList(upmsOrg);
    }

    @Override
    public Page<UpmsMenu> findByPageRedis(Page<UpmsMenu> page, UpmsMenu upmsMenu) {
        Jedis jedis = RedisUtil.getJedis();
        Set<String> zrange = jedis.zrange("menu"+page.getPageNum(),0,10);
        if(zrange.size()==0){
            page.setResults(upmsMenuDao.findByPage(page,null));
            for (UpmsMenu upmsMenu1 :page.getResults()){
                try{
                    jedis.zadd("menu"+page.getPageNum(),1, StringUtils.objectToJson(upmsMenu1));
                    } catch (Exception e){

                }
            }
            jedis.set("menulist"+page.getPageNum(),""+page.getTotalPage());
            jedis.set("menulist"+page.getPageNum()+"s",""+page.getTotalRecord());
        return  page;
        }
        else{
            List<UpmsMenu> upmsMenus = new ArrayList<>();
            Page<UpmsMenu> pageList= new Page<>();

            for(String s:zrange){
                UpmsMenu upmsMenu1 = StringUtils.json2Object(s,UpmsMenu.class);
                upmsMenus.add(upmsMenu1);
            }
            String num = jedis.get("menulist"+page.getPageNum());
            String rec = jedis.get("menulist"+page.getPageNum()+"s");
            pageList.setTotalPage(Integer.valueOf(num).intValue());
            pageList.setTotalRecord(Integer.valueOf(rec).intValue());
            pageList.setPageNum(page.getPageNum());
            pageList.setResults(upmsMenus);
          return  pageList;
        }
    }
}
