import React from "react"
import axios from "axios"
import { Button, Table } from "antd"
import { domain } from "./App"
import moment from "moment"

const buttonStyle = {
  height: "60px",
  margin: "10px",
  padding: "10px",
  fontsize: "18px",
}
const date7ago = moment().subtract(7, "days").format("YYYY-MM-DD")
// class TrData extends React.Component {
//   constructor(props) {
//     super(props)
//   }

//   render() {
//     return this.props.items.map((item) => {
//       const checkType = item.checkType
//       const userAddress = item.userAddress
//       const userCheckTime = item.userCheckTime
//       return (
//         <tr>
//           <td>{sessionStorage.userId}</td>
//           <td>{checkType}</td>
//           <td>{userAddress}</td>
//           <td>{userCheckTime}</td>
//         </tr>
//       )
//     })
//   }
// }

const columns = [
  {
    title: "用户",
    dataIndex: "userId",
    key: "userId",
    render: (text) => sessionStorage.userId,
  },
  {
    title: "打卡类型",
    dataIndex: "checkType",
    key: "checkType",
  },
  {
    title: "打卡地址",
    dataIndex: "userAddress",
    key: "userAddress",
  },
  {
    title: "打卡时间",
    dataIndex: "userCheckTime",
    key: "userCheckTime",
  },
]
const columnList = [
  {
    title: "id",
    dataIndex: "id",
    key: "id",
  },
  {
    title: "名称",
    dataIndex: "name",
    key: "name",
  },

  {
    title: "详细记录",
    dataIndex: "columnVals",
    key: "columnVals",
    render: (columnVals) => {
      return columnVals.map((item) => {
        return (
          <p>
            日期：{moment(item.date).format("YYYY-MM-DD HH:MM:SS")} --{" "}
            {item.value}
          </p>
        )
      })
    },
  },
]

class List extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      items: null,
      ListItem: null,
      isLoaded: false,
    }
  }

  getAttendanceIntelligenceReports = () => {
    // 获取存储的用户ID
    const userId = sessionStorage.getItem("userId")
    // demo直接构建了要请求的数据，实际开发需要从页面获取

    this.setState({
      ...this.state,
      items: null,
    })
    // 获取用户考勤智能统计信息
    axios
      .get(
        domain +
          "/attendance/intelligence?userId=" +
          userId +
          "&fromDate=" +
          date7ago +
          "&toDate=" +
          moment().format("YYYY-MM-DD")
      )
      .then((response) => {
        this.setState({
          ...this.state,
          ListItem: response.data.data,
        })
      })
      .catch((error) => {
        // alert(JSON.stringify(error))
        console.log(error.message)
      })
  }

  getAttendanceReports = () => {
    this.setState({
      ...this.state,
      ListItem: null,
    })
    // 获取存储的用户ID
    const userId = sessionStorage.getItem("userId")
    // demo直接构建了要请求的数据，实际开发需要从页面获取
    // 获取用户考勤信息
    axios
      .get(domain + "/attendance?userId=" + userId + "&workDate=" + date7ago)
      .then((response) => {
        // alert(response.data.data.attendanceResultList,'this.state.items')
        this.setState({
          items: response.data.data.attendanceResultList||[],
          isLoaded: true,
        })
        // console.log(response)
      })
      .catch((error) => {
        alert(JSON.stringify(error))
        // console.log(error.message)
      })
  }

  render() {
    return (
      <div className="btn">
        <p>
          <Button onClick={this.getAttendanceReports} type="primary">
            获取7天内用户考勤信息
          </Button>
        </p>
        <p>
          <Button
            type="primary"
            onClick={this.getAttendanceIntelligenceReports}
          >
            获取用户智能考勤统计信息
          </Button>
        </p>
        {this.state.items?.length > 0 && (
          <Table
            columns={columns}
            dataSource={this.state.items}
            style={{ overflowX: "scroll" }}
          />
        )}

        {this.state.items?.length === 0 && (
            <div>暂无7天内考勤信息，试试在钉钉考勤打卡吧</div>
        )}
        {this.state.ListItem?.length > 0 && (
          <Table
            columns={columnList}
            dataSource={this.state.ListItem}
            style={{ overflowX: "scroll" }}
          />
        )}
        {this.state.ListItem?.length === 0 && (
            <div>暂无智能考勤统计数据，试试在oa后台创建考勤报表吧</div>
        )}
        {/* <table>
          <thead>
            <tr>
              <th>用户</th>
              <th>打卡类型</th>
              <th>打卡地址</th>
              <th>打卡时间</th>
            </tr>
          </thead>
          <tbody>
            <TrData items={this.state.items} />
          </tbody>
        </table> */}
      </div>
    )
  }
}

export default List
