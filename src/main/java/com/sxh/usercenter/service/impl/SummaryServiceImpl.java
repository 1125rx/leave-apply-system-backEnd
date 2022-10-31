package com.sxh.usercenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxh.usercenter.Model.domain.Summary;
import com.sxh.usercenter.service.SummaryService;
import com.sxh.usercenter.Mapper.SummaryMapper;
import org.springframework.stereotype.Service;

/**
* @author sxh
* @description 针对表【summary(请假次数统计)】的数据库操作Service实现
* @createDate 2022-08-29 21:25:52
*/
@Service
public class SummaryServiceImpl extends ServiceImpl<SummaryMapper, Summary>
    implements SummaryService{

}




