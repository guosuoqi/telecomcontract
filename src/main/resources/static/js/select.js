/**
 * 初始化下拉选择框
 */
function initCodeType(type) {
    var roomId=$("#typeHidIdOne").val();
    var towerId=$("#typeHidIdTwo").val();
    var contractId=$("#typeHidIdThree").val();
    $(".selectpicker").selectpicker({
        noneSelectedText: '--请选择--' //默认显示内容  
    });
    $.ajax({
        url: "/contract/queryType",
        type: "get",
        success: function (data) {
            var typeHtmlcontract = '<option value="-1">--请选择--</option>';
            var typeHtmlRoom = '<option value="-1">--请选择--</option>';
            var typeHtml = '<option value="-1">--请选择--</option>';
            for (var i = 0; i < data.length; i++) {
                if(data[i].codeType =='contract'){
                    if(contractId==data[i].codeId){
                        typeHtmlcontract += '<option value="' + data[i].codeId + '" selected>' + data[i].codtypeHidIdOneeName + '</option>';
                    }else {
                        typeHtmlcontract += '<option value="' + data[i].codeId + '">' + data[i].codeName + '</option>';
                    }
                }else if(data[i].codeType =='room'){
                    if (roomId==data[i].codeId){
                        typeHtmlRoom += '<option value="' + data[i].codeId + '" selected>' + data[i].codeName + '</option>';
                    } else {
                        typeHtmlRoom += '<option value="' + data[i].codeId + '">' + data[i].codeName + '</option>';
                    }

                }
                // else if(id == 'reportModule'
                //     && ($("#reportSystem").val() == undefined || $("#reportSystem").val() == '')){
                //     typeHtml += '<option value="' + data[i].flowcode + '">' + data[i].flowname + '</option>';
                // }
                else {
                    if(towerId==data[i].codeId){
                        typeHtml += '<option  value="' + data[i].codeId + '" selected>' + data[i].codeName + '</option>';
                    }else {
                        typeHtml += '<option  value="' + data[i].codeId + '">' + data[i].codeName + '</option>';
                    }

                }
            }
            if(type==1){
                $("#roomType").html(typeHtmlRoom);
                $("#towerType").html(typeHtmlcontract);
                $("#contractType").html(typeHtml);
            }else if (type==2) {
                $("#room").html(typeHtmlRoom);
                $("#contract").html(typeHtmlcontract);
                $("#tower").html(typeHtml);
            }
            else{
                $("#roomUp").html(typeHtmlRoom);
                $("#contractUp").html(typeHtmlcontract);
                $("#towerUp").html(typeHtml);
            }
            $('.selectpicker').selectpicker('refresh');
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



