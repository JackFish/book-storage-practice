/**
 * Created by ksb on 16. 11. 10.
 */
import React, {PropTypes, Component} from 'react';
import {Form, FormControl, FormGroup, HelpBlock, ButtonGroup, Button} from 'react-bootstrap';
import {WithContext as ReactTags} from 'react-tag-input';

import {connect} from 'react-redux';
import {reduxForm, FieldArray, Field} from 'redux-form';
import {asyncConnect} from 'redux-connect';
import {push as pushState} from 'react-router-redux';

import Block from './Block';
import {BLOCK_TYPE_DRAFT, BLOCK_TYPE_IMAGE, BLOCK_TYPE_YOUBUTE} from '../../helpers/constants';

import bookReportFormValidate from "./Validate/BookReportFormValidate";
import {findDetailOne, create, update, reset, setBlockStateList} from 'redux/reducers/bookReport';
import {findInfiniteSummaryList} from 'redux/reducers/bookRecord'
import {showBookRecordModal, hideBookRecordModal} from 'redux/reducers/modal';

import BookRecordModal from "../../components/Modal/BookRecordModal";

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
        detailOne: state.bookReport.detailOne
    })
)
export default class BookReportFormWrapper extends Component {
    static propTypes = {
        detailOne: PropTypes.object
    }

    render(){
        const {detailOne} = this.props;

        return (
            <div>
                <BookReportForm initialValues={detailOne} />
            </div>
        )
    }
}

@reduxForm(
    {
        form: 'BookRecordForm',
        fields: ['idx', 'subject',
            'bookRecord.idx', 'bookRecord.subject',
            'blockList[].idx', 'blockList[].type',
            'blockList[].caption', 'blockList[].youtubeUrl',
            'tagList[].text',
        ],
        validate: bookReportFormValidate
    },
    state => ({
        loading: state.bookReport.loading,
        page: state.bookReport.summaryList.number ? state.bookReport.summaryList.number + 1 : 1,
        detailOne: state.bookReport.detailOne,
        search: state.bookReport.search,
        blockStateList: state.bookReport.blockStateList,
        isShowBookRecordModal: state.modal.isShowBookRecordModal,
        modalLoading: state.bookRecord.loading,
        modalSummaryList: state.bookRecord.infiniteSummaryList,
    }),
    {pushState, create, update, setBlockStateList, showBookRecordModal, hideBookRecordModal, findInfiniteSummaryList}
)
class BookReportForm extends Component {
    componentWillMount(){
        const {detailOne, setBlockStateList} = this.props;

        if(detailOne && detailOne.blockList){
            const blockStateList = detailOne.blockList.map((block, i) => {
                if(block.type === BLOCK_TYPE_DRAFT) {
                    return {idx: block.idx, type: block.type,
                        contentStateJson: block.contentStateJson, plainText: null};
                } else if(block.type === BLOCK_TYPE_IMAGE){
                    const imageList = block.imageGroup.imageList.map((image, j) => {
                        return {imageIdx: image.imageIdx, imageUrl: image.originUrl, sortOrder: j+1, file: null};
                    });
                    return {
                        idx: block.idx, type: block.type,
                        imageGroupIdx: block.imageGroup.imageGroupIdx, imageList: imageList
                    };
                } else {
                    return {
                        idx: block.idx, type: block.type
                    };
                }
            });

            setBlockStateList(blockStateList);
        }
    }

    addDraft(){
        const {fields: {blockList}, blockStateList, setBlockStateList} = this.props;
        blockList.addField({type:BLOCK_TYPE_DRAFT});
        setBlockStateList([...blockStateList, {idx: 0, type: BLOCK_TYPE_DRAFT, contentStateJson: null, plainText: null}]);
    }

    addImage(){
        const {fields: {blockList}, blockStateList, setBlockStateList} = this.props;
        blockList.addField({type:BLOCK_TYPE_IMAGE});
        setBlockStateList([...blockStateList, {idx: 0, type: BLOCK_TYPE_IMAGE, imageGroupIdx:0, imageList: []}]);
    }

    addYoutube(){
        const {fields: {blockList}, blockStateList, setBlockStateList} = this.props;
        blockList.addField({type:BLOCK_TYPE_YOUBUTE});
        setBlockStateList([...blockStateList, {idx: 0, type: BLOCK_TYPE_YOUBUTE}]);
    }

    removeBlock(i){
        const {fields: {blockList}, blockStateList, setBlockStateList} = this.props;
        blockList.removeField(i);
        setBlockStateList(blockStateList.filter((blockState, j) => i !== j));
    }

