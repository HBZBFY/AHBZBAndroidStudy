package com.feiyue.gulimail.gulimailmember.controller;

import com.feiyue.common.utils.PageUtils;
import com.feiyue.common.utils.R;
import com.feiyue.gulimail.gulimailmember.service.MemberLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("member/memberlevel")
public class MemberLevelController {

    @Autowired
    private MemberLevelService memberLevelService;

    @RequestMapping("/list")
    public R list(@RequestParam Map<String,Object> parm) {
        PageUtils page = memberLevelService.pagelist(parm);
        return R.ok().put("data", page);
    }
}
