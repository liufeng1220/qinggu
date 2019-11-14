package co.liufeng.ucenter.controller;

import co.liufeng.common.vo.R;
import co.liufeng.ucenter.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author Refeily
 * @since 2019-11-13
 */
@RestController
@RequestMapping("/admin/ucenter/member")
@Api(description = "用户中心")
public class MemberController {
    @Autowired
    private MemberService memberService;
    @ApiOperation(value = "今日注册数")
    @GetMapping(value = "count-register/{day}")
    public R registerCount(
@ApiParam(name = "day", value = "统计日期")
        @PathVariable String day){
        Integer count = memberService.countRegisterByDay(day);
        return R.ok().data("countRegister", count);
    }
}

