/**
 * Created by ksb on 17. 2. 5.
 */
import React, {PropTypes, Component} from 'react';
import {Grid} from 'react-bootstrap';
var Socket = require('sockjs-client');
var Stomp = require('stompjs');

let socket;
let stompClient;

export default class BookChat extends Component {
    // static propTypes = {
    //     children: PropTypes.object.isRequired
    // }

    componentDidMount() {
        // const {socketUrl, downloadImage, setStompClient, setLoading, removeAll, setId} = this.props;

        const socketUrl = "http://localhost:8082/websocket";

        const stompConnect = () => {
            socket = new Socket(socketUrl);
            stompClient = Stomp.over(socket);
            stompClient.debug = null;
            stompClient.connect({}, function (frame) {
                console.log('Connected: ' + frame);
                const url = '/talk/room.list';
                stompClient.subscribe(url, function (response) {
                    // const url = 'http://' + config.apiHost + ':' + config.apiPort + '/image/download';
                    const responseBody = JSON.parse(response.body);
                    console.log(responseBody);
                });
            }, stompReconnect);

            // setStompClient(stompClient);
        }

        const stompReconnect = () => {
            setTimeout(stompConnect, 1000);
        }

        stompConnect();
    }

    test1 = () => {
        stompClient.send("/app/talk/room.find", {}, JSON.stringify({}));
    }

    test2 = () => {
        stompClient.send("/app/talk/room.insert", {}, JSON.stringify({}));
    }

    test3 = () => {
        stompClient.send("/app/talk/room.update", {}, JSON.stringify({}));
    }

    test4 = () => {
        stompClient.send("/app/talk/room.delete", {}, JSON.stringify({}));
    }

    render() {
        return (
            <div className="l-main-container">
                <h1>대화</h1>
                <div className="l-description">
                    <p>
                        채팅
                    </p>
                </div>
                <div className="l-main-content">
                    <button onClick={this.test1}>목록</button>
                    <button onClick={this.test2}>추가</button>
                    <button onClick={this.test3}>수정</button>
                    <button onClick={this.test4}>삭제</button>
                </div>
            </div>
        )
    }
}