//初始化日历选择
layui.use(['laydate','table'], function(){
    var laydate = layui.laydate;
    var table = layui.table;
    laydate.render({
        elem: '#test'
        ,range: true
    });
    //表格配置
    table.init('order',{

    })
});
//搜索功能