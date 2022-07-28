layui.use(['form', 'layer', 'table'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;
    
    var tableIns = table.render({
        elem: '#categoryList',
        url:  '/db/get_all_db_config',
        cellMinWidth: 95,
        page: true,
        height: 'full-125',
        limit: 10,
        limits: [10, 15, 20, 25],
        id: "blogListTable",
        cols: [[
           /* {type: "checkbox", fixed: "left",width:'3%'},*/
            {type: 'numbers',width :'5%',title: '序号'},
            {field: 'id', title: 'ID',  align: "center",width:'5%'},
            {field: 'connectName', title: 'connectName', align:"center",width:'15%'},
            {field: 'type', title: 'type', align:"center",width:'10%'},
            {field: 'url', title: 'url', align: 'center',width:'45%'},
            {title: '操作',  templet: '#categoryListBar', fixed: "right", align: "center",width:'20%'}
        ]]
    });



    //列表操作
    table.on('tool(categoryList)', function (obj) {
        var layEvent = obj.event,
            data = obj.data;
        if (layEvent === 'edit') { //编辑
            window.location.href="/db/editDbConfig.html?id="+data.id;
        } else if (layEvent === 'del') { //删除
            layer.confirm('confirm delete it？', {icon: 3, title: 'confirm'}, function (index) {
                $.post("/db/delete",{
                     id : data.id  //将需要删除的newsId作为参数传入
                },function(data){
                    if(data.code === 0){
                        tableIns.reload();
                        layer.close(index);
                        layer.msg(data.msg);
                    }
                 })
            });
        }
    });

    /*$(".addCategoryBtn").click(function(){
        layer.prompt({
            title: 'add dbConfig'
        },function(val, index){
            layer.confirm('confirm？', {icon: 3, title: 'info'}, function (i) {
                $.get("/db/insert",{
                    cName: val
                },function(data){
                    if(data === "success"){
                        tableIns.reload();
                        layer.close(i)
                        layer.close(index);
                        layer.msg("添加成功")
                    }
                })
            });
        });
    })*/



});


