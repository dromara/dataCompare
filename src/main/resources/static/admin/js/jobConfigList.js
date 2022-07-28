layui.use(['form', 'layer','table'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

//新闻列表
    var category = document.getElementById('ca').value;
    var tableIns = table.render({
        elem: '#jobConfigList',
        url:  '/job/get_all_job_config',
        cellMinWidth: 95,
        //page: true,
        height: 'full-125',
        //limit: 10,
        //limits: [10, 15, 20, 25],
        id: "blogListTable",
        cols: [[
           /* {type: "checkbox", fixed: "left",width:'3%'},*/
            {type: 'numbers',width :'4%',title: '序号'},
            {field: 'id', title: 'ID',  align: "center",width:'14%'},
            {field: 'originTableName', title: 'originTableName', align:"center",width:'33%'},
            {field: 'toTableName', title: 'toTableName', align: 'center',width:'11%'},
            {field: 'compareType', title: 'compareType', align: 'center',width:'11%'},
            {field: 'schduleTime', title: 'schduleTime', align: 'center',width:'11%'},
            {field: 'schduleStatus', title: 'schduleStatus', align: 'center',width:'11%'},
            {field: 'schduleStatus', title: 'schduleStatus', align:'center',width:'14%', templet:function(d){
                    return d.schduleStatus=='0'?'true':'false';
                }},
            {title: '操作',  templet: '#categoryListBar', fixed: "right", align: "center",width:'15%'}
        ]]
    });

    //是否展示
    form.on('switch(show)', function (obj) {
        var index = layer.msg('修改中，请稍候', {icon: 16, time: false, shade: 0.8});
        var id = obj.elem.id
        setTimeout(function () {
            layer.close(index);
            if (obj.elem.checked) {
                $.get("/admin/showBlog",{
                    articleId : id  //将需要删除的newsId作为参数传入
                },function(data){
                    if (data === "success"){
                        layer.msg("展示成功")
                    }
                })
            } else {
                $.get("/admin/hideBlog",{
                    articleId : id  //将需要删除的newsId作为参数传入
                },function(data){
                    if (data === "success"){
                        layer.msg("取消展示成功")
                    }
                })
            }
        }, 500);
    })

    form.on('select(chooseCategory)', function(data){
        if(data.value === ''){
            window.location.href = "/admin/blogList";
        }else
            window.location.href = "/admin/blogList?category=" + data.value;

    })


    $(".addNews_btn").click(function(){
        parent.addTab($(this));
    })

    //批量删除
    /*$(".delAll_btn").click(function () {
        var checkStatus = table.checkStatus('newsListTable'),
            data = checkStatus.data,
            newsId = [];
        if (data.length > 0) {
            for (var i in data) {
                newsId.push(data[i].newsId);
            }
            layer.confirm('确定删除选中的文章？', {icon: 3, title: '提示信息'}, function (index) {
                // $.get("删除文章接口",{
                //     newsId : newsId  //将需要删除的newsId作为参数传入
                // },function(data){
                tableIns.reload();
                layer.close(index);
                // })
            })
        } else {
            layer.msg("请选择需要删除的文章");
        }
    })
*/
    //列表操作
    table.on('tool(blogList)', function (obj) {
        var layEvent = obj.event,
            data = obj.data;

        if (layEvent === 'edit') { //编辑
            parent.addTab($(this));

        } else if (layEvent === 'del') { //删除
            layer.confirm('确定删除此文章？', {icon: 3, title: '提示信息'}, function (index) {
                $.get("/admin/deleteBlog",{
                     articleId : data.articleId  //将需要删除的newsId作为参数传入
                },function(data){
                    if(data === "success"){
                        tableIns.reload();
                        layer.close(index);
                    }
                 })
            });
        } else if (layEvent === 'look') { //预览
            var t = window.location.href.split("admin/")[0]
            layer.open({
                type: 2,
                title: '预览',
                shade: false,
                area: ['500px','600px'],
                maxmin: false,
                content: t + 'blog/' + data.articleId,
                zIndex: layer.zIndex, //重点1
                success: function(layero){
                    layer.setTop(layero); //重点2
                }
            });
        }
    });
});


