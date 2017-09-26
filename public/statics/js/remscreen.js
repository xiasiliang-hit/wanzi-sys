/*适应屏幕rem*/
!function(win) {
    function resize() {
        var domWidth = domEle.getBoundingClientRect().width;
        if(domWidth / v > 750){
            domWidth = 750* v;
        }
        win.rem = domWidth / 7.5;
        domEle.style.fontSize = win.rem + "px";

    }
    var v, initial_scale, timeCode, dom = win.document, domEle = dom.documentElement, viewport = dom.querySelector('meta[name="viewport"]'), flexible = dom.querySelector('meta[name="flexible"]');
    if (viewport) {
        //viewport：<meta name="viewport"content="initial-scale=0.5, minimum-scale=0.5, maximum-scale=0.5,user-scalable=no,minimal-ui"/>
        var o = viewport.getAttribute("content").match(/initial\-scale=(["']?)([\d\.]+)\1?/);
        if(o){
            initial_scale = parseFloat(o[2]);
            v = parseInt(1 / initial_scale);
        }
    } else {
        if (flexible) {
            var o = flexible.getAttribute("content").match(/initial\-dpr=(["']?)([\d\.]+)\1?/);
            if(o){
                v = parseFloat(o[2]);
                initial_scale = parseFloat((1 / v).toFixed(2))
            }
        }
    }
    if (!v && !initial_scale) {
        var n = (win.navigator.appVersion.match(/android/gi), win.navigator.appVersion.match(/iphone/gi));
        v = win.devicePixelRatio;
        v = n ? v >= 3 ? 3 : v >= 2 ? 2 : 1 : 1, initial_scale = 1 / v
    }
    //没有viewport标签的情况下
    if (domEle.setAttribute("data-dpr", v), !viewport) {
        if (viewport = dom.createElement("meta"), viewport.setAttribute("name", "viewport"), viewport.setAttribute("content", "initial-scale=" + initial_scale + ", maximum-scale=" + initial_scale + ", minimum-scale=" + initial_scale + ", user-scalable=no"), domEle.firstElementChild) {
            domEle.firstElementChild.appendChild(viewport)
        } else {
            var m = dom.createElement("div");
            m.appendChild(viewport), dom.write(m.innerHTML)
        }
    }
    win.dpr = v;
    win.addEventListener("resize", function() {
        clearTimeout(timeCode), timeCode = setTimeout(resize, 300)
    }, false);
    win.addEventListener("pageshow", function(b) {
        b.persisted && (clearTimeout(timeCode), timeCode = setTimeout(resize, 300))
    }, false);
    /* 个人觉得没必要完成后就把body的字体设置为12
    "complete" === dom.readyState ? dom.body.style.fontSize = 12 * v + "px" : dom.addEventListener("DOMContentLoaded", function() {
        //dom.body.style.fontSize = 12 * v + "px"
    }, false);
    */
    resize();
}(window);

function sendContent(content,type,link,title){
    var timer = null;
    var oDiv = new $('<div>');  //文字少圆角黑色透明提示框
    var oTran = new $('<div>'); //透明白色遮罩层

    var oGray = new $('<div>'); //黑色透明遮罩层
    var oModal = new $('<div>');//白色有title以及按钮的提示框

    var oNote = new $('<div>');//黑色有透明度带对号图片的提示框
 
    type =!type?1:type;
    
    if(type==1){//文字少圆角黑色透明提示框
        oTran.addClass('show_box');
        oDiv.addClass('note_img');
        oDiv.text(content); 
        if(content.length<12){
            oDiv.addClass('note_img1 text-center');
        }   
        $('body').append(oDiv);
        $('body').append(oTran);
        $('.note_img,.show_box').show();
        $('body').on('click',function(){
            clearInterval(timer);
            $('.note_img,.show_box').hide();
        })
    }
    else if(type==2){//白色有title以及按钮的提示框
        oGray.addClass('gray');
        oModal.addClass('modal_box');
        oModal.html('<h4 class="text-center">'+title+'</h4><p>'+content+'</p><p class="sure_btn text-center">确认</p>');
        $('body').append(oGray);
        $('body').append(oModal);
        $('.gray,.modal_box').show();
        $('.sure_btn').live('click',function(){
            $('.gray,.modal_box').remove();
            if(link){
                window.location.href = link;
            }
        })
    }
    else if(type==3){//黑色有透明度带对号图片的提示框
        oTran.addClass('show_box');
        oNote.addClass('note_box');
        oNote.html('<img src="images/note_sure.png" alt=""><p>'+content+'</p>');
        $('body').append(oNote);
        $('body').append(oTran);
        $('.note_box,.show_box').show();
        $('body').on('click',function(){
            clearInterval(timer);
            $('.note_box,.show_box').hide();
        })
    }
    
    timer = setInterval(function(){
        $('.note_img,.show_box,.note_box').remove();
        if(link && type!=2){
            window.location.href = link;
        }
        clearInterval(timer);
    }, 5000)
    
}

