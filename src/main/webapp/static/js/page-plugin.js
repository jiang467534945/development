window.onload = function(){

    //生成分页
    savePage(getTable());

    //绑定全选
    bindCheckAll();

}

//根据分页参数生成分页
function savePage(list) {
    var _html = "";

    if(list.pageNum > 1){
        _html += "<li><a href=\"javascript:void(0);\" onclick=\"nextPage("+ 1 +")\">首页</a></li>";
        _html += "<li><a href=\"javascript:void(0);\" onclick=\"nextPage("+ (list.pageNum-1) +")\">上一页</a></li>";
    } else {
        _html += "<li class=\"disabled\"><a href=\"javascript:void(0);\">首页</a></li>";
        _html += "<li class=\"disabled\"><a href=\"javascript:void(0);\">上一页</a></li>";
    }


    if(list.totalPage > 10 && (list.pageNum-4) > 1){
        if((list.totalPage-list.pageNum) > 5){
            for(var i=list.pageNum-4;i <= list.pageNum+5;i++){
                if(list.pageNum == i) {
                    _html += "<li class=\"active\"><a href=\"javascript:void(0);\">"+i+"</a></li>";
                } else {
                    _html += "<li><a href=\"javascript:void(0);\" onclick=\"nextPage("+ i +");\">"+i+"</a></li>";
                }
            }
        } else {
            for(var i=list.totalPage-9;i <= list.totalPage;i++){
                if(list.pageNum == i) {
                    _html += "<li class=\"active\"><a href=\"javascript:void(0);\">"+i+"</a></li>";
                } else {
                    _html += "<li><a href=\"javascript:void(0);\" onclick=\"nextPage("+ i +");\">"+i+"</a></li>";
                }
            }
        }
    } else {
        for(var i=1;i <= list.totalPage;i++){
            if(list.pageNum == i) {
                _html += "<li class=\"active\"><a href=\"javascript:void(0);\">"+i+"</a></li>";
            } else {
                _html += "<li><a href=\"javascript:void(0);\" onclick=\"nextPage("+ i +");\">"+i+"</a></li>";
            }
        }
    }

    if(list.pageNum < list.totalPage) {
        _html += "<li><a href=\"javascript:void(0);\" onclick=\"nextPage("+ (list.pageNum+1) +");\">下一页</a></li>";
        _html += "<li><a href=\"javascript:void(0);\" onclick=\"nextPage("+ list.totalPage +")\">尾页</a></li>";
    } else {
        _html += "<li class=\"disabled\"><a href=\"javascript:void(0);\">下一页</a></li>";
        _html += "<li class=\"disabled\"><a href=\"javascript:void(0);\">尾页</a></li>";

    }

    _html += "<li><div class=\"col-md-1\" style=\"width:6%;\">" +
        "<input type=\"text\" id=\"to-page-number\" class=\"form-control\" style=\"width:100%;\" >" +
        "</div></li>";
    _html += "<li><a href=\"javascript:void(0);\" onclick=\"toYourPage(" + list.totalPage + ");\">跳转</a></li>";

    $(".pagination").html(_html);
}

//跳转指定页
function toYourPage(num){
    var toPageNumber = parseInt($("#to-page-number").val());
    if(!isNaN(toPageNumber) && toPageNumber<=num){
        nextPage(toPageNumber);
    } else {
        layer.msg("请输入正确的页数",{time:700});
    }
}

//跳转下一页
function nextPage(num) {
    var tableParam = getParams();
    tableParam.pageNum = num;
    $.ajax({
        url:$("#form").attr("action"),
        type:"post",
        data:tableParam,
        dataType:"html",
        async:true,
        success:function(data){
            $("#form").empty();
            $("#form").html($(data).find("form").html());
            savePage(getTable());
            bindCheckAll();
        }
    });
}

//获取分页参数
function getTable() {
    var table = $("#table");
    var list = {};
    list.pageNum = table.data("pn");
    list.pageSize = table.data("ps");
    list.totalPage = table.data("tp");
    list.totalRecord = table.data("tr");
    return list;
}

//获取当前页数
function getPageNum(){
    return $("#table").data("pn");
}

//根据id获取下面所有值
function getParams(formId){
    var params = {};
    $.each($("#"+formId).serializeArray(),function(index){
        params[this.name] = this.value;
    })
    return params;
}

//获取所有被选中的复选框的值
function getAllCheck(){
    var checkArray = [];
    $("tbody input:checkbox").each(function(index){
        if(this.checked){
            checkArray.push(this.value);
        }
    })
    return checkArray;
}

//绑定全选
function bindCheckAll(){
    $("#check-all").click(function(){
        if(this.checked){
            $("tbody input:checkbox").each(function(index){
                this.checked = true;
            })
        } else {
            $("tbody input:checkbox").each(function(index){
                this.checked = false;
            })
        }
    })
}

//局部刷新当前列表页
function reloadPage(){
    nextPage(getPageNum());
}
