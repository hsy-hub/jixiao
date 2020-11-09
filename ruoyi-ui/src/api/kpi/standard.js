import request from '@/utils/request'

// 查询绩效评价体系列表
export function listStandard(query) {
  return request({
    url: '/kpi/standard/list',
    method: 'get',
    params: query
  })
}

// 查询绩效评价体系详细
export function getStandard(id) {
  return request({
    url: '/kpi/standard/' + id,
    method: 'get'
  })
}

// 新增绩效评价体系
export function addStandard(data) {
  return request({
    url: '/kpi/standard',
    method: 'post',
    data: data
  })
}

// 修改绩效评价体系
export function updateStandard(data) {
  return request({
    url: '/kpi/standard',
    method: 'put',
    data: data
  })
}

// 删除绩效评价体系
export function delStandard(id) {
  return request({
    url: '/kpi/standard/' + id,
    method: 'delete'
  })
}

// 导出绩效评价体系
export function exportStandard(query) {
  return request({
    url: '/kpi/standard/export',
    method: 'get',
    params: query
  })
}