/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Bool.java
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.techFacilities.repository.searchBody;

import lombok.Data;

import java.util.List;

/**
 * Created by zpy on 2018/9/14.
 */
@Data
public class Bool {
    private List<Must> must;
}
