layui.use(['jquery', 'layer', 'util'], function () {
    var $ = layui.jquery,
        layer = layui.layer,
        util = layui.util;
    util.fixbar();
    //导航控制
    master.start($);
});
var slider = 0;
var pathname = window.location.pathname;

var master = {};
master.start = function ($) {
    $('#nav li').hover(function () {
        $(this).addClass('current');
    }, function () {
        var href = $(this).find('a').attr("href");
        //alert(href)
        if (href !== pathname) {
            $(this).removeClass('current');
        }
    });
    selectNav();

    function selectNav() {
        var navobjs = $("#nav a");
        $.each(navobjs, function () {
            var href = $(this).attr("href");
            if (href === pathname || (href === '/' && pathname.substring(0, 5) === '/blog')) {
                $(this).parent().addClass('current');
            }
        });
    };
    $('.phone-menu').on('click', function () {
        $('#nav').toggle(500);
    });
    $(".blog-user img").hover(function () {
        var tips = layer.tips('点击退出', '.blog-user', {
            tips: [3, '#009688'],
        });
    }, function () {
        layer.closeAll('tips');
    })
};
