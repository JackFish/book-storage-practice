/**
 * Created by ksb on 17. 2. 4.
 */
import React, {PropTypes, Component} from 'react';
import {Modal, Button} from 'react-bootstrap';

export default class BookRecordModal extends Component {

    componentWillMount(){
        const {findSummaryList} = this.props;
        findSummaryList();
    }

    handleScroll = (event) => {
        const {loading, summaryList, findSummaryList} = this.props;
        const {innerDiv} = this.refs;
        let scrollTop = event.target.scrollTop,
            divHeight = event.target.offsetHeight,
            innerDivHeight = innerDiv.offsetHeight,
            itemTranslate = innerDivHeight - scrollTop - divHeight;

        if(!loading && !summaryList.last && itemTranslate == 0)
            findSummaryList(summaryList.number+1);
    }

    render() {
        const {summaryList, setBookRecord, isShowBookRecordModal, hideBookRecordModal} = this.props;

        return (
            <Modal
                show={isShowBookRecordModal}
                onHide={hideBookRecordModal}
                container={this}
                aria-labelledby="contained-modal-title"
                >
                <Modal.Header closeButton>
                    <Modal.Title id="contained-modal-title">책 목록</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <div onScroll={this.handleScroll} style={{height:"200px", overflow:"auto"}}>
                        <div ref="innerDiv">
                            {
                                summaryList && summaryList.content && summaryList.content.length>0 &&
                                    summaryList.content.map((summary, i) =>
                                        <Button key={i} bsSize="large" block
                                            onClick={() => {
                                                setBookRecord(summary);
                                                hideBookRecordModal();
                                            }}>
                                            {summary.subject}
                                        </Button>
                                    )
                            }
                        </div>
                    </div>
                </Modal.Body>
                <Modal.Footer>
                    <Button onClick={hideBookRecordModal}>닫기</Button>
                </Modal.Footer>
            </Modal>
        )
    }
}