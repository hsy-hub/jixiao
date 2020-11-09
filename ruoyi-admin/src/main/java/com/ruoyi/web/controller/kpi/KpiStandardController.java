package com.ruoyi.web.controller.kpi;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.kpi.domain.KpiStandard;
import com.ruoyi.kpi.service.IKpiStandardService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 绩效评价体系Controller
 * 
 * @author 王佳乐
 * @date 2020-10-28
 */
@RestController
@RequestMapping("/kpi/standard")
public class KpiStandardController extends BaseController
{
    @Autowired
    private IKpiStandardService kpiStandardService;

    /**
     * 查询绩效评价体系列表
     */
    @PreAuthorize("@ss.hasPermi('kpi:standard:list')")
    @GetMapping("/list")
    public TableDataInfo list(KpiStandard kpiStandard)
    {
        startPage();
        List<KpiStandard> list = kpiStandardService.selectKpiStandardList(kpiStandard);
        return getDataTable(list);
    }

    /**
     * 导出绩效评价体系列表
     */
    @PreAuthorize("@ss.hasPermi('kpi:standard:export')")
    @Log(title = "绩效评价体系", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(KpiStandard kpiStandard)
    {
        List<KpiStandard> list = kpiStandardService.selectKpiStandardList(kpiStandard);
        ExcelUtil<KpiStandard> util = new ExcelUtil<KpiStandard>(KpiStandard.class);
        return util.exportExcel(list, "standard");
    }

    /**
     * 获取绩效评价体系详细信息
     */
    @PreAuthorize("@ss.hasPermi('kpi:standard:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(kpiStandardService.selectKpiStandardById(id));
    }

    /**
     * 新增绩效评价体系
     */
    @PreAuthorize("@ss.hasPermi('kpi:standard:add')")
    @Log(title = "绩效评价体系", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody KpiStandard kpiStandard)
    {
        return toAjax(kpiStandardService.insertKpiStandard(kpiStandard));
    }

    /**
     * 修改绩效评价体系
     */
    @PreAuthorize("@ss.hasPermi('kpi:standard:edit')")
    @Log(title = "绩效评价体系", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody KpiStandard kpiStandard)
    {
        return toAjax(kpiStandardService.updateKpiStandard(kpiStandard));
    }

    /**
     * 删除绩效评价体系
     */
    @PreAuthorize("@ss.hasPermi('kpi:standard:remove')")
    @Log(title = "绩效评价体系", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(kpiStandardService.deleteKpiStandardByIds(ids));
    }
}
