/**
 * 初始化下拉选择框
 */
function initCodeType() {
    $(".selectpicker").selectpicker({
        noneSelectedText: '--请选择--' //默认显示内容  
    });
    $("select").each(function () {
        var id = $(this).attr("id");
        $.ajax({
            url: "/report/queryCodeType",
            type: "post",
            data: {
                codeType: id
            },
            success: function (data) {
                var typeHtml = '<option value="">--请选择--</option>';
                for (var i = 0; i < data.length; i++) {
                    if(id == 'orgCode'){
                        typeHtml += '<option value="' + data[i].orgCode + '">' + data[i].orgName + '</option>';
                    }else if(id == 'reportSystem'){
                        typeHtml += '<option value="' + data[i].syscode + '">' + data[i].sysname + '</option>';
                    }else if(id == 'department'){
                        typeHtml += '<option value="' + data[i].departmentId + '">' + data[i].departmentName + '</option>';
                    }
                    // else if(id == 'reportModule'
                    //     && ($("#reportSystem").val() == undefined || $("#reportSystem").val() == '')){
                    //     typeHtml += '<option value="' + data[i].flowcode + '">' + data[i].flowname + '</option>';
                    // }
                    else {
                        typeHtml += '<option value="' + data[i].codeId + '">' + data[i].codeName + '</option>';
                    }
                }
                $("#" + id).html(typeHtml);
                $('.selectpicker').selectpicker('refresh');
            },
            error: function () {
                alert("代码错误，请使用debug调试 ！！！");
            }
        })
    })
}

function initModule() {
    // console.log($(tag).attr("id"));
    // console.log($("#reportSystem").val())
    $.ajax({
        url: "/report/queryCodeType",
        type: "post",
        data: {
            codeType: "reportModule",
            code : $("#reportSystem").val()
        },
        success: function (data) {
            var typeHtml = '<option value="">--请选择--</option>';
            console.log(data);
            for (var i = 0; i < data.length; i++) {
                typeHtml += '<option value="' + data[i].flowcode + '">' + data[i].flowname + '</option>';
            }
            $("#reportModule").html(typeHtml);
        },
        error: function () {
            alert("代码错误，请使用debug调试 ！！！");
        }
    })
}

/**
 * json映射
 * @param jsonObj
 */
function josnCopy(jsonObj){
    for (var key in jsonObj) {
        var doms = $("#"+key);
        console.log(jsonObj[key]);
        if(doms.length==0) continue;
        $("#"+key).val(jsonObj[key]);
    }
}



