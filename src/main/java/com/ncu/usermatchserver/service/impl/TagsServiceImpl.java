package com.ncu.usermatchserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ncu.usermatchserver.model.domain.Tags;
import com.ncu.usermatchserver.service.TagsService;
import com.ncu.usermatchserver.mapper.TagsMapper;
import org.springframework.stereotype.Service;

/**
* @author winter
* @description 针对表【tags】的数据库操作Service实现
* @createDate 2025-05-09 18:58:31
*/
@Service
public class TagsServiceImpl extends ServiceImpl<TagsMapper, Tags>
    implements TagsService{

}




