(this.webpackJsonpfrontend=this.webpackJsonpfrontend||[]).push([[0],{275:function(t,e,n){"use strict";n.r(e);var c=n(4),s=n.n(c),i=n(31),r=n.n(i),a=(n(36),n(37),n(7)),o=n.n(a),d=n(13),u=n(14),h=n(15),j=n(17),l=n(16),b=n(2),f={height:"60px",margin:"10px",padding:"10px",fontsize:"18px"},g=function(t){Object(j.a)(n,t);var e=Object(l.a)(n);function n(t){return Object(u.a)(this,n),e.call(this,t)}return Object(h.a)(n,[{key:"render",value:function(){return this.props.items.map((function(t){var e=t.checkType,n=t.userAddress,c=t.userCheckTime;return Object(b.jsxs)("tr",{children:[Object(b.jsx)("td",{children:sessionStorage.userId}),Object(b.jsx)("td",{children:e}),Object(b.jsx)("td",{children:n}),Object(b.jsx)("td",{children:c})]})}))}}]),n}(s.a.Component),O=function(t){Object(j.a)(n,t);var e=Object(l.a)(n);function n(t){var c;return Object(u.a)(this,n),(c=e.call(this,t)).getAttendanceIntelligenceReports=function(){var t=sessionStorage.getItem("userId");o.a.get(p+"/attendance/intelligence?userId="+t+"&fromDate=2021-06-09&toDate=2021-06-25").then((function(t){console.log(t)})).catch((function(t){console.log(t.message)}))},c.getAttendanceReports=function(){var t=sessionStorage.getItem("userId");o.a.get(p+"/attendance?userId="+t+"&workDate=2021-06-25").then((function(t){c.setState({items:t.data.data.attendanceResultList,isLoaded:!0})})).catch((function(t){alert(JSON.stringify(t))}))},c.state={items:[],isLoaded:!1},c}return Object(h.a)(n,[{key:"render",value:function(){return Object(b.jsxs)("div",{children:[Object(b.jsx)("button",{style:f,onClick:this.getAttendanceReports,children:"\u83b7\u53d6\u7528\u6237\u8003\u52e4\u4fe1\u606f"}),Object(b.jsx)("button",{style:f,onClick:this.getAttendanceIntelligenceReports,children:"\u83b7\u53d6\u7528\u6237\u667a\u80fd\u8003\u52e4\u7edf\u8ba1\u4fe1\u606f"}),Object(b.jsxs)("table",{children:[Object(b.jsx)("thead",{children:Object(b.jsxs)("tr",{children:[Object(b.jsx)("th",{children:"\u7528\u6237"}),Object(b.jsx)("th",{children:"\u6253\u5361\u7c7b\u578b"}),Object(b.jsx)("th",{children:"\u6253\u5361\u5730\u5740"}),Object(b.jsx)("th",{children:"\u6253\u5361\u65f6\u95f4"})]})}),Object(b.jsx)("tbody",{children:Object(b.jsx)(g,{items:this.state.items})})]})]})}}]),n}(s.a.Component),p="";d.ready((function(){var t;fetch(p+"/config").then((function(t){return t.json()})).then((function(e){t=e.data.corpId,d.runtime.permission.requestAuthCode({corpId:t,onSuccess:function(t){o.a.get(p+"/login?authCode="+t.code).then((function(t){sessionStorage.setItem("userId",t.data.data.userid),sessionStorage.setItem("deptId",t.data.data.deptIdList[0])})).catch((function(t){alert(JSON.stringify(t))}))},onFail:function(t){alert(JSON.stringify(t))}})}))}));var x=function(){return Object(b.jsx)("div",{className:"App",children:Object(b.jsx)(O,{})})},m=function(t){t&&t instanceof Function&&n.e(3).then(n.bind(null,276)).then((function(e){var n=e.getCLS,c=e.getFID,s=e.getFCP,i=e.getLCP,r=e.getTTFB;n(t),c(t),s(t),i(t),r(t)}))};r.a.render(Object(b.jsx)(s.a.StrictMode,{children:Object(b.jsx)(x,{})}),document.getElementById("root")),m()},36:function(t,e,n){},37:function(t,e,n){}},[[275,1,2]]]);
//# sourceMappingURL=main.f0684ff8.chunk.js.map