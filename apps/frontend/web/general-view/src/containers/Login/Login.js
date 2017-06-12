/**
 * Created by gwonseongbong on 8/10/16.
 */
import React, {Component} from 'react';
import {Grid, Row, Col, Button, Input, FormGroup, FormControl, ControlLabel, HelpBlock} from 'react-bootstrap';

import {reduxForm} from 'redux-form';
import {push as pushState} from 'react-router-redux';

import {authToken, load, login} from 'redux/reducers/auth';

import LoginValidation from "./Validate/LoginValidation";

@reduxForm(
    {
        form: 'login',
        fields: ['email', 'password'],
        validate: LoginValidation
    },
    state => ({
        loaded: state.auth.loaded,
        user: state.auth.user,
        url: state.auth.url
    }),
    {pushState, authToken, load, login}
)
export default class Login extends Component {
    state = {loginFail: false}

    locationHref(provider){
        // var secUrl = 'http://' + config.secHost + (config.secPort ? ':' + config.secPort : '') + '/auth/' + provider;
        var secUrl = 'http://local.bookstorage.kr:8081/auth/' + provider + '';
        window.LOGIN = this.loginProc;
        window.open(secUrl);
    }

    loginProc = () => {
        const { load, pushState } = this.props;
        load().then((result) => {
                console.log(result);
                pushState('/');
            }
        ).catch((e) => alert('로그인 에러가 발생하였습니다.'));
    }

    loginFail(err){
        const {resetForm} = this.props;
        this.setState({loginFail: true});
        resetForm();
    }

    render() {
        const {fields: {email, password}, handleSubmit, values} = this.props;
        const {pushState, authToken, login} = this.props;

        const loginFailDiv = this.state.loginFail ? {} : {display:"none"};

        return (
            <Grid>
                <h1>로그인</h1>
                <form className="form-horizontal">
                    <Row>
                        <Col xs={12}>
                            <FormGroup controlId="email" validationState={email.visited && email.invalid ? 'error' : null} style={{marginLeft:"0",marginRight:"0"}}>
                                <FormControl type="text" placeholder="이메일을 입력하세요" {...email} />
                            </FormGroup>
                            {email.touched && email.error && <HelpBlock>{email.error}</HelpBlock>}
                        </Col>
                    </Row>
                    <Row>
                        <Col xs={12}>
                            <FormGroup controlId="password" validationState={password.visited && password.invalid ? 'error' : null} style={{marginLeft:"0",marginRight:"0"}}>
                                <FormControl type="password" placeholder="비밀번호를 입력하세요" {...password} />
                            </FormGroup>
                            {password.touched && password.error && <HelpBlock>{password.error}</HelpBlock>}
                        </Col>
                    </Row>
                    <Row style={loginFailDiv}>
                        {loginFailDiv && <Col xs={12}><div className="alert alert-danger" role="alert">로그인에 실패하였습니다.</div></Col>}
                    </Row>
                    <Button bsStyle="primary" block onClick={handleSubmit(() => {
                        authToken().then(result => login(values, result.token)).then(() => {
                            pushState('/');
                        }).catch((err) => this.loginFail(err));
                    })
                    }>로그인</Button>
                    <Row>
                        <Button className="btn-social btn-social-facebook" onClick={() => this.locationHref('facebook')}><i />페이스북 회원가입/로그인</Button>
                    </Row>
                    <Row>
                        <Button className="btn-social btn-social-kakao" onClick={() => this.locationHref('kakao')}><i />카카오 회원가입/로그인</Button>
                    </Row>
                    <Row>
                        <Button className="btn-social btn-social-naver" onClick={() => this.locationHref('naver')}><i />네이버 회원가입/로그인</Button>
                    </Row>
                    <Row>
                        <Button className="btn-social btn-social-google" onClick={() => this.locationHref('google')}><i />구글 회원가입/로그인</Button>
                    </Row>
                    {/*<Row>
                        <Button className="btn-social btn-social-instagram" onClick={() => this.locationHref('instagram')}><i />인스타그램 회원가입/로그인</Button>
                    </Row>*/}
                </form>
            </Grid>
        )
    }
}

export class LoginSuccess extends Component {
    componentDidMount(){
        window.opener.LOGIN();
        window.open('', '_self').close();
    }
    render(){
        return (
            <div></div>
        )
    }
}