package co.liufeng.statistics.service;

import co.liufeng.domain.statistics.Daily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author Refeily
 * @since 2019-11-13
 */
public interface DailyService extends IService<Daily> {
    void createStatisticsByDay(String day);

    Map<String, Object> getChartData(String begin, String end, String type);
}
