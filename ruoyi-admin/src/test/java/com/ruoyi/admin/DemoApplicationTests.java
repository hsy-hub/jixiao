package com.ruoyi.admin;

import com.google.common.collect.Lists;
import com.ruoyi.kpi.domain.KpiGrade;
import com.ruoyi.kpi.service.IKpiGradeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    private IKpiGradeService kpiGradeService;

    @Test
    void contextLoads() {
        KpiGrade k = new KpiGrade("111111", "王佳乐", "2020", 0, 1L);
        KpiGrade k1 = new KpiGrade("222222", "王佳乐1", "2020", 0, 1L);
        KpiGrade k2 = new KpiGrade("333333", "王佳乐2", "2020", 0, 1L);

        List<KpiGrade> list = Lists.newArrayList(k,k1,k2);

        for (KpiGrade a:list) {
            System.out.println(a);
       }

//        kpiGradeService.allInsertKpiGrade(list);
    }

}