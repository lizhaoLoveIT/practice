//初始化日历选择
layui.use(['laydate','table','jquery'], function(){
    var laydate = layui.laydate;
    var table = layui.table;
    var $ =layui.jquery;
    laydate.render({
        elem: '#test'
        ,range: true
    });
    //表格配置
    table.render({
        elem: '#demo'
        ,url: '/admin/index' //数据接口
        ,page: true //开启分页
        ,cols: [[ //表头
            {field: 'id', title: 'ID', sort: true, fixed: 'left'}
            ,{field: 'username', title: '手机'}
            ,{field: 'sex', title: '用户名', sort: true}
            ,{field: 'city', title: '登录方式',}
            ,{field: 'sign', title: '渠道名',}
            ,{field: 'experience', title: '所在国家',  sort: true}
            ,{field: 'score', title: '国内时长', sort: true}
            ,{field: 'classify', title: '国外时长'}
            ,{field: 'wealth', title: '最后登录时间',  sort: true}
            ,{field: 'wealth', title: '设备',  sort: true}
            ,{field: 'wealth', title: '应用版本',  sort: true}
        ]]
    });
    $(".aa").click(function () {
        location.href = './index'
    })
});
//搜索功能