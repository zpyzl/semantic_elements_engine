/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Must.java
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.techFacilities.repository.searchBody;

import lombok.Data;

/**
 * Created by zpy on 2018/9/14.
 */
@Data
public class Must {
    private Match match;
    public Must(Match match){
        this.match = match;
    }
}
