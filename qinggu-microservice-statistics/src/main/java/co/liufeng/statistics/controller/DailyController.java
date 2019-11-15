package co.liufeng.statistics.controller;

import co.liufeng.common.vo.R;
import co.liufeng.statistics.service.DailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author Refeily
 * @since 2019-11-13
 */
@RestController
@RequestMapping("/admin/statistics/daily")
public class DailyController {
    @Autowired
    private DailyService dailyService;
    @GetMapping("{day}")
    public R createStatisticsByDate(@PathVariable String day){
        dailyService.createStatisticsByDay(day);
        return R.ok();
    }
}