    save(){
        const {create, update, values, blockStateList} = this.props;

        const blockList = values.blockList.map((block, i)=>{
            if(block.type === BLOCK_TYPE_DRAFT){
                return {
                    idx: block.idx, type: block.type, sortOrder: i+1,
                    contentStateJson: blockStateList[i].contentStateJson,
                    plainText: blockStateList[i].plainText,
                };
            } else if(block.type === BLOCK_TYPE_IMAGE){
                return {
                    idx: block.idx, type: block.type, sortOrder: i+1,
                    caption: block.caption,
                    imageGroup: {
                        imageGroupIdx: blockStateList[i].imageGroupIdx,
                        imageList: blockStateList[i].imageList
                            .map(image=>{
                                return {imageIdx: image.imageIdx, sortOrder: image.sortOrder}
                            })
                    }
                };
            } else if(block.type === BLOCK_TYPE_YOUBUTE){
                return {
                    idx: block.idx, type: block.type, sortOrder: i+1,
                    caption: block.caption, youtubeUrl: block.youtubeUrl,
                };
            }
        });

        const imageFileList = blockStateList
            .filter(block=>block.type === BLOCK_TYPE_IMAGE)
            .map(block=>block.imageList)
            .reduce((previousValue, currentValue, currentIndex) => {
                return [...previousValue, ...currentValue
                    .filter(image => image.file != null)
                    .map(image=> image.file)];
            }, []);

        const data = {...values, blockList: blockList};

        if(data.idx && data.idx>0){
            update(data, imageFileList).then(() => this.move());
        } else {
            create(data, imageFileList).then(() => this.move());
        }
    }

    cancel(){
        this.move();
    }

    move = () => {
        const {pushState, page, search} = this.props;
        let path = '/bookReport/';
        if(page){
            path += 'page/' + page;
        }
        if(search && search.term && search.type){
            path += '?term=' + search.term + '&type=' + search.type;
        }
        pushState(path);
    }

    setBookRecord = (bookRecord) => {
        const {fields: {bookRecord: {idx, subject}}} = this.props;
        idx.onChange(bookRecord.idx);
        subject.onChange(bookRecord.subject);
    }

    render() {
        const {fields: {idx, subject, bookRecord, blockList, tagList}, values, handleSubmit, submitting, invalid} = this.props;
        const {loading, blockStateList, setBlockStateList, isShowBookRecordModal, showBookRecordModal, hideBookRecordModal,
            modalLoading, modalSummaryList, findInfiniteSummaryList} = this.props;

        return (
            <Form horizontal >
                <FormGroup style={{margin:"0 0 15px 0"}} validationState={subject.visited && subject.invalid ? 'error' : null}>
                    <FormControl type="text" placeholder="제목" {...subject} />
                    {subject.touched && subject.error && <HelpBlock>{subject.error}</HelpBlock>}
                </FormGroup>
                <FormGroup style={{margin:"0 0 15px 0"}} validationState={bookRecord.subject.visited && bookRecord.subject.invalid ? 'error' : null}>
                    <FormControl type="text" placeholder="책" {...bookRecord.subject} onClick={() => showBookRecordModal()} readOnly/>
                    {bookRecord.subject.touched && subject.error && <HelpBlock>{bookRecord.subject.error}</HelpBlock>}
                </FormGroup>
                {
                    blockList && blockList.length>0 &&
                    blockList.map((block, i) =>
                        <Block key={i}
                               index={i}
                               block={block}
                               blockState={blockStateList[i]}
                               setBlockState={(index, parameterBlockState) => {
                                   setBlockStateList(blockStateList.map((blockState, j)=>
                                       index===j ? parameterBlockState : blockState
                                   ));
                               }}
                               removeBlock={() => this.removeBlock(i)}
                        />)
                }
                <ButtonGroup justified>
                    <Button href="#" onClick={this.addDraft.bind(this)}>드래프트추가</Button>
                    <Button href="#" onClick={this.addImage.bind(this)}>사진추가</Button>
                    <Button href="#" onClick={this.addYoutube.bind(this)}>유튜브추가</Button>
                </ButtonGroup>
                <FormGroup style={{margin:"15px 0 15px 0", wordBreak: 'break-all'}}>
                    <ReactTags tags={values.tagList}
                               handleAddition={(text) => values.tagList
                                   .filter(tag => tag.text === text).length==0 && tagList.addField({text: text})}
                               handleDelete={(index) => tagList.removeField(index)}
                    />
                </FormGroup>
                <ButtonGroup className="pull-right">
                    <Button type="submit" disabled={submitting || invalid ||loading}
                             bsStyle="primary" onClick={handleSubmit(() => this.save())}>저장</Button>
                    <Button disabled={loading} onClick={this.cancel.bind(this)}>취소</Button>
                </ButtonGroup>
                <BookRecordModal loading={modalLoading}
                                 summaryList={modalSummaryList}
                                 findSummaryList={findInfiniteSummaryList}
                                 setBookRecord={this.setBookRecord}
                                 isShowBookRecordModal={isShowBookRecordModal}
                                 hideBookRecordModal={hideBookRecordModal} />
            </Form>
        )
    }
}