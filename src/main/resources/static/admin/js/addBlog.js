layui.use(['form','layer','element'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery;
    form.render();
    var hasSaved = false;

    window.onbeforeunload = function() {
        if(!hasSaved) {
            return false;
        }
        // return true;  // 不能加这个【懵】
    }

    function doSave() {
        hasSaved = true;
    }

    form.on('select(chooseCate)', function(data){
        $("#hiddenCategory").val($("#categorySelect option:selected").attr('title'));

    })


    $("#chooseTags").click(function () {
        layer.open({
            type: 1,
            area: '400px',
            content: $('#id'),
            btn: ['确认', '取消']
            , yes: function (index, layero) {
                var tagIds = '';
                var tagNames = '';
                var count = 0;
                $("input[id='tagCB']:checked").each(function () {
                    count++;
                    if (count === 1) {
                        tagIds = tagIds + $(this).val();
                        tagNames = tagNames + $(this).attr('title');
                    } else {
                        tagIds = tagIds + "," + $(this).val();
                        tagNames = tagNames + ',' + $(this).attr('title');
                    }
                })
                if (tagNames !== '') {
                    $("#chooseTags").val(tagNames);
                    $('#hiddenTags').val(tagIds);
                } else{
                    $("#chooseTags").val('');
                    $("#hiddenTags").val('');
                }
                layer.close(index)
            }
            , btn2: function (index, layero) {

            }
        })
    });
    $('#buttonSubmit').click(function (e) {
        if ($("#articleTitle").val() === "") {
            layer.alert("标题不能为空，请重新输入",{title:"警告"});
            return;
        }
        if ($("#hiddenCategory").val() === "") {
            layer.alert("分类不能为空，请重新选择",{title:"警告"})
            return
        }
        if ($("#outline").val() === "") {
            layer.alert("摘要不能为空，请重新输入",{title:"警告"})
            return
        }

        if ($("#articleContent").val() === "") {
            layer.alert("内容不能为空，请重新输入",{title:"警告"})
            return
        }
        $('#articleId').val(new Date().getTime())
        layer.confirm('确定提交此文章？', {icon: 3, title: '提示信息'}, function (index) {
            layer.msg("发布成功",{time:500})
            setTimeout(function () {
                $("#addBlogForm").submit();
                var layId = '';
                $(".layui-tab-title.top_tab li",parent.document).each(function(){
                    if($(this).find("cite").text() == "添加博客"){
                        layId = $(this).attr("lay-id");
                    }
                })
                parent.refresh()
                doSave()
                parent.deleteTab(layId)

            },500)
        });
    })
});




















