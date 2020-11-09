package com.ruoyi.kpi.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 绩效评价体系对象 kpi_standard
 * 
 * @author 王佳乐
 * @date 2020-10-28
 */
public class KpiStandard extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id序号 */
    private Long id;

    /** 年份 */
    @Excel(name = "年份")
    private String year;

    /** 学期(0为第一学期，1为0) */
    @Excel(name = "学期(0为第一学期，1为0)")
    private Integer semester;

    /** 一级指标 */
    @Excel(name = "一级指标")
    private String oneIndicator;

    /** 二级指标 */
    @Excel(name = "二级指标")
    private String twoIndicator;

    /** 三级指标 */
    @Excel(name = "三级指标")
    private String threeIndicator;

    /** 成绩 */
    @Excel(name = "成绩")
    private Long grade;

    /** 详情 */
    @Excel(name = "详情")
    private String details;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setYear(String year) 
    {
        this.year = year;
    }

    public String getYear() 
    {
        return year;
    }
    public void setSemester(Integer semester) 
    {
        this.semester = semester;
    }

    public Integer getSemester() 
    {
        return semester;
    }
    public void setOneIndicator(String oneIndicator) 
    {
        this.oneIndicator = oneIndicator;
    }

    public String getOneIndicator() 
    {
        return oneIndicator;
    }
    public void setTwoIndicator(String twoIndicator) 
    {
        this.twoIndicator = twoIndicator;
    }

    public String getTwoIndicator() 
    {
        return twoIndicator;
    }
    public void setThreeIndicator(String threeIndicator) 
    {
        this.threeIndicator = threeIndicator;
    }

    public String getThreeIndicator() 
    {
        return threeIndicator;
    }
    public void setGrade(Long grade) 
    {
        this.grade = grade;
    }

    public Long getGrade() 
    {
        return grade;
    }
    public void setDetails(String details) 
    {
        this.details = details;
    }

    public String getDetails() 
    {
        return details;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("year", getYear())
            .append("semester", getSemester())
            .append("oneIndicator", getOneIndicator())
            .append("twoIndicator", getTwoIndicator())
            .append("threeIndicator", getThreeIndicator())
            .append("grade", getGrade())
            .append("details", getDetails())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
