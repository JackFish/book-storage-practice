/**
 * Created by ksb on 2017. 5. 29..
 */
import React, {Component, PropTypes} from 'react';
import {Form, FormControl, FormGroup, InputGroup, DropdownButton, MenuItem, Radio, ControlLabel, HelpBlock, Row, Col, ButtonGroup, Button, Modal, Thumbnail, Badge, Glyphicon } from 'react-bootstrap';

import {connect} from 'react-redux';
import {reduxForm, getValues} from 'redux-form';
import {asyncConnect} from 'redux-connect';
import {push as pushState} from 'react-router-redux';

import LawyerFormValidation from "./LawyerFormValidation";
import LawyerFormAsyncValidate from "./LawyerFormAsyncValidate";
// import {reset, findOne, create, update, setSortLawyerLawFirm, loadWorkStateTypeCode} from 'redux/reducers/lawyer';

@asyncConnect([
    {
        promise: ({store : {dispatch, getState}, params}) => {
            const promises = [];

            promises.push(dispatch(loadWorkStateTypeCode()));

            if(params.idx){
                promises.push(dispatch(findOne(params.idx)));
            } else {
                promises.push(dispatch(reset()));
            }

            return Promise.all(promises);
        }
    }
])
@connect(
    state => ({
        lawyer: state.lawyer.lawyer
    })
)
export default class LawyerFormWrapper extends Component {
    static propTypes = {
        lawyer: PropTypes.object
    }

    render(){
        const {lawyer, params: {idx}} = this.props;
        const formType = idx ? 'update' : 'create';

        return (
            <div>
                <LawyerForm initialValues={lawyer} formType={formType} />
            </div>
        )
    }
}

@sortable
class SortItem extends React.Component {
    render() {
        return (
            <div {...this.props}>
                {this.props.children}
            </div>
        );
    }
}

@reduxForm(
    {
        form: 'LawyerForm',
        fields: ['idx', 'user.email', 'user.password', 'user.userName', 'lawyerLawFirmList[]',
            'lawyerLawFirmList[].lawyerLawFirmIdx', 'lawyerLawFirmList[].workState', 'lawyerLawFirmList[].sortOrder',
            'lawyerLawFirmList[].lawFirm.idx', 'lawyerLawFirmList[].lawFirm.name',
            'imageGroup.imageGroupIdx', 'imageGroup.mainImageThumbUrl', 'imageGroup.imageList[].imageIdx',
            'file.file', 'file.previewUrl'],
        asyncValidate: LawyerFormAsyncValidate,
        asyncBlurFields: ['user.email'],
        validate: LawyerFormValidation
    },
    state => ({
        loading: state.lawyer.loading,
    }),
    {pushState}
)
class LawyerForm extends Component {
    static propTypes = {}

