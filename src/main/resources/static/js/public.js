//初始化消息提醒
var message = new MyMessage.message({
    iconFontSize: "24px", //图标大小,默认为20px
    messageFontSize: "15px", //信息字体大小,默认为12px
    showTime: 3000, //消失时间,默认为3000
    align: "right", //显示的位置类型center,right,left
});
//loading
$('body').append('<div id="loading" style="z-index:9999"><div class="l-bg"></div><div class="l-img"></div><p class="l-message">操作中...</p></div>')

//表格选择多条数据
var $table = $('.checked-table');
$table.on('change', 'thead input[type="checkbox"]', function () {
    $tdCheckbox = $table.find('td input[type="checkbox"]')
    if ($(this).is(':checked')) {
        $tdCheckbox.prop('checked', true);
    } else {
        $tdCheckbox.prop('checked', false);
    }
});
$table.on('change', 'tbody input[type="checkbox"]', function () {
    var $thCheckbox = $table.find('th input[type="checkbox"]'),
        checkboxSum = $table.find('td input[type="checkbox"]').length;
    checkedSum = $table.find('td input[type="checkbox"]:checked').length;
    if (checkedSum < checkboxSum) {
        $thCheckbox.prop('checked', false);
    } else if (checkedSum = checkboxSum) {
        $thCheckbox.prop('checked', true);
    }
});

//loading面板
var loading = {
    show: function (message) {
        if (arguments.length > 0) {
            $('#loading').find('.l-message').text(message);
        }
        $('#loading').fadeIn(300);

    },
    hide: function () {
        $('#loading').fadeOut(300);
    }
}

//上传和删除文件
$('#uf-btn-upload').on('click', function () {
    $('#uf-input-upload').val('');
    $('#uf-input-upload').click();
});
$('#uf-input-upload').on('change', function (e) {
    if (e.target.value.lastIndexOf('.doc') < 0 || e.target.value.lastIndexOf('.docx') < 0) {
        message.add('请选择正确的文件', 'error');
    } else {
        $('#uf-form').submit();
        console.log("上传文件");
        loading.show("上传文件中...");
    }
});
var $uf_f_l = $('.uf-file-list');
//监听frame的load事件判断是否上传成功
$('#uf-frame').on('load', function () {
    var response = $("#uf-frame").contents().find("body").html();
    console.log(response);
    if (response.length > 0) {
        try {
            response = JSON.parse(response);
            var file_item = '<p><a target="_blank" href="/' + response.path + '"> ' + response.name + '</a><a class="file-del" href="javascript:;" data-path="' + response.path + '" data-name="' + response.name + '" data-id="' + response.id + '">[删除]</a></p>'
            $uf_f_l.append(file_item);
            loading.hide();
            message.add("上传文件成功");
        } catch (e) {
            loading.hide();
            message.add("上传文件失败", "error");
        }
    } else {
        loading.hide();
        message.add("上传文件失败", "error");
    }

});
$uf_f_l.on('click', '.file-del', function () {
    var delUrl = $uf_f_l.attr('data-delUrl'),
        fileId = $(this).attr('data-id');
    //code
    $(this).parent("p").remove();
});

/**
 * mid-table表格的添加和删除一行
 * templateId:模板的id
 * $added_dom：追加html的地方
 * delDom:删除的那块的html的标签
 * */
function mid_table_action(templateId, added_dom, delDom, fnBeforeAdd, fnAfterRemove) {
    //添加表格的一行
    $mid_table = $('.mid-table');
    $mid_table.find('#mt-btn-add').on('click', function () {
        if (fnBeforeAdd) {
            var index = fnBeforeAdd();
            var mt_tr_html = template(templateId, {
                index: index
            });
        } else {
            var mt_tr_html = template(templateId);
        }
        $('#' + templateId).parent(added_dom).append(mt_tr_html);
    });
    //删除
    $mid_table.find('#mt-btn-del').on('click', function () {
        var $checkeds = $mid_table.find('tbody input[type="checkbox"]:checked');
        if ($checkeds.length) {
            $checkeds.each(function (index, Element) {
                $(this).parents(delDom).remove();
                if (fnAfterRemove) {
                    fnAfterRemove();
                }
            });
        } else {
            message.add("请先选择一条或多条数据", "warning")
        }

        if (!$mid_table.find('td input[type="checkbox"]').length) {
            $mid_table.find('th input[type="checkbox"]').prop('checked', false);
        }
    });
}


//宣传事务统计页面的操作
/*
 * 使用的页面
 * 宣传物资领用申请统计
 * 宣传品(资料)制作统计
 * 宣传物资领用申请统计
 * */
$("#btn-reset").on('click', function () {
    var $form = $('.form-inline');
    for (var i = 0, l = $form.length; i < l; i++) {
        $form[i].reset()
    }
});

function setBtnQuery(fn) {
    $('#btn-query').on('click', function () {
        fn({
            startDate: $('input[name="startDate"]').val(),
            endDate: $('input[name="endDate"]').val(),
            way: $('input[name="way"]').val(),
            sortWay: $('input[name="sortWay"]').val()
        })
    });
}


/*
 * 表单验证*/
var fv = {
    isNull: function (dom, msg) {
        /*
         *验证value是否为空字符串
         *dom:inout的jquery对象
         * msg：提示信息
         * */
        if (dom.val() == '') {
            dom.parent('.form-group').addClass('has-error')
            message.add('请输入' + msg, 'error');
            return false;
        } else {
            return true;
        }
    },
    init: function (dom, msg) {
        /*
         *提示某个input的值是否未填
         *dom:inout的jquery对象
         * msg：提示信息
         * */
        dom.on('blur', function () {
            if ($(this).val() != '') {
                if ($(this).parent('.form-group').hasClass('has-error')) {
                    $(this).parent('.form-group').removeClass('has-error');
                }
            } else {
                $("#title").parent('.form-group').addClass('has-error');
                message.add('请输入' + msg, 'error');
            }
        });
        dom.on('focus', function () {
            if ($(this).parent('.form-group').hasClass('has-error')) {
                $(this).parent('.form-group').removeClass('has-error');
            }
        });
    },
}