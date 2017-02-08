/**
 * Created by ksb on 16. 11. 10.
 */
import React, {PropTypes, Component} from 'react';
import {ListGroup, ListGroupItem, Media, Pagination, ButtonGroup, Button, Badge} from 'react-bootstrap';
import {connect} from 'react-redux';
import {asyncConnect} from "redux-connect";
import {push as pushState} from 'react-router-redux';
import {findSummaryList, visible, invisible, remove} from 'redux/reducers/bookReport';

@asyncConnect([{
    promise: ({store: {dispatch, getState}, params, location}) => {
        const term = location.query.term ? location.query.term : '';
        const type = location.query.type ? location.query.type : '';

        console.log(term, type)

        if (params.page) {
            return dispatch(findSummaryList(Number(params.page) - 1, term, type));
        } else {
            return dispatch(findSummaryList(0, term, type));
        }
    }
}])
@connect(
    state => ({
        loading: state.bookReport.loading,
        summaryList: state.bookReport.summaryList
    }),
    {pushState, visible, invisible, remove}
)
export default class BookReportList extends Component {

    removeAction(idx){
        const {summaryList: {content}, pushState, remove} = this.props;
        const term = this.props.location.query.term ? this.props.location.query.term : '';
        const type = this.props.location.query.type ? this.props.location.query.type : '';

        remove(idx).then(() => {
            let page =  this.props.params.page;

            if(page && content.length === 1){
                page--;
            } else if(!page) {
                page = 0;
            }

            if(page < 1){
                pushState('/bookReport?term=' + term + '&type=' + type);
            } else {
                pushState('/bookReport/page/' + page + '?term=' + term + '&type=' + type);
            }
        });
    }

    render() {
        const {loading, summaryList, visible, invisible, pushState} = this.props;
        const term = this.props.location.query.term ? this.props.location.query.term : '';
        const type = this.props.location.query.type ? this.props.location.query.type : '';
        const bodyStyle = {wordBreak: 'break-all'};

        return (
            <div>
                <ListGroup>
                    {
                        summaryList.content && summaryList.content.length > 0 && summaryList.content.map((summary, i) =>
                            <ListGroupItem key={i}>
                                <Media>
                                    <Media.Left>
                                        {summary.bookRecord ? summary.bookRecord.subject : ""}
                                    </Media.Left>
                                    <Media.Body>
                                        <ButtonGroup className="pull-right">
                                            <Button disabled={loading} onClick={() => pushState('/bookReport/form/' + summary.idx)}>수정</Button>
                                            {
                                                summary.visible ? <Button disabled={loading} onClick={() => invisible(summary.idx)}>비게시</Button>
                                                    : <Button disabled={loading} onClick={() => visible(summary.idx)}>게시</Button>
                                            }
                                            <Button disabled={loading} onClick={() => this.removeAction(summary.idx)}>삭제</Button>
                                        </ButtonGroup>
                                        <Media.Heading style={bodyStyle}>{summary.subject}</Media.Heading>
                                        <p>
                                            {
                                                summary.tagList && summary.tagList.map((tag, j) =>
                                                        <Badge key={j}>{tag.text}</Badge>
                                                )
                                            }
                                        </p>
                                    </Media.Body>
                                </Media>
                            </ListGroupItem>
                        )
                    }
                </ListGroup>
                <div className="text-center">
                    <Pagination
                        first={summaryList.last}
                        last={summaryList.first}
                        boundaryLinks
                        items={summaryList.totalPages}
                        maxButtons={5}
                        activePage={summaryList.number + 1}
                        onSelect={(e, se) => pushState('/bookReport/page/' + e + '?term=' + term + '&type=' + type)}
                    />
                </div>
            </div>
        )
    }
}