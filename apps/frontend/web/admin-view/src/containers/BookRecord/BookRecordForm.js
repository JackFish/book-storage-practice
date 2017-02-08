/**
 * Created by ksb on 16. 11. 10.
 */
import React, {PropTypes, Component} from 'react';
import {Form, FormControl, FormGroup, ControlLabel, HelpBlock, Grid, Row, Col, ButtonGroup, Button, Thumbnail} from 'react-bootstrap';

import {connect} from 'react-redux';
import {reduxForm} from 'redux-form';
import {asyncConnect} from 'redux-connect';
import {push as pushState} from 'react-router-redux';
import Sortable, {sortable, SortableItemMixin} from 'react-anything-sortable';

import bookRecordFormValidate from "./Validate/BookRecordFormValidate";
import bookRecordFormAsyncValidate from "./Validate/BookRecordFormAsyncValidate";
import {findDetailOne, create, update, reset} from 'redux/reducers/bookRecord';

@asyncConnect([
    {
        promise: ({store : {dispatch, getState}, params}) => {
            if(params.idx){
                return dispatch(findDetailOne(params.idx));
            } else {
                return dispatch(reset());
            }
        }
    }
])
@connect(
    state => ({
        detailOne: state.bookRecord.detailOne
    })
)
export default class BookRecordFormWrapper extends Component {
    static propTypes = {
        detailOne: PropTypes.object
    }

    render(){
        const {detailOne} = this.props;

        return (
            <div>
                <BookRecordForm initialValues={detailOne} />
            </div>
        )
    }
}

const SortItem = React.createClass({
    mixins: [SortableItemMixin],

    getDefaultProps() {
        return {
            className: 'sort-item'
        };
    },

    render() {
        const { className, children } = this.props;
        return this.renderWithSortable(
            <Col xs={12} sm={12} md={6} lg={4} className={className}>
                {children}
            </Col>
        );
    }
});

@reduxForm(
    {
        form: 'BookRecordForm',
        fields: ['idx', 'subject', 'author', 'publisher', 'summary', 'visible', 'user.name'],
        asyncValidate: bookRecordFormAsyncValidate,
        asyncBlurFields: ['subject'],
        validate: bookRecordFormValidate
    },
    state => ({
        loading: state.bookRecord.loading,
        page: state.bookRecord.summaryList.number ? state.bookRecord.summaryList.number + 1 : 1,
        detailOne: state.bookRecord.detailOne,
        search: state.bookRecord.search
    }),
    {pushState, create, update}
)
class BookRecordForm extends Component {
    state = {
        imageList: []
    }

    componentWillMount(){
        const {detailOne} = this.props;

        const sortImageList = detailOne.imageGroup.imageList.map((image, i) => {
            return {imageIdx: image.imageIdx, imageUrl: image.originUrl, sortOrder: i+1, file: null};
        });

        this.setState({imageList:sortImageList});
    }

    handleSort(sortedArray) {
        this.setState({imageList:sortedArray.map((image, i) => {
            return {...image, sortOrder: i+1}
        })});
    }

    move(){
        const {pushState, page, search} = this.props;
        let path = '/bookRecord/';
        if(page){
            path += 'page/' + page;
        }
        if(search.term && search.type){
            path += '?term=' + search.term + '&type=' + search.type;
        }
        pushState(path);
    }

