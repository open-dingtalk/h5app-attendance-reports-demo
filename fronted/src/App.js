import './App.css';
import axios from 'axios';
import * as dd from 'dingtalk-jsapi';
import List from "./List";

//内网穿透工具介绍:
// https://developers.dingtalk.com/document/resourcedownload/http-intranet-penetration?pnamespace=app
// 替换成后端服务域名
export const domain = "";
function App() {
    return (
        <div className="App">
            <List />
        </div>
    );
};


dd.ready(function() {
  // dd.ready参数为回调函数，在环境准备就绪时触发，jsapi的调用需要保证在该回调函数触发后调用，否则无效。
  dd.runtime.permission.requestAuthCode({
    corpId: "ding9f50b15bccd16741", //三方企业ID
    onSuccess: function(result) {
      // alert(JSON.stringify(result));
      axios.get(domain + "/login?authCode=" + result.code)
          .then(response => {
              // alert("success")
              // alert(JSON.stringify(response));
              // alert(JSON.stringify(response.data));
              // alert(JSON.stringify(response.data.result.userid));
              // alert(JSON.stringify(response.data.result.deptIdList[0]));
              // 登录成功后储存用户部门和ID
              sessionStorage.setItem("userId", response.data.data.userid);
              sessionStorage.setItem("deptId", response.data.data.deptIdList[0]);
          })
          .catch(error => {
              alert("error");
            alert(JSON.stringify(error))
            // console.log(error.message)
          });

    },
    onFail : function(err) {
        alert("fail");
      alert(JSON.stringify(err))
    }
  });
});

export default App;
