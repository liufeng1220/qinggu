package co.liufeng.edu.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rafeily
 * @date 2019/11/10 22:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectVo {
    private String id;
    private String title;
    private List list = new ArrayList<SubjectVo>();
}
