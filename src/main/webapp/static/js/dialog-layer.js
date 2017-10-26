var LAYER = {
    addLay:function (id,title,url) {
        $.ajax({
            type:'get',
            dataType:'html',
            url:url,
            success:function (data) {
                top[id] = layer.open({
                    type: 1,
                    title: title,
                    maxmin: true,
                    shadeClose: true, //点击遮罩关闭层
                    area : ['800px' , '520px'],
                    content: data
                });
            },
            error:function (data) {
                alert("没有找到此页面");

            }
        })
    },
    addGenLay:function (id,title,url,width,height) {
        $.ajax({
                type:'get',
                dataType:'html',
                url:url,
                success:function (data) {
                    top[id]= layer.open({
                        type: 1,
                        title: title,
                        maxmin: true,
                        shadeClose: true, //点击遮罩关闭层
                        area : [width , height],
                        content: data,
                        btn: ['保存'],
                        yes: function(index, layero){
                            $.ajax({
                                url:'/gen/save',
                                data:$("#genForm").serialize(),
                                type:'post',
                                dataType:'json',
                                success:function (data) {
                                    if(data=="1"){
                                        alert("保存成功");
                                    }
                                    else{ alert("保存失败");
                                    }
                                },error:function () {

                                }


                            })
                            layer.close(index); //如果设定了yes回调，需进行手工关闭
                        }
                    });
                },
                error:function (data) {
                    alert("没有找到此页面");

                }
            }
        )
    },
    addMenuIframe:function (title,url,width,height) {
        layer.open({
            type: 2,
            title: title,
            maxmin: true,
            shadeClose: true, //点击遮罩关闭层
            area : [width , height],
            content: url,
            btn: ['选择'],
            yes: function(index, layero){
                var res = window["layui-layer-iframe" + index].callbackdata();
                $("#menuId").val(res.id), $("#menuName1").val(res.menuName);
                layer.close(index); //如果设定了yes回调，需进行手工关闭
            }
        });
    },
    addOrgMenuIframe:function (id,title,url,width,height) {
        top[id] = layer.open({
            type: 2,
            title: title,
            maxmin: true,
            shadeClose: true, //点击遮罩关闭层
            area : [width , height],
            content: url,
            btn: ['选择'],
            yes: function(index, layero){
               var res= window["layui-layer-iframe" + index].getMyChecked();
                $.ajax({
                    url:'/orgMenu/authSave',
                    dataType:'json',
                    data:res,
                    type:'post',
                    async:false,
                    success: function (data) {
                        layer.close(index); //如果设定了yes回调，需进行手工关闭
                        if (data.success){
                            layer.msg(data.msg,{icon:1,time:1000});
                        }else{
                            layer.msg(data.msg,{icon: 2,time:1000});
                        }
                    },
                    error : function () {
                        layer.msg("获取失败",{icon:2,time:1000});
                    }
                })
            }
        });
    },
    addIframe:function (title,url) {
        layer.open({
            type: 2,
            title: title,
            maxmin: true,
            shadeClose: true, //点击遮罩关闭层
            area : ['800px' , '520px'],
            content: url
        });
    },
    addIframeWithConfirm:function (title,url,width,height,_confirm) {
        layer.open({
            type: 2,
            title: title,
            maxmin: false,
            shadeClose: true, //点击遮罩关闭层
            resize: false,
            area : [width , height],
            btn: ['确定','取消'],
            content: url,
            yes:_confirm,
            btn2:function(index, layero){}
        });
    },
    addLayerWithSize:function(id,title,url,width,height){
        $.ajax({
            url:url,
            type:"GET",
            dataType:"HTML",
            success:function(data){
                top[id] = layer.open({
                    type: 1,
                    title: title,
                    maxmin: true,
                    shadeClose: true, //点击遮罩关闭层
                    resize: false,
                    area : [width , height],
                    content: data
                });
            }
        });
    },
    addLayerWithConfirm:function(id,title,url,width,height,_confirm){
        $.ajax({
            url:url,
            type:"GET",
            dataType:"HTML",
            success:function(data){
                top[id] = layer.open({
                    type: 1,
                    title: title,
                    maxmin: true,
                    shadeClose: true, //点击遮罩关闭层
                    resize: false,
                    area : [width , height],
                    content: data,
                    btn: ['确定','取消'],
                    yes:_confirm,
                    btn2:function(index, layero){}
                });
            }
        });
    }

}