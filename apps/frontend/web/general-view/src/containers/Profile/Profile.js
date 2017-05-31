/**
 * Created by ksb on 2017. 5. 29..
 */
import React, {Component, PropTypes} from 'react';
import {Form, FormControl, FormGroup, InputGroup, DropdownButton, MenuItem, Radio, ControlLabel, HelpBlock, Row, Col, ButtonGroup, Button, Modal, Thumbnail, Badge, Glyphicon } from 'react-bootstrap';

import {connect} from 'react-redux';
import {reduxForm, getValues} from 'redux-form';
import {push as pushState} from 'react-router-redux';

import UserValidation from "./Validate/UserValidation";
import UserAsyncValidate from "./Validate/UserAsyncValidate";
import {update} from 'redux/reducers/auth';

@connect(
    state => ({
        user: state.auth.user
    })
)
export default class ProfileWrapper extends Component {
    static propTypes = {
        user: PropTypes.object
    }

    render(){
        const {user} = this.props;

        return (
            <div>
                <Profile initialValues={user} />
            </div>
        )
    }
}

@reduxForm(
    {
        form: 'Profile',
        fields: ['email', 'password', 'userName'],
        asyncValidate: UserAsyncValidate,
        asyncBlurFields: ['userName'],
        validate: UserValidation
    },
    state => ({
        loading: state.auth.loading,
    }),
    {pushState, update}
)
export class Profile extends Component {
    static propTypes = {}

    render() {
        const {fields: {email, password, userName}, values, handleSubmit, submitting, invalid} = this.props;
        const {loading, pushState, update} = this.props;

        return (
            <Form horizontal onSubmit={handleSubmit}>
                <Row>
                    <Col sm={9}>
                        <FormGroup controlId="email" validationState={email.visited && email.invalid ? 'error' : null}>
                            <Col sm={3}>
                                <ControlLabel>이메일</ControlLabel>
                            </Col>
                            <Col sm={9}>
                                <FormControl type="email" disabled={true} placeholder="이메일" {...email} />
                            </Col>
                        </FormGroup>
                        <FormGroup controlId="password" validationState={password.visited && password.invalid ? 'error' : null}>
                            <Col sm={3}>
                                <ControlLabel>비밀번호</ControlLabel>
                            </Col>
                            <Col sm={9}>
                                <FormControl type="password" placeholder="비밀번호"  {...password} />
                                {password.touched && password.error && <HelpBlock>{password.error}</HelpBlock>}
                            </Col>
                        </FormGroup>
                        <FormGroup controlId="userName" validationState={userName.visited && userName.invalid ? 'error' : null}>
                            <Col sm={3}>
                                <ControlLabel>이름</ControlLabel>
                            </Col>
                            <Col sm={9}>
                                <FormControl type="text" placeholder="이름"  {...userName} />
                                {userName.touched && userName.error && <HelpBlock>{userName.error}</HelpBlock>}
                            </Col>
                        </FormGroup>
                    </Col>
                </Row>

                <ButtonGroup className="pull-right">
                    <Button type="submit" disabled={submitting || invalid || loading}
                            bsStyle="primary" onClick={handleSubmit(() => {
                        update(values).then(() => pushState('/'));
                    })}>
                        저장
                    </Button>
                    <Button disabled={loading} onClick={() => pushState('/')}>
                        취소
                    </Button>
                </ButtonGroup>

            </Form>
        )
    }
}