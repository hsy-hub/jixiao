import request from '@/utils/request'

// 查询绩效成绩列表
export function listGrade(query) {
  return request({
    url: '/kpi/grade/list',
    method: 'get',
    params: query
  })
}

// 查询绩效成绩详细
export function getGrade(id) {
  return request({
    url: '/kpi/grade/' + id,
    method: 'get'
  })
}


// 新增绩效成绩
export function addGrade(data) {
  return request({
    url: '/kpi/grade',
    method: 'post',
    data: data
  })
}

// 批量新增绩效成绩
export function allAddGrade(data) {
  return request({
    url: '/kpi/grade/allAdd',
    method: 'post',
    data: data
  })
}


// 修改绩效成绩
export function updateGrade(data) {
  return request({
    url: '/kpi/grade',
    method: 'put',
    data: data
  })
}

// 删除绩效成绩
export function delGrade(id) {
  return request({
    url: '/kpi/grade/' + id,
    method: 'delete'
  })
}

// 导出绩效成绩
export function exportGrade(query) {
  return request({
    url: '/kpi/grade/export',
    method: 'get',
    params: query
  })
}
