var imgSrc = []; //图片路径
var imgFile = []; //文件流
var imgName = []; //图片名字
//选择图片
function imgUpload(obj) {
	var oInput = '#' + obj.inputId;
	var imgBox = '#' + obj.imgBox;
	var btn = '#' + obj.buttonId;
	$(oInput).on("change", function() {
		var fileImg = $(oInput)[0];
		var fileList = fileImg.files;
		for(var i = 0; i < fileList.length; i++) {
			addOneContent(fileList[i],imgBox);
        }
	})
	$(btn).on('click', function() {
		var data = new Object;
		data[obj.data] = imgFile;
        var imgObjData = getImgData(imgBox);
		submitPicture(obj.upUrl, imgObjData,0);
	})
}

function addOneContent(file,obj){
    $(imgBox).html("");
    var reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = function (e) {
        var oldBox = $(obj).html();
        $(obj).html(oldBox + '<div class="imgContainer"><img title=' + file.name + ' alt=' + file.name + ' size=' + file.size + ' src=' + this.result + ' onclick="imgDisplay(this)"><p onclick="removePicture(this);" class="imgDelete">删除</p></div>');
    }
}

function removePicture(obj){
	$(obj).parent().remove();
}

//图片展示
function addNewContent(obj) {
	$(imgBox).html("");
	for(var a = 0; a < imgFile.length; a++) {
		var oldBox = $(obj).html();
		$(obj).html(oldBox + '<div class="imgContainer"><img title=' + imgFile[a].name + ' alt=' + imgFile[a].name + ' src=' + imgFile[a].src + ' onclick="imgDisplay(this)"><p onclick="removeImg(this,' + a + ')" class="imgDelete">删除</p></div>');
	}
}

//删除
function removeImg(obj, index) {
	imgSrc.splice(index, 1);
	imgFile.splice(index, 1);
	imgName.splice(index, 1);
	var boxId = "#" + $(obj).parent('.imgContainer').parent().attr("id");
	addNewContent(boxId);
}

function getImgData(obj) {
	var objparam = [];
    $(obj).find("img").each(function(index){
        var imgData = {};
        var imgObj = $(this)[0];
        imgData.name = imgObj.title;
        imgData.size = $(imgObj).attr("size");
        imgData.src = imgObj.src;
        objparam.push(imgData);
    })
	return objparam;
}

//上传(将文件流数组传到后台)
function submitPicture(url,imgObjData,number) {
	// var dataObj = imgObjData[num];
        $.ajax({
            type: "post",
            url: url,
            async: false,
            data: imgObjData[number],
            traditional: true,
            success: function(data) {
            	if(data.success){
                    number++;
                    if(number < imgObjData.length){
                        submitPicture(url,imgObjData,number)
                    } else {
                        var msg="上传完成,共上传"+imgObjData.length+"条，上传成功"+number+"条";
                    	layer.msg(msg,{icon: 1,time:3000},function () {
                            layer.close(top.layer_userAdd);
                            reloadPage();
                        });
                    }
				}else{
                    if(data.code==1){ //删除失败
                        var msg="上传完成,共上传"+imgObjData.length+"条，上传成功"+number+"条,上传失败"+(imgObjData.length-number)+"条，原因:数据异常";
                        layer.msg(msg,{icon: 2,time:1000},function () {
                            layer.close(top.layer_userAdd);
                            reloadPage();
                        });
                    }
                    if(data.code==2){
                        var msg1="上传完成,共上传"+imgObjData.length+"条，上传成功"+number+"条,上传失败"+(imgObjData.length-number)+"条，原因:可用空间不足";
                        console.info("共计："+imgObjData.length+"---"+typeof(imgObjData.length));
                        console.info("成功："+number+"----"+typeof(number));

                        console.info("失败："+((imgObjData.length)-number));
                        layer.msg(msg1,{icon: 0,time:3000},function () {
                            layer.close(top.layer_userAdd);
                            reloadPage();
                        });
                    }
                }

				if(data.code==1){
                    layer.close(top.layer_userAdd);
                    reloadPage();
                    return new Tip(data.code,data.msg);
                }
            }
        });
	}
//图片灯箱 图片放大
function imgDisplay(obj) {
    console.info(obj);
	var src = $(obj).attr("src");
	console.info(src);
	var imgHtml = '<div style="width: 100%;height: 100vh;overflow: auto;background: rgba(0,0,0,0.5);text-align: center;position: fixed;top: 0;left: 0;z-index: 100000000;"><img src=' + src + ' style="margin-top: 100px;width: 70%;margin-bottom: 100px;"/><p style="font-size: 50px;position: fixed;top: 30px;right: 30px;color: white;cursor: pointer;" onclick="closePicture(this)">×</p></div>'
	$('body').append(imgHtml);
}
//关闭
function closePicture(obj) {
	$(obj).parent("div").remove();
}

//图片预览路径
function getObjectURL(file) {
    console.info(file);
	var url = null;
	if(window.createObjectURL != undefined) { // basic
		url = window.createObjectURL(file);
	} else if(window.URL != undefined) { // mozilla(firefox)
		url = window.URL.createObjectURL(file);
	} else if(window.webkitURL != undefined) { // webkit or chrome
		url = window.webkitURL.createObjectURL(file);
	}
	return url;
}