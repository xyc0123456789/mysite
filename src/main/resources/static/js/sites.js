var scrolltemplate=`
    <div :id="id" class="mt-5">
        <h5>{{title}}</h5>
        <div style="position: relative;overflow: hidden" :style="pstyle" id="scrollarea">
            <div class="scon" style="position: absolute">
            <p v-html="contents"></p>
            </div>
        </div>
    </div>`;
Vue.component("scroll",{
    props:["id","title","contents","pstyle"],
    data:function(){return {timer:0}},
    template:scrolltemplate,
    mounted:function () {
        var p = $("#"+this.id).find("#scrollarea");
        var h = $(p).height();
        var s = $(p).find(".scon");
        $(s).css("top",h);
        var currentY = h;
        this.timer=setInterval(function () {
            $(s).css("top",currentY-2);
            currentY-=2;
            if (currentY<-$(s).height()-20){
                currentY=h;
            }
        },80);
    },
    destroyed:function () {
        clearInterval(this.timer)
    }
});


var slidetemplate=`
    <div :id="id" class="carousel slide" data-ride="carousel" :height="imgh">
        <div class="carousel-inner" :height="imgh">
            <div class="item" :class="idx==0?'active':''" v-for="(s,idx) in slides" style="height: 200px;width: 1170px">
                <img :src="s.timg"  class="d-block w-100" :alt="s.title" style="height: 200px;width: 1170px">
            </div>
        </div>
    </div>`;
Vue.component("siteSlide",{
    props: ['id','slides','imgh','imgw'],
    template:slidetemplate
});

var menutemplate=`
    <nav class="navbar navbar-expand-lg navbar-light">

        <button class="navbar-toggler" type="button" data-toggle="collapse"
                :data-target="'#'+id" :aria-controls="id"
                aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" :id="id" style="height: 10px">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item" :class="(idx==0?' active':'')+(m.sublists?(m.sublists.length>0?' dropdown':''):'')" v-for="(m,idx) in menus">
                    <a class="nav-link" :class="(m.sublists?(m.sublists.length>0?' dropdown-toggle':''):'')"
                            :href="m.href" :id="'navbardropdown_'+idx" :data-toggle="(m.sublists?(m.sublists.length>0?'dropdown':''):'')">
                            <img src="/images/2.jpg" style="width: 10%;height: 10px">&nbsp;{{m.name}}</a>
                    <div class="dropdown-menu" v-if="(m.sublists?(m.sublists.length>0):false)">
                        <a class="dropdown-item" v-for="ms in m.sublists" :href="ms.href">{{ms.name}}</a>
                    </div>
                </li>
            </ul>
            <a href="#" class="ml-3"><img src="/images/1.jpg" style="height: 20px"></a>
        </div>
    </nav>`;
Vue.component("navMenu",{
    props:['id','menus'],
    template:menutemplate
});



var vm=new Vue({
    el:"#vueroot",
    mounted:function(){
        $this=this;
        $.get("/c/scroll").done(function (scrolls) {
            var s="";
            for (var b of scrolls){
                s = "<h6>"+b.title+"</h6>";
                s+="<img src='"+b.timg +"' height='100'>"
                s+="<p>"+b.contents+"</p>";
            }
            $this.contents=s;
        });
        $.get("/c/slide").done(function (slides) {
            $this.slides=slides;
        });
        $.get("/c/gg").done(function (gg) {
            $this.gg=gg;
        })
        $.get("/c/con").done(function (con) {
            $this.homecon=con;
        })
        $.get("/c/site").done(function (site) {
            $this.sites=site;//{"logo",{},"copyright",{}}
        })
    },
    data:{
        menus:[{mnid: 1, name:'Home', href :'/' , sublists: []},
            {mid:2, name:' About Suncom', href :'/c/detail/home/1' , sublists: []},
            {mid:3, name:'Events' , href :'Link Address', sublists: []},
            {mid: 4, name:'Products', href:' Link Address', sublists: [
                    {mid:7, name:'VSAT/Earth Station' , href:'Link Address' , sublists:[]},
                    {mid:8, name:' Antenna TVRO Anterna' , href:' Link: Address' , sublists: []},
                    {mid:9, name:'Mobile Satellite Antenna', href:'Link: Address', sublists: []},
                    {mid: 10, name:'Waveguide' , href :' Link Address', sublists: []},
                    {mid:11, name:'Associated Antenna', href :'Link address' , sublists:[]}
                ]},
            {mid:5, name:' Solutions', href :'Link Address', sublists: []},
            {mid:6, name:' Contact Us' , href:'Link Address', sublists: []}
        ],
        slides:[
            {timg:"images/night1.jpg",title:""},
            {timg:"images/night2.jpg",title:""},
            {timg:"images/night3.jpg",title:""},
            {timg:"images/night4.jpg",title:""},
            {timg:"images/night5.jpg",title:""},
        ],
        homecon:[],
        contents:"这里是测试的滚动内容",
        sites:{},
        gg:{title:'',contents:''},
        pstyle:{height:"50px"}
    }
});