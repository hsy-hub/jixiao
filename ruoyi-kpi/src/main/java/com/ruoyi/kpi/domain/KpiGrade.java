package com.ruoyi.kpi.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 绩效成绩对象 kpi_grade
 * 
 * @author 王佳乐
 * @date 2020-10-28
 */
public class KpiGrade extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 用户名 */
    @Excel(name = "用户名")
    private String nickName;

    /** 学工号 */
    @Excel(name = "学工号")
    private String userName;

    /** 年份 */
    @Excel(name = "年份")
    private String year;

    /** 学期 */
    @Excel(name = "学期")
    private Integer semester;

    /** 部门id */
    @Excel(name = "部门id")
    private Long deptId;

    /** 成绩单 */
    @Excel(name = "成绩单")
    private String score;

    /** 总成绩 */
    @Excel(name = "总成绩")
    private Long totalScore;

    /** 状态 */
    @Excel(name = "状态")
    private Integer state;

    /** 说明 */
    @Excel(name = "说明")
    private String details;

    public KpiGrade() {
    }

    public KpiGrade(String nickName, String userName, String year, Integer semester, Long deptId) {
        this.nickName = nickName;
        this.userName = userName;
        this.year = year;
        this.semester = semester;
        this.deptId = deptId;
    }

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setNickName(String nickName) 
    {
        this.nickName = nickName;
    }

    public String getNickName() 
    {
        return nickName;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
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
    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }
    public void setScore(String score) 
    {
        this.score = score;
    }

    public String getScore() 
    {
        return score;
    }
    public void setTotalScore(Long totalScore) 
    {
        this.totalScore = totalScore;
    }

    public Long getTotalScore() 
    {
        return totalScore;
    }
    public void setState(Integer state) 
    {
        this.state = state;
    }

    public Integer getState() 
    {
        return state;
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
            .append("nickName", getNickName())
            .append("userName", getUserName())
            .append("year", getYear())
            .append("semester", getSemester())
            .append("deptId", getDeptId())
            .append("score", getScore())
            .append("totalScore", getTotalScore())
            .append("state", getState())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("details", getDetails())
            .toString();
    }
}