    render() {
        const {fields: {idx, subject, author, publisher, summary}, values, handleSubmit, submitting, invalid} = this.props;
        const {loading, pushState, create, update, page, search} = this.props;

        return (
            <Form horizontal onSubmit={handleSubmit}>
                {
                    idx.value
                        ?
                        <FormGroup controlId="subject">
                            <Col sm={2}>
                                제목
                            </Col>
                            <Col sm={10}>
                                {subject.value}
                            </Col>
                        </FormGroup>
                        :
                        <FormGroup controlId="subject" validationState={subject.visited && subject.invalid ? 'error' : null}>
                            <Col sm={2}>
                                제목
                            </Col>
                            <Col sm={10}>
                                <FormControl type="text" placeholder="제목" {...subject} />
                                {subject.touched && subject.error && <HelpBlock>{subject.error}</HelpBlock>}
                            </Col>
                        </FormGroup>
                }
                <FormGroup controlId="author" validationState={author.visited && author.invalid ? 'error' : null}>
                    <Col sm={2}>
                        저자
                    </Col>
                    <Col sm={10}>
                        <FormControl type="text" placeholder="저자" {...author} />
                        {author.touched && author.error && <HelpBlock>{author.error}</HelpBlock>}
                    </Col>
                </FormGroup>
                <FormGroup controlId="publisher" validationState={publisher.visited && publisher.invalid ? 'error' : null}>
                    <Col sm={2}>
                        출판사
                    </Col>
                    <Col sm={10}>
                        <FormControl type="text" placeholder="출판사" {...publisher} />
                        {publisher.touched && publisher.error && <HelpBlock>{publisher.error}</HelpBlock>}
                    </Col>
                </FormGroup>
                <FormGroup controlId="summary" validationState={summary.visited && summary.invalid ? 'error' : null}>
                    <Col sm={2}>
                        요약
                    </Col>
                    <Col sm={10}>
                        <FormControl type="text" placeholder="요약" {...summary} />
                        {summary.touched && summary.error && <HelpBlock>{summary.error}</HelpBlock>}
                    </Col>
                </FormGroup>

                <FormGroup controlId="carYearPhoto">
                    <ControlLabel>이미지</ControlLabel>
                    <FormControl type="file" onChange={(e) => {
                        e.preventDefault();
                        const file = e.target.files[0];
                        const fileExt = file.name.slice(file.name.indexOf(".") + 1).toLowerCase();
                        if(fileExt == "jpg" || fileExt == "png" ||  fileExt == "gif" ||  fileExt == "bmp"){
                            let reader = new FileReader();
                            reader.onloadend = () => {
                                this.setState({imageList:[...this.state.imageList,
                                    {imageIdx: 0, imageUrl: reader.result, sortOrder: this.state.imageList.length+1, file:file}]});

                            };
                            reader.readAsDataURL(file);
                        } else {
                            console.log(fileExt + "올바른 파일이 아닙니다.");
                        }
                    }}/>
                </FormGroup>
                <Row>
                    <Sortable onSort={::this.handleSort} dynamic sortHandle="handle">
                        {
                            this.state.imageList && this.state.imageList.length>0 && this.state.imageList.map((image, i) =>
                                <SortItem key={i} sortData={image}>
                                    <Thumbnail src={image.imageUrl} alt="">
                                        <Button bsStyle="primary" onClick={() => {
                                            this.setState({imageList: this.state.imageList.filter((image, j) => i !== j)});
                                        }}>삭제</Button>
                                        <Button className="handle">이동</Button>
                                    </Thumbnail>
                                </SortItem>
                            )
                        }
                    </Sortable>
                </Row>

                <ButtonGroup className="pull-right">
                    <Button type="submit" disabled={submitting || invalid ||loading}
                            bsStyle="primary" onClick={handleSubmit(() => {
                        if(idx.value){
                            update({...values, imageGroup: {imageList: this.state.imageList}})
                                .then(() => this.move())
                                .catch((error)=> console.log(error));
                        } else {
                            create({...values, imageGroup: {imageList: this.state.imageList}})
                                .then(() => this.move())
                                .catch((error)=> console.log(error));
                        }
                    })}>
                        저장
                    </Button>
                    <Button disabled={loading} onClick={() => pushState('/bookRecord/page/' + page + '?term=' + search.term + '&type=' + search.type)}>
                        취소
                    </Button>
                </ButtonGroup>
            </Form>
        )
    }
}