    render() {
        const {fields: {idx, user, lawyerLawFirmList, imageGroup, file}, values, handleSubmit, submitting, invalid} = this.props;

        return (
            <Form horizontal onSubmit={handleSubmit}>
                <Row>
                    <Col sm={3}>
                        <div className="lawyer-image-wrapper" onClick={() => document.getElementById("lawyer-image-file").click()}>
                            {
                                isImageGroup &&
                                <img width={100} height={100} src={imageGroup.mainImageThumbUrl.value} />
                            }
                            {
                                isPreviewUrl &&
                                <img width={100} height={100} src={file.previewUrl.value} />
                            }
                            {
                                !isLawyerImage && <div>이미지 선택</div>
                            }
                        </div>
                        <FormGroup>
                            <FormControl id="lawyer-image-file" type="file" onChange={(e) => {
                                e.preventDefault();

                                const f = e.target.files[0];
                                let reader = new FileReader();

                                reader.onloadend = () => {
                                    file.file.onChange(f);
                                    file.previewUrl.onChange(reader.result);

                                    if(imageGroup.imageList && imageGroup.imageList.length > 0){
                                        imageGroup.imageList.removeField(0);
                                        imageGroup.mainImageThumbUrl.onChange('');
                                    }
                                };

                                reader.readAsDataURL(f);
                            }}/>
                        </FormGroup>
                    </Col>
                    <Col sm={9}>
                        <FormGroup controlId="user.email" validationState={user.email.visited && user.email.invalid ? 'error' : null}>
                            <Col sm={3}>
                                <ControlLabel>이메일</ControlLabel>
                            </Col>
                            <Col sm={9}>
                                <FormControl disabled={idx.value} type="email" placeholder="이메일" {...user.email} />
                                {user.email.touched && user.email.error && <HelpBlock>{user.email.error}</HelpBlock>}
                            </Col>
                        </FormGroup>
                        <FormGroup controlId="user.password" validationState={user.password.visited && user.password.invalid ? 'error' : null}>
                            <Col sm={3}>
                                <ControlLabel>비밀번호</ControlLabel>
                            </Col>
                            <Col sm={9}>
                                <FormControl type="password" placeholder="비밀번호"  {...user.password} />
                                {user.password.touched && user.password.error && <HelpBlock>{user.password.error}</HelpBlock>}
                            </Col>
                        </FormGroup>
                        <FormGroup controlId="user.userName" validationState={user.userName.visited && user.userName.invalid ? 'error' : null}>
                            <Col sm={3}>
                                <ControlLabel>변호사명</ControlLabel>
                            </Col>
                            <Col sm={9}>
                                <FormControl type="text" placeholder="변호사명"  {...user.userName} />
                                {user.userName.touched && user.userName.error && <HelpBlock>{user.userName.error}</HelpBlock>}
                            </Col>
                        </FormGroup>
                    </Col>
                </Row>

                <Row style={{borderTop:"1px solid #ccc"}}>
                    <Col sm={12}>
                        <FormGroup style={{padding:"0 15px",paddingTop:"15px"}}>
                            <ControlLabel>로펌</ControlLabel>
                            <Button className="pull-right" onClick={::this.addLawyerLawFirmList}>추가</Button>
                        </FormGroup>

                        <Sortable onSort={::this.handleSort} dynamic sortHandle="handle" className="vertical-container" direction="vertical">
                            {
                                lawyerLawFirmList.map((lawyerLawFirm, index)=>
                                    <SortItem key={index} sortData={lawyerLawFirm}>
                                        <FormGroup key={index} controlId={'lawyerLawFirmList['+index+']'} validationState={(lawyerLawFirm.lawFirm.name.touched && lawyerLawFirm.invalid) || lawyerLawFirm.workState.invalid === "WORK" ? 'error' : null}>
                                            <InputGroup style={{padding:"0 15px"}}>
                                                <InputGroup.Addon>
                                                    <Glyphicon className="handle ui-move-cursor" glyph="transfer" />
                                                </InputGroup.Addon>
                                                <InputGroup.Addon>
                                                    <select {...lawyerLawFirm.workState}>
                                                        {workStateTypeCode.map(workState => <option key={workState.code} value={workState.code}>{workState.value}</option>)}
                                                    </select>
                                                </InputGroup.Addon>
                                                <FormControl type="text" placeholder="로펌" {...lawyerLawFirm.lawFirm.name} />
                                                <InputGroup.Button>
                                                    <Button disabled={lawyerLawFirmList.length===1} onClick={() => this.removeLawyerLawFirmList(index)}>삭제</Button>
                                                </InputGroup.Button>
                                            </InputGroup>
                                            {
                                                ((lawyerLawFirm.lawFirm.name.touched && lawyerLawFirm.invalid) || lawyerLawFirm.workState.invalid === "WORK")
                                                &&
                                                <HelpBlock style={{paddingLeft:"15px"}}>{lawyerLawFirm.error}</HelpBlock>
                                            }
                                        </FormGroup>
                                    </SortItem>, this)
                            }
                        </Sortable>
                    </Col>
                </Row>

                <ButtonGroup className="pull-right">
                    <Button type="submit" disabled={submitting || invalid || loading}
                            bsStyle="primary" onClick={handleSubmit(() => {
                        if(idx.value){
                            update(values).then(() => pushState('/lawyer/find/' + page));
                        } else {
                            create(values).then(() => pushState('/lawyer'));
                        }
                    })}>
                        저장
                    </Button>
                    <Button disabled={loading} onClick={() => pushState('/lawyer/find/' + page)}>
                        취소
                    </Button>
                </ButtonGroup>

            </Form>
        )
    }
}