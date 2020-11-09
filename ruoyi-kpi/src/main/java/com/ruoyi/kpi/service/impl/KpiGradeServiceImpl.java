package com.ruoyi.kpi.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.kpi.mapper.KpiGradeMapper;
import com.ruoyi.kpi.domain.KpiGrade;
import com.ruoyi.kpi.service.IKpiGradeService;

/**
 * 绩效成绩Service业务层处理
 *
 * @author 王佳乐
 * @date 2020-10-28
 */
@Service
public class KpiGradeServiceImpl implements IKpiGradeService {
    @Autowired
    private KpiGradeMapper kpiGradeMapper;

    /**
     * 查询绩效成绩
     *
     * @param id 绩效成绩ID
     * @return 绩效成绩
     */
    @Override
    public KpiGrade selectKpiGradeById(Long id) {
        return kpiGradeMapper.selectKpiGradeById(id);
    }

    /**
     * 查询绩效成绩列表
     *
     * @param kpiGrade 绩效成绩
     * @return 绩效成绩
     */
    @Override
    public List<KpiGrade> selectKpiGradeList(KpiGrade kpiGrade) {
        return kpiGradeMapper.selectKpiGradeList(kpiGrade);
    }

    /**
     * 新增绩效成绩
     *
     * @param kpiGrade 绩效成绩
     * @return 结果
     */
    @Override
    public int insertKpiGrade(KpiGrade kpiGrade) {
        kpiGrade.setCreateTime(DateUtils.getNowDate());
        return kpiGradeMapper.insertKpiGrade(kpiGrade);
    }

    /**
     * 修改绩效成绩
     *
     * @param kpiGrade 绩效成绩
     * @return 结果
     */
    @Override
    public int updateKpiGrade(KpiGrade kpiGrade) {
        kpiGrade.setUpdateTime(DateUtils.getNowDate());
        return kpiGradeMapper.updateKpiGrade(kpiGrade);
    }

    /**
     * 批量删除绩效成绩
     *
     * @param ids 需要删除的绩效成绩ID
     * @return 结果
     */
    @Override
    public int deleteKpiGradeByIds(Long[] ids) {
        return kpiGradeMapper.deleteKpiGradeByIds(ids);
    }

    /**
     * 删除绩效成绩信息
     *
     * @param id 绩效成绩ID
     * @return 结果
     */
    @Override
    public int deleteKpiGradeById(Long id) {
        return kpiGradeMapper.deleteKpiGradeById(id);
    }

    /**
     * 批量添加绩效成绩
     *<KpiGrade>
     * @param list
     * @return
     */
    @Override
    public int allInsertKpiGrade(List<KpiGrade> list) {
//        System.out.println("------------------------------------------------------------------------------------------");
//        System.out.println(list.size());
//        for (KpiGrade a:list) {
//            System.out.println(a);
//        }
        return kpiGradeMapper.allInsertKpiGrade(list);
//        return 1;
    }

}
