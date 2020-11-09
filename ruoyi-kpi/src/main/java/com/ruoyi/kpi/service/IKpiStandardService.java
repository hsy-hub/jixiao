package com.ruoyi.kpi.service;

import java.util.List;
import com.ruoyi.kpi.domain.KpiStandard;

/**
 * 绩效评价体系Service接口
 * 
 * @author 王佳乐
 * @date 2020-10-28
 */
public interface IKpiStandardService 
{
    /**
     * 查询绩效评价体系
     * 
     * @param id 绩效评价体系ID
     * @return 绩效评价体系
     */
    public KpiStandard selectKpiStandardById(Long id);

    /**
     * 查询绩效评价体系列表
     * 
     * @param kpiStandard 绩效评价体系
     * @return 绩效评价体系集合
     */
    public List<KpiStandard> selectKpiStandardList(KpiStandard kpiStandard);

    /**
     * 新增绩效评价体系
     * 
     * @param kpiStandard 绩效评价体系
     * @return 结果
     */
    public int insertKpiStandard(KpiStandard kpiStandard);

    /**
     * 修改绩效评价体系
     * 
     * @param kpiStandard 绩效评价体系
     * @return 结果
     */
    public int updateKpiStandard(KpiStandard kpiStandard);

    /**
     * 批量删除绩效评价体系
     * 
     * @param ids 需要删除的绩效评价体系ID
     * @return 结果
     */
    public int deleteKpiStandardByIds(Long[] ids);

    /**
     * 删除绩效评价体系信息
     * 
     * @param id 绩效评价体系ID
     * @return 结果
     */
    public int deleteKpiStandardById(Long id);
}
