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
import com.ruoyi.kpi.domain.KpiGrade;
import com.ruoyi.kpi.service.IKpiGradeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 绩效成绩Controller
 * 
 * @author 王佳乐
 * @date 2020-10-28
 */
@RestController
@RequestMapping("/kpi/grade")
public class KpiGradeController extends BaseController
{
    @Autowired
    private IKpiGradeService kpiGradeService;

    /**
     * 查询绩效成绩列表
     */
    @PreAuthorize("@ss.hasPermi('kpi:grade:list')")
    @GetMapping("/list")
    public TableDataInfo list(KpiGrade kpiGrade)
    {
        startPage();
        List<KpiGrade> list = kpiGradeService.selectKpiGradeList(kpiGrade);
        return getDataTable(list);
    }

    /**
     * 导出绩效成绩列表
     */
    @PreAuthorize("@ss.hasPermi('kpi:grade:export')")
    @Log(title = "绩效成绩", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(KpiGrade kpiGrade)
    {
        List<KpiGrade> list = kpiGradeService.selectKpiGradeList(kpiGrade);
        ExcelUtil<KpiGrade> util = new ExcelUtil<KpiGrade>(KpiGrade.class);
        return util.exportExcel(list, "grade");
    }

    /**
     * 获取绩效成绩详细信息
     */
    @PreAuthorize("@ss.hasPermi('kpi:grade:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(kpiGradeService.selectKpiGradeById(id));
    }

    /**
     * 新增绩效成绩
     */
    @PreAuthorize("@ss.hasPermi('kpi:grade:add')")
    @Log(title = "绩效成绩", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody KpiGrade kpiGrade)
    {
        return toAjax(kpiGradeService.insertKpiGrade(kpiGrade));
    }

    /**
     * 新增绩效成绩
     */
    @PreAuthorize("@ss.hasPermi('kpi:grade:allAdd')")
    @Log(title = "绩效成绩", businessType = BusinessType.INSERT)
    @PostMapping("/allAdd")
//    <KpiGrade>
    public AjaxResult allAdd(@RequestBody List<KpiGrade> list)
    {
        return toAjax(kpiGradeService.allInsertKpiGrade(list));
    }



    /**
     * 修改绩效成绩
     */
    @PreAuthorize("@ss.hasPermi('kpi:grade:edit')")
    @Log(title = "绩效成绩", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody KpiGrade kpiGrade)
    {
        return toAjax(kpiGradeService.updateKpiGrade(kpiGrade));
    }

    /**
     * 删除绩效成绩
     */
    @PreAuthorize("@ss.hasPermi('kpi:grade:remove')")
    @Log(title = "绩效成绩", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(kpiGradeService.deleteKpiGradeByIds(ids));
    }
}
