var click = device.mobile() ? 'touchstart' : 'click';

//iframe高度自适应
function changeFrameHeight(ifm) {
    // document.documentElement.style.overflow='hidden';
	ifm.height = document.documentElement.clientHeight - 115;
}

function resizeFrameHeight() {
    var iframeHeight = document.documentElement.clientHeight - 115;
    $('iframe').each(function(index){
        this.height = iframeHeight;
    })
}

$(window).resize(function() {
    resizeFrameHeight();

});

// ========== 选项卡操作 ==========
$(function() {
  // 选项卡点击
    $(document).on('click', '.page-tabs a', function() {

        $('.page-tabs a').removeClass('active');
        $(this).addClass('active');


        $('.page-content-wrap').removeClass('active');


        $('#iframe_' + $(this).data('index')).addClass('active');


/*        $('#iframe_home').addClass('active');

        var marginLeft = ($('#tabs').css('marginLeft').replace('px', ''));*/

        // 滚动到可视区域:在左侧

    });



    // 选项卡右键菜单
    var menu = new BootstrapMenu('.page-tabs a', {
        fetchElementData: function(item) {
            return item;
        },
        actionsGroups: [
            ['close', 'refresh'],
            ['closeOther', 'closeAll'],
            ['closeRight', 'closeLeft']
        ],
        actions: {
            close: {
                name: '关闭',
                iconClass: 'zmdi zmdi-close',
                onClick: function(item) {
                    Tab.closeTab($(item));
                }
            },
            closeOther: {
                name: '关闭其他',
                iconClass: 'zmdi zmdi-arrow-split',
                onClick: function(item) {
                    var index = $(item).data('index');
                    $('.content_tab li').each(function() {
                        if ($(this).data('index') != index) {
                            Tab.closeTab($(this));
                        }
                    });
                }
            },
            closeAll: {
                name: '关闭全部',
                iconClass: 'zmdi zmdi-swap',
                onClick: function() {
                    $('.content_tab li').each(function() {
                        Tab.closeTab($(this));
                    });
                }
            },
            closeRight: {
                name: '关闭右侧所有',
                iconClass: 'zmdi zmdi-arrow-right',
                onClick: function(item) {
                    var index = $(item).data('index');
                    $($('.content_tab li').toArray().reverse()).each(function() {
                        if ($(this).data('index') != index) {
                            Tab.closeTab($(this));
                        } else {
                            return false;
                        }
                    });
                }
            },
            closeLeft: {
                name: '关闭左侧所有',
                iconClass: 'zmdi zmdi-arrow-left',
                onClick: function(item) {
                    var index = $(item).data('index');
                    $('.content_tab li').each(function() {
                        if ($(this).data('index') != index) {
                            Tab.closeTab($(this));
                        } else {
                            return false;
                        }
                    });
                }
            },
            refresh: {
                name: '刷新',
                iconClass: 'zmdi zmdi-refresh',
                onClick: function(item) {
                    var index = $(item).data('index');
                    var $iframe = $('#iframe_' + index).find('iframe');
                    $iframe.attr('src', $iframe.attr('src'));
                }
            }
        }
    });
   });
// ========== 选项卡操作 ==========
// 选项卡对象
var Tab = {
	addTab: function(title, url) {

		var index = url.replace(/\./g, '_').replace(/\//g, '_').replace(/:/g, '_').replace(/\?/g, '_').replace(/,/g, '_').replace(/=/g, '_').replace(/&/g, '_');
		// 如果存在选项卡，则激活，否则创建新选项卡
		if ($('#tab_' + index).length == 0) {
			// 添加选项卡

			$('.page-tabs a').removeClass('active');
			var tab = '<a id="tab_' + index +'"data-index="' + index + '" class="active"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">' + title + '</a>';
			$('.page-tabs').append(tab);
			// 添加iframe
			$('.page-content-wrap').removeClass('active');
            var o=document.getElementById("content");
            o.style.height="1080px"
            var iframe = '<div id="iframe_' + index + '" class="tab_iframe page-content-wrap active"><iframe src="' + url + '" width="100%" frameborder="0" scrolling="auto"  onload="changeFrameHeight(this)" ></iframe></div>';
			$('.page-content').append(iframe);
            setTimeout('removeStyle()',800);

		} else {
			$('#tab_' + index).trigger('click');
		}
		// 关闭侧边栏
		$('#guide').trigger(click);
	},
	closeTab: function($item) {
		var closeable = $item.data('closeable');
		if (closeable != false) {
			// 如果当前时激活状态则关闭后激活左边选项卡
		if($item.hasClass('active')) {
                    $item.prev().trigger('click');

            }
			// 关闭当前选项卡
			var index = $item.data('index');
            $('#iframe_' + index).remove();
			$item.remove();
		}
/*
		initScrollShow();
*/
	}
}

function removeStyle(){
    $("#content").removeAttr("style");

}

