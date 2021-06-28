import React from "react";
import axios from "axios";
import {domain} from "./App";

const buttonStyle = {height: '60px', margin: '10px', padding: '10px', fontsize: '18px'};

class TrData extends React.Component {
    constructor(props) {
        super(props);

    }

    render() {
        return (
            this.props.items.map((item) => {
                const checkType = item.checkType;
                const userAddress = item.userAddress;
                const userCheckTime = item.userCheckTime;
                return (
                    <tr>
                        <td>{sessionStorage.userId}</td>
                        <td>{checkType}</td>
                        <td>{userAddress}</td>
                        <td>{userCheckTime}</td>
                    </tr>
                )
            })
        )
    }
}

class List extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            items: [],
            isLoaded: false
        };
    };

    getAttendanceIntelligenceReports = () => {
        // 获取存储的用户ID
        const userId = sessionStorage.getItem('userId');
        // demo直接构建了要请求的数据，实际开发需要从页面获取
        // 获取用户考勤智能统计信息
        axios.get(domain + '/attendance/intelligence?userId=' + userId + '&fromDate=2021-06-09&toDate=2021-06-25')
            .then(response => {
                // alert(JSON.stringify(response))
                console.log(response)
            })
            .catch(error => {
                // alert(JSON.stringify(error))
                console.log(error.message)
            })
    };

    getAttendanceReports = () => {
        // 获取存储的用户ID
        const userId = sessionStorage.getItem('userId');
        // demo直接构建了要请求的数据，实际开发需要从页面获取
        // 获取用户考勤信息
        axios.get(domain + '/attendance?userId=' + userId + '&workDate=2021-06-25')
            .then(response => {
                this.setState(
                    {items: response.data.data.attendanceResultList, isLoaded: true}
                )
                // console.log(response)
            })
            .catch(error => {
                alert(JSON.stringify(error))
                // console.log(error.message)
            })
    };

    render() {
        return (<div>
            <button style={buttonStyle} onClick={this.getAttendanceReports}>获取用户考勤信息</button>
            <button style={buttonStyle} onClick={this.getAttendanceIntelligenceReports}>获取用户智能考勤统计信息</button>
            <table>
                <thead>
                <tr>
                    <th>用户</th>
                    <th>打卡类型</th>
                    <th>打卡地址</th>
                    <th>打卡时间</th>
                </tr>
                </thead>
                <tbody>

                <TrData items={this.state.items}/>

                </tbody>
            </table>
        </div>)
    }
}

export default List;
