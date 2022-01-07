import "./App.css"
import axios from "axios"
import * as dd from "dingtalk-jsapi"
import List from "./List"

//内网穿透工具介绍:
// https://developers.dingtalk.com/document/resourcedownload/http-intranet-penetration?pnamespace=app
// 替换成后端服务域名
export const domain = ""
function App() {
  return (
    <div className="content">
      <div className="header">
        <img
          src="https://img.alicdn.com/imgextra/i3/O1CN01Mpftes1gwqxuL0ZQE_!!6000000004207-2-tps-240-240.png"
          className="headImg"
          alt=""
        />
        钉钉模板
      </div>
      <div className="App">
        <List />
      </div>
    </div>
  )
}

dd.ready(function () {
  // let corpId;
  // axios.get(domain + "/config")
  //     .then(response => {
  //         corpId = response.data.corpId;
  //     })
  //     .catch(error => {
  //         alert(JSON.stringify(error))
  //         // console.log(error.message)
  //     })
  let corpId
  fetch(domain + "/config")
    .then((res) => res.json())
    .then((result) => {
      // alert(JSON.stringify(result));
      corpId = result.data.corpId
      // dd.ready参数为回调函数，在环境准备就绪时触发，jsapi的调用需要保证在该回调函数触发后调用，否则无效。
      dd.runtime.permission.requestAuthCode({
        corpId: corpId, //三方企业ID
        onSuccess: function (result) {
          // alert(JSON.stringify(result));
          axios
            .get(domain + "/login?authCode=" + result.code)
            .then((response) => {
              // alert(JSON.stringify(response));
              // alert(JSON.stringify(response.data));
              // alert(JSON.stringify(response.data.data.userid));
              // alert(JSON.stringify(response.data.data.deptIdList[0]));
              // 登录成功后储存用户部门和ID
              sessionStorage.setItem("userId", response.data.data.userid)
              sessionStorage.setItem("deptId", response.data.data.deptIdList[0])
            })
            .catch((error) => {
              alert(JSON.stringify(error))
              // console.log(error.message)
            })
        },
        onFail: function (err) {
          alert(JSON.stringify(err))
        },
      })
    })
})

export default App
