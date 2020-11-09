package com.ruoyi.kpi.mapper;

import java.util.List;
import com.ruoyi.kpi.domain.KpiGrade;

/**
 * 绩效成绩Mapper接口
 * 
 * @author 王佳乐
 * @date 2020-10-28
 */
public interface KpiGradeMapper 
{
    /**
     * 查询绩效成绩
     * 
     * @param id 绩效成绩ID
     * @return 绩效成绩
     */
    public KpiGrade selectKpiGradeById(Long id);

    /**
     * 查询绩效成绩列表
     * 
     * @param kpiGrade 绩效成绩
     * @return 绩效成绩集合
     */
    public List<KpiGrade> selectKpiGradeList(KpiGrade kpiGrade);

    /**
     * 新增绩效成绩
     * 
     * @param kpiGrade 绩效成绩
     * @return 结果
     */
    public int insertKpiGrade(KpiGrade kpiGrade);

    /**
     * 修改绩效成绩
     * 
     * @param kpiGrade 绩效成绩
     * @return 结果
     */
    public int updateKpiGrade(KpiGrade kpiGrade);

    /**
     * 删除绩效成绩
     * 
     * @param id 绩效成绩ID
     * @return 结果
     */
    public int deleteKpiGradeById(Long id);

    /**
     * 批量删除绩效成绩
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteKpiGradeByIds(Long[] ids);


    /**
     * 批量添加绩效成绩
     *
     * @param list
     * @return
     */
    public int allInsertKpiGrade(List<KpiGrade> list);


}
