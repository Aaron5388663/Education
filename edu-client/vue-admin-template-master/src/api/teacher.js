import request from '@/utils/request'

export default {

    // 多条件分页查询讲师列表信息
    getTeacherListPage(current, limit, teacherQuery) {
        return request({
            url: `/teacher/pageTeacherCondition/${current}/${limit}`,
            method: 'post',
            data: teacherQuery
          })
    },

    deleteTeacher(id) {
        return request({
            url: `/teacher/deleteTeacher/${id}`,
            method: 'delete'
        })
    }
}
