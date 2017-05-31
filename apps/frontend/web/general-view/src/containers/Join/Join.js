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
import {create} from 'redux/reducers/auth';

@reduxForm(
    {
        form: 'Join',
        fields: ['email', 'password', 'name'],
        asyncValidate: UserAsyncValidate,
        asyncBlurFields: ['email', 'name'],
        validate: UserValidation
    },
    state => ({
        loading: state.auth.loading,
    }),
    {pushState, create}
)
export default class Join extends Component {
    static propTypes = {}

    render() {
        const {fields: {email, password, name}, values, handleSubmit, submitting, invalid} = this.props;
        const {loading, pushState, create} = this.props;

        return (
            <Form horizontal onSubmit={handleSubmit}>
                <Row>
                    <Col sm={9}>
                        <FormGroup controlId="email" validationState={email.visited && email.invalid ? 'error' : null}>
                            <Col sm={3}>
                                <ControlLabel>이메일</ControlLabel>
                            </Col>
                            <Col sm={9}>
                                <FormControl type="email" placeholder="이메일" {...email} />
                                {email.touched && email.error && <HelpBlock>{email.error}</HelpBlock>}
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
                        <FormGroup controlId="name" validationState={name.visited && name.invalid ? 'error' : null}>
                            <Col sm={3}>
                                <ControlLabel>이름</ControlLabel>
                            </Col>
                            <Col sm={9}>
                                <FormControl type="text" placeholder="이름"  {...name} />
                                {name.touched && name.error && <HelpBlock>{name.error}</HelpBlock>}
                            </Col>
                        </FormGroup>
                    </Col>
                </Row>

                <ButtonGroup className="pull-right">
                    <Button type="submit" disabled={submitting || invalid || loading}
                            bsStyle="primary" onClick={handleSubmit(() => {
                            create(values).then(() => pushState('/login'));
                    })}>
                        저장
                    </Button>
                    <Button disabled={loading} onClick={() => pushState('/login')}>
                        취소
                    </Button>
                </ButtonGroup>

            </Form>
        )
    }
}