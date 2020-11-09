package com.ruoyi.kpi.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.kpi.mapper.KpiStandardMapper;
import com.ruoyi.kpi.domain.KpiStandard;
import com.ruoyi.kpi.service.IKpiStandardService;

/**
 * 绩效评价体系Service业务层处理
 * 
 * @author 王佳乐
 * @date 2020-10-28
 */
@Service
public class KpiStandardServiceImpl implements IKpiStandardService 
{
    @Autowired
    private KpiStandardMapper kpiStandardMapper;

    /**
     * 查询绩效评价体系
     * 
     * @param id 绩效评价体系ID
     * @return 绩效评价体系
     */
    @Override
    public KpiStandard selectKpiStandardById(Long id)
    {
        return kpiStandardMapper.selectKpiStandardById(id);
    }

    /**
     * 查询绩效评价体系列表
     * 
     * @param kpiStandard 绩效评价体系
     * @return 绩效评价体系
     */
    @Override
    public List<KpiStandard> selectKpiStandardList(KpiStandard kpiStandard)
    {
        return kpiStandardMapper.selectKpiStandardList(kpiStandard);
    }

    /**
     * 新增绩效评价体系
     * 
     * @param kpiStandard 绩效评价体系
     * @return 结果
     */
    @Override
    public int insertKpiStandard(KpiStandard kpiStandard)
    {
        kpiStandard.setCreateTime(DateUtils.getNowDate());
        return kpiStandardMapper.insertKpiStandard(kpiStandard);
    }

    /**
     * 修改绩效评价体系
     * 
     * @param kpiStandard 绩效评价体系
     * @return 结果
     */
    @Override
    public int updateKpiStandard(KpiStandard kpiStandard)
    {
        kpiStandard.setUpdateTime(DateUtils.getNowDate());
        return kpiStandardMapper.updateKpiStandard(kpiStandard);
    }

    /**
     * 批量删除绩效评价体系
     * 
     * @param ids 需要删除的绩效评价体系ID
     * @return 结果
     */
    @Override
    public int deleteKpiStandardByIds(Long[] ids)
    {
        return kpiStandardMapper.deleteKpiStandardByIds(ids);
    }

    /**
     * 删除绩效评价体系信息
     * 
     * @param id 绩效评价体系ID
     * @return 结果
     */
    @Override
    public int deleteKpiStandardById(Long id)
    {
        return kpiStandardMapper.deleteKpiStandardById(id);
    }
}